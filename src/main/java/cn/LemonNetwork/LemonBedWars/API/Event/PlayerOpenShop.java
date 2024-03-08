package cn.lemonnetwork.lemonbedwars.API.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerOpenShop extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String inventory;

    private int type;

    public PlayerOpenShop(Player player, String inventory, int type) {
        this.player = player;
        this.inventory = inventory;
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public String getInventory() {
        return inventory;
    }

    public int ShopType() { return type; }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
