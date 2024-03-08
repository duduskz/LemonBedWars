package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Block implements Listener {
    @EventHandler
    public void Break(BlockBreakEvent event) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "waiting")) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void Break(BlockPlaceEvent event) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "waiting")) {
                event.setCancelled(true);
            }
        }
    }
}
