package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.Utils.ItemUtil;
import cn.lemonnetwork.lemonbedwars.Utils.Menu.ItemEditor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PickupItemClearTag implements Listener {
    @EventHandler
    public void pickup(PlayerPickupItemEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem().getItemStack().clone();
        String tag = ItemUtil.getTag(event.getItem().getItemStack(), "item", String.class);
        if (tag == null) return;
        event.setCancelled(true);
        event.getItem().remove();
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_PICKUP, 1, 1);
        event.getPlayer().getInventory().addItem(new ItemEditor()
                .name(item.getItemMeta().getDisplayName())
                .lore(item.getItemMeta().getLore())
                .amount(item.getAmount())
                .material(item.getType())
                .durability(item.getDurability())
                .build());
//        if (tag.equalsIgnoreCase("emeraldGeneratorItem")) ItemUtil.tag(item.getItemStack(), "item", null);
//        if (tag.equalsIgnoreCase("diamondGeneratorItem")) ItemUtil.tag(item.getItemStack(), "item", null);
//        if (tag.equalsIgnoreCase("teamGeneratorItem")) ItemUtil.tag(item.getItemStack(), "item", null);
    }
}
