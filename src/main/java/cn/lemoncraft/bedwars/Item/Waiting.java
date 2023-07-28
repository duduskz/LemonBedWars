package cn.lemoncraft.bedwars.Item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Waiting {
    public ItemStack getItem(String itemName, String lang) {
        if (itemName.equalsIgnoreCase("返回大厅")) {
            ItemStack gamemenu = new ItemStack(Material.BED, 1);
            ItemMeta meta = gamemenu.getItemMeta();
            ArrayList lore = new ArrayList();
            if (lang.equalsIgnoreCase("zhcn")) {
                meta.setDisplayName("§c§l返回大厅 §7(右键点击)");
                lore.add("§7右键返回至起床战争大厅");
            } else if (lang.equalsIgnoreCase("en")){
                meta.setDisplayName("§c§lRuturn to Lobby §7(Right Click)");
                lore.add("§7Right-click to leave to the lobby!");
            }

            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            gamemenu.setItemMeta(meta);
            return gamemenu;
        }
        return null;
    }
}