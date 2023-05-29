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
    public ItemStack getProtect(Player player) {
        ItemStack protect = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta protectmeta = protect.getItemMeta();
        String IIIIIIIII = "????";
        if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 0) {
            IIIIIIIII = "I";
        } else {
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                IIIIIIIII = "II";
            }
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                IIIIIIIII = "III";
            }
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 3) {
                IIIIIIIII = "IV";
            }
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 4) {
                IIIIIIIII = "IV";
            }
        }
        int candiamond;
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7己方所有成员的盔甲将获得永久保护附魔！");

        lore.add("");
        candiamond = 2;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 5;
        }
        lore.add("§71级:  保护I，§b" + candiamond + " 钻石");
        candiamond = 4;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 10;
        }
        lore.add("§72级:  保护II，§b" + candiamond + " 钻石");
        candiamond = 8;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 20;
        }
        lore.add("§73级:  保护III，§b" + candiamond + " 钻石");
        candiamond = 16;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 30;
        }
        lore.add("§74级:  保护IV，§b" + candiamond + " 钻石");
        lore.add("");
        if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 0) {
            candiamond = 2;
            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                candiamond = 5;
            }
        } else {
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                candiamond = 4;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 10;
                }
            }
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                candiamond = 8;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 20;
                }
            }
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 3) {
                candiamond = 16;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 30;
                }
            }
            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 4) {
                lore.add("§c该项已满级!");
            }
        }
        if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) != 4){
            if (player.getInventory().contains(Material.DIAMOND, candiamond)){
                lore.add("§e点击购买");
                protectmeta.setDisplayName("§a装备强化 "+IIIIIIIII);
            } else {
                lore.add("§c§c你没有足够的钻石购买此附魔!");
                protectmeta.setDisplayName("§C装备强化 "+IIIIIIIII);
            }
        }
        protectmeta.setLore(lore);
        protect.setItemMeta(protectmeta);
        return protect;
    }
    public ItemStack getSharp(Player player) {
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
        return sharp;
    }
    @EventHandler
    public void openshopmenu(NPCRightClickEvent e) {

        Player player = e.getClicker();
        if (e.getNPC().getName().equalsIgnoreCase("§c")) {
            ItemStack sharp = getSharp(e.getClicker());
            ItemStack protect = getProtect(e.getClicker());
            inv.setItem(10, sharp);
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
                if (e.getCurrentItem().getItemMeta().getLore().contains("§c你没有足够的钻石购买此附魔!")) {
                        player.sendMessage("§c你没有足够的钻石购买此附魔!");
                        return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a装备强化")) {
                    if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 4) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage("§c此附魔已满级!");
                    } else {
                        BedWars.protectUpgrade.replace(GameStart.getcoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(e.getWhoClicked().getName()).getName()) + 1);
                        String IIIIIIIII = "布吉岛";

                            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                                IIIIIIIII = "I";
                            }
                            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                                IIIIIIIII = "II";
                            }
                            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 3) {
                                IIIIIIIII = "III";
                            }
                            if (BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()) == 4) {
                                IIIIIIIII = "IV";
                            }
                        for (String forplayer : GameStart.getcoreboard().getEntryTeam(player.getName()).getEntries()) {
                            ItemMeta im = Bukkit.getPlayer(forplayer).getInventory().getBoots().getItemMeta();
                            im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getBoots().setItemMeta(im);

                            ItemMeta im1 = Bukkit.getPlayer(forplayer).getInventory().getLeggings().getItemMeta();
                            im1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getLeggings().setItemMeta(im);

                            ItemMeta im2 = Bukkit.getPlayer(forplayer).getInventory().getChestplate().getItemMeta();
                            im2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getChestplate().setItemMeta(im);

                            ItemMeta im3 = Bukkit.getPlayer(forplayer).getInventory().getHelmet().getItemMeta();
                            im3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, BedWars.protectUpgrade.get(GameStart.getcoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getHelmet().setItemMeta(im);

                            Bukkit.getPlayer(forplayer).sendMessage(BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().substring(0, 2) + player.getName() + " §a购买了 §6装备强化 "+IIIIIIIII);
                        }
                        ItemStack protect = getProtect(player);
                        player.getOpenInventory().setItem(e.getSlot(), protect);
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
                                            if (is.getType().equals(Material.WOOD_SWORD) || is.getType().equals(Material.STONE_SWORD) || is.getType().equals(Material.IRON_SWORD) || is.getType().equals(Material.DIAMOND_SWORD) || is.getType().equals(Material.WOOD_AXE) || is.getType().equals(Material.STONE_AXE) || is.getType().equals(Material.IRON_SWORD) || is.getType().equals(Material.DIAMOND_SWORD)) {
                                                ItemMeta im = is.getItemMeta();
                                                im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                                                is.setItemMeta(im);
                                            }
                                    } catch (NullPointerException error) {

                                    }
                                }
                            Bukkit.getPlayer(teamplayer).sendMessage(BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().substring(0, 2) + player.getName() + " §a购买了 §6锋利附魔");
                        }
                        ItemStack sharp = getSharp((Player) e.getWhoClicked());
                        int candiamond = 4;

                        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                            candiamond = 8;
                        }
                        player.getOpenInventory().setItem(e.getSlot(), sharp);
                        player.getInventory().remove(new ItemStack(Material.DIAMOND, candiamond));

                    }
                }
            }
        } catch (NullPointerException | IllegalArgumentException error) {
        }
    }
}
