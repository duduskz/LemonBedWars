package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.Menu.SpectatorMenu;
import cn.lemoncraft.bedwars.Utils.ActionBar;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class SpectatorListener implements Listener {


    @EventHandler
    public void pickup(PlayerPickupItemEvent e){
        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void drop(PlayerDropItemEvent e){
        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void openmenu(PlayerInteractEvent e){
        try {



        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a追踪玩家 §7(右键点击)")){
            SpectatorMenu.open(e.getPlayer(), 1);
        }
        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a旁观者设置 §7(右键点击)")){
            SpectatorMenu.open(e.getPlayer(), 2);
        }
        } catch (NullPointerException n) {

        }
    }
    @EventHandler
    public void useitem(InventoryClickEvent e){
        try {



            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a追踪玩家 §7(右键点击)")){
                SpectatorMenu.open((Player) e.getWhoClicked(), 1);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a旁观者设置 §7(右键点击)")){
                SpectatorMenu.open((Player) e.getWhoClicked(), 2);
            }
        } catch (NullPointerException n) {

        }
    }
    @EventHandler
    public void place(BlockPlaceEvent e){
        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            FileConfiguration config = JavaPlugin.getPlugin(BedWars.class).getConfig();
            if (Objects.equals(config.getString("BungeeMode"), "Game")) {
                if (Objects.equals(BedWars.state, "Play")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void inv(InventoryClickEvent event) {
        try {


            if (Objects.equals(event.getInventory().getName(), "追踪玩家")) {
                event.setCancelled(true);
                String[] playername = event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().split(" ");
                Player Clickplayer = Bukkit.getPlayer(playername[1]);
                PlayerDataManage playerDataManage = new PlayerDataManage();
                if (event.isLeftClick()) {
                    if (playerDataManage.getSpectatorSettings(Bukkit.getPlayer(event.getWhoClicked().getName()), "anto-first")) {
                        first(Bukkit.getPlayer(event.getWhoClicked().getName()), Clickplayer);
                    } else {
                        event.getWhoClicked().teleport(Clickplayer);
                    }
                } else {

                    TextComponent message = new TextComponent("§6点击这里来举报玩家 " + Clickplayer.getName());
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/report " + Clickplayer.getName()));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a点击复制指令至聊天框").create()));
                    Bukkit.getPlayer(event.getWhoClicked().getName()).spigot().sendMessage(message);
                }
                event.getWhoClicked().closeInventory();

            }
            if (Objects.equals(event.getInventory().getName(), "旁观者设置")) {
                event.setCancelled(true);
                PlayerDataManage playerDataManage = new PlayerDataManage();
                Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("关闭速度效果")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    PlayerDataManage.setSpectatorSettingsint(player, "Speed", 0);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 I")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0));
                    PlayerDataManage.setSpectatorSettingsint(player, "Speed", 1);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 II")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                    PlayerDataManage.setSpectatorSettingsint(player, "Speed", 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 III")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2));
                    PlayerDataManage.setSpectatorSettingsint(player, "Speed", 3);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 IV")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 3));
                    PlayerDataManage.setSpectatorSettingsint(player, "Speed", 4);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("启动自动传送")) {
                    playerDataManage.setSpectatorSettings(player, "anto-teleport", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("禁动自动传送")) {
                    playerDataManage.setSpectatorSettings(player, "anto-teleport", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("显示旁观者")) {
                    playerDataManage.setSpectatorSettings(player, "hide-spectator", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("隐藏旁观者")) {
                    playerDataManage.setSpectatorSettings(player, "hide-spectator", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("启用夜视")) {
                    playerDataManage.setSpectatorSettings(player, "night-vision", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("禁用夜视")) {
                    playerDataManage.setSpectatorSettings(player, "night-vision", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("启用第一人称")) {
                    playerDataManage.setSpectatorSettings(player, "anto-first", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("停用第一人称")) {
                    playerDataManage.setSpectatorSettings(player, "anto-first", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
            }
        } catch (NullPointerException n) {

        }
    }
    @EventHandler
    public void block(BlockBreakEvent e) {
        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            FileConfiguration config = JavaPlugin.getPlugin(BedWars.class).getConfig();
            if (Objects.equals(config.getString("BungeeMode"), "Game")) {
                if (Objects.equals(BedWars.state, "Play")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void damageplayer(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (Objects.equals(GameStart.getcoreboard().getEntryTeam(event.getDamager().getName()).getName(), "旁观者")) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void interactplayer(PlayerInteractEntityEvent e){
        first(e.getPlayer(), Bukkit.getPlayer(e.getRightClicked().getName()));
    }
    public void first(Player player1, Player player2){
        if (Objects.equals(GameStart.getcoreboard().getEntryTeam(player1.getName()).getName(), "旁观者")) {
            if (!Objects.equals(GameStart.getcoreboard().getEntryTeam(player2.getName()).getName(), "旁观者")) {
                player1.sendTitle("§a正在旁观 " + BedWars.api.getUserManager().getUser(player2.getUniqueId()).getCachedData().getMetaData().getPrefix() + player2.getName(), "§c右键打开菜单     §a潜行退出旁观");
                player1.sendMessage("§a你正在旁观 " + BedWars.api.getUserManager().getUser(player2.getUniqueId()).getCachedData().getMetaData().getPrefix() + player2.getName() + "§a。如要退出，请按下潜行");
                player1.setGameMode(GameMode.SPECTATOR);
                if (BedWars.spectator.containsKey(player1.getName())){
                    BedWars.spectator.replace(player1.getName(), player2.getName());
                } else {
                    BedWars.spectator.put(player1.getName(), player2.getName());
                }
                player1.setSpectatorTarget(player2);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (BedWars.spectator.get(player1.getName()).equals(player2.getName())) {
                            double distance = player1.getLocation().distance(player2.getLocation());
                            ActionBar.sendMessage(player1, "§f目标: §a"+player2.getName()+" §f距离: §a"+distance+"m §f血量: §a"+ Bukkit.getPlayer(player2.getName()).getHealth());

                        } else {
                            cancel();
                        }

                    }
                }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class),0L, 20L);
            }
        }
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        FileConfiguration config = JavaPlugin.getPlugin(BedWars.class).getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                if (Objects.equals(GameStart.getcoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者") && e.getPlayer().getSpectatorTarget() != null) {
                    e.getPlayer().setGameMode(GameMode.SURVIVAL);
                    e.getPlayer().setAllowFlight(true);
                    e.getPlayer().setFlying(true);
                    e.getPlayer().sendTitle("§e退出旁观模式", "");
                }
            }
        }
    }
}
