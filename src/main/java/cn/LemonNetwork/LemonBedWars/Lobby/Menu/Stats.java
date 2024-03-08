package cn.lemonnetwork.lemonbedwars.Lobby.Menu;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.Hologram;
import cn.lemonnetwork.lemonbedwars.Utils.ItemUtil;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.achievement.Database;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Stats implements Listener {
//    public HashMap<String, NPC> PlayerNPC = new HashMap<>();
    public static void add(Player player) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (config.get("Lobby.StatsNPC") == null) {
            player.sendMessage("§a你添加了一个 §e玩家数据NPC");
        } else {
            player.sendMessage("§a你修改了 §e玩家数据NPC §a的位置");
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        Location location = player.getLocation();
        config.set("Lobby.StatsNPC", location.getWorld().getName() + "," + decimalFormat.format(player.getLocation().getX()) + "," + decimalFormat.format(player.getLocation().getY()) + "," + decimalFormat.format(player.getLocation().getZ()) + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
        plugin.saveConfig();
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§d");
            SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
            skinTrait.setSkinName("Avauntic", true);
            String[] Locations = config.getString("Lobby.StatsNPC").split(",");
            Location Location = new Location(Bukkit.getWorld(Locations[0]), Double.parseDouble(Locations[1]), Double.parseDouble(Locations[2]), Double.parseDouble(Locations[3]));
            npc.data().set(NPC.Metadata.NAMEPLATE_VISIBLE, false);
            npc.spawn(Location);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        if (config.get("Lobby.StatsNPC") != null) {
//            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§d");
//            SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
//            skinTrait.setSkinName(player.getName(), true);
//            String[] Locations = config.getString("Lobby.StatsNPC").split(",");
//            Location Location = new Location(Bukkit.getWorld(Locations[0]), Double.parseDouble(Locations[1]), Double.parseDouble(Locations[2]), Double.parseDouble(Locations[3]));
//            npc.spawn(Location);
//            npc.data().set(NPC.Metadata.NAMEPLATE_VISIBLE, false);
//            PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(npc.getEntity().getEntityId());
//            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
            String lang = PlayerDataManage.getPlayerLang(player);
            ArrayList<String> lines = new ArrayList<>();
            int achievements_quantity = 0;
            for (String achievement : Database.getPlayerAchievements(player)) {
                if (achievement.contains("bedwars_")) {
                    achievements_quantity++;
                }
            }
            if (lang.equalsIgnoreCase("zhcn")) {
                lines.add("&6&l你的起床战争数据");
                lines.add("&f等级: &7["+PlayerDataManage.getLevel(player)+"✫]");
                lines.add("&f进度: &a" + (PlayerDataManage.getPlayerXP(player) - ((PlayerDataManage.getLevel(player) - 1) * 5000))+"&7/&b5000");
                lines.add("&f成就: &e"+achievements_quantity+"&a/4");
                lines.add("&f总胜利数: &a" + PlayerDataManage.getPlayerALLData(player, "win"));
                lines.add("&f总击杀数: &a" + PlayerDataManage.getPlayerALLData(player, "kills"));
                lines.add("&e&l点击查看数据");
            } else if (lang.equalsIgnoreCase("en")) {
                lines.add("&6&lYour BED WARS Data");
                lines.add("&fLevel: &7["+PlayerDataManage.getLevel(player)+"✫]");
                lines.add("&fProgress: &a" + (PlayerDataManage.getPlayerXP(player) - ((PlayerDataManage.getLevel(player) - 1) * 5000))+"&7/&b5000");
                lines.add("&fAchievements: &e"+achievements_quantity+"&a/4");
                lines.add("&fTotal Wins: &a" + PlayerDataManage.getPlayerALLData(player, "win"));
                lines.add("&fTotal Kills: &a" + PlayerDataManage.getPlayerALLData(player, "kills"));
                lines.add("&e&lClick to view data");
            }
            lines.replaceAll(s -> s.replace("&", "§"));
//            if (PlayerNPC.get(player.getName()) == null) {
//                PlayerNPC.put(player.getName(), npc);
//            } else {
//                PlayerNPC.replace(player.getName(), npc);
//            }
            Hologram.createHologram(player, lines, config.getString("Lobby.StatsNPC"), 0.3);
        }
    }
    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equalsIgnoreCase("起床战争统计数据") || event.getInventory().getTitle().equalsIgnoreCase("Bed Wars Stats")) {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("关闭") || event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("close")) {
                player.closeInventory();
            } else {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void OpenMenu(NPCRightClickEvent event) {
        if (event.getNPC().getName().contains("§d")) {
            Player player = event.getClicker();
            String lang = PlayerDataManage.getPlayerLang(player);
            Inventory inventory = null;

            if (lang.equalsIgnoreCase("zhcn")) {
                inventory = Bukkit.createInventory(null, 54, "起床战争统计数据");
                DecimalFormat df = new DecimalFormat("#.##");
                inventory.setItem(49, ItemUtil.CreateItem(Material.BARRIER, "&c关闭", new ArrayList<>()));
                inventory.setItem(4, ItemUtil.CreateItem(Material.PAPER, "&a综合统计数据", new ArrayList<>() {{
                    add("&7游戏场次: &a" + PlayerDataManage.getPlayerALLData(player, "games_played"));
                    add("");
                    add("&7击杀数: &a" + PlayerDataManage.getPlayerALLData(player, "kills"));
                    add("&7死亡数: &a" + PlayerDataManage.getPlayerALLData(player, "deaths"));
                    if (PlayerDataManage.getPlayerALLData(player, "kills") == 0 || PlayerDataManage.getPlayerALLData(player, "deaths") == 0) {
                        add("&7击杀:死亡: &cN/A");
                    } else {
                        add("&7击杀:死亡: &a" + df.format(PlayerDataManage.getPlayerALLData(player, "kills") / PlayerDataManage.getPlayerALLData(player, "deaths")));
                    }
                    add("&7最终击杀数: &a" + PlayerDataManage.getPlayerALLData(player, "final_kills"));
                    add("&7最终死亡数: &a" + PlayerDataManage.getPlayerALLData(player, "final_deaths"));
                    if (PlayerDataManage.getPlayerALLData(player, "final_kills") == 0 || PlayerDataManage.getPlayerALLData(player, "final_deaths") == 0) {
                        add("&7最终击杀:最终死亡: &cN/A");
                    } else {
                        add("&7最终击杀:最终死亡: &a" + df.format(PlayerDataManage.getPlayerALLData(player, "final_kills") / PlayerDataManage.getPlayerALLData(player, "final_deaths")));
                    }
                    add("");
                    add("&7胜场数: &a" + PlayerDataManage.getPlayerALLData(player, "win"));
                    add("&7败场数: &a" + PlayerDataManage.getPlayerALLData(player, "looses"));
                    add("");
                    add("&7摧毁的床数: &a" + PlayerDataManage.getPlayerALLData(player, "beds_destroyed"));
                }}));
                inventory.setItem(19, getModeItem(player, "单挑", 1));
                inventory.setItem(21, getModeItem(player, "双人", 2));
                inventory.setItem(23, getModeItem(player, "3v3v3v3", 3));
                inventory.setItem(25, getModeItem(player, "4v4v4v4", 4));
                inventory.setItem(31, getModeItem(player, "4v4", 4));
            } else if (lang.equalsIgnoreCase("en")) {
                inventory = Bukkit.createInventory(null, 54, "Bed Wars Stats");
                DecimalFormat df = new DecimalFormat("#.##");
                inventory.setItem(49, ItemUtil.CreateItem(Material.BARRIER, "&cClose", new ArrayList<>()));
                inventory.setItem(4, ItemUtil.CreateItem(Material.PAPER, "&aComprehensive Stats", new ArrayList<>() {{
                    add("&7Games Played: &a" + PlayerDataManage.getPlayerALLData(player, "games_played"));
                    add("");
                    if (PlayerDataManage.getPlayerALLData(player, "kills") == 0 || PlayerDataManage.getPlayerALLData(player, "deaths") == 0) {
                        add("&7Kill/Death Ratio: &cN/A");
                    } else {
                        add("&7Kill/Death Ratio: &a" + df.format(PlayerDataManage.getPlayerALLData(player, "kills") / PlayerDataManage.getPlayerALLData(player, "deaths")));
                    }
                    add("&7Final Kills: &a" + PlayerDataManage.getPlayerALLData(player, "final_kills"));
                    add("&7Final Deaths: &a" + PlayerDataManage.getPlayerALLData(player, "final_deaths"));
                    if (PlayerDataManage.getPlayerALLData(player, "final_kills") == 0 || PlayerDataManage.getPlayerALLData(player, "final_deaths") == 0) {
                        add("&7Final Kill/Final Death Ratio: &cN/A");
                    } else {
                        add("&7Final Kill/Final Death Ratio: &a" + df.format(PlayerDataManage.getPlayerALLData(player, "final_kills") / PlayerDataManage.getPlayerALLData(player, "final_deaths")));
                    }
                    add("");
                    add("&7Wins: &a" + PlayerDataManage.getPlayerALLData(player, "win"));
                    add("&7Looses: &a" + PlayerDataManage.getPlayerALLData(player, "looses"));
                    add("");
                    add("&7Beds Destroyed: &a" + PlayerDataManage.getPlayerALLData(player, "beds_destroyed"));
                }}));
                inventory.setItem(19, getModeItem(player, "单挑", 1));
                inventory.setItem(21, getModeItem(player, "双人", 2));
                inventory.setItem(23, getModeItem(player, "3v3v3v3", 3));
                inventory.setItem(25, getModeItem(player, "4v4v4v4", 4));
                inventory.setItem(31, getModeItem(player, "4v4", 4));
            }
            player.openInventory(inventory);
        }
    }
    private ItemStack getModeItem(Player player, String mode, int amount) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
            return ItemUtil.CreateItems(Material.PAPER, "&a" + mode + "模式数据", new ArrayList<>() {{
                add("&7游戏场次: &a" + PlayerDataManage.getPlayerData(mode, player, "games_played"));
                add("");
                add("&7击杀数: &a" + PlayerDataManage.getPlayerData(mode, player, "kills"));
                add("&7死亡数: &a" + PlayerDataManage.getPlayerData(mode, player, "deaths"));
                if (PlayerDataManage.getPlayerData(mode, player, "kills") == 0 || PlayerDataManage.getPlayerData(mode, player, "deaths") == 0) {
                    add("&7击杀:死亡: &cN/A");
                } else {
                    add("&7击杀:死亡: &a" + df.format(PlayerDataManage.getPlayerData(mode, player, "kills") / PlayerDataManage.getPlayerData(mode, player, "deaths")));
                }
                add("&7最终击杀数: &a" + PlayerDataManage.getPlayerData(mode, player, "final_kills"));
                add("&7最终死亡数: &a" + PlayerDataManage.getPlayerData(mode, player, "final_deaths"));
                if (PlayerDataManage.getPlayerData(mode, player, "final_kills") == 0 || PlayerDataManage.getPlayerData(mode, player, "final_deaths") == 0) {
                    add("&7最终击杀:最终死亡: &cN/A");
                } else {
                    add("&7最终击杀:最终死亡: &a" + df.format(PlayerDataManage.getPlayerData(mode, player, "final_kills") / PlayerDataManage.getPlayerData(mode, player, "final_deaths")));
                }
                add("");
                add("&7胜场数: &a" + PlayerDataManage.getPlayerData(mode, player, "win"));
                add("&7败场数: &a" + PlayerDataManage.getPlayerData(mode, player, "looses"));
                add("");
                add("&7摧毁的床数: &a" + PlayerDataManage.getPlayerData(mode, player, "beds_destroyed"));
            }}, amount);
        } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")) {
            return ItemUtil.CreateItems(Material.PAPER, ("&a" + mode.replace("单挑", "Solo").replace("双人", "Double")) + " Mode Data", new ArrayList<>() {{
                add("&7Games Played: &a" + PlayerDataManage.getPlayerData(mode, player, "games_played"));
                add("");
                add("&7Kills: &a" + PlayerDataManage.getPlayerData(mode, player, "kills"));
                add("&7Deaths: &a" + PlayerDataManage.getPlayerData(mode, player, "deaths"));
                if (PlayerDataManage.getPlayerData(mode, player, "kills") == 0 || PlayerDataManage.getPlayerData(mode, player, "deaths") == 0) {
                    add("&7Kill/Death Ratio: &cN/A");
                } else {
                    add("&7Kill/Death Ratio: &a" + df.format(PlayerDataManage.getPlayerData(mode, player, "kills") / PlayerDataManage.getPlayerData(mode, player, "deaths")));
                }
                add("&7Final Kills: &a" + PlayerDataManage.getPlayerData(mode, player, "final_kills"));
                add("&7Final Deaths: &a" + PlayerDataManage.getPlayerData(mode, player, "final_deaths"));
                if (PlayerDataManage.getPlayerData(mode, player, "final_kills") == 0 || PlayerDataManage.getPlayerData(mode, player, "final_deaths") == 0) {
                    add("&7Final Kill/Final Death Ratio: &cN/A");
                } else {
                    add("&7Final Kill/Final Death Ratio: &a" + df.format(PlayerDataManage.getPlayerData(mode, player, "final_kills") / PlayerDataManage.getPlayerData(mode, player, "final_deaths")));
                }
                add("");
                add("&7Wins: &a" + PlayerDataManage.getPlayerData(mode, player, "win"));
                add("&7Looses: &a" + PlayerDataManage.getPlayerData(mode, player, "looses"));
                add("");
                add("&7Beds Destroyed: &a" + PlayerDataManage.getPlayerData(mode, player, "beds_destroyed"));
            }}, amount);
        }
        return null;
    }
//    @EventHandler
//    public void onPlayerQuit(PlayerQuitEvent event) {
//        if (PlayerNPC != null) {
//            PlayerNPC.replace(event.getPlayer().getName(), null);
//            PlayerNPC = null;
//        }
//    }
}
