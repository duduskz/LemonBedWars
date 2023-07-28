package cn.lemoncraft.bedwars.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemUtil {
    public static ItemStack CreateItem(Material type, String disname, ArrayList<String> lore) {
        ItemStack item = new ItemStack(type);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(disname.replace("&", "§"));
        lore.replaceAll(s -> s.replace("&", "§"));
        im.setLore(lore);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        im.addItemFlags(ItemFlag.HIDE_DESTROYS);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(im);
        return item;
    }
    public static ItemStack CreateItem(Material type, String disname, ArrayList<String> lore, int data) {
        ItemStack item = new ItemStack(type, 1, (short) data);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(disname.replace("&", "§"));
        lore.replaceAll(s -> s.replace("&", "§"));
        im.setLore(lore);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        im.addItemFlags(ItemFlag.HIDE_DESTROYS);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(im);
        return item;
    }
}
