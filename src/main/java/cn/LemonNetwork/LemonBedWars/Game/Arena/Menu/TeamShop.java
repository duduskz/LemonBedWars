package cn.lemonnetwork.lemonbedwars.Game.Arena.Menu;

import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import cn.lemonnetwork.lemonbedwars.Utils.ItemUtil;
import cn.hpnetwork.lemonnick.API.LemonNickAPI;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
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
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class TeamShop implements Listener {
    Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
    FileConfiguration config = plugin.getConfig();
    public ItemStack getTrap(Player player, int trap) {
        Team team = GameStart.getScoreboard().getEntryTeam(player.getName());
        ItemStack itemStack = new ItemStack(Material.AIR);
        int candiamond = LemonBedWars.Trap.get(team.getName()).size() + 1;
        if (trap == 0) {
            itemStack.setType(Material.LEVER);
            ItemMeta itemMeta = itemStack.getItemMeta();

            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7敌人到达己方基地时获得8秒失明和缓慢效果");
            lore.add("");
            lore.add("§7花费: §b" + candiamond + " 钻石");
            lore.add("");
            String color = "§c";
            if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(0)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    color = "§a";
                    lore.add("§e点击购买");
                }

            } else {

                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(0)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    lore.add("§c你没有足够的钻石购买此陷阱!");
                }

            }
            itemMeta.setDisplayName(color+"这是一个陷阱！");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        } else if (trap == 1) {
            itemStack.setType(Material.FEATHER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7当有敌对玩家进入你的基地后");
            lore.add("§7你和你的队友将会获得速度 II和跳跃提升 II");
            lore.add("");
            lore.add("§7花费: §b" + candiamond + " 钻石");
            lore.add("");
            String color = "§c";
            if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(1)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    color = "§a";
                    lore.add("§e点击购买");
                }

            } else {

                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(1)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    lore.add("§c你没有足够的钻石购买此陷阱!");
                }

            }
            itemMeta.setDisplayName(color+"反击陷阱");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        } else if (trap == 2) {
            itemStack.setType(Material.REDSTONE_TORCH_ON);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7显示隐身的玩家的名称和队伍名。");
            lore.add("");
            lore.add("§7花费: §b" + candiamond + " 钻石");
            lore.add("");
            String color = "§c";
            if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(2)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    color = "§a";
                    lore.add("§e点击购买");
                }

            } else {

                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(2)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    lore.add("§c你没有足够的钻石购买此陷阱!");
                }

            }
            itemMeta.setDisplayName(color+"报警陷阱");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        } else if (trap == 3) {
            itemStack.setType(Material.IRON_PICKAXE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§7当敌对玩家进入你的基地时");
            lore.add("§7将会获得10秒的挖掘疲劳");
            lore.add("");
            lore.add("§7花费: §b" + candiamond + " 钻石");
            lore.add("");
            String color = "§c";
            if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(3)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    color = "§a";
                    lore.add("§e点击购买");
                }

            } else {

                if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(3)) {
                    lore.add("§c你已经购买了此陷阱!");
                } else {
                    lore.add("§c你没有足够的钻石购买此陷阱!");
                }

            }
            itemMeta.setDisplayName(color+"挖掘疲劳陷阱");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
    public ItemStack getDragon(Player player) {
        ArrayList<String> dragonLore = new ArrayList<>() {{
            add("&7在绝杀模式中, 己方队伍将");
            add("&7拥有2条末影龙");
            add("");
            add("&7花费: &b5 钻石");
            add("");
        }};
        String color = "&c";
        if (LemonBedWars.Dragon.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
            dragonLore.add("&c你已经购买了此附魔!");
        } else {
            if (player.getInventory().contains(Material.DIAMOND, 5)) {
                dragonLore.add("&e点击购买!");
                color = "&a";
            } else {
                dragonLore.add("&c你没有足够的钻石购买此附魔!");
            }
        }
        return ItemUtil.CreateItem(Material.DRAGON_EGG, color+"龙增益", dragonLore);
    }
    public ItemStack getProtect(Player player) {
        ItemStack protect = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta protectmeta = protect.getItemMeta();
        String IIIIIIIII = "????";

        int candiamond;
        String color = "§7";
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7己方所有成员的盔甲将获得永久保护附魔！");
        lore.add("");
        candiamond = 2;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 5;
        }
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 0) {
            IIIIIIIII = "I";

        }
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) >= 1) {
            IIIIIIIII = "II";
            color = "§a";
        }
        lore.add(color+"1级:  保护I，§b" + candiamond + " 钻石");
        color = "§7";
        candiamond = 4;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 10;
        }
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) >= 2) {
            IIIIIIIII = "III";
            color = "§a";
        }
        lore.add(color+"2级:  保护II，§b" + candiamond + " 钻石");
        color = "§7";
        candiamond = 8;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 20;
        }
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) >= 3) {
            IIIIIIIII = "IV";
            color = "§a";
        }
        lore.add(color + "3级:  保护III，§b" + candiamond + " 钻石");
        color = "§7";
        candiamond = 16;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 30;
        }
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 4) {
            IIIIIIIII = "IV";
            color = "§a";
        }
        lore.add(color + "4级:  保护IV，§b" + candiamond + " 钻石");

        lore.add("");
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 0) {
            candiamond = 2;
            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                candiamond = 5;
            }
        } else {
            if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                candiamond = 4;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 10;
                }
            }
            if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                candiamond = 8;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 20;
                }
            }
            if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 3) {
                candiamond = 16;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 30;
                }
            }
        }
        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) != 4){
            if (player.getInventory().contains(Material.DIAMOND, candiamond)){
                lore.add("§e点击购买");
                protectmeta.setDisplayName("§a装备强化 "+IIIIIIIII);
            } else {
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0, 0);
                lore.add("§c你没有足够的钻石购买此附魔!");
                protectmeta.setDisplayName("§c装备强化 "+IIIIIIIII);
            }
        } else {
            protectmeta.setDisplayName("§a装备强化 "+IIIIIIIII);
            lore.add("§a该项已满级!");
        }
        protectmeta.setLore(lore);
        protect.setItemMeta(protectmeta);
        return protect;
    }
    public ItemStack getHaste(Player player) {
        ItemStack Haste = new ItemStack(Material.GOLD_PICKAXE);
        ItemMeta Hastemeta = Haste.getItemMeta();
        String IIIIIIIII = "????";

        int candiamond;
        String color = "§7";
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7己方所有成员都会给予永久的急迫效果！");

        lore.add("");
        candiamond = 2;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 4;
        }
        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) >= 0) {
            IIIIIIIII = "I";

        }
        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) >= 1) {
            IIIIIIIII = "II";
            color = "§a";
        }
        lore.add(color+"1级:  急迫I，§b" + candiamond + " 钻石");
        color = "§7";
        candiamond = 4;
        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
            candiamond = 6;
        }
        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 2) {
            IIIIIIIII = "II";
            color = "§a";
        }
        lore.add(color+"2级:  急迫II，§b" + candiamond + " 钻石");
        lore.add("");
        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 0) {
            candiamond = 2;
            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                candiamond = 4;
            }
        } else {
            if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                candiamond = 4;
                if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                    candiamond = 6;
                }
            }
            if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                lore.add("§c该项已满级!");
            }
        }
        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) != 2){
            if (player.getInventory().contains(Material.DIAMOND, candiamond)){
                lore.add("§e点击购买");
                Hastemeta.setDisplayName("§a疯狂矿工 "+IIIIIIIII);
            } else {
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0, 0);
                lore.add("§c你没有足够的钻石购买此附魔!");
                Hastemeta.setDisplayName("§c疯狂矿工 "+IIIIIIIII);
            }
        } else {
            Hastemeta.setDisplayName("§a疯狂矿工 "+IIIIIIIII);
        }
        Hastemeta.setLore(lore);
        Haste.setItemMeta(Hastemeta);
        return Haste;
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
            if (LemonBedWars.sharp.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                sharpmeta.setDisplayName("§a锋利附魔");
                lore.add("§c你已经购买了此附魔!");
            } else {
                sharpmeta.setDisplayName("§a锋利附魔");
                lore.add("§e点击购买");
            }

        } else {

            if (LemonBedWars.sharp.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                lore.add("§c你已经购买了此附魔!");
                sharpmeta.setDisplayName("§c锋利附魔");
            } else {
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0, 0);
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
            Inventory inventory = Bukkit.createInventory(null, 54, "升级与陷阱");
            inventory.setItem(10, getSharp(e.getClicker()));
            inventory.setItem(11, getProtect(e.getClicker()));
            inventory.setItem(12, getHaste(e.getClicker()));
            inventory.setItem(21, getDragon(e.getClicker()));
            inventory.setItem(14, getTrap(e.getClicker(), 0));
            inventory.setItem(15, getTrap(e.getClicker(), 1));
            inventory.setItem(16, getTrap(e.getClicker(), 2));
            inventory.setItem(23, getTrap(e.getClicker(), 3));
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
            ItemMeta glass_meta = glass.getItemMeta();
            glass_meta.setDisplayName("§8↑购买类型");
            ArrayList<String> glass_meta_lore = new ArrayList<>();
            glass_meta_lore.add("§8↓陷阱");
            glass_meta.setLore(glass_meta_lore);
            glass.setItemMeta(glass_meta);
            inventory.setItem(27, glass);
            inventory.setItem(28, glass);
            inventory.setItem(29, glass);
            inventory.setItem(30, glass);
            inventory.setItem(31, glass);
            inventory.setItem(32, glass);
            inventory.setItem(33, glass);
            inventory.setItem(34, glass);
            inventory.setItem(35, glass);
            player.openInventory(inventory);
            inventory.setItem(39, ItemUtil.CreateItem(Material.STAINED_GLASS, "&c陷阱#1: 没有陷阱！", new ArrayList<>() {{
                add("&7这是你陷阱的槽位！");
                add("&7槽位最大是3个，无法继续扩充");
                add("");
                add("&7第一个进入你陷阱的人");
                add("&7将会将这个槽位的陷阱触发");
                add("");
                add("&7下一个陷阱所需钻石: &b" + (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() + 1) + "钻石");
            }}, 8));
            inventory.setItem(40, ItemUtil.CreateItem(Material.STAINED_GLASS, "&c陷阱#2: 没有陷阱！", new ArrayList<>() {{
                add("&7这是你陷阱的槽位！");
                add("&7槽位最大是3个，无法继续扩充");
                add("");
                add("&7第二个进入你陷阱的人");
                add("&7将会将这个槽位的陷阱触发");
                add("");
                add("&7下一个陷阱所需钻石: &b" + (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() + 1) + "钻石");
            }}, 8));
            inventory.setItem(41, ItemUtil.CreateItem(Material.STAINED_GLASS, "&c陷阱#3: 没有陷阱！", new ArrayList<>() {{
                add("&7这是你陷阱的槽位！");
                add("&7槽位最大是3个，无法继续扩充");
                add("");
                add("&7第三个进入你陷阱的人");
                add("&7将会将这个槽位的陷阱触发");
                add("");
                add("&7下一个陷阱所需钻石: &b" + (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() + 1) + "钻石");
            }}, 8));
            int number = 39;
            for (int Trap : LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                inventory.setItem(number, getTrap(player, Trap));
                number = number + 1;
            }
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
                    if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 4) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage("§c此附魔已满级!");
                    } else {
                        String IIIIIIIII = "布吉岛";
                        int candiamond = 0;

                        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 0) {
                            IIIIIIIII = "I";
                            candiamond = 2;

                            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                                candiamond = 5;
                            }
                        }
                        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                            IIIIIIIII = "II";
                            candiamond = 4;

                            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                                candiamond = 10;
                            }
                        }
                        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                            candiamond = 8;

                            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                                candiamond = 15;
                            }
                            IIIIIIIII = "III";
                        }
                        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 3) {
                            IIIIIIIII = "IV";
                            candiamond = 16;

                            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                                candiamond = 32;
                            }
                        }
                        if (LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 4) {
                            IIIIIIIII = "IV";
                        }
                        LemonBedWars.protectUpgrade.replace(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName()) + 1);

                        for (String forplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                            ItemMeta im = Bukkit.getPlayer(forplayer).getInventory().getBoots().getItemMeta();
                            im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getBoots().setItemMeta(im);

                            ItemMeta im1 = Bukkit.getPlayer(forplayer).getInventory().getLeggings().getItemMeta();
                            im1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getLeggings().setItemMeta(im1);

                            ItemMeta im2 = Bukkit.getPlayer(forplayer).getInventory().getChestplate().getItemMeta();
                            im2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getChestplate().setItemMeta(im2);

                            ItemMeta im3 = Bukkit.getPlayer(forplayer).getInventory().getHelmet().getItemMeta();
                            im3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, LemonBedWars.protectUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()), false);
                            Bukkit.getPlayer(forplayer).getInventory().getHelmet().setItemMeta(im3);
                            Bukkit.getPlayer(forplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                            Bukkit.getPlayer(forplayer).sendMessage("§6" + player.getName() + " §a购买了 §6装备强化 "+IIIIIIIII);
                        }
                        ItemStack protect = getProtect(player);
                        player.getOpenInventory().setItem(e.getSlot(), protect);
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));

                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a疯狂矿工")) {
                    if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage("§c此附魔已满级!");
                    } else {
                        String IIIIIIIII = "布吉岛";
                        int candiamond = 0;

                        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 0) {
                            IIIIIIIII = "I";
                            candiamond = 2;

                            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                                candiamond = 4;
                            }
                        }
                        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 1) {
                            IIIIIIIII = "II";
                            candiamond = 4;

                            if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                                candiamond = 6;
                            }
                        }
                        if (LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()) == 2) {
                            IIIIIIIII = "II";
                        }
                        LemonBedWars.HasteUpgrade.replace(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName()) + 1);

                        for (String forplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                            Bukkit.getPlayer(forplayer).addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(999999,  LemonBedWars.HasteUpgrade.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())));
                            Bukkit.getPlayer(forplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                            Bukkit.getPlayer(forplayer).sendMessage("§6" + player.getName() + " §a购买了 §6疯狂矿工 "+IIIIIIIII);
                        }
                        ItemStack haste = getHaste(player);
                        player.getOpenInventory().setItem(e.getSlot(), haste);
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));

                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a锋利附魔")) {
                    if (LemonBedWars.sharp.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage("§c你已经购买了此附魔!");
                    } else {
                        LemonBedWars.sharp.replace(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), true);

                        for (String teamplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                            for (ItemStack is : Bukkit.getPlayer(teamplayer).getInventory().getContents()) {
                                try {
                                    if (is.getType().equals(Material.WOOD_SWORD) || is.getType().equals(Material.STONE_SWORD) || is.getType().equals(Material.IRON_SWORD) || is.getType().equals(Material.DIAMOND_SWORD) || is.getType().equals(Material.WOOD_AXE) || is.getType().equals(Material.STONE_AXE) || is.getType().equals(Material.IRON_SWORD) || is.getType().equals(Material.DIAMOND_SWORD)) {
                                        ItemMeta im = is.getItemMeta();
                                        im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                                        is.setItemMeta(im);
                                    }
                                } catch (NullPointerException ignored) {

                                }
                            }
                            Bukkit.getPlayer(teamplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                            Bukkit.getPlayer(teamplayer).sendMessage("§6" + player.getName() + " §a购买了 §6锋利附魔");
                        }
                        ItemStack sharp = getSharp((Player) e.getWhoClicked());
                        int candiamond = 4;

                        if (config.getString("Map.ModeType").contains("4v4") || config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) {
                            candiamond = 8;
                        }
                        player.getOpenInventory().setItem(e.getSlot(), sharp);
                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));

                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a龙增益")) {
                    if (LemonBedWars.sharp.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage("§c你已经购买了此附魔!");
                    } else {
                        LemonBedWars.sharp.replace(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), true);

                        for (String teamplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                            Bukkit.getPlayer(teamplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                            Bukkit.getPlayer(teamplayer).sendMessage("§6" + player.getName() + " §a购买了 §6龙增益");
                        }

                        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 5));
                        e.getInventory().setItem(e.getSlot(), getDragon(player));
                    }
                }
                int candiamond = LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() + 1;
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("这是一个陷阱！")) {
                    if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                        if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() != 3) {
                            if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(0)) {
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                player.sendMessage("§c你已经购买了此陷阱!");
                            } else {
                                // BedWars.Trap.replace(GameStart.getScoreboard().getEntryTeam(e.getWhoClicked().getName()).getName(), true);

                                for (String teamplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                                    ArrayList<Integer> traplist = LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName());
                                    traplist.add(0);
                                    LemonBedWars.Trap.replace(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), traplist);
                                    Bukkit.getPlayer(teamplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                                    Bukkit.getPlayer(teamplayer).sendMessage("§6" + player.getName() + " §a购买了 §6这是一个陷阱！");
                                }
                                ItemStack trap = getTrap((Player) e.getWhoClicked(), 0);
                                player.getOpenInventory().setItem(e.getSlot(), trap);
                                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));
                                int number = 39;
                                for (int Trap : LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                                    player.getOpenInventory().setItem(number, getTrap(player, Trap));
                                    number = number + 1;
                                }
                            }
                        } else {
                            player.sendMessage("§c你们团队已经没有槽位来容纳这个陷阱了!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        }
                    } else {
                        player.sendMessage("§c你没有足够的钻石购买此陷阱！");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("报警陷阱")) {
                    if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                        if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() != 3) {
                            if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(2)) {
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                player.sendMessage("§c你已经购买了此陷阱!");
                            } else {
                                for (String teamplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                                    ArrayList<Integer> traplist = LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName());
                                    traplist.add(2);
                                    LemonBedWars.Trap.replace(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), traplist);
                                    Bukkit.getPlayer(teamplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                                    Bukkit.getPlayer(teamplayer).sendMessage("§6" + player.getName() + " §a购买了 §6报警陷阱");
                                }
                                ItemStack trap = getTrap((Player) e.getWhoClicked(), 2);
                                player.getOpenInventory().setItem(e.getSlot(), trap);
                                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));
                                int number = 39;
                                for (int Trap : LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                                    player.getOpenInventory().setItem(number, getTrap(player, Trap));
                                    number = number + 1;
                                }
                            }
                        } else {
                            player.sendMessage("§c你们团队已经没有槽位来容纳这个陷阱了!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        }
                    } else {
                        player.sendMessage("§c你没有足够的钻石购买此陷阱！");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("反击陷阱")) {
                    if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                        if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() != 3) {
                            if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(1)) {
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                player.sendMessage("§c你已经购买了此陷阱!");
                            } else {
                                for (String teamplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                                    ArrayList<Integer> traplist = LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName());
                                    traplist.add(1);
                                    LemonBedWars.Trap.replace(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), traplist);
                                    Bukkit.getPlayer(teamplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                                    Bukkit.getPlayer(teamplayer).sendMessage("§6" + player.getName() + " §a购买了 §6反击陷阱");
                                }
                                ItemStack trap = getTrap((Player) e.getWhoClicked(), 1);
                                player.getOpenInventory().setItem(e.getSlot(), trap);
                                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));
                                int number = 39;
                                for (int Trap : LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                                    player.getOpenInventory().setItem(number, getTrap(player, Trap));
                                    number = number + 1;
                                }
                            }
                        } else {
                            player.sendMessage("§c你们团队已经没有槽位来容纳这个陷阱了!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        }
                    } else {
                        player.sendMessage("§c你没有足够的钻石购买此陷阱！");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("挖掘疲劳陷阱")) {
                    if (player.getInventory().contains(Material.DIAMOND, candiamond)) {
                        if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).size() != 3) {
                            if (LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName()).contains(3)) {
                                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                                player.sendMessage("§c你已经购买了此陷阱!");
                            } else {
                                for (String teamplayer : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                                    ArrayList<Integer> traplist = LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName());
                                    traplist.add(3);
                                    LemonBedWars.Trap.replace(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), traplist);
                                    Bukkit.getPlayer(teamplayer).playSound(player.getLocation(), Sound.NOTE_PLING, 1, 24);
                                    Bukkit.getPlayer(teamplayer).sendMessage("§6" + player.getName() + " §a购买了 §6挖掘疲劳陷阱！");
                                }
                                ItemStack trap = getTrap((Player) e.getWhoClicked(), 3);
                                player.getOpenInventory().setItem(e.getSlot(), trap);
                                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, candiamond));
                                int number = 39;
                                for (int Trap : LemonBedWars.Trap.get(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {
                                    player.getOpenInventory().setItem(number, getTrap(player, Trap));
                                    number = number + 1;
                                }
                            }
                        } else {
                            player.sendMessage("§c你们团队已经没有槽位来容纳这个陷阱了!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        }
                    } else {
                        player.sendMessage("§c你没有足够的钻石购买此陷阱！");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            }
        } catch (NullPointerException | IllegalArgumentException ignored) {}
    }
}
