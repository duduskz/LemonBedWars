package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.Game.Arena.Menu.SpectatorMenu;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.ActionBar;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
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
import org.bukkit.event.entity.EntityDamageEvent;
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
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        if (Objects.equals(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void drop(PlayerDropItemEvent e){
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        if (Objects.equals(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void interact(PlayerInteractEvent e){
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        if (GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName().equalsIgnoreCase("旁观者")) e.setCancelled(true);
        try {


            if (e.getItem().getItemMeta().getDisplayName().contains("追踪玩家")) {
                SpectatorMenu.open(e.getPlayer(), 1);
            }
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a旁观者设置 §7(右键点击)")) {
                SpectatorMenu.open(e.getPlayer(), 2);
            }
        } catch (NullPointerException n) {

        }
    }
    @EventHandler
    public void useitem(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a追踪玩家 §7(右键点击)")) {
            SpectatorMenu.open((Player) e.getWhoClicked(), 1);
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a旁观者设置 §7(右键点击)")) {
            SpectatorMenu.open((Player) e.getWhoClicked(), 2);
        }

    }
    @EventHandler
    public void place(BlockPlaceEvent e){
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        if (Objects.equals(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            FileConfiguration config = JavaPlugin.getPlugin(LemonBedWars.class).getConfig();
            if (Objects.equals(config.getString("BungeeMode"), "Game")) {
                if (Objects.equals(LemonBedWars.state, "Play")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void damaage(EntityDamageEvent e){
        if (!(e.getEntity() instanceof Player)) return;
        if (LemonBedWars.state.equalsIgnoreCase("Play")) {
            if (Objects.equals(GameStart.getScoreboard().getEntryTeam(e.getEntity().getName()).getName(), "旁观者")) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void inv(InventoryClickEvent event) {
        try {
            if (Objects.equals(event.getInventory().getName(), "追踪玩家")) {
                event.setCancelled(true);

                String[] playername = event.getCurrentItem().getItemMeta().getDisplayName().split(" ");
                Player Clickplayer;
                if (playername.length == 2) {
                    Clickplayer = Bukkit.getPlayer(playername[1]);
                } else {
                    Clickplayer = Bukkit.getPlayer(event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().substring(2));
                }
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
                    PlayerDataManage.setSpectatorSettings(player, "Speed", 0);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 I")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0));
                    PlayerDataManage.setSpectatorSettings(player, "Speed", 1);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 II")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                    PlayerDataManage.setSpectatorSettings(player, "Speed", 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 III")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2));
                    PlayerDataManage.setSpectatorSettings(player, "Speed", 3);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("速度 IV")) {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 3));
                    PlayerDataManage.setSpectatorSettings(player, "Speed", 4);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("启动自动传送")) {
                    PlayerDataManage.setSpectatorSettings(player, "anto-teleport", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("禁动自动传送")) {
                    PlayerDataManage.setSpectatorSettings(player, "AntoTeleport", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("显示旁观者")) {
                    playerDataManage.setSpectatorSettings(player, "HideSpectator", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("隐藏旁观者")) {
                    PlayerDataManage.setSpectatorSettings(player, "HideSpectator", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("启用夜视")) {
                    PlayerDataManage.setSpectatorSettings(player, "NightVision", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("禁用夜视")) {
                    PlayerDataManage.setSpectatorSettings(player, "NightVision", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("启用第一人称")) {
                    PlayerDataManage.setSpectatorSettings(player, "AntoFirst", true);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
                if (event.getInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().contains("停用第一人称")) {
                    playerDataManage.setSpectatorSettings(player, "AntoFirst", false);
                    player.closeInventory();
                    SpectatorMenu.open(player, 2);
                }
            }
        } catch (NullPointerException n) {

        }
    }
    @EventHandler
    public void block(BlockBreakEvent e) {
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        if (Objects.equals(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者")) {
            FileConfiguration config = JavaPlugin.getPlugin(LemonBedWars.class).getConfig();
            if (Objects.equals(config.getString("BungeeMode"), "Game")) {
                if (Objects.equals(LemonBedWars.state, "Play")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void damageplayer(EntityDamageByEntityEvent event) {
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        if (event.getEntity() instanceof Player) {
            if (Objects.equals(GameStart.getScoreboard().getEntryTeam(event.getDamager().getName()).getName(), "旁观者")) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void interactplayer(PlayerInteractEntityEvent e){
        if (!LemonBedWars.state.equalsIgnoreCase("Play")) return;
        first(e.getPlayer(), Bukkit.getPlayer(e.getRightClicked().getName()));
    }
    public void first(Player player1, Player player2){

        if (Objects.equals(GameStart.getScoreboard().getEntryTeam(player1.getName()).getName(), "旁观者")) {
            if (!Objects.equals(GameStart.getScoreboard().getEntryTeam(player2.getName()).getName(), "旁观者")) {
                player1.sendTitle("§a正在旁观 " + PlayerDataManage.getPlayerPrefix(player2) + player2.getName(), "§c右键打开菜单     §a潜行退出旁观");
                player1.sendMessage("§a你正在旁观 " + PlayerDataManage.getPlayerPrefix(player2) + player2.getName() + "§a。如要退出，请按下潜行");
                player1.setGameMode(GameMode.SPECTATOR);
                if (LemonBedWars.spectator.containsKey(player1.getName())){
                    LemonBedWars.spectator.replace(player1.getName(), player2.getName());
                } else {
                    LemonBedWars.spectator.put(player1.getName(), player2.getName());
                }
                player1.setSpectatorTarget(player2);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (LemonBedWars.spectator.get(player1.getName()).equals(player2.getName())) {
                            int distance = (int) player1.getLocation().distance(player2.getLocation());
                            ActionBar.sendMessage(player1, "§f目标: §a"+player2.getName()+ ((distance <= 1) ? " " : " §f距离: §a"+distance)+"m §f血量: §a"+ (int) Bukkit.getPlayer(player2.getName()).getHealth());

                        } else {
                            cancel();
                        }

                    }
                }.runTaskTimer(JavaPlugin.getPlugin(LemonBedWars.class),0L, 20L);
            }
        }
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        FileConfiguration config = JavaPlugin.getPlugin(LemonBedWars.class).getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "Play")) {
                if (Objects.equals(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName(), "旁观者") && e.getPlayer().getSpectatorTarget() != null) {
                    e.getPlayer().setGameMode(GameMode.SURVIVAL);
                    e.getPlayer().setAllowFlight(true);
                    e.getPlayer().setFlying(true);
                    e.getPlayer().sendTitle("§e退出旁观模式", "");
                }
            }
        }
    }
}
