package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.TAB;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static cn.lemoncraft.bedwars.Utils.LocationUtil.getStringLocation;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            BedWars.backlobby.put(event.getPlayer().getName(), false);
            if (Objects.equals(BedWars.state, "Play")) {
                if (GameStart.getcoreboard().getEntryTeam(player.getName()) == null || Objects.equals(GameStart.getcoreboard().getEntryTeam(player.getName()).getName(), "旁观者")) {
                    TAB.set(player, "     §b§l你正在§e§l" + BedWars.serverip + "§b§l上进行游戏\n", "\n§b击杀数: §e0"+" §b最终击杀数: §e0"+" §b破坏床数: §e0"+"\n\n     §a§lRank以及更多！§c§l请访问Store." + BedWars.serverip + "");
                    player.sendMessage("§a由于这场游戏还没结束，已为你切换为旁观者");
                    GameStart.ShowScoreBoard(player);
                    String[] spawn = getStringLocation(config.getString("Map.Spectator"));
                    player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    BedWars.kill.put(player.getName(),0);
                    BedWars.finalkill.put(player.getName(),0);
                    BedWars.breakbed.put(player.getName(),0);
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    player.getInventory().clear();
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    Game item = new Game();
                    player.getInventory().setItem(4, item.getItem("旁观者设置"));
                    player.getInventory().setItem(8, item.getItem("返回大厅"));
                    player.getInventory().setItem(0, item.getItem("追踪玩家"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(player);
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));
                    GameStart.getcoreboard().getTeam("旁观者").addEntry(player.getName());
                } else {
                    if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(player.getName()).getDisplayName(), "yes")) {

                        player.sendMessage("§c你的床已被摧毁，已为你切换为旁观者");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                TAB.set(player, "     §b§l你正在§e§l" + BedWars.serverip + "§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(player.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(player.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(player.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store." + BedWars.serverip + "");

                            }
                        }.runTaskTimer(plugin,0L,80L);
                        GameStart.ShowScoreBoard(player);
                        String[] spawn = getStringLocation(config.getString("Map.Spectator"));
                        player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        player.getInventory().clear();
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        Game item = new Game();
                        player.getInventory().setItem(0, item.getItem("追踪玩家"));
                        player.getInventory().setItem(4, item.getItem("旁观者设置"));
                        player.getInventory().setItem(8, item.getItem("返回大厅"));
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(player);
                        }
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));
                        GameStart.getcoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
                        GameStart.getcoreboard().getTeam("旁观者").addEntry(player.getName());
                    } else {
                        String[] spawn = getStringLocation(config.getString("Map.Spectator"));
                        player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        player.getInventory().clear();
                        player.setAllowFlight(true);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                TAB.set(player, "     §b§l你正在§e§l" + BedWars.serverip + "§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(player.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(player.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(player.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store." + BedWars.serverip + "");

                            }
                        }.runTaskTimer(plugin,0L,80L);
                        GameStart.ShowScoreBoard(player);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(player);

                        }
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));
                        player.setFlying(true);
                        Bukkit.broadcastMessage(GameStart.getcoreboard().getEntryTeam(player.getName()).getSuffix()+player.getName()+" §7重新连接！");
                        BedWars.ReSpawning.add(player);
                        player.sendMessage("§e你将在 §c10 §e秒后重生！");
                        player.sendTitle("§c你死了", "§e你将在 §c10 §e秒后重生");
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c9 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c9 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 20L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c8 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c8 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 40L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c7 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c7 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 60L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c6 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c6 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 80L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c5 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c5 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 100L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c4 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c4 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 120L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c3 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c3 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 140L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c2 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c2 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 160L);
                        new BukkitRunnable() {
                            public void run() {
                                player.sendMessage("§e你将在 §c1 §e秒后重生！");
                                player.sendTitle("§c你死了", "§e你将在 §c1 §e秒后重生");
                            }
                        }.runTaskLater(plugin, 180L);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendTitle("§a已重生", "");
                                player.sendMessage("§a你已重生！");
                                BedWars.ReSpawning.remove(player);
                                String[] spawn = getStringLocation(config.getString("Map." + GameStart.getcoreboard().getEntryTeam(player.getName()).getName() + ".Spawn"));
                                player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                                player.setAllowFlight(false);
                                player.setFlying(false);
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    players.showPlayer(player);
                                }
                                player.getInventory().clear();
                                ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
                                ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
                                itemMeta.spigot().setUnbreakable(true);
                                WOOD_SWORD.setItemMeta(itemMeta);
                                player.getInventory().addItem(WOOD_SWORD);
                                Game item = new Game();
                                player.getInventory().setItem(8, item.getItem("指南针"));
                                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                            }
                        }.runTaskLater(plugin, 200L);
                    }
                }
            }
        }
    }
}