package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NoEntityDamageTeamPlayer implements Listener {
    @EventHandler
    public void entity(EntityDamageByEntityEvent e) {
        if (BedWars.state.equalsIgnoreCase("Play")) {
            if (GameStart.getScoreboard().getEntryTeam(e.getDamager().getName()).getName().equalsIgnoreCase(GameStart.getScoreboard().getEntryTeam(e.getEntity().getName()).getName())) {
                e.setCancelled(true);
            }
        }
    }
}
