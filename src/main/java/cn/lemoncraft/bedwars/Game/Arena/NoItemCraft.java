package cn.lemoncraft.bedwars.Game.Arena;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class NoItemCraft implements Listener {
    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory craftingInventory = event.getInventory();
        ItemStack result = craftingInventory.getResult();
        if (result != null) {
            craftingInventory.setResult(null);
        }
    }
}
