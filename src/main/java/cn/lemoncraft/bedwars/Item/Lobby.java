package cn.lemoncraft.bedwars.Item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Lobby {
    public ItemStack getItem(String itemName) {
        if (itemName.equalsIgnoreCase("游戏菜单")) {
            ItemStack gamemenu = new ItemStack(Material.COMPASS, 1);
            ItemMeta meta = gamemenu.getItemMeta();
            meta.setDisplayName("§a游戏菜单 §7(右键点击)");
            ArrayList lore = new ArrayList();
            lore.add("§a右键打开游戏菜单");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            gamemenu.setItemMeta(meta);
            return gamemenu;
        } else if (itemName.equalsIgnoreCase("个人档案")) {
            ItemStack xq = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            ItemMeta meta1 = xq.getItemMeta();
            meta1.setDisplayName("§b个人档案 §7(右键点击)");
            ArrayList lore = new ArrayList();
            lore.add("§a右键打开个人档案菜单");


            meta1.setLore(lore);
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            xq.setItemMeta(meta1);
            return xq;
        } else if (itemName.equalsIgnoreCase("商店与特效")) {
            ItemStack xq = new ItemStack(Material.EMERALD, 1);
            ItemMeta meta1 = xq.getItemMeta();
            meta1.setDisplayName("§6商店与特效 §7(右键点击)");
            ArrayList lore = new ArrayList();
            lore.add("§a右键打开商店与特效菜单");
            meta1.setLore(lore);
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            xq.setItemMeta(meta1);
            return xq;
        } else if (itemName.equalsIgnoreCase("选择大厅")) {
            ItemStack jtb = new ItemStack(Material.NETHER_STAR, 1);
            ItemMeta meta = jtb.getItemMeta();
            meta.setDisplayName("§e选择大厅 §7(右键点击)");
            ArrayList lore = new ArrayList();
            lore.add("§a右键打开选择大厅菜单");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            jtb.setItemMeta(meta);
            return jtb;
        }
    return null;
    }
}