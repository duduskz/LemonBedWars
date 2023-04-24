package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
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
                String prefix = GameStart.getcoreboard().getEntryTeam(player.getName()).getSuffix();
                if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(player.getName()).getName(), "旁观者")) {
                    BedWars.canRejoinPlayer.add(player);

                    Bukkit.broadcastMessage(prefix + player.getName() + " §7断开连接！");

                    if (GameStart.getcoreboard().getEntryTeam(player.getName()).getEntries().size() == 1) {
                        GameStart.getcoreboard().getEntryTeam(player.getName()).setDisplayName("0");
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            GameStart.getcoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
                            if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(player.getName()).getDisplayName(), "yes")) {
                                GameStart.getcoreboard().getEntryTeam(player.getName()).setDisplayName(String.valueOf(GameStart.getcoreboard().getEntryTeam(player.getName()).getEntries().size()));
                            }
                            Connection conn;
                            String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
                            String user = plugin.getConfig().getString("MySQL.username");
                            String password = plugin.getConfig().getString("MySQL.password");
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                conn = DriverManager.getConnection(url, user, password);
                                Statement statement = conn.createStatement();
                                String sql = "DELETE FROM player_rejoin WHERE uuid = '"+player.getUniqueId().toString()+"'";
                                statement.executeQuery(sql);
                                conn.close();
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    }.runTaskLater(plugin, 6000L);
                }
            }
        }
    }
}
