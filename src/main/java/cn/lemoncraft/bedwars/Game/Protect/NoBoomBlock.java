package cn.lemoncraft.bedwars.Game.Protect;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class NoBoomBlock implements Listener {
    @EventHandler
    public void onExplode(BlockExplodeEvent e) {
        if (!BedWars.changedBlocks.contains(e.getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }
}
