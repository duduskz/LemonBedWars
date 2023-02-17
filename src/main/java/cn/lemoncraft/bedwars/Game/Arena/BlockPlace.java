package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class BlockPlace implements Listener {
    @EventHandler
    public void place(BlockPlaceEvent e){
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                //e.getBlock().setData(new );
                if (e.getBlock().getType() == Material.FIRE){
                    e.setCancelled(true);
                } else {
                    BedWars.changedBlocks.add(e.getBlock().getLocation());
                }
            }
        }
    }
}
