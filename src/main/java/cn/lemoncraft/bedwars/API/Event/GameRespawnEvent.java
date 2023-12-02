package cn.lemoncraft.bedwars.API.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameRespawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public GameRespawnEvent(Player player) {
        this.player = player;
    }




    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public Player getPlayer() {
        return player;
    }
}
