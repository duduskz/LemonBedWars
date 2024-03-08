package cn.lemonnetwork.lemonbedwars.API.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

public class GameBreakBedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Team bedTeam;

    public GameBreakBedEvent(Player player, Team bedTeam) {
        this.player = player;
        this.bedTeam = bedTeam;
    }




    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Team getBedTeam() {
        return bedTeam;
    }

    public Player getPlayer() {
        return player;
    }
}
