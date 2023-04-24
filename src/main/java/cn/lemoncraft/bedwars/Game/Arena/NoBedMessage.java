package cn.lemoncraft.bedwars.Game.Arena;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoBedMessage implements Listener {
    @EventHandler
    public void bed(PlayerInteractEvent e) {
        try {
            if (e.getClickedBlock().getType() == Material.BED_BLOCK && !e.getPlayer().isSneaking() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
            }
        } catch (NullPointerException n) {
        }

    }
}