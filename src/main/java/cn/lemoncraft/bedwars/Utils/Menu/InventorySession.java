package cn.lemoncraft.bedwars.Utils.Menu;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class InventorySession {


    private final Player player;
    private final String sessionId;
    private final InventoryBuilder inventory;

    private boolean sessionStatus = false;

    public InventorySession(Player player, InventoryBuilder inventory) {
        this.player = player;
        this.inventory = inventory;
        sessionId = UUID.randomUUID().toString().substring(0, 8);
    }

    public Player getPlayer() {
        return player;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean getSessionStatus() {
        return sessionStatus;
    }

    public InventoryBuilder getInventoryBuilder() {
        return inventory;
    }

    public abstract void onStart();
    public abstract void onClose();

    public void start() {
        if (sessionStatus) return;
        InventorySession previousSession = Finder.findSession(player);
        if (previousSession != null) {
            close(false);
            //Logger.debug(plugin, "Closing the previous inventory session " + previousSession.getSessionId() + " for player " + previousSession.getPlayer().getName() + ".");
        }
        sessionStatus = true;
        BedWars.inventorySessions.add(this);
        onStart();

        Inventory inv = inventory.build();
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null || item.getItemMeta() == null || item.getType() == Material.AIR) continue;
            inv.setItem(i, new ItemEditor(item).tag("inventory session id", getSessionId()).build());
        }
        player.openInventory(inv);
        //Logger.debug(plugin, "Inventory session " + sessionId + " for player " + player.getName() + " has been started.");
    }

    public void close(boolean closeInv) {
        if (!sessionStatus) return;
        sessionStatus = false;
        onClose();
        BedWars.inventorySessions.remove(this);
        if (closeInv) player.closeInventory();
        //Logger.debug(plugin, "Inventory session " + sessionId + " for player " + player.getName() + " has been closed.");
    }

    public static class Finder {
        public static InventorySession findSession(Player player) {
            for (InventorySession session : BedWars.inventorySessions) {
                if (session.getPlayer().equals(player)) return session;
            }
            return null;
        }

        public static InventorySession findSession(String id) {
            for (InventorySession session : BedWars.inventorySessions) {
                if (session.getSessionId().equals(id)) return session;
            }
            return null;
        }
    }
}
