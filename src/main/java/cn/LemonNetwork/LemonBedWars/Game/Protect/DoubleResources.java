package cn.lemonnetwork.lemonbedwars.Game.Protect;

import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import cn.lemonnetwork.lemonbedwars.Utils.ItemUtil;
import cn.lemonnetwork.lemonbedwars.Utils.Menu.ItemEditor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DoubleResources implements Listener {
    @EventHandler
    public void doubleresource(PlayerPickupItemEvent e) {
        ItemStack item = e.getItem().getItemStack();
        String tag = ItemUtil.getTag(item, "item", String.class);
        if (tag != null) {
            if (tag.equalsIgnoreCase("teamGeneratorItem")) {
                if (e.getItem().getItemStack().getType().equals(Material.IRON_INGOT) || e.getItem().getItemStack().getType().equals(Material.GOLD_INGOT)) {
                    double radiusSquared = 3 * 2;

                    List<Entity> entities = e.getPlayer().getNearbyEntities(2, 1, 2); // All entities withing a box
                    for (Entity entity : entities) {

                        if (entity.getLocation().distanceSquared(e.getPlayer().getLocation()) > radiusSquared)
                            continue; // All entities within a sphere

                        if (entity instanceof Player) {
                            if (!GameStart.getScoreboard().getEntryTeam(entity.getName()).getName().equalsIgnoreCase("旁观者")) {
                                ((Player) entity).getInventory().addItem(new ItemEditor()
                                        .name(item.getItemMeta().getDisplayName())
                                        .lore(item.getItemMeta().getLore())
                                        .amount(item.getAmount())
                                        .material(item.getType())
                                        .durability(item.getDurability())
                                        .build());
                            }

                        }

                    }
                }
            }
        }
    }
}
