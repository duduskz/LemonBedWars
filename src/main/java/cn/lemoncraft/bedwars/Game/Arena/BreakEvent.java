package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
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

import java.util.Objects;

public class BreakEvent implements Listener {
    public void ifbreakevent(String teamname, Location breakblock, BlockBreakEvent event) {
        if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getName(), "旁观者")) {
            Plugin plugin = BedWars.getPlugin(BedWars.class);
            FileConfiguration config = plugin.getConfig();

            String[] playerbed = BedWars.getLocaton(config.getString("Map." + teamname + ".Bed"));
            if (breakblock.getX() == Double.parseDouble(playerbed[0]) && breakblock.getY() == Double.parseDouble(playerbed[1]) && breakblock.getZ() == Double.parseDouble(playerbed[2])) {
                if (GameStart.getcoreboard().getTeam(teamname).getDisplayName().equalsIgnoreCase("yes")) {
                    BedWars.breakbed.replace(event.getPlayer().getName(), BedWars.breakbed.get(event.getPlayer().getName()) + 1);
                    PlayerDataManage playerDataManage = new PlayerDataManage();
                    event.getPlayer().sendMessage("§6+20 硬币 (破坏床奖励)");
                    playerDataManage.addPlayerCoins(event.getPlayer(), 20);
                    playerDataManage.addPlayerBreakBed(event.getPlayer(), 1, config.getString("Map.Mode"));
                    int teamplayerint;
                    for (teamplayerint = 0; teamplayerint < GameStart.getcoreboard().getTeam(teamname).getEntries().size(); teamplayerint = teamplayerint + 1) {
                    }
                    GameStart.getcoreboard().getTeam(teamname).setDisplayName(String.valueOf(teamplayerint));
                    for (Player p : Bukkit.getOnlinePlayers()) {

                        if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase(teamname)) {
                            p.sendTitle("§c床已被摧毁！", "你将不能重生！");
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > §7你的床被 " + GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");
                            p.sendMessage("");
                            p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1, 1);
                        } else {
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > " + GameStart.getcoreboard().getTeam(teamname).getSuffix() + GameStart.getcoreboard().getTeam(teamname).getName() + " §7的床被 " + GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");

                            p.sendMessage("");
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                        }

                    }
                }
            }
            if (breakblock.getX() == Double.parseDouble(playerbed[3]) && breakblock.getY() == Double.parseDouble(playerbed[4]) && breakblock.getZ() == Double.parseDouble(playerbed[5])) {
                if (GameStart.getcoreboard().getTeam(teamname).getDisplayName().equalsIgnoreCase("yes")) {
                    BedWars.breakbed.replace(event.getPlayer().getName(), BedWars.breakbed.get(event.getPlayer().getName()) + 1);
                    PlayerDataManage playerDataManage = new PlayerDataManage();
                    playerDataManage.addPlayerBreakBed(event.getPlayer(), 1, config.getString("Map.Mode"));
                    event.getPlayer().sendMessage("§6+20 硬币 (破坏床奖励)");
                    playerDataManage.addPlayerCoins(event.getPlayer(), 20);
                    int teamplayerint;
                    for (teamplayerint = 0; teamplayerint < GameStart.getcoreboard().getTeam(teamname).getEntries().size(); teamplayerint = teamplayerint + 1) {
                    }
                    GameStart.getcoreboard().getTeam(teamname).setDisplayName(String.valueOf(teamplayerint));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getName().equalsIgnoreCase(teamname)) {

                            p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1, 1);
                            p.sendTitle("§c床已被摧毁！", "你将不能重生！");
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > §7你的床被 " + GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");
                            p.sendMessage("");
                        } else {

                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                            p.sendMessage("");
                            p.sendMessage("§f§l床被摧毁了 > " + GameStart.getcoreboard().getTeam(teamname).getSuffix() + GameStart.getcoreboard().getTeam(teamname).getName() + " §7的床被 " + GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getSuffix() + event.getPlayer().getName() + " §7摧毁了！");
                            p.sendMessage("");
                        }

                    }
                }
            }
        }
    }

    @EventHandler
    public void Break(BlockBreakEvent event) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            FileConfiguration config = plugin.getConfig();
            if (Objects.equals(BedWars.state, "Play")) {

                if (event.getBlock().getType() != Material.BED_BLOCK) {
                    if (!BedWars.changedBlocks.contains(event.getBlock().getLocation())) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§c你不可以破坏这个方块！");
                    }

                }
            }

            Location breakblock = event.getBlock().getLocation();
            if (event.getBlock().getType() == Material.BED_BLOCK) {
                String playerteam = GameStart.getcoreboard().getEntryTeam(event.getPlayer().getName()).getName();
                String[] playerbed = BedWars.getLocaton(config.getString("Map." + playerteam + ".Bed"));
                if (breakblock.getX() == Double.parseDouble(playerbed[0]) && breakblock.getY() == Double.parseDouble(playerbed[1]) && breakblock.getZ() == Double.parseDouble(playerbed[2])) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("§c你不可以破坏你自己的床！");
                } else {
                    if (breakblock.getX() == Double.parseDouble(playerbed[3]) && breakblock.getY() == Double.parseDouble(playerbed[4]) && breakblock.getZ() == Double.parseDouble(playerbed[5])) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("§c你不可以破坏你自己的床！");
                    } else {
                        ifbreakevent("红队", breakblock, event);
                        ifbreakevent("蓝队", breakblock, event);
                        ifbreakevent("绿队", breakblock, event);
                        ifbreakevent("黄队", breakblock, event);
                        ifbreakevent("青队", breakblock, event);
                        ifbreakevent("白队", breakblock, event);
                        ifbreakevent("粉队", breakblock, event);
                        ifbreakevent("灰队", breakblock, event);

                    }

                }
            }
        }
    }
}