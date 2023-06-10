package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class PlayerLeave implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void Death(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Game")) {
                String prefix = GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix();
                if (!Objects.equals(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), "旁观者")) {
                    BedWars.canRejoinPlayer.add(player);

                    Bukkit.broadcastMessage(prefix + player.getName() + " §7断开连接！");

                    if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getDisplayName().equalsIgnoreCase("yes")) {
                        GameStart.getScoreboard().getEntryTeam(player.getName()).setDisplayName(String.valueOf(Integer.parseInt(GameStart.getScoreboard().getEntryTeam(player.getName()).getDisplayName()) - 1));
                    }


                    if (GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries().size() == 1) {
                        GameStart.getScoreboard().getEntryTeam(player.getName()).setDisplayName("0");
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            GameStart.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
                            if (!Objects.equals(GameStart.getScoreboard().getEntryTeam(player.getName()).getDisplayName(), "yes")) {
                                GameStart.getScoreboard().getEntryTeam(player.getName()).setDisplayName(String.valueOf(GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries().size()));
                            }
                            try {
                                Statement statement = PlayerDataManage.BedWarsdataSource.getConnection().createStatement();
                                String sql = "DELETE FROM player_rejoin WHERE uuid = '"+player.getUniqueId().toString()+"'";
                                statement.executeQuery(sql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    }.runTaskLater(plugin, 6000L);
                }
            }
        }
    }
}
