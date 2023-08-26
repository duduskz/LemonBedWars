package cn.lemoncraft.bedwars.Game.Arena.Menu;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GamePlayer;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpectatorMenu {
    public static void open(Player player, int zhi){
        if (zhi == 1){
            int size = GamePlayer.getplayer().size();
            if (size <= 9) {
                size = 9;
            } else if (size <= 18) {
                size = 18;
            }
            Inventory inv = Bukkit.createInventory(null,size , "追踪玩家");
            for (String playername : GamePlayer.getplayer()){
                inv.addItem(Game.getSkull(Bukkit.getPlayer(playername)));
                BedWars.getPlugin(BedWars.class).getLogger().info("addItem:"+playername);
            }
            player.openInventory(inv);
        } else if (zhi == 2){
            Inventory inv = Bukkit.createInventory(null,36 , "旁观者设置");
            ItemStack clear = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemMeta meta1 = clear.getItemMeta();
            meta1.setDisplayName("§a关闭速度效果");
            meta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            clear.setItemMeta(meta1);
            inv.setItem(11,clear);
            ItemStack speed1 = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemMeta meta2 = speed1.getItemMeta();
            meta2.setDisplayName("§a速度 I");
            meta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            speed1.setItemMeta(meta2);
            inv.setItem(12,speed1);
            ItemStack speed2 = new ItemStack(Material.IRON_BOOTS, 1);
            ItemMeta meta3 = speed2.getItemMeta();
            meta3.setDisplayName("§a速度 II");
            meta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            speed2.setItemMeta(meta3);
            inv.setItem(13,speed2);
            ItemStack speed3 = new ItemStack(Material.GOLD_BOOTS, 1);
            ItemMeta meta4 = speed3.getItemMeta();
            meta4.setDisplayName("§a速度 III");
            meta4.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            speed3.setItemMeta(meta4);
            inv.setItem(14,speed3);
            ItemStack speed4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
            ItemMeta meta5 = speed4.getItemMeta();
            meta5.setDisplayName("§a速度 IV");
            meta5.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            speed4.setItemMeta(meta5);
            inv.setItem(15,speed4);
            if (PlayerDataManage.getSpectatorSettings(player, "Anto-First")){
                ItemStack item = new ItemStack(Material.WATCH, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§c停用第一人称旁观");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击使用指南针跟踪玩家时");
                lore.add("§7停用第一人称旁观");
                lore.add("§7你也可以右键点击一名玩家");
                lore.add("§7来启用第一人称旁观");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(25,item);
            } else {
                ItemStack item = new ItemStack(Material.WATCH, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§a启用第一人称旁观");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击使用指南针跟踪玩家时");
                lore.add("§7停用第一人称旁观");
                lore.add("§7你也可以右键点击一名玩家");
                lore.add("§7来启用第一人称旁观");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(25,item);
            }
            if (PlayerDataManage.getSpectatorSettings(player, "Night-Vision")){
                ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§c禁用夜视");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击禁用夜视！");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(23,item);
            } else {
                ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§a启用夜视");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击启用夜视！");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(23,item);
            }
            if (PlayerDataManage.getSpectatorSettings(player, "Hide-Other")){
                ItemStack item = new ItemStack(Material.GLOWSTONE_DUST, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§c隐藏旁观者");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击以隐藏其他旁观者");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(27,item);
            } else {
                ItemStack item = new ItemStack(Material.SULPHUR, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§a显示旁观者");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击以显示其他旁观者");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(27,item);
            }
            if (PlayerDataManage.getSpectatorSettings(player, "Anto-Teleport")){
                ItemStack item = new ItemStack(Material.COMPASS, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§a启用自动传送");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击启用自动传送");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(22,item);
            } else {
                ItemStack item = new ItemStack(Material.COMPASS, 1);
                ItemMeta meta6 = item.getItemMeta();
                meta6.setDisplayName("§a禁用自动传送");
                List<String> lore = new ArrayList<>();;
                lore.add("§7点击禁用自动传送");
                meta6.setLore(lore);
                meta6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta6);
                inv.setItem(22,item);
            }
            player.openInventory(inv);
        }

    }
}
