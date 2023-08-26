package cn.lemoncraft.bedwars.Item;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShopItem {

    public Boolean ifquick(String itemname, ResultSet rs) {
        try {
            return rs.getString("i" + "19").equalsIgnoreCase(itemname) || rs.getString("i" + "20").equalsIgnoreCase(itemname) || rs.getString("i" + "21").equalsIgnoreCase(itemname) || rs.getString("i" + "22").equalsIgnoreCase(itemname) || rs.getString("i" + "23").equalsIgnoreCase(itemname) || rs.getString("i" + "24").equalsIgnoreCase(itemname) || rs.getString("i" + "25").equalsIgnoreCase(itemname) || rs.getString("i" + "28").equalsIgnoreCase(itemname) || rs.getString("i" + "29").equalsIgnoreCase(itemname) || rs.getString("i" + "30").equalsIgnoreCase(itemname) || rs.getString("i" + "31").equalsIgnoreCase(itemname) || rs.getString("i" + "32").equalsIgnoreCase(itemname) || rs.getString("i" + "33").equalsIgnoreCase(itemname) || rs.getString("i" + "34").equalsIgnoreCase(itemname) || rs.getString("i" + "37").equalsIgnoreCase(itemname) || rs.getString("i" + "38").equalsIgnoreCase(itemname) || rs.getString("i" + "39").equalsIgnoreCase(itemname) || rs.getString("i" + "40").equalsIgnoreCase(itemname) || rs.getString("i" + "41").equalsIgnoreCase(itemname) || rs.getString("i" + "42").equalsIgnoreCase(itemname) || rs.getString("i" + "43").equalsIgnoreCase(itemname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ItemStack getItem(String itemName, Player player) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_shop WHERE uuid = '" + player.getUniqueId().toString() + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if (itemName.equalsIgnoreCase("空")) {
                if (player.getOpenInventory().getTitle().contains("添加快捷购买")) {
                    ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("§c空槽!");
                    ArrayList lore = new ArrayList();
                    lore.add("§7这是一个快速购买空槽!");
                    lore.add("§b单击此处 §7将需要添加的物品");
                    lore.add("§7将它添加到这里.");
                    meta.setLore(lore);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                } else {
                    ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("§c空槽!");
                    ArrayList lore = new ArrayList();
                    lore.add("§7这是一个快速购买空槽!");
                    lore.add("§bShift+左键 §7商店中的物品!");
                    lore.add("§7来将它添加到这里.");
                    meta.setLore(lore);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
            }
            if (itemName.equalsIgnoreCase("羊毛")) {
                ItemStack item = new ItemStack(Material.WOOL, 16);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 4)) {
                    meta.setDisplayName("§a羊毛");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f4 铁锭");
                    lore.add("");
                    lore.add("§7非常适合在各岛屿间搭桥使用");
                    lore.add("");
                    if (!ifquick("羊毛", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c羊毛");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f4 铁锭");
                    lore.add("");
                    lore.add("§7非常适合在各岛屿间搭桥使用");
                    lore.add("");
                    if (!ifquick("羊毛", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("石剑")) {
                ItemStack item = new ItemStack(Material.STONE_SWORD, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 10)) {
                    meta.setDisplayName("§a石剑");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f10 铁锭");
                    lore.add("");
                    if (!ifquick("石剑", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c石剑");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f10 铁锭");
                    lore.add("");
                    if (!ifquick("石剑", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("锁链护甲")) {
                ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 35)) {
                    meta.setDisplayName("§a永久的锁链护甲");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f35 铁锭");
                    lore.add("");
                    lore.add("§7锁链护腿和锁链靴子");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("锁链护甲", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    if (player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
                        lore.add("§e点击购买");
                    } else {
                        if (player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                            lore.add("§a已拥有");
                        } else {
                            lore.add("§a已拥有更高等级的护甲");
                        }
                    }
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c永久的锁链护甲");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f35 铁锭");
                    lore.add("");
                    lore.add("§7锁链护腿和锁链靴子");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("锁链护甲", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("镐")) {

                if (BedWars.pickaxe.get(player.getName()) == 0) {
                    ItemStack item = new ItemStack(Material.WOOD_PICKAXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.IRON_INGOT, 10)) {
                        meta.setDisplayName("§a木镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c木镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 铁锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.pickaxe.get(player.getName()) == 1) {
                    ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.IRON_INGOT, 10)) {
                        meta.setDisplayName("§a铁镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c铁镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 铁锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.pickaxe.get(player.getName()) == 2) {
                    ItemStack item = new ItemStack(Material.GOLD_PICKAXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.GOLD_INGOT, 3)) {
                        meta.setDisplayName("§a金镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §63 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c金镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §63 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 金锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.pickaxe.get(player.getName()) == 3) {
                    ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.GOLD_INGOT, 6)) {
                        meta.setDisplayName("§a钻石镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §66 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c钻石镐");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §66 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("镐", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 金锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.pickaxe.get(player.getName()) == 4) {
                    ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("§a钻石镐");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §66 金锭");
                    lore.add("");
                    lore.add("§7这是一个可升级的工具");
                    lore.add("§7每次死亡都会减少一级");
                    lore.add("§7直到第一级不会减少");
                    lore.add("");
                    if (!ifquick("镐", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§a已满级");
                    meta.setLore(lore);
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }

            }
            if (itemName.equalsIgnoreCase("斧")) {

                if (BedWars.axe.get(player.getName()) == 0) {
                    ItemStack item = new ItemStack(Material.WOOD_AXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.IRON_INGOT, 10)) {
                        meta.setDisplayName("§a木斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c木斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 铁锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.axe.get(player.getName()) == 1) {
                    ItemStack item = new ItemStack(Material.STONE_AXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.IRON_INGOT, 10)) {
                        meta.setDisplayName("§a石斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c石斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §f10 铁锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 铁锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.axe.get(player.getName()) == 2) {
                    ItemStack item = new ItemStack(Material.IRON_AXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.GOLD_INGOT, 3)) {
                        meta.setDisplayName("§a铁斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §63 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c铁斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §63 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 金锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.axe.get(player.getName()) == 3) {
                    ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    if (player.getInventory().contains(Material.GOLD_INGOT, 6)) {
                        meta.setDisplayName("§a钻石斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §66 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c钻石斧");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §66 金锭");
                        lore.add("");
                        lore.add("§7这是一个可升级的工具");
                        lore.add("§7每次死亡都会减少一级");
                        lore.add("§7直到第一级不会减少");
                        lore.add("");
                        if (!ifquick("斧", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 金锭");
                        meta.setLore(lore);
                    }
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
                if (BedWars.axe.get(player.getName()) == 4) {
                    ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("§a钻石斧");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §66 金锭");
                    lore.add("");
                    lore.add("§7这是一个可升级的工具");
                    lore.add("§7每次死亡都会减少一级");
                    lore.add("§7直到第一级不会减少");
                    lore.add("");
                    if (!ifquick("斧", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§a已满级");
                    meta.setLore(lore);
                    meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }

            }
            if (itemName.equalsIgnoreCase("TNT")) {
                ItemStack item = new ItemStack(Material.TNT, 1);
                ItemMeta meta = item.getItemMeta();
                if (config.getString("Map.ModeType").equalsIgnoreCase("单挑") || config.getString("Map.ModeType").equalsIgnoreCase("双人")) {
                    if (player.getInventory().contains(Material.GOLD_INGOT, 4)) {
                        meta.setDisplayName("§aTNT");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §64 金锭");
                        lore.add("");
                        if (!ifquick("TNT", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§cTNT");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §64 金锭");
                        lore.add("");
                        if (!ifquick("TNT", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 金锭");
                        meta.setLore(lore);
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                } else {

                    if (player.getInventory().contains(Material.GOLD_INGOT, 8)) {
                        meta.setDisplayName("§aTNT");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §68 金锭");
                        lore.add("");
                        if (!ifquick("TNT", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§cTNT");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §68 金锭");
                        lore.add("");
                        if (!ifquick("TNT", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 金锭");
                        meta.setLore(lore);
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
            }
            if (itemName.equalsIgnoreCase("金苹果")) {
                ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 3)) {
                    meta.setDisplayName("§a金苹果");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §63 金锭");
                    lore.add("");
                    if (!ifquick("金苹果", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c金苹果");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f3 金锭");
                    lore.add("");
                    if (!ifquick("金苹果", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("硬化粘土")) {
                ItemStack item = new ItemStack(Material.STAINED_CLAY, 16, (short) 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 12)) {
                    meta.setDisplayName("§a硬化粘土");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f12 铁锭");
                    lore.add("");
                    lore.add("§7保护你的床的最基础的方块.");
                    lore.add("");
                    if (!ifquick("硬化粘土", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c硬化粘土");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f12 铁锭");
                    lore.add("");
                    lore.add("§7保护你的床的最基础的方块.");
                    lore.add("");
                    if (!ifquick("硬化粘土", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("防爆玻璃")) {
                ItemStack item = new ItemStack(Material.GLASS, 4);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 12)) {
                    meta.setDisplayName("§a防爆玻璃");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f12 铁锭");
                    lore.add("");
                    lore.add("§7可以用来免疫爆炸.");
                    lore.add("");
                    if (!ifquick("防爆玻璃", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c防爆玻璃");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f12 铁锭");
                    lore.add("");
                    lore.add("§7可以用来免疫爆炸.");
                    lore.add("");
                    if (!ifquick("防爆玻璃", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("末地石")) {
                ItemStack item = new ItemStack(Material.ENDER_STONE, 16);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 24)) {
                    meta.setDisplayName("§a末地石");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f24 铁锭");
                    lore.add("");
                    lore.add("§7保护你的床的坚固方块.");
                    lore.add("");
                    if (!ifquick("末地石", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c末地石");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f24 铁锭");
                    lore.add("");
                    lore.add("§7保护你的床的坚固方块.");
                    lore.add("");
                    if (!ifquick("末地石", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("梯子")) {
                ItemStack item = new ItemStack(Material.LADDER, 16);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 4)) {
                    meta.setDisplayName("§a梯子");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f4 铁锭");
                    lore.add("");
                    lore.add("§7《对拯救困在树上的弱智队友很有用.》");
                    lore.add("");
                    if (!ifquick("梯子", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c梯子");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f4 铁锭");
                    lore.add("");
                    lore.add("§7《对拯救困在树上的弱智队友很有用.》");
                    lore.add("");
                    if (!ifquick("梯子", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("木板")) {
                ItemStack item = new ItemStack(Material.WOOD, 16);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 4)) {
                    meta.setDisplayName("§a木板");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §64 金锭");
                    lore.add("");
                    lore.add("§7保护你的床的坚固方块.");
                    lore.add("");
                    if (!ifquick("木板", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c木板");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §64 金锭");
                    lore.add("");
                    lore.add("§7保护你的床的坚固方块.");
                    lore.add("");
                    if (!ifquick("木板", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("黑曜石")) {
                ItemStack item = new ItemStack(Material.OBSIDIAN, 4);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 4)) {
                    meta.setDisplayName("§a黑曜石");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §24 绿宝石");
                    lore.add("");
                    lore.add("§7保护你的床的坚固方块.");
                    lore.add("");
                    if (!ifquick("黑曜石", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");
                    }
                    if (!config.getString("Map.ModeType").equalsIgnoreCase("4v4")) {
                        lore.add("§e点击购买");
                    } else {
                        lore.add("§c无法在该模式购买!");
                    }
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c黑曜石");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §24 绿宝石");
                    lore.add("");
                    lore.add("§7保护你的床的坚固方块.");
                    lore.add("");
                    if (!ifquick("黑曜石", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }

                    if (!config.getString("Map.ModeType").equalsIgnoreCase("4v4")) {
                        lore.add("§c你没有足够的 绿宝石");
                    } else {
                        lore.add("§c无法在该模式购买!");
                    }
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("铁剑")) {
                ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 7)) {
                    meta.setDisplayName("§a铁剑");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §67 金锭");
                    lore.add("");
                    if (!ifquick("铁剑", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c铁剑");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §67 金锭");
                    lore.add("");
                    if (!ifquick("铁剑", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");
                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("钻石剑")) {
                ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
                ItemMeta meta = item.getItemMeta();
                if (!config.getString("Map.ModeType").contains("单挑") || config.getString("Map.ModeType").contains("双人")) {
                    if (player.getInventory().contains(Material.EMERALD, 4)) {
                        meta.setDisplayName("§a钻石剑");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §24 绿宝石");
                        lore.add("");
                        if (!ifquick("钻石剑", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c钻石剑");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §24 绿宝石");
                        lore.add("");
                        if (!ifquick("钻石剑", rs)) {
                            lore.add("§bShift加单击移除至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");
                        }
                        lore.add("§c你没有足够的 绿宝石");
                        meta.setLore(lore);
                    }
                } else {
                    if (player.getInventory().contains(Material.EMERALD, 3)) {
                        meta.setDisplayName("§a钻石剑");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §23 绿宝石");
                        lore.add("");
                        if (!ifquick("钻石剑", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c钻石剑");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §23 绿宝石");
                        lore.add("");
                        if (!ifquick("钻石剑", rs)) {
                            lore.add("§bShift加单击移除至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");
                        }
                        lore.add("§c你没有足够的 绿宝石");
                        meta.setLore(lore);
                    }
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("击退棒")) {
                ItemStack item = new ItemStack(Material.STICK, 1);

                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                if (player.getInventory().contains(Material.GOLD_INGOT, 10)) {
                    meta.setDisplayName("§a击退棒 (击退 I)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §610 金锭");
                    lore.add("");
                    if (!ifquick("击退棒", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c击退棒 (击退 I)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §610 金锭");
                    lore.add("");
                    if (!ifquick("击退棒", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("铁护甲")) {
                ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 12)) {
                    meta.setDisplayName("§a永久的铁护甲");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §612 金锭");
                    lore.add("");
                    lore.add("§7铁护腿和铁靴子");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("铁护甲", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    if (player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS || player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
                        lore.add("§e点击购买");
                    } else {
                        if (player.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
                            lore.add("§a已拥有");
                        } else {
                            lore.add("§a已拥有更高等级的护甲");
                        }
                    }
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c永久的铁护甲");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §612 金锭");
                    lore.add("");
                    lore.add("§7铁护腿和铁靴子");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("铁护甲", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("钻石护甲")) {
                ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 6)) {
                    meta.setDisplayName("§a永久的钻石护甲");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §26 绿宝石");
                    lore.add("");
                    lore.add("§7钻石护腿和钻石靴子");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("钻石护甲", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    if (player.getInventory().getBoots().getType() != Material.DIAMOND_BOOTS) {
                        lore.add("§e点击购买");
                    } else {
                        lore.add("§a已拥有");
                    }
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c永久的钻石护甲");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §26 绿宝石");
                    lore.add("");
                    lore.add("§7钻石护腿和钻石靴子");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("钻石护甲", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 绿宝石");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("剪刀")) {
                ItemStack item = new ItemStack(Material.SHEARS, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 20)) {
                    meta.setDisplayName("§a剪刀");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f20 铁锭");
                    lore.add("");
                    lore.add("§7可以用来快速破坏羊毛");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("剪刀", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    if (!BedWars.shears.get(player.getName())) {
                        lore.add("§e点击购买");
                    } else {
                        lore.add("§a已拥有");
                    }
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c剪刀");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f20 铁锭");
                    lore.add("");
                    lore.add("§7可以用来快速破坏羊毛");
                    lore.add("§7死亡不掉落");
                    lore.add("");
                    if (!ifquick("剪刀", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("箭")) {
                ItemStack item = new ItemStack(Material.ARROW, 8);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 2)) {
                    meta.setDisplayName("§a箭");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §62 金锭");
                    lore.add("");
                    if (!ifquick("箭", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c箭");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §62 金锭");
                    lore.add("");
                    if (!ifquick("箭", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("弓")) {
                ItemStack item = new ItemStack(Material.BOW, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 12)) {
                    meta.setDisplayName("§a弓");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §612 金锭");
                    lore.add("");
                    if (!ifquick("弓", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c弓");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §612 金锭");
                    lore.add("");
                    if (!ifquick("弓", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("力量弓")) {
                ItemStack item = new ItemStack(Material.BOW, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 24)) {
                    meta.setDisplayName("§a弓 (力量 I)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §624 金锭");
                    lore.add("");
                    if (!ifquick("力量弓", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c弓 (力量 I)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §624 金锭");
                    lore.add("");
                    if (!ifquick("力量弓", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("力量冲击弓")) {
                ItemStack item = new ItemStack(Material.BOW, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 4)) {
                    meta.setDisplayName("§a弓 (力量 I 冲击 I)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §24 绿宝石");
                    lore.add("");
                    if (!ifquick("力量冲击弓", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c弓 (力量 I 冲击 I)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §24 绿宝石");
                    lore.add("");
                    if (!ifquick("力量冲击弓", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 绿宝石");
                    meta.setLore(lore);
                }
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("速度药水")) {
                ItemStack item = new ItemStack(Material.POTION, 1);
                PotionMeta imm = (PotionMeta) item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 1)) {
                    imm.setDisplayName("§a速度药水 (45 秒)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §21 绿宝石");
                    lore.add("");
                    if (!ifquick("速度药水", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    imm.setLore(lore);
                } else {
                    imm.setDisplayName("§c速度药水 (45 秒)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §21 绿宝石");
                    lore.add("");
                    if (!ifquick("速度药水", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 绿宝石");
                    imm.setLore(lore);
                }
                imm.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2), true);
                imm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(imm);
                return item;
            }
            if (itemName.equalsIgnoreCase("跳跃药水")) {
                ItemStack item = new ItemStack(Material.POTION, 1);
                PotionMeta imm = (PotionMeta) item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 1)) {
                    imm.setDisplayName("§a跳跃药水 (45 秒)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §21 绿宝石");
                    lore.add("");
                    if (!ifquick("跳跃药水", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    imm.setLore(lore);
                } else {
                    imm.setDisplayName("§c跳跃药水 (45 秒)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §21 绿宝石");
                    lore.add("");
                    if (!ifquick("跳跃药水", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 绿宝石");
                    imm.setLore(lore);
                }
                imm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                imm.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 900, 5), true);
                item.setItemMeta(imm);
                return item;
            }
            if (itemName.equalsIgnoreCase("隐身药水")) {
                ItemStack item = new ItemStack(Material.POTION, 1);
                PotionMeta imm = (PotionMeta) item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 2)) {
                    imm.setDisplayName("§a隐身药水 (30 秒)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §22 绿宝石");
                    lore.add("");
                    if (!ifquick("隐身药水", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    imm.setLore(lore);
                } else {
                    imm.setDisplayName("§c隐身药水 (30 秒)");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §22 绿宝石");
                    lore.add("");
                    if (!ifquick("隐身药水", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 绿宝石");
                    imm.setLore(lore);
                }
                imm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                imm.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 1), true);
                item.setItemMeta(imm);
                return item;
            }
            if (itemName.equalsIgnoreCase("蠹虫")) {
                ItemStack item = new ItemStack(Material.SNOW_BALL, 1);
                ItemMeta imm = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 40)) {
                    imm.setDisplayName("§a蠹虫");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f40 铁锭");
                    lore.add("");
                    lore.add("§7生成一个蠹虫来扰乱敌人");
                    lore.add("§7生成15秒后去世");
                    lore.add("");
                    if (!ifquick("蠹虫", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    imm.setLore(lore);
                } else {
                    imm.setDisplayName("§c蠹虫");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f40 铁锭");
                    lore.add("");
                    lore.add("§7生成一个蠹虫来扰乱敌人");
                    lore.add("§7生成15秒后去世");
                    lore.add("");
                    if (!ifquick("蠹虫", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    imm.setLore(lore);
                }
                imm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(imm);
                return item;
            }
            if (itemName.equalsIgnoreCase("梦幻守卫")) {
                ItemStack item = new ItemStack(Material.MONSTER_EGG, 1);
                ItemMeta imm = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 120)) {
                    imm.setDisplayName("§a梦幻守卫");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f120 铁锭");
                    lore.add("");
                    lore.add("§7生成一个铁傀儡来扰乱敌人");
                    lore.add("§7生成1分钟后去世");
                    lore.add("");
                    if (!ifquick("梦幻守卫", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    imm.setLore(lore);
                } else {
                    imm.setDisplayName("§c梦幻守卫");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f120 铁锭");
                    lore.add("");
                    lore.add("§7生成一个铁傀儡来扰乱敌人");
                    lore.add("§7生成1分钟后去世");
                    lore.add("");
                    if (!ifquick("梦幻守卫", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    imm.setLore(lore);
                }
                imm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(imm);
                return item;
            }
            if (itemName.equalsIgnoreCase("火球")) {
                ItemStack item = new ItemStack(Material.FIREBALL, 1);
                ItemMeta imm = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 40)) {
                    imm.setDisplayName("§a火球");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f40 铁锭");
                    lore.add("");
                    lore.add("§7右键发射，非常适合");
                    lore.add("§7用来击退敌人和炮爷出击");
                    lore.add("");
                    if (!ifquick("火球", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    imm.setLore(lore);
                } else {
                    imm.setDisplayName("§c火球");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f40 铁锭");
                    lore.add("");
                    lore.add("§7右键发射，非常适合");
                    lore.add("§7用来击退敌人和炮爷出击");
                    lore.add("");
                    if (!ifquick("火球", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    imm.setLore(lore);
                }
                imm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(imm);
                return item;
            }
            if (itemName.equalsIgnoreCase("末影珍珠")) {
                ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.EMERALD, 4)) {
                    meta.setDisplayName("§a末影珍珠");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §24 绿宝石");
                    lore.add("");
                    lore.add("§7可以用来自救和快速前往敌人家中");
                    lore.add("");
                    if (!ifquick("末影珍珠", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c末影珍珠");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §24 绿宝石");
                    lore.add("");
                    lore.add("§7可以用来自救和快速前往敌人家中");
                    lore.add("");
                    if (!ifquick("末影珍珠", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 绿宝石");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("水桶")) {
                ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 4)) {
                    meta.setDisplayName("§a水桶");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §64 金锭");
                    lore.add("");
                    lore.add("§7可以用来落地水和减速");
                    lore.add("");
                    if (!ifquick("水桶", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c水桶");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §64 金锭");
                    lore.add("");
                    lore.add("§7可以用来落地水和减速");
                    lore.add("");
                    if (!ifquick("水桶", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("搭桥蛋")) {
                ItemStack item = new ItemStack(Material.EGG, 1);
                ItemMeta meta = item.getItemMeta();
                if (config.getString("Map.ModeType").equalsIgnoreCase("单挑") || config.getString("Map.ModeType").equalsIgnoreCase("双人")) {

                    if (player.getInventory().contains(Material.EMERALD, 1)) {
                        meta.setDisplayName("§a搭桥蛋");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §21 绿宝石");
                        lore.add("");
                        lore.add("§7可以在蛋经过的地方生成羊毛来组成桥");
                        lore.add("");
                        if (!ifquick("搭桥蛋", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c搭桥蛋");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §21 绿宝石");
                        lore.add("");
                        lore.add("§7可以在蛋经过的地方生成羊毛来组成桥");
                        lore.add("");
                        if (!ifquick("搭桥蛋", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 绿宝石");
                        meta.setLore(lore);
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                } else {
                    if (player.getInventory().contains(Material.EMERALD, 2)) {
                        meta.setDisplayName("§a搭桥蛋");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §22 绿宝石");
                        lore.add("");
                        lore.add("§7可以在蛋经过的地方生成羊毛来组成桥");
                        lore.add("");
                        if (!ifquick("搭桥蛋", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§e点击购买");
                        meta.setLore(lore);
                    } else {
                        meta.setDisplayName("§c搭桥蛋");
                        ArrayList lore = new ArrayList();
                        lore.add("§7花费: §22 绿宝石");
                        lore.add("");
                        lore.add("§7可以在蛋经过的地方生成羊毛来组成桥");
                        lore.add("");
                        if (!ifquick("搭桥蛋", rs)) {
                            lore.add("§bShift加单击添加至快捷购买");
                        } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                            lore.add("§bShift加单击移除至快捷购买");

                        }
                        lore.add("§c你没有足够的 绿宝石");
                        meta.setLore(lore);
                    }
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(meta);
                    return item;
                }
            }
            if (itemName.equalsIgnoreCase("魔法牛奶")) {
                ItemStack item = new ItemStack(Material.MILK_BUCKET, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 4)) {
                    meta.setDisplayName("§a魔法牛奶");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §64 金锭");
                    lore.add("");
                    lore.add("§7在一分钟内免疫敌人的陷阱");
                    lore.add("");
                    if (!ifquick("魔法牛奶", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c魔法牛奶");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §64 金锭");
                    lore.add("");
                    lore.add("§7在一分钟内免疫敌人的陷阱");
                    lore.add("");
                    if (!ifquick("魔法牛奶", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("海绵")) {
                ItemStack item = new ItemStack(Material.SPONGE, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.GOLD_INGOT, 3)) {
                    meta.setDisplayName("§a海绵");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §63 金锭");
                    lore.add("");
                    lore.add("§7可以吸掉首家狗在床旁边放的水");
                    lore.add("");
                    if (!ifquick("海绵", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c海绵");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §63 金锭");
                    lore.add("");
                    lore.add("§7可以吸掉首家狗在床旁边放的水");
                    lore.add("");
                    if (!ifquick("海绵", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 金锭");
                    meta.setLore(lore);
                }

                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            if (itemName.equalsIgnoreCase("防御塔")) {
                ItemStack item = new ItemStack(Material.CHEST, 1);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().contains(Material.IRON_INGOT, 25)) {
                    meta.setDisplayName("§a防御塔");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f25 铁锭");
                    lore.add("");
                    lore.add("§7快速搭起堡垒来进攻敌人！");
                    lore.add("");
                    if (!ifquick("防御塔", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§e点击购买");
                    meta.setLore(lore);
                } else {
                    meta.setDisplayName("§c防御塔");
                    ArrayList lore = new ArrayList();
                    lore.add("§7花费: §f25 铁锭");
                    lore.add("");
                    lore.add("§7快速搭起堡垒来进攻敌人！");
                    lore.add("");
                    if (!ifquick("防御塔", rs)) {
                        lore.add("§bShift加单击添加至快捷购买");
                    } else if (player.getOpenInventory().getTitle().contains("快捷商店")) {
                        lore.add("§bShift加单击移除至快捷购买");

                    }
                    lore.add("§c你没有足够的 铁锭");
                    meta.setLore(lore);
                }
                meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                return item;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
