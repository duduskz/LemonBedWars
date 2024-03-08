package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class DropItem implements Listener {
    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "waiting")) {
                event.setCancelled(true);
            }
        }
    }
}
