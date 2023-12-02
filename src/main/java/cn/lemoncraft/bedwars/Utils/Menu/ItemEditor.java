package cn.lemoncraft.bedwars.Utils.Menu;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;
import java.util.List;

public class ItemEditor {

    private ItemStack item;
    private final ItemMeta meta;

    public ItemEditor(ItemStack item) {
        this.item = item;
        meta = item.getItemMeta();
    }

    public ItemEditor() {
        this(new ItemStack(Material.BARRIER));
    }

    public ItemEditor name(String name) {
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemEditor material(Material material) {
        item.setType(material);
        return this;
    }

    public ItemEditor amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemEditor durability(int durability) {
        item.setDurability((short) durability);
        return this;
    }

    public ItemEditor lore(List<String> lore) {
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemEditor enchant(Enchantment enchantment, int level) {
        if (enchantment == null) return this;
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemEditor disenchant(Enchantment... enchantments) {
        if (enchantments == null) {
            for (Enchantment enchantment : meta.getEnchants().keySet()) {
                meta.removeEnchant(enchantment);
            }
        } else {
            for (Enchantment enchantment : enchantments) {
                meta.removeEnchant(enchantment);
            }
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemEditor glow(boolean b) {
        if (b) {
            enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        } else {
            disenchant();
        }
        return this;
    }

    /**
     * This method MUST be used as the last option.
     */
    public ItemEditor skull(GameProfile profile) {
        material(Material.SKULL_ITEM);
        durability(3);
        SkullMeta sm = (SkullMeta) item.getItemMeta();
        try {
            Field profileField = sm.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(sm, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            //Logger.logThrowable(MasterControl.getInstance(), e, "error while reflecting for skull profile");
        }
        item.setItemMeta(sm);
        return this;
    }

    /**
     * This method MUST be used as the last option.
     */
    public ItemEditor leatherColor(Color color) {
        LeatherArmorMeta am = (LeatherArmorMeta) item.getItemMeta();
        am.setColor(color);
        item.setItemMeta(am);
        return this;
    }

    public ItemEditor bedWarsBlockColor(Team team) {
        int woolcolor = 0;
        if (team.getName().equalsIgnoreCase("红队")) {
            woolcolor = 14;
        }
        if (team.getName().equalsIgnoreCase("蓝队")) {
            woolcolor = 11;
        }
        if (team.getName().equalsIgnoreCase("绿队")) {
            woolcolor = 5;
        }
        if (team.getName().equalsIgnoreCase("黄队")) {
            woolcolor = 4;
        }
        if (team.getName().equalsIgnoreCase("白队")) {
            woolcolor = 0;
        }
        if (team.getName().equalsIgnoreCase("青队")) {
            woolcolor = 3;
        }
        if (team.getName().equalsIgnoreCase("粉队")) {
            woolcolor = 6;
        }
        if (team.getName().equalsIgnoreCase("灰队")) {
            woolcolor = 8;
        }
        item.setDurability((short) woolcolor);
        return this;
    }

    public ItemEditor flags(ItemFlag... flag) {
        if (flag == null) {
            meta.addItemFlags(ItemFlag.values());
        } else {
            meta.addItemFlags(flag);
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemEditor unbreakable() {
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemEditor cursorPickup(boolean b) {
        tag("cursor pickup", b);
        return this;
    }

    public ItemEditor droppable(boolean b) {
        tag("droppable", b);
        return this;
    }

    public ItemEditor tag(String key, Object value) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tags = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagCompound serverInventory = tags.getCompound("MASTERCONTROL_INVENTORY_ITEM") == null ? new NBTTagCompound() : tags.getCompound("MASTERCONTROL_INVENTORY_ITEM");
        if (value instanceof String) {
            serverInventory.setString(key, (String) value);
        }
        if (value instanceof Boolean) {
            serverInventory.setBoolean(key, (Boolean) value);
        }
        if (value instanceof Integer) {
            serverInventory.setInt(key, (Integer) value);
        }
        if (value instanceof Float) {
            serverInventory.setFloat(key, (Float) value);
        }
        if (value instanceof Double) {
            serverInventory.setDouble(key, (Double) value);
        }
        if (value instanceof Byte) {
            serverInventory.setByte(key, (Byte) value);
        }
        if (value instanceof Long) {
            serverInventory.setLong(key, (Long) value);
        }
        tags.set("MASTERCONTROL_INVENTORY_ITEM", serverInventory);
        nmsItem.setTag(tags);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    public ItemStack build() {
        return item;
    }

    public static boolean hasTag(ItemStack item, String key) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tags = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagCompound serverInventory = tags.getCompound("MASTERCONTROL_INVENTORY_ITEM") == null ? new NBTTagCompound() : tags.getCompound("MASTERCONTROL_INVENTORY_ITEM");
        return serverInventory.hasKey(key);
    }

    public static <T> T getTag(ItemStack item, String key, Class<T> clazz) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tags = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
        NBTTagCompound serverInventory = tags.getCompound("MASTERCONTROL_INVENTORY_ITEM") == null ? new NBTTagCompound() : tags.getCompound("MASTERCONTROL_INVENTORY_ITEM");
        if (String.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getString(key));
        }
        if (Integer.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getInt(key));
        }
        if (Boolean.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getBoolean(key));
        }
        if (Float.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getFloat(key));
        }
        if (Double.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getDouble(key));
        }
        if (Byte.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getByte(key));
        }
        if (Long.class.isAssignableFrom(clazz)) {
            return clazz.cast(serverInventory.getLong(key));
        }

        throw new UnsupportedOperationException("data type not supported");
    }


}
