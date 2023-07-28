package cn.lemoncraft.bedwars.Game.Protect;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class NoDropItem implements Listener {
    @EventHandler
    public void nodrop(PlayerDropItemEvent event) {
        String[] spawn = LocationUtil.getStringLocation(BedWars.getPlugin(BedWars.class).getConfig().getString("Map.红队.resources"));
        if (BedWars.state.equalsIgnoreCase("Play")) {
            if (event.getPlayer().getLocation().getY() < Double.parseDouble(spawn[1])) {
                event.setCancelled(true);
            }
        }
    }
}
