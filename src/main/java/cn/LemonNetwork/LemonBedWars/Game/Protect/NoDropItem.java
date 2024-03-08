package cn.lemonnetwork.lemonbedwars.Game.Protect;

import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class NoDropItem implements Listener {
    @EventHandler
    public void nodrop(PlayerDropItemEvent event) {
        String[] spawn = LocationUtil.getStringLocation(LemonBedWars.getPlugin(LemonBedWars.class).getConfig().getString("Map.红队.resources"));
        if (LemonBedWars.state.equalsIgnoreCase("Play")) {
            if (event.getPlayer().getLocation().getY() < Double.parseDouble(spawn[1])) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void noclicl(InventoryClickEvent event) {
        String[] spawn = LocationUtil.getStringLocation(LemonBedWars.getPlugin(LemonBedWars.class).getConfig().getString("Map.红队.resources"));
        if (LemonBedWars.state.equalsIgnoreCase("Play")) {
            if (event.getWhoClicked().getLocation().getY() < Double.parseDouble(spawn[1])) {
                event.setCancelled(true);
            }
        }
    }
}
