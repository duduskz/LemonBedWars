package cn.lemonnetwork.lemonbedwars.API.Event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final String MapName;

    public GameStartEvent(String MapName) {
        this.MapName =  MapName;
    }


    public String getMapName() {
        return MapName;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
