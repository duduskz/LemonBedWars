package cn.lemonnetwork.lemonbedwars.Utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemUtil {
    public static ItemStack tag(ItemStack itemStack, String key, Object value) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tags = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagCompound lemonServer = tags.getCompound("_server_item_tag") == null ? new NBTTagCompound() : tags.getCompound("lemon_server_item_tag");
        if (value instanceof String) {
            lemonServer.setString(key, (String) value);
        }
        if (value instanceof Boolean) {
            lemonServer.setBoolean(key, (Boolean) value);
        }
        if (value instanceof Integer) {
            lemonServer.setInt(key, (Integer) value);
        }
        if (value instanceof Float) {
            lemonServer.setFloat(key, (Float) value);
        }
        if (value instanceof Double) {
            lemonServer.setDouble(key, (Double) value);
        }
        if (value instanceof Byte) {
            lemonServer.setByte(key, (Byte) value);
        }
        if (value instanceof Long) {
            lemonServer.setLong(key, (Long) value);
        }
        tags.set("lemon_server_item_tag", lemonServer);
        nmsItem.setTag(tags);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
    public static boolean hasTag(ItemStack item, String key) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tags = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagCompound lemonServer = tags.getCompound("lemon_server_item_tag") == null ? new NBTTagCompound() : tags.getCompound("lemon_server_item_tag");
        return lemonServer.hasKey(key);
    }

    public static <T> T getTag(ItemStack item, String key, Class<T> clazz) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tags = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagCompound lemonServer = tags.getCompound("lemon_server_item_tag") == null ? new NBTTagCompound() : tags.getCompound("lemon_server_item_tag");
        if (String.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getString(key));
        }
        if (Integer.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getInt(key));
        }
        if (Boolean.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getBoolean(key));
        }
        if (Float.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getFloat(key));
        }
        if (Double.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getDouble(key));
        }
        if (Byte.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getByte(key));
        }
        if (Long.class.isAssignableFrom(clazz)) {
            return clazz.cast(lemonServer.getLong(key));
        }

        throw new UnsupportedOperationException("Data type not supported");
    }
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

    public static ItemStack CreateItems(Material type, String disname, ArrayList<String> lore, int amount) {
        ItemStack item = new ItemStack(type, amount);
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
