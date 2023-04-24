package cn.lemoncraft.bedwars.Game.Arena.Menu;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Quest implements Listener {
    @EventHandler
    public void openinv(NPCRightClickEvent e){
        if (e.getNPC().getName().equalsIgnoreCase("§b")) {
            Inventory inv = Bukkit.createInventory(null, 45, "起床战争任务");
            ItemStack bed = new ItemStack(Material.BED);
            ItemMeta bedmeta = bed.getItemMeta();
            bedmeta.setDisplayName("§a起床战争任务");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7查看所有在起床战争中可用的任务和挑战 §c(制作中)");
            bedmeta.setLore(lore);
            bed.setItemMeta(bedmeta);
            inv.setItem(4,bed);
            e.getClicker().openInventory(inv);
        }
    }
}
