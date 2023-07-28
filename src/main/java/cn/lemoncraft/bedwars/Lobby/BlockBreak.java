package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class BlockBreak implements Listener {
    @EventHandler
    public void Break(BlockBreakEvent event) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            if (!BedWars.Build.contains(event.getPlayer().getName())) {
                event.setCancelled(true);
            }
        }
    }

}
