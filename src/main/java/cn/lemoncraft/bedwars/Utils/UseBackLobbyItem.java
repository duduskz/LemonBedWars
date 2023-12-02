package cn.lemoncraft.bedwars.Utils;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.Menu.Selector;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UseBackLobbyItem implements Listener {
    @EventHandler
    public void useitem(PlayerInteractEvent event) {
        String lang = PlayerDataManage.getPlayerLang(event.getPlayer());
        try {
            if (Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§c§l返回大厅 §7(右键点击)") || Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§c§lRuturn to Lobby §7(Right Click)")) {
                if (BedWars.backlobby.get(event.getPlayer().getName())) {
                    BedWars.backlobby.replace(event.getPlayer().getName(), false);
                    if (lang.equalsIgnoreCase("zhcn")) {
                        event.getPlayer().sendMessage("§c§l传送取消了！");
                    } else {
                        event.getPlayer().sendMessage("§c§lTeleport cancelled!");
                    }
                } else {
                    BedWars.backlobby.replace(event.getPlayer().getName(), true);
                    if (lang.equalsIgnoreCase("zhcn")) {
                        event.getPlayer().sendMessage("§a§l3秒后你将传送到大厅...再次右键来取消传送");
                    } else {
                        event.getPlayer().sendMessage("§a§lTeleporting uou to the lobby in 3 seconds... Right-click again to cancel the teleport!");
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            BedWars.backlobby.replace(event.getPlayer().getName(), false);
                            List<String> lobby = JavaPlugin.getPlugin(BedWars.class).getConfig().getStringList("LobbyServer");
                            out.writeUTF(lobby.get(new Random().nextInt(lobby.size())));
                            event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(BedWars.class), "BungeeCord", out.toByteArray());

                        }
                    }.runTaskLater(JavaPlugin.getPlugin(BedWars.class), 60L);
                }
            }
            if (Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§a§l选择队伍 §7(右键点击)") || Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§a§lSelect Team §7(Right Click)")) {
                Selector.open(event.getPlayer());
            }
        } catch (NullPointerException ignored) {}
    }
    @EventHandler
    public void inv(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("返回大厅") || event.getCurrentItem().getItemMeta().getDisplayName().contains("Return to Lobby")) {
                String lang = PlayerDataManage.getPlayerLang((Player)event.getWhoClicked());
                if (BedWars.backlobby.get(event.getWhoClicked().getName())) {
                    BedWars.backlobby.replace(event.getWhoClicked().getName(), false);
                    if (lang.equalsIgnoreCase("zhcn")) {
                        event.getWhoClicked().sendMessage("§c§l传送取消了！");
                    } else {
                        event.getWhoClicked().sendMessage("§c§lTeleport cancelled!");
                    }
                } else {
                    BedWars.backlobby.replace(event.getWhoClicked().getName(), true);
                    if (lang.equalsIgnoreCase("zhcn")) {
                        event.getWhoClicked().sendMessage("§a§l3秒后你将传送到大厅...再次右键来取消传送");
                    } else {
                        event.getWhoClicked().sendMessage("§a§lTeleporting uou to the lobby in 3 seconds... Right-click again to cancel the teleport!");
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            BedWars.backlobby.replace(event.getWhoClicked().getName(), false);
                            List<String> lobby = JavaPlugin.getPlugin(BedWars.class).getConfig().getStringList("LobbyServer");
                            out.writeUTF(lobby.get(new Random().nextInt(lobby.size())));
                            ((Player) event.getWhoClicked()).sendPluginMessage(JavaPlugin.getPlugin(BedWars.class), "BungeeCord", out.toByteArray());

                        }
                    }.runTaskLater(JavaPlugin.getPlugin(BedWars.class), 60L);
                }
            }
        } catch (NullPointerException ignored) {}
    }
}