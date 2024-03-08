package cn.lemonnetwork.lemonbedwars.Menu;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Random;

public class bwmenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§c你不能在控制台输入此命令!");
        }

        if (sender instanceof Player) {
            Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
            FileConfiguration config = plugin.getConfig();
            Player player = (Player) sender;

            if (args.length == 1) {

                Inventory shop = Bukkit.createInventory(player, 36, "游玩起床战争");
                ItemStack play = new ItemStack(Material.BED);
                ItemMeta play_meta = play.getItemMeta();
                play_meta.setDisplayName("§a起床战争 (" + args[0] + ")");
                ArrayList<String> play_meta_lore = new ArrayList();
                play_meta_lore.add("§7游玩起床战争 " + args[0]);
                play_meta_lore.add("");
                play_meta_lore.add("§e点击游玩！");
                play_meta.setLore(play_meta_lore);
                play.setItemMeta(play_meta);
                ItemStack mapsel = new ItemStack(Material.SIGN);
                ItemMeta mapsel_meta = mapsel.getItemMeta();
                mapsel_meta.setDisplayName("§e地图选择器 ("+args[0]+")");
                ArrayList<String> mapsel_meta_lore = new ArrayList();
                mapsel_meta_lore.add("§7在可用的地图中挑选一张游玩。");
                mapsel_meta_lore.add("");
                mapsel_meta_lore.add("§e点击选择！");
                mapsel_meta.setLore(mapsel_meta_lore);
                mapsel.setItemMeta(mapsel_meta);
                Random random = new Random();
                int suijiid = random.nextInt(16);
                ItemStack Practice = new ItemStack(Material.WOOL,1,(short)suijiid);
                ItemMeta Practice_meta = Practice.getItemMeta();

                Practice_meta.setDisplayName("§a练习");
                ArrayList<String> Practice_meta_lore = new ArrayList();
                Practice_meta_lore.add("§7练习起床战争各个技巧来提高你的实力！");
                Practice_meta_lore.add("");
                Practice_meta_lore.add("§e点击选择模式！");
                Practice_meta.setLore(Practice_meta_lore);
                Practice.setItemMeta(Practice_meta);
                ItemStack close = new ItemStack(Material.BARRIER);
                ItemMeta close_meta = close.getItemMeta();
                close_meta.setDisplayName("§c关闭");
                close.setItemMeta(close_meta);
                ItemStack rejoin = new ItemStack(Material.ENDER_PEARL);
                ItemMeta rejoin_meta = rejoin.getItemMeta();
                rejoin_meta.setDisplayName("§c点击这里重新加入！");
                ArrayList<String> rejoin_meta_lore = new ArrayList();
                rejoin_meta_lore.add("§7如果你掉线了，点击这里或输入/rejoin来重新回到游戏.");
                rejoin_meta.setLore(rejoin_meta_lore);
                rejoin.setItemMeta(rejoin_meta);
                shop.setItem(12, play);
                shop.setItem(14, mapsel);
                shop.setItem(27, Practice);
                shop.setItem(31, close);
                shop.setItem(35, rejoin);
                player.openInventory(shop);
            }
        }

        return false;
    }
}
