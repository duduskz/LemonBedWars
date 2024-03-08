package cn.lemonnetwork.lemonbedwars.Game.Arena;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scoreboard.Team;

public class DragonTargetEvent implements Listener {
    @EventHandler
    public void onDragonTarget(EntityTargetEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            if (event.getTarget() instanceof Player) {
                Player targetPlayer = (Player) event.getTarget();
                for (Team team : GameStart.getScoreboard().getTeams()) {
                    if (!team.getName().equalsIgnoreCase("旁观者")) {
                        if (BedWarsListener.Dragon.get(team.getName()).contains(event.getEntity().getEntityId())) {
                            if (team.getEntries().contains(targetPlayer.getName())) {
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
