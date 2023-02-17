package cn.lemoncraft.bedwars.Item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Waiting {
    public ItemStack getItem(String itemName) {
        if (itemName.equalsIgnoreCase("返回大厅")) {
            ItemStack gamemenu = new ItemStack(Material.BED, 1);
            ItemMeta meta = gamemenu.getItemMeta();
            meta.setDisplayName("§c§l返回大厅 §7(右键点击)");
            ArrayList lore = new ArrayList();
            lore.add("§7右键返回至起床战争大厅");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            gamemenu.setItemMeta(meta);
            return gamemenu;
        }
        return null;
    }
}