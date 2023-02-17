package cn.lemoncraft.bedwars.Menu;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class bwmenuListener implements Listener {
    @EventHandler
    public void useitem(InventoryClickEvent event) {

        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getTitle().equalsIgnoreCase("游玩起床战争")) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§c关闭")) {
                event.setCancelled(false);
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§c点击这里重新加入！")) {
                event.setCancelled(false);
                player.performCommand("rejoin");
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§a练习")) {
                event.setCancelled(false);
                player.performCommand("bwp");
            }
            if (event.getCurrentItem().getType() == Material.BED) {
                String strInfo = event.getCurrentItem().getItemMeta().getDisplayName();
                String group = strInfo.substring(strInfo.indexOf("(") + 1, strInfo.indexOf(")"));
                player.sendMessage("§a正在传送至 起床战争" + group);
                player.performCommand("bw join " + group);
                event.setCancelled(false);
                player.closeInventory();
            }
            if (event.getCurrentItem().getType() == Material.SIGN) {
                String strInfo = event.getCurrentItem().getItemMeta().getDisplayName();
                String group = strInfo.substring(strInfo.indexOf("(") + 1, strInfo.indexOf(")"));
                player.performCommand("bw mapsel " + group);
                event.setCancelled(false);
                player.closeInventory();
            }
        }
    }
}