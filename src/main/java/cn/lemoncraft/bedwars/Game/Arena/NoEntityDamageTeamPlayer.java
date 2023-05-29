package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NoEntityDamageTeamPlayer implements Listener {
    @EventHandler
    public void entity(EntityDamageByEntityEvent e) {
        if (BedWars.state.equalsIgnoreCase("Play")) {
            if (GameStart.getcoreboard().getEntryTeam(e.getDamager().getName()).getName().equalsIgnoreCase(GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getName())) {
                e.setCancelled(true);
            }
        }
    }
}
