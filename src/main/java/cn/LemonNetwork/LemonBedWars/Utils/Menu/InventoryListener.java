package cn.lemonnetwork.lemonbedwars.Utils.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        // Inventory Session Detector Start
        InventorySession inventorySession = InventorySession.Finder.findSession(player);
        if (inventorySession != null) {
            inventorySession.close(false);
        }
        // Inventory Session Detector End
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getItemMeta() == null) return;

        String buttonTag = ItemEditor.getTag(item, "inventory button", String.class);
        String sessionId = ItemEditor.getTag(item, "inventory session id", String.class);

        if (buttonTag == null) return;
        if (sessionId == null) return;

        InventorySession inventorySession = InventorySession.Finder.findSession(sessionId);
        if (inventorySession == null) return;

        for (ContentInfo info : inventorySession.getInventoryBuilder().getInfo()) {
            InventoryButton button = info.getButton();
            if (!ItemEditor.getTag(button.getItemStack(), "inventory button", String.class).equals(buttonTag)) continue;
            button.onClick();
            e.setCancelled(true);
            break;
        }
    }
}
