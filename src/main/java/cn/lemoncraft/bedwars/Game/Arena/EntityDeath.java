package cn.lemoncraft.bedwars.Game.Arena;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void EntityDeathe(EntityDeathEvent e){
        if (e.getEntity().getType() == EntityType.IRON_GOLEM) {
            if (e.getEntity().getKiller() != null) {
                Bukkit.broadcastMessage(e.getEntity().getCustomName().substring(0, 3) + " 的铁傀儡 §7被 " + GameStart.getcoreboard().getEntryTeam(e.getEntity().getKiller().getName()).getSuffix() + e.getEntity().getKiller().getName() + " §7击败了!");
            }
        }
        if (e.getEntity().getType() == EntityType.ENDER_DRAGON){
            for (Entity e1 : e.getEntity().getWorld().getEntities()){
                if (e1.getType() == EntityType.ENDER_DRAGON){
                    e1.remove();
                }
            }
        }
    }
}
