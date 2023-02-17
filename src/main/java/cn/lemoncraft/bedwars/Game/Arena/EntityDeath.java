package cn.lemoncraft.bedwars.Game.Arena;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void EntityDeath(EntityDeathEvent e){
        if (e.getEntity().getType() == EntityType.ENDER_DRAGON){
            for (Entity e1 : e.getEntity().getWorld().getEntities()){
                if (e1.getType() == EntityType.ENDER_DRAGON){
                    e1.remove();
                }
            }
        }
    }
}
