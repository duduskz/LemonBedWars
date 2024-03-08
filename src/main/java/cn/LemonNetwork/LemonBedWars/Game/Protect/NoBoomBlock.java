package cn.lemonnetwork.lemonbedwars.Game.Protect;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class NoBoomBlock implements Listener {
    @EventHandler
    public void onExplode(BlockExplodeEvent e) {
        if (!LemonBedWars.changedBlocks.contains(e.getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }
}
