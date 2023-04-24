package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class BedWarsListener {

    public static void start(){
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                BedWars.Listenertime = BedWars.Listenertime - 1;
                if (BedWars.Listenertime == 0) {
                    if (BedWars.Listenername.equalsIgnoreCase("钻石生成点II级")) {
                        BedWars.Listenername = "绿宝石生成点II级";
                        BedWars.Listenertime = 360;
                        BedWars.Listeners.replace("diamond2", true);
                        Bukkit.broadcastMessage("§b钻石生成点 §e已经升至§cII§e级");
                    } else {
                        if (BedWars.Listenername.equalsIgnoreCase("绿宝石生成点II级")) {
                            BedWars.Listenername = "钻石生成点III级";
                            BedWars.Listenertime = 360;
                            BedWars.Listeners.replace("emerald2", true);
                            Bukkit.broadcastMessage("§2绿宝石生成点 §e已经升至§cII§e级");
                        } else {
                            if (BedWars.Listenername.equalsIgnoreCase("钻石生成点III级")) {
                                BedWars.Listenername = "绿宝石生成点III级";
                                BedWars.Listenertime = 360;
                                BedWars.Listeners.replace("diamond3", true);
                                Bukkit.broadcastMessage("§b钻石生成点 §e已经升至§cIII§e级");
                            } else {
                                if (BedWars.Listenername.equalsIgnoreCase("绿宝石生成点III级")) {
                                    BedWars.Listenername = "床自毁";
                                    BedWars.Listenertime = 600;
                                    BedWars.Listeners.replace("emerald3", true);
                                    Bukkit.broadcastMessage("§2绿宝石生成点 §e已经升至§cIII§e级");
                                } else {
                                    if (BedWars.Listenername.equalsIgnoreCase("床自毁")) {
                                        BedWars.Listenername = "绝杀模式";
                                        BedWars.Listenertime = 600;
                                        BedWars.Listeners.replace("BedDestroy", true);
                                        Bukkit.broadcastMessage("§c§l所有的床已被破坏！");
                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                            player.sendTitle("§c床已被破坏！", "所有人的床都已被破坏！");
                                            player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                                        }
                                        for (Team t : GameStart.getcoreboard().getTeams()) {
                                            String[] spawn = LocationUtil.getStringLocation(plugin.getConfig().getString("Map."+t.getName()+".Bed"));
                                            Location bed = new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]));
                                            bed.getBlock().setType(Material.AIR);
                                            t.setDisplayName(String.valueOf(t.getEntries().size()));
                                        }
                                    } else {
                                        if (BedWars.Listenername.equalsIgnoreCase("绝杀模式")) {
                                            BedWars.Listenername = "游戏结束";
                                            BedWars.Listenertime = 600;
                                            BedWars.Listeners.replace("KillMode", true);
                                            Bukkit.broadcastMessage("§c§l所有的床已被破坏！");
                                            List<Team> yesteam = new ArrayList<>();
                                            for (Player player : Bukkit.getOnlinePlayers()) {
                                                if (!yesteam.contains(GameStart.getcoreboard().getEntryTeam(player.getName()))) {
                                                    if (!GameStart.getcoreboard().getEntryTeam(player.getName()).getName().equals("旁观者")) {
                                                        yesteam.add(GameStart.getcoreboard().getEntryTeam(player.getName()));
                                                    }
                                                }
                                            }
                                            for (Player player : Bukkit.getOnlinePlayers()) {
                                                player.sendTitle("§c绝杀模式", "");
                                                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
                                            }
                                            for (Team t : yesteam) {
                                                FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
                                                Bukkit.broadcastMessage("§c绝杀模式:§6+§b2条" + t.getSuffix() + t.getName() + "龙！");
                                                String[] spawn = LocationUtil.getStringLocation(config.getString("Map.Spectator"));
                                                EnderDragon ed = BedWars.playworld.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), EnderDragon.class);
                                                ed.setCustomName(t.getSuffix() + t.getName() + "龙");
                                                ed.setVelocity(ed.getLocation().getDirection().setY(0.6D).multiply(2.0D));
                                                ed.damage(5);
                                                EnderDragon ed1 = BedWars.playworld.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), EnderDragon.class);
                                                ed1.setCustomName(t.getSuffix() + t.getName() + "龙");
                                                ed1.setVelocity(ed1.getLocation().getDirection().setY(0.6D).multiply(2.0D));

                                                ed1.damage(5);
                                            }
                                        } else {
                                            if (BedWars.Listenername.equalsIgnoreCase("游戏结束")) {
                                                BedWars.Listenername = "-";
                                                BedWars.Listenertime = 705;
                                                Bukkit.broadcastMessage("§a由于你们最后无法决定胜负，本场游戏以平局结束.");
                                                GameEnd.gameend(null);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(BedWars.getPlugin(BedWars.class),20L,20L);
    }
}
