package cn.lemonnetwork.lemonbedwars.Lobby.Menu;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.ItemUtil;
import cn.lemonnetwork.lemonbedwars.Utils.Menu.*;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Shop implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack cosmeticsItem = ItemUtil.CreateItem(Material.EMERALD, "§a起床战争菜单/商店", new ArrayList<>() {{
            add("§7硬币: §6" + PlayerDataManage.getPlayerCoins(player));
        }});
        player.getInventory().setItem(2, ItemUtil.tag(cosmeticsItem, "Menu", "BedWarsLobbyMenu"));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (!event.getAction().name().contains("CLICK")) return;
        String itemTag = ItemUtil.getTag(event.getItem(), "Menu", String.class);
        if (itemTag == null) return;
        if (itemTag.equalsIgnoreCase("BedWarsLobbyMenu")) {
            openMenu(player);
        }
    }

    private void openMenu(Player player) {
        InventoryBuilder builder = new InventoryBuilder("起床战争菜单/商店", 54);
            builder.addContentInfo(new ContentInfo(new InventoryButton("cosmetics") {
                @Override
                public ItemStack getItemStack() {
                    return new ItemEditor()
                            .material(Material.BED)
                            .amount(1)
                            .flags(ItemFlag.values())
                            .name("§a我的特效")
                            .lore(new ArrayList<>() {{
                                add("§7浏览已解锁的起床战争特效");
                                add("§7或直接用硬币购买");
                                add("§7你也可以打开§6战利宝箱");
                                add("§7解锁全新特效!");
                            }})
                            .tag("inventory button", "cosmetics").build();
                }

                @Override
                public void onClick() {
                    player.sendMessage("§c§l制作中...");
                }
            }, 15));

        builder.addContentInfo(new ContentInfo(new InventoryButton("close") {
            @Override
            public ItemStack getItemStack() {
                return new ItemEditor()
                        .material(Material.BARRIER)
                        .amount(1)
                        .flags(ItemFlag.values())
                        .name("§c关闭")
                        .tag("inventory button", "close").build();
            }

            @Override
            public void onClick() {
                player.closeInventory();
            }
        }, 49));


        builder.addContentInfo(new ContentInfo(new InventoryButton("coins") {
            @Override
            public ItemStack getItemStack() {
                return new ItemEditor()
                        .material(Material.EMERALD)
                        .amount(1)
                        .flags(ItemFlag.values())
                        .name("§7硬币: §6" + PlayerDataManage.getPlayerCoins(player))
                        .lore(new ArrayList<>() {{
                            add("§6https://store."+ LemonBedWars.serverip+"/");
                        }})
                        .tag("inventory button", "coins").build();
            }
        }, 50));
        InventorySession session = new InventorySession(player, builder) {
            @Override
            public void onStart() {

            }

            @Override
            public void onClose() {

            }
        };
        session.start();
    }
}
