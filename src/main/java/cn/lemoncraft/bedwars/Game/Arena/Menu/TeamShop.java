package cn.lemoncraft.bedwars.Game.Arena.Menu;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TeamShop implements Listener {
    int protectLevel, forgeLevel;
    Inventory inv;
    ItemStack sharpness, protectDiamond, fastDig, forge;
    List<String> lores;
    String introduce, tip, 单挑Cost;//介绍，最下面的提示
    String cost[] = new String[5];//购买花费

    {
        lores = new ArrayList<>();
        inv = Bukkit.createInventory(null, 54, "升级与陷阱");
        sharpness = new ItemStack(Material.DIAMOND_SWORD);
        protectDiamond = new ItemStack(Material.DIAMOND_LEGGINGS);
        fastDig = new ItemStack(Material.GOLD_PICKAXE);
        forge = new ItemStack(Material.FURNACE);
    }
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    FileConfiguration config = plugin.getConfig();
    @EventHandler
    public void openshopmenu(NPCRightClickEvent e) {

        Player player = e.getClicker();
        if (e.getNPC().getName().equalsIgnoreCase("§c")) {
            ItemStack sharp = new ItemStack(Material.IRON_SWORD);
            ItemMeta sharpmeta = sharp.getItemMeta();
            int candiamond = 4;
            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                candiamond = 8;
            }
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7你队伍的所有成员的剑和斧将永久获得锋利I附魔");
            lore.add("");
            lore.add("§7花费: §b" + candiamond + " 钻石");
            lore.add("");
            if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                if (BedWars.sharp.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName())) {
                    sharpmeta.setDisplayName("§a锋利附魔");
                    lore.add("§a你已经购买了此附魔!");
                } else {
                    sharpmeta.setDisplayName("§a锋利附魔");
                    lore.add("§e点击购买");
                }

            } else {

                if (BedWars.sharp.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName())) {
                    lore.add("§a你已经购买了此附魔!");
                    sharpmeta.setDisplayName("§a锋利附魔");
                } else {
                    sharpmeta.setDisplayName("§c锋利附魔");
                    lore.add("§c你没有足够的钻石购买此附魔!");
                }

            }
            sharpmeta.setLore(lore);
            sharp.setItemMeta(sharpmeta);

            inv.setItem(10, sharp);
            ItemStack protect = new ItemStack(Material.IRON_CHESTPLATE);
            ItemMeta protectmeta = protect.getItemMeta();

            inv.setItem(11, protect);
            inv.setItem(19, forge);
            player.openInventory(inv);
        }
    }

    @EventHandler
    public void onInv(InventoryClickEvent e) {
        try {


            if (e.getView().getTitle().equals("升级与陷阱")) {
                Player player = (Player) e.getWhoClicked();
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c锋利附魔")) {
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    if (BedWars.sharp.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName())) {
                        player.sendMessage("§c你已经购买了此附魔!");
                    } else {
                        player.sendMessage("§c你没有足够的钻石购买此附魔!");

                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a锋利附魔")) {
                    if (BedWars.sharp.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName())) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage("§c你已经购买了此附魔!");
                    } else {
                        BedWars.sharp.replace(GameStart.getcoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), true);
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                        for (String teamplayer : GameStart.getcoreboard().getEntryTeam(player.getName()).getEntries()) {
                                for (ItemStack is : Bukkit.getPlayer(teamplayer).getInventory().getContents()) {
                                    try {
                                        if (is != null) {
                                            if (is.getType().equals(Material.WOOD_SWORD) || is.getType().equals(Material.STONE_SWORD) || is.getType().equals(Material.IRON_SWORD) || is.getType().equals(Material.DIAMOND_SWORD) || is.getType().equals(Material.WOOD_AXE) || is.getType().equals(Material.STONE_AXE) || is.getType().equals(Material.IRON_SWORD) || is.getType().equals(Material.DIAMOND_SWORD)) {
                                                ItemMeta im = is.getItemMeta();
                                                im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                                            }
                                        }
                                    } catch (NullPointerException error) {

                                    }
                                }
                            Bukkit.getPlayer(teamplayer).sendMessage(BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().substring(0, 2) + player.getName() + " §a购买了 §6锋利附魔");
                        }
                        ItemStack sharp = new ItemStack(Material.IRON_SWORD);
                        ItemMeta sharpmeta = sharp.getItemMeta();
                        int candiamond = 4;

                        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                            candiamond = 8;
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        sharpmeta.setDisplayName("§a锋利附魔");
                        lore.add("§7你队伍的所有成员的剑和斧将永久获得锋利I附魔");
                        lore.add("");
                        lore.add("§7花费: §b" + candiamond + " 钻石");
                        lore.add("");
                        lore.add("§a你已经购买了此附魔!");
                        sharpmeta.setLore(lore);
                        sharp.setItemMeta(sharpmeta);
                        player.getOpenInventory().setItem(e.getSlot(), sharp);
                        player.getInventory().remove(new ItemStack(Material.DIAMOND, candiamond));
                    }
                }
            }
        } catch (NullPointerException | IllegalArgumentException error) {
        }
    }
}
