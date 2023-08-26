package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class PlayerLeave implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void Death(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                String prefix = GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix();
                if (!Objects.equals(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), "旁观者")) {
                    BedWars.canRejoinPlayer.add(player);
                    Bukkit.broadcastMessage(prefix + player.getName() + " §7断开连接！");
                    if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getDisplayName().equalsIgnoreCase("yes")) {
                        GameStart.getScoreboard().getEntryTeam(player.getName()).setDisplayName(String.valueOf(Integer.parseInt(GameStart.getScoreboard().getEntryTeam(player.getName()).getDisplayName()) - 1));
                    } else {
                        if (GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries().size() == 1) {
                            GameStart.getScoreboard().getEntryTeam(player.getName()).setDisplayName("0");
                        }
                    }
                    int teamWithPlayersCount = 0;
                    Team winnerTeam = null;
                    for (Team team : GameStart.getScoreboard().getTeams()) {
                        if (!team.getName().equalsIgnoreCase("旁观者")) {
                            if (!team.getDisplayName().equalsIgnoreCase("0")) {
                                teamWithPlayersCount++;
                                winnerTeam = team;
                            }
                        }
                    }
                    if (teamWithPlayersCount == 1) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (Objects.equals(GameStart.getScoreboard().getEntryTeam(p.getName()), winnerTeam)) {
                                p.sendMessage("§b+25 起床战争经验 (获胜奖励)");
                                p.sendMessage("§6+50 硬币 (获胜奖励)");
                                PlayerDataManage.addPlayerXP(p, 25);
                                PlayerDataManage.addPlayerCoins(p, 50);
                            } else {
                                p.sendTitle("§c§l游戏结束", "");
                            }
                        }
                        GameEnd.gameend(winnerTeam.getName());

                    }

                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            GameStart.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
                            if (!Objects.equals(GameStart.getScoreboard().getEntryTeam(player.getName()).getDisplayName(), "yes")) {
                                GameStart.getScoreboard().getEntryTeam(player.getName()).setDisplayName(String.valueOf(GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries().size()));
                            }
                            try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
                                 Statement statement = connection.createStatement()) {
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
