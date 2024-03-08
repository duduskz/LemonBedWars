package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.API.Event.GameBreakBedEvent;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.lemonbedwars.Utils.TAB;
import cn.lemonnetwork.lemonbedwars.Utils.TitleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class BreakEvent implements Listener {
    public void ifbreakevent(String teamname, Location breakblock, BlockBreakEvent event) {
            Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
            FileConfiguration config = plugin.getConfig();

            String[] playerbed = LocationUtil.getStringLocation(config.getString("Map." + teamname + ".Bed"));
            if (breakblock.getX() == Double.parseDouble(playerbed[0]) && breakblock.getY() == Double.parseDouble(playerbed[1]) && breakblock.getZ() == Double.parseDouble(playerbed[2])) {
                if (GameStart.getScoreboard().getTeam(teamname).getDisplayName().equalsIgnoreCase("yes")) {
                    LemonBedWars.breakbed.replace(event.getPlayer().getName(), LemonBedWars.breakbed.get(event.getPlayer().getName()) + 1);
                    event.getPlayer().sendMessage("§6+20 硬币 (破坏床奖励)");
                    PlayerDataManage.addPlayerCoins(event.getPlayer(), 20);
                    PlayerDataManage.addPlayerBreakBed(event.getPlayer(), 1, config.getString("Map.Mode"));
                    int teamplayerint;
                    for (teamplayerint = 0; teamplayerint < GameStart.getScoreboard().getTeam(teamname).getEntries().size(); teamplayerint = teamplayerint + 1) {}

                    Bukkit.getServer().getPluginManager().callEvent(new GameBreakBedEvent(event.getPlayer(), GameStart.getScoreboard().getTeam(teamname)));
                    GameStart.getScoreboard().getTeam(teamname).setDisplayName(String.valueOf(teamplayerint));
                    for (Player p : Bukkit.getOnlinePlayers()) {

                        if (GameStart.getScoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase(teamname)) {
                            p.sendTitle("§c床已被摧毁！", "你将不能重生！");
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > §7你的床被 " + GameStart.getScoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");
                            p.sendMessage("");
                            p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1, 1);
                        } else {
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > " + GameStart.getScoreboard().getTeam(teamname).getSuffix() + GameStart.getScoreboard().getTeam(teamname).getName() + " §7的床被 " + GameStart.getScoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");

                            p.sendMessage("");
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                        }

                    }
                }
            }
            if (breakblock.getX() == Double.parseDouble(playerbed[3]) && breakblock.getY() == Double.parseDouble(playerbed[4]) && breakblock.getZ() == Double.parseDouble(playerbed[5])) {
                if (GameStart.getScoreboard().getTeam(teamname).getDisplayName().equalsIgnoreCase("yes")) {
                    LemonBedWars.breakbed.replace(event.getPlayer().getName(), LemonBedWars.breakbed.get(event.getPlayer().getName()) + 1);
                    PlayerDataManage playerDataManage = new PlayerDataManage();
                    playerDataManage.addPlayerBreakBed(event.getPlayer(), 1, config.getString("Map.Mode"));
                    event.getPlayer().sendMessage("§6+20 硬币 (破坏床奖励)");
                    if (PlayerDataManage.getPlayerLang(event.getPlayer()).equalsIgnoreCase("zhcn")) {
                        TAB.set(event.getPlayer(), "     §b§l你正在§e§l" + LemonBedWars.serverip + "§b§l上进行游戏\n", "\n§b击杀数: §e" + LemonBedWars.kill.get(event.getPlayer().getName()) + " §b最终击杀数: §e" + LemonBedWars.finalkill.get(event.getPlayer().getName()) + " §b破坏床数: §e" + LemonBedWars.breakbed.get(event.getPlayer().getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store." + LemonBedWars.serverip + "");
                    } else {
                        TAB.set(event.getPlayer(), "     §b§lYou are playing on §e§l" + LemonBedWars.serverip + "\n", "\n§bKills: §e" + LemonBedWars.kill.get(event.getPlayer().getName()) + " §bFlial Kills: §e" + LemonBedWars.finalkill.get(event.getPlayer().getName()) + " §bDestroyed Beds: §e" + LemonBedWars.breakbed.get(event.getPlayer().getName()) + "\n\n     §a§lRank & More! §c§lStore." + LemonBedWars.serverip + "");
                    }
                    playerDataManage.addPlayerCoins(event.getPlayer(), 20);
                    int teamplayerint;
                    for (teamplayerint = 0; teamplayerint < GameStart.getScoreboard().getTeam(teamname).getEntries().size(); teamplayerint = teamplayerint + 1) {
                    }
                    GameStart.getScoreboard().getTeam(teamname).setDisplayName(String.valueOf(teamplayerint));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (GameStart.getScoreboard().getEntryTeam(event.getPlayer().getName()).getName().equalsIgnoreCase(teamname)) {

                            p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1, 1);
                            TitleUtil.sendTitle(p, 20, 40, 20, "§c床已被摧毁！", "你将不能重生！");
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > §7你的床被 " + GameStart.getScoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");
                            p.sendMessage("");
                        } else {

                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > " + GameStart.getScoreboard().getTeam(teamname).getSuffix() + GameStart.getScoreboard().getTeam(teamname).getName() + " §7的床被 " + GameStart.getScoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");
                            p.sendMessage("");
                        }

                    }
                }
        }
    }

    @EventHandler
    public void Break(BlockBreakEvent event) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            FileConfiguration config = plugin.getConfig();
            if (Objects.equals(LemonBedWars.state, "Play")) {

                if (event.getBlock().getType() != Material.BED_BLOCK) {
                    if (!LemonBedWars.changedBlocks.contains(event.getBlock().getLocation())) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§c你不可以破坏这个方块！");
                    }

                }
            }

            Location breakblock = event.getBlock().getLocation();
            if (event.getBlock().getType() == Material.BED_BLOCK) {
                String playerteam = GameStart.getScoreboard().getEntryTeam(event.getPlayer().getName()).getName();
                String[] playerbed = LocationUtil.getStringLocation(config.getString("Map." + playerteam + ".Bed"));
                if (breakblock.getX() == Double.parseDouble(playerbed[0]) && breakblock.getY() == Double.parseDouble(playerbed[1]) && breakblock.getZ() == Double.parseDouble(playerbed[2])) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("§c你不可以破坏你自己的床！");
                } else {
                    if (breakblock.getX() == Double.parseDouble(playerbed[3]) && breakblock.getY() == Double.parseDouble(playerbed[4]) && breakblock.getZ() == Double.parseDouble(playerbed[5])) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§c你不可以破坏你自己的床！");
                    } else {
                        for (Team team : GameStart.getScoreboard().getTeams()) {
                            if (!team.getName().equalsIgnoreCase("旁观者")) {
                                ifbreakevent(team.getName(), breakblock, event);
                            }
                        }


                    }

                }
            }
        }
    }
}