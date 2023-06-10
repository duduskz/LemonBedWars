package cn.lemoncraft.bedwars.Game.Protect;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DoubleResources implements Listener {
    @EventHandler
    public void doubleresource(PlayerPickupItemEvent e){
        if (e.getItem().getItemStack().getType().equals(Material.IRON_INGOT)){
            double radiusSquared = 3*2;

            List<Entity> entities = e.getPlayer().getNearbyEntities(1, 1, 1); // All entities withing a box
            for (Entity entity : entities) {

                if(entity.getLocation().distanceSquared(e.getPlayer().getLocation()) > radiusSquared) continue; // All entities within a sphere

                if(entity instanceof Player){

                    ((Player) entity).getInventory().addItem(new ItemStack(Material.IRON_INGOT));

                }

            }
        }
        if (e.getItem().getItemStack().getType().equals(Material.GOLD_INGOT)){
            double radiusSquared = 3*2;

            List<Entity> entities = e.getPlayer().getNearbyEntities(1, 1, 1); // All entities withing a box
            for (Entity entity : entities) {

                if(entity.getLocation().distanceSquared(e.getPlayer().getLocation()) > radiusSquared) continue; // All entities within a sphere

                if(entity instanceof Player){

                    ((Player) entity).getInventory().addItem(new ItemStack(Material.IRON_INGOT));

                }

            }
        }
    }
}
