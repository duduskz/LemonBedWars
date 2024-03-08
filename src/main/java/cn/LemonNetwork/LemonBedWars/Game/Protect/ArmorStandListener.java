package cn.lemonnetwork.lemonbedwars.Game.Protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class ArmorStandListener implements Listener {
    @EventHandler
    public void nopick(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }
}
