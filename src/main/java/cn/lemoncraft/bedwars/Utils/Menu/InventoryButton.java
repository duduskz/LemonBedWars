package cn.lemoncraft.bedwars.Utils.Menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class InventoryButton {

    private final ItemStack buttonItem;

    public InventoryButton(String buttonTag) {
        this.buttonItem = new ItemEditor()
                .material(Material.STAINED_GLASS_PANE)
                .amount(1)
                .durability(14)
                .name(ChatColor.RED + ChatColor.BOLD.toString() + "NO OVERRIDES!")
                .lore(Collections.singletonList(ChatColor.GRAY + "There are no data overrides on this button."))
                .flags(ItemFlag.values())
                .cursorPickup(false)
                .tag("inventory button", buttonTag)
                .build();
    }

    public ItemStack getItemStack() {
        return buttonItem;
    }

    public void onClick() {
    }
}
