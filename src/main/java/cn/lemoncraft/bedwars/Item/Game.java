package cn.lemoncraft.bedwars.Item;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static ItemStack getSkull(Player player){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        DecimalFormat dF = new DecimalFormat("0.000");
        int health = Integer.parseInt(dF.format((float) player.getHealth()/20)) * 100;
        SkullMeta skullmeta = (SkullMeta) itemStack.getItemMeta();
        skullmeta.setOwner(player.getName());
        skullmeta.setDisplayName(BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix()+player.getName());
        List<String> Lore = null;
        Lore.add("§7血量: §f" + health+"%");
        Lore.add("§7队伍: "+ GameStart.getcoreboard().getEntryTeam(player.getName()).getSuffix()+GameStart.getcoreboard().getEntryTeam(player.getName()).getName());
        Lore.add("");
        Lore.add("§7左键点击来旁观！");
        Lore.add("§7右键点击来举报！");
        skullmeta.setLore(Lore);
        itemStack.setItemMeta(skullmeta);
        return itemStack;
    }

    public ItemStack getItem(String itemName) {
        if (itemName.equalsIgnoreCase("追踪玩家")) {
            ItemStack xq = new ItemStack(Material.COMPASS, 1);
            ItemMeta meta1 = xq.getItemMeta();
            meta1.setDisplayName("§a追踪玩家 §7(右键点击)");
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            xq.setItemMeta(meta1);
            return xq;
        } else if (itemName.equalsIgnoreCase("旁观者设置")) {
            ItemStack jtb = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
            ItemMeta meta = jtb.getItemMeta();
            meta.setDisplayName("§a旁观者设置 §7(右键点击)");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            jtb.setItemMeta(meta);
            return jtb;
        } else if (itemName.equalsIgnoreCase("返回大厅")) {
            ItemStack gamemenu = new ItemStack(Material.BED, 1);
            ItemMeta meta = gamemenu.getItemMeta();
            meta.setDisplayName("§c§l返回大厅 §7(右键点击)");
            ArrayList lore = new ArrayList();
            lore.add("§7右键返回至起床战争大厅");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            gamemenu.setItemMeta(meta);
            return gamemenu;
        } else if (itemName.equalsIgnoreCase("指南针")) {
            ItemStack gamemenu = new ItemStack(Material.COMPASS, 1);
            ItemMeta meta = gamemenu.getItemMeta();
            meta.setDisplayName("§a指南针 §7(右键点击)");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            gamemenu.setItemMeta(meta);
            return gamemenu;
        }

        return null;
    }
}
