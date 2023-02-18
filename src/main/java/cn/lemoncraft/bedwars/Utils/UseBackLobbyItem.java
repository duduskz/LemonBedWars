package cn.lemoncraft.bedwars.Utils;

import cn.lemoncraft.bedwars.BedWars;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UseBackLobbyItem implements Listener {
    @EventHandler
    public void useitem(PlayerInteractEvent event) {
        try {
            if (Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§c§l返回大厅 §7(右键点击)")) {
                if (BedWars.backlobby.get(event.getPlayer().getName())) {
                    BedWars.backlobby.replace(event.getPlayer().getName(), false);
                    event.getPlayer().sendMessage("§c§l传送取消了！");
                } else {
                    BedWars.backlobby.replace(event.getPlayer().getName(), true);
                    event.getPlayer().sendMessage("§a§l3秒后你将传送到大厅...再次右键来取消传送");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            BedWars.backlobby.replace(event.getPlayer().getName(), false);
                            out.writeUTF(event.getPlayer().getName());
                            List<String> lobby = JavaPlugin.getPlugin(BedWars.class).getConfig().getStringList("LobbyServer");
                            out.writeUTF(lobby.get(new Random().nextInt(lobby.size())));
                            event.getPlayer().sendPluginMessage(JavaPlugin.getPlugin(BedWars.class), "BungeeCord", out.toByteArray());

                        }
                    }.runTaskLater(JavaPlugin.getPlugin(BedWars.class), 60L);
                }
            }
        } catch (NullPointerException n) {

        }
    }
}