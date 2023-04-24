package cn.lemoncraft.bedwars.Game.Protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class NoMob implements Listener {
    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
}
