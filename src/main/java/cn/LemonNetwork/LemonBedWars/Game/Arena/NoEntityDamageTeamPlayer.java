package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NoEntityDamageTeamPlayer implements Listener {
    @EventHandler
    public void entity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            if (LemonBedWars.state.equalsIgnoreCase("Play")) {
                if (GameStart.getScoreboard().getEntryTeam(e.getDamager().getName()).getName().equalsIgnoreCase(GameStart.getScoreboard().getEntryTeam(e.getEntity().getName()).getName())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
