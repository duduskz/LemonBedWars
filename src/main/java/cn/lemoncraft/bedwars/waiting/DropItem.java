package cn.lemoncraft.bedwars.waiting;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class DropItem implements Listener {
    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "waiting")) {
                event.setCancelled(true);
            }
        }
    }
}
