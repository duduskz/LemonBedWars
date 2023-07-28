package cn.lemoncraft.bedwars.Game.Protect;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemListener implements Listener {
    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        try {

            if (event.getItemDrop().getItemStack().getType().equals(Material.IRON_BOOTS) || event.getItemDrop().getItemStack().getType().equals(Material.IRON_LEGGINGS)) {
                event.setCancelled(true);
            }
            if (event.getItemDrop().getItemStack().getType().equals(Material.DIAMOND_BOOTS) || event.getItemDrop().getItemStack().getType().equals(Material.DIAMOND_LEGGINGS)) {
                event.setCancelled(true);
            }
            if (event.getItemDrop().getItemStack().getType().equals(Material.LEATHER_BOOTS) || event.getItemDrop().getItemStack().getType().equals(Material.LEATHER_LEGGINGS)) {
                event.setCancelled(true);
            }
            if (event.getItemDrop().getItemStack().getType().equals(Material.CHAINMAIL_BOOTS) || event.getItemDrop().getItemStack().getType().equals(Material.CHAINMAIL_LEGGINGS) || event.getItemDrop().getItemStack().getType().equals(Material.LEATHER_CHESTPLATE) || event.getItemDrop().getItemStack().getType().equals(Material.LEATHER_HELMET)) {
                event.setCancelled(true);
            }
            if (event.getItemDrop().getItemStack().getType().equals(Material.COMPASS) || event.getItemDrop().getItemStack().getType().equals(Material.WOOD_SWORD)) {
                event.setCancelled(true);
            }
            if (event.getItemDrop().getItemStack().getType().equals(Material.STONE_SWORD) || event.getItemDrop().getItemStack().getType().equals(Material.IRON_SWORD) || event.getItemDrop().getItemStack().getType().equals(Material.DIAMOND_SWORD)) {
                if (!event.getPlayer().getInventory().contains(Material.WOOD_SWORD)) {
                    ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
                    ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
                    itemMeta.spigot().setUnbreakable(true);
                    WOOD_SWORD.setItemMeta(itemMeta);
                    event.getPlayer().getInventory().addItem(WOOD_SWORD);
                }
            }
        } catch (NullPointerException n) {

        }
    }

    @EventHandler
    public void cilck(InventoryClickEvent event) {
        try {

            if (event.getCurrentItem().getType().equals(Material.IRON_BOOTS) || event.getCurrentItem().getType().equals(Material.IRON_LEGGINGS)) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS) || event.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS)) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS) || event.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS)) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getType().equals(Material.CHAINMAIL_BOOTS) || event.getCurrentItem().getType().equals(Material.CHAINMAIL_LEGGINGS) || event.getCurrentItem().getType().equals(Material.LEATHER_CHESTPLATE) || event.getCurrentItem().getType().equals(Material.LEATHER_HELMET)) {
                event.setCancelled(true);
            }
        } catch (NullPointerException n) {

        }
    }
    @EventHandler
    public void pickup(PlayerPickupItemEvent e) {
        if (e.getItem().getItemStack().getType().equals(Material.STONE_SWORD) || e.getItem().getItemStack().getType().equals(Material.IRON_SWORD) || e.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)) {
            if (e.getItem().getItemStack().getType().equals(Material.WOOD_SWORD)) {

                e.getPlayer().getInventory().remove(Material.WOOD_SWORD);
                e.getPlayer().getInventory().remove(e.getItem().getItemStack());
                e.getPlayer().getInventory().addItem(e.getItem().getItemStack());
            }
        }

    }
}
