package cn.lemonnetwork.lemonbedwars.API.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameKillEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Player deathPlayer;
    private final boolean isFinal;

    public GameKillEvent(Player player, Player deathPlayer, boolean isFinal) {
        this.isFinal = isFinal;
        this.player = player;
        this.deathPlayer = deathPlayer;
    }




    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getDeathPlayer() {
        return deathPlayer;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public Player getPlayer() {
        return player;
    }
}
