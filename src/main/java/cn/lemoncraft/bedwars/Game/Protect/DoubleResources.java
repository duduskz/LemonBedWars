package cn.lemoncraft.bedwars.Game.Protect;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class DoubleResources implements Listener {
    @EventHandler
    public void doubleresource(PlayerPickupItemEvent e){
        if (e.getItem().getItemStack().getType().equals(Material.IRON_INGOT)){

        }
    }
}
