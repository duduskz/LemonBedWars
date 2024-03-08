package cn.lemonnetwork.lemonbedwars.Game.Protect;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class NoMob implements Listener {
    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND) && !e.getEntity().getType().equals(EntityType.IRON_GOLEM)) {
            e.setCancelled(true);
        }
    }
}
