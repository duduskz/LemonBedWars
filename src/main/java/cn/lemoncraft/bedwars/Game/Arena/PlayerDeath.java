package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class PlayerDeath implements Listener {
    @EventHandler
    public void Death(PlayerDeathEvent e) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        PlayerDataManage playerDataManage = new PlayerDataManage();
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                String color = GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getSuffix();
                if (Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getDisplayName(), "yes")) {
                    if (e.getEntity().getLocation().getY() < 0) {
                        if (e.getEntity().getKiller() == null || e.getEntity() == e.getEntity().getKiller()) {

                            e.setDeathMessage(color + e.getEntity().getName() + " §7掉入了虚空！");
                        } else {
                            String killercolor = GameStart.getcoreboard().getEntryTeam(e.getEntity().getKiller().getName()).getSuffix();
                            e.setDeathMessage(color + e.getEntity().getName() + " §7 被 " + killercolor + e.getEntity().getKiller().getName() + " §7推入了虚空！");
                            BedWars.kill.replace(e.getEntity().getKiller().getName(), BedWars.kill.get(e.getEntity().getKiller().getName()) + 1);
                            playerDataManage.addPlayerKill(e.getEntity(),1,config.getString("Map.Mode"));
                            e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.EXPLODE, 1.0F, 16.0F);
                        }

                    } else {
                        if (e.getEntity().getKiller() == null || e.getEntity() == e.getEntity().getKiller()) {

                            e.setDeathMessage(color + e.getEntity().getName() + " §7死了！");

                        } else {
                            String killercolor = GameStart.getcoreboard().getEntryTeam(e.getEntity().getKiller().getName()).getSuffix();
                            e.setDeathMessage(color + e.getEntity().getName() + " §7 被 " + killercolor + e.getEntity().getKiller().getName() + " §7击杀！");
                            BedWars.kill.replace(e.getEntity().getKiller().getName(), BedWars.kill.get(e.getEntity().getKiller().getName()) + 1);
                            e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.NOTE_PLING, 1.0F, 16.0F);
                            playerDataManage.addPlayerKill(e.getEntity(),1,config.getString("Map.Mode"));
                        }
                    }
                    ((CraftPlayer)e.getEntity().getPlayer()).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                    String[] spawn = BedWars.getLocaton(config.getString("Map.Spectator"));
                    e.getEntity().teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    e.getEntity().getInventory().clear();
                    e.getEntity().setAllowFlight(true);
                    e.getEntity().setFlying(true);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(e.getEntity());

                    }
                    e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));

                    e.getEntity().setFlying(true);

                    e.getEntity().setFlying(true);
                    e.getEntity().setFlying(true);
                    e.getEntity().sendMessage("§e你将在 §c5 §e秒后重生！");
                    e.getEntity().sendTitle("§c你死了", "§e你将在 §c5 §e秒后重生");
                    new BukkitRunnable() {
                        public void run() {
                            e.getEntity().sendMessage("§e你将在 §c4 §e秒后重生！");
                            e.getEntity().sendTitle("§c你死了", "§e你将在 §c4 §e秒后重生");
                        }
                    }.runTaskLater(plugin, 20L);
                    new BukkitRunnable() {
                        public void run() {
                            e.getEntity().sendMessage("§e你将在 §c3 §e秒后重生！");
                            e.getEntity().sendTitle("§c你死了", "§e你将在 §c3 §e秒后重生");
                        }
                    }.runTaskLater(plugin, 40L);
                    new BukkitRunnable() {
                        public void run() {
                            e.getEntity().sendMessage("§e你将在 §c2 §e秒后重生！");
                            e.getEntity().sendTitle("§c你死了", "§e你将在 §c2 §e秒后重生");
                        }
                    }.runTaskLater(plugin, 60L);
                    new BukkitRunnable() {
                        public void run() {
                            e.getEntity().sendMessage("§e你将在 §c1 §e秒后重生！");
                            e.getEntity().sendTitle("§c你死了", "§e你将在 §c1 §e秒后重生");
                        }
                    }.runTaskLater(plugin, 80L);
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            e.getEntity().getInventory().clear();
                            e.getEntity().setGameMode(GameMode.SURVIVAL);
                            e.getEntity().sendTitle("§a已重生", "");
                            e.getEntity().sendMessage("§a你已重生！");
                            if (BedWars.shears.get(e.getEntity().getName())){
                                e.getEntity().getInventory().setItem(1,new ItemStack(Material.SHEARS));
                            }
                            if (BedWars.pickaxe.get(e.getEntity().getName()) == 1){
                                ItemStack item = new ItemStack(Material.WOOD_PICKAXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(2,item);
                            }
                            if (BedWars.pickaxe.get(e.getEntity().getName()) == 2){
                                ItemStack item = new ItemStack(Material.WOOD_PICKAXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(2,item);
                                BedWars.pickaxe.replace(e.getEntity().getName(), 1);
                            }
                            if (BedWars.pickaxe.get(e.getEntity().getName()) == 3){
                                ItemStack item = new ItemStack(Material.IRON_PICKAXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(2,item);
                                BedWars.pickaxe.replace(e.getEntity().getName(), 2);
                            }
                            if (BedWars.pickaxe.get(e.getEntity().getName()) == 4){
                                ItemStack item = new ItemStack(Material.GOLD_PICKAXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(2,item);
                                BedWars.pickaxe.replace(e.getEntity().getName(), 3);
                            }
                            if (BedWars.axe.get(e.getEntity().getName()) == 1){
                                ItemStack item = new ItemStack(Material.WOOD_AXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(3,item);
                            }
                            if (BedWars.axe.get(e.getEntity().getName()) == 2){
                                ItemStack item = new ItemStack(Material.WOOD_AXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(3,item);
                                BedWars.axe.replace(e.getEntity().getName(), 1);
                            }
                            if (BedWars.axe.get(e.getEntity().getName()) == 3){
                                ItemStack item = new ItemStack(Material.STONE_AXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(3,item);
                                BedWars.axe.replace(e.getEntity().getName(), 2);
                            }
                            if (BedWars.axe.get(e.getEntity().getName()) == 4){
                                ItemStack item = new ItemStack(Material.IRON_AXE);
                                ItemMeta meta = item.getItemMeta();
                                meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                                item.setItemMeta(meta);
                                e.getEntity().getInventory().setItem(3,item);
                                BedWars.axe.replace(e.getEntity().getName(), 3);
                            }
                            String[] spawn = BedWars.getLocaton(config.getString("Map." + GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getName() + ".Spawn"));
                            e.getEntity().teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.showPlayer(e.getEntity());
                            }
                            ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
                            ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
                            itemMeta.spigot().setUnbreakable(true);
                            WOOD_SWORD.setItemMeta(itemMeta);
                            e.getEntity().getInventory().addItem(WOOD_SWORD);

                            Game item = new Game();
                            e.getEntity().getInventory().setItem(8,item.getItem("指南针"));
                            e.getEntity().removePotionEffect(PotionEffectType.INVISIBILITY);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    e.getEntity().setFlying(false);
                                    e.getEntity().setAllowFlight(false);
                                }
                            }.runTaskLater(plugin, 5L);

                        }
                    }.runTaskLater(plugin, 100L);

                } else {
                    if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getName(), "旁观者")) {

                        int TeamRemainingPlayer = Integer.parseInt(GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getDisplayName()) - 1;
                        GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).setDisplayName(String.valueOf(TeamRemainingPlayer));

                        if (e.getEntity().getLocation().getY() < 0) {
                            if (e.getEntity().getKiller() == null || e.getEntity() == e.getEntity().getKiller()) {
                                e.setDeathMessage(color + e.getEntity().getName() + " §7掉入了虚空！ §b§l最终击杀！" );
                            } else {
                                BedWars.finalkill.replace(e.getEntity().getKiller().getName(), BedWars.finalkill.get(e.getEntity().getKiller().getName()) + 1);
                                String killercolor = GameStart.getcoreboard().getEntryTeam(e.getEntity().getKiller().getName()).getSuffix();
                                e.setDeathMessage(color + e.getEntity().getName() + " §7 被 " + killercolor + e.getEntity().getKiller().getName() + " §7推入了虚空！ §b§l最终击杀！");
                                playerDataManage.addPlayerFinalKill(e.getEntity(), 1, config.getString("Map.Mode"));
                                e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.NOTE_PLING, 1.0F, 16.0F);
                                e.getEntity().getKiller().sendMessage("§6+20 硬币 (最终击杀奖励)");
                                playerDataManage.addPlayerCoins(e.getEntity().getKiller().getPlayer(), 20);

                            }

                        } else {
                            if (e.getEntity().getKiller() == null || e.getEntity() == e.getEntity().getKiller()) {
                                e.setDeathMessage(color + e.getEntity().getName() + " §7死了！ §b§l最终击杀！");
                            } else {
                                String killercolor = GameStart.getcoreboard().getEntryTeam(e.getEntity().getKiller().getName()).getSuffix();
                                e.setDeathMessage(color + e.getEntity().getName() + " §7 被 " + killercolor + e.getEntity().getKiller().getName() + " §7击杀！ §b§l最终击杀！");
                                BedWars.finalkill.replace(e.getEntity().getKiller().getName(), BedWars.finalkill.get(e.getEntity().getKiller().getName()) + 1);
                                playerDataManage.addPlayerFinalKill(e.getEntity(), 1, config.getString("Map.Mode"));
                                e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.NOTE_PLING, 1.0F, 16.0F);
                                e.getEntity().getKiller().sendMessage("§6+20 硬币 (最终击杀奖励)");
                                playerDataManage.addPlayerCoins(e.getEntity().getKiller().getPlayer(), 20);
                            }
                        }
                        ((CraftPlayer) e.getEntity().getPlayer()).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        e.getEntity().sendMessage("§c你已被淘汰！");
                        String[] spawn = BedWars.getLocaton(config.getString("Map.Spectator"));
                        e.getEntity().teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        e.getEntity().getInventory().clear();
                        e.getEntity().setAllowFlight(true);
                        Game item = new Game();
                        e.getEntity().getInventory().setItem(0, item.getItem("追踪玩家"));
                        e.getEntity().getInventory().setItem(4, item.getItem("旁观者设置"));
                        e.getEntity().getInventory().setItem(8, item.getItem("返回大厅"));
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(e.getEntity());
                        }

                        e.getEntity().setFlying(true);
                        e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));

                        if (TeamRemainingPlayer == 0) {
                            if (config.getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")) {
                                if (Objects.equals(GameStart.getcoreboard().getTeam("红队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("蓝队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("绿队").getDisplayName(), "0") && !Objects.equals(GameStart.getcoreboard().getTeam("黄队").getDisplayName(), "0")) {

                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(p.getName()).getName(), "黄队")) {
                                            p.sendTitle("§6胜利！", "");
                                            p.sendMessage("§b+25 起床战争经验 (获胜奖励)");
                                            p.sendMessage("§6+50 硬币 (获胜奖励)");
                                            playerDataManage.addPlayerXP(p, 25);
                                            playerDataManage.addPlayerCoins(p, 50);
                                        } else {
                                            p.sendTitle("§c§l游戏结束", "");
                                        }
                                        GameEnd.gameend("黄队");
                                    }

                                }
                                if (Objects.equals(GameStart.getcoreboard().getTeam("红队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("蓝队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("黄队").getDisplayName(), "0") && !Objects.equals(GameStart.getcoreboard().getTeam("绿队").getDisplayName(), "0")) {

                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(p.getName()).getName(), "绿队")) {
                                            p.sendTitle("§6胜利！", "");
                                            p.sendMessage("§b+25 起床战争经验 (获胜奖励)");
                                            p.sendMessage("§6+50 硬币 (获胜奖励)");
                                            playerDataManage.addPlayerXP(p, 25);
                                            playerDataManage.addPlayerCoins(p, 50);
                                        } else {
                                            p.sendTitle("§c§l游戏结束", "");
                                        }
                                        GameEnd.gameend("绿队");
                                    }

                                }
                                if (Objects.equals(GameStart.getcoreboard().getTeam("红队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("绿队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("黄队").getDisplayName(), "0") && !Objects.equals(GameStart.getcoreboard().getTeam("蓝队").getDisplayName(), "0")) {

                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(p.getName()).getName(), "蓝队")) {
                                            p.sendTitle("§6胜利！", "");
                                            p.sendMessage("§b+25 起床战争经验 (获胜奖励)");
                                            p.sendMessage("§6+50 硬币 (获胜奖励)");
                                            playerDataManage.addPlayerXP(p, 25);
                                            playerDataManage.addPlayerCoins(p, 50);
                                        } else {
                                            p.sendTitle("§c§l游戏结束", "");
                                        }
                                        GameEnd.gameend("蓝队");
                                    }

                                }
                                if (Objects.equals(GameStart.getcoreboard().getTeam("蓝队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("蓝队").getDisplayName(), "0") && Objects.equals(GameStart.getcoreboard().getTeam("黄队").getDisplayName(), "0") && !Objects.equals(GameStart.getcoreboard().getTeam("红队").getDisplayName(), "0")) {

                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(p.getName()).getName(), "红队")) {
                                            p.sendTitle("§6胜利！", "");
                                            p.sendMessage("§b+25 起床战争经验 (获胜奖励)");
                                            p.sendMessage("§6+50 硬币 (获胜奖励)");
                                            playerDataManage.addPlayerXP(p, 25);
                                            playerDataManage.addPlayerCoins(p, 50);
                                        } else {
                                            p.sendTitle("§c§l游戏结束", "");
                                        }
                                        GameEnd.gameend("红队");
                                    }

                                }

                            }

                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.sendMessage("");
                                p.sendMessage("§f§l团灭 > " + GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getSuffix() + GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getName() + " §c已被淘汰！");
                                p.sendMessage("");

                            }

                            GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).removeEntry(e.getEntity().getName());
                            GameStart.getcoreboard().getTeam("旁观者").addEntry(e.getEntity().getName());
                        }
                    } else {
                        ((CraftPlayer)e.getEntity().getPlayer()).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        String[] spawn = BedWars.getLocaton(config.getString("Map.Spectator"));
                        e.getEntity().teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        e.getEntity().getInventory().clear();
                        e.getEntity().setAllowFlight(true);
                        e.setDeathMessage(null);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(e.getEntity());
                        }
                        e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0));

                        e.getEntity().setFlying(true);

                    }

                }
            }
        }
    }
}