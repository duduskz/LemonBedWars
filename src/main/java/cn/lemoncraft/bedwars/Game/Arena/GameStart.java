package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.SpecialMode.RushMode;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import cn.lemoncraft.bedwars.Utils.NameTAG;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import cn.lemoncraft.bedwars.Utils.TAB;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.*;

public class GameStart {
    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public static Scoreboard getcoreboard() {
        return scoreboard;
    }

    public static String isyou(Team team, String playername) {
        for (String name : team.getEntries()) {
            if (Objects.equals(playername, name)) {
                return "§7你";
            }
        }


        return "";
    }


    public static void start() {

        char[] c = "ACDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        String RandomString = String.valueOf(c[new Random().nextInt(c.length)]);
        for (NPCRegistry r : CitizensAPI.getNPCRegistries()){ r.deregisterAll(); }
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        try {
            Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("showplayerhealth", "health");

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName("§c❤");
        } catch (IllegalArgumentException e) {
            Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("showplayerhealth");

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName("§c❤");
        }
        FileConfiguration config = plugin.getConfig();
        BedWars.state = "Play"; // 设置当前游戏状态
        MinecraftServer.getServer().setMotd("play");
        ItemDrop.start();
        NPCcreate.NPCstart();

        List<Player> playerList = new ArrayList<>(Bukkit.getServer().getOnlinePlayers()); //获取所有在线玩家
        for (Player p : playerList) { //遍历
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F); //播放音效
            List<String> arenastarttutorial = config.getStringList("arena-start-tutorial"); //获取游戏开始信息
            for (String s : arenastarttutorial) { // 遍历
                p.sendMessage(s); //玩家发送一行当前遍历到的文本
            }

        }


        Team red = scoreboard.registerNewTeam("红队");
        red.setAllowFriendlyFire(false);
        red.setCanSeeFriendlyInvisibles(true);
        red.setPrefix("§c红 ");
        red.setSuffix("§c");
        red.setDisplayName("0");
        Team blue = scoreboard.registerNewTeam("蓝队");
        blue.setAllowFriendlyFire(false);
        blue.setCanSeeFriendlyInvisibles(true);
        blue.setPrefix("§9蓝 ");
        blue.setSuffix("§9");
        blue.setDisplayName("0");
        if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否不为4v4
            Team green = scoreboard.registerNewTeam("绿队");
            green.setAllowFriendlyFire(false);
            green.setCanSeeFriendlyInvisibles(true);
            green.setPrefix("§a绿 ");
            green.setSuffix("§a");
            green.setDisplayName("0");
            Team yellow = scoreboard.registerNewTeam("黄队");
            yellow.setAllowFriendlyFire(false);
            yellow.setCanSeeFriendlyInvisibles(true);
            yellow.setPrefix("§e黄 ");
            yellow.setSuffix("§e");
            yellow.setDisplayName("0");

            if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") || Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否不为4v4v4v4
                Team aqua = scoreboard.registerNewTeam("青队");
                aqua.setAllowFriendlyFire(false);
                aqua.setCanSeeFriendlyInvisibles(true);
                aqua.setPrefix("§b青 ");
                aqua.setSuffix("§b");
                aqua.setDisplayName("0");
                Team white = scoreboard.registerNewTeam("白队");
                white.setAllowFriendlyFire(false);
                white.setCanSeeFriendlyInvisibles(true);
                white.setPrefix("§f白 ");
                white.setSuffix("§f");
                white.setDisplayName("0");
                Team pink = scoreboard.registerNewTeam("粉队");
                pink.setAllowFriendlyFire(false);
                pink.setCanSeeFriendlyInvisibles(true);
                pink.setPrefix("§d粉 ");
                pink.setSuffix("§b");
                pink.setDisplayName("0");
                Team gray = scoreboard.registerNewTeam("灰队");
                gray.setAllowFriendlyFire(false);
                gray.setCanSeeFriendlyInvisibles(true);
                gray.setPrefix("§8灰 ");
                gray.setSuffix("§8");
                gray.setDisplayName("0");
            }
        }
        Team spectator = scoreboard.registerNewTeam("旁观者");
        spectator.setAllowFriendlyFire(false);
        spectator.setCanSeeFriendlyInvisibles(true);
        spectator.setPrefix("§7旁观者 ");
        spectator.setSuffix("§7");
        spectator.setDisplayName("0");
        PlayerDataManage.AddQuickShopItem();
        for (int i = 0; i < playerList.size(); i++) {
            Player forplayer = playerList.get(i);

            BedWars.Fireballcooldown.put(forplayer, 0);
            BedWars.kill.put(forplayer.getName(), 0);
            BedWars.finalkill.put(forplayer.getName(), 0);
            BedWars.deaths.put(forplayer.getName(), 0);
            BedWars.finaldeaths.put(forplayer.getName(), 0);
            BedWars.breakbed.put(forplayer.getName(), 0);
            BedWars.shoutcd.put(forplayer.getName(), 0);
            BedWars.xp.put(forplayer.getName(), 0);
            BedWars.coins.put(forplayer.getName(), 0);
            BedWars.shears.put(forplayer.getName(), false);
            BedWars.axe.put(forplayer.getName(), 0);
            BedWars.pickaxe.put(forplayer.getName(), 0);

            BedWars.playeradditem.put(forplayer.getName(), "空");

            forplayer.getInventory().clear();
            ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
            ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
            itemMeta.spigot().setUnbreakable(true);
            char[] c = "ACDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
            String RandomString = String.valueOf(c[new Random().nextInt(c.length)]);
            Objective objective2 = scoreboard.registerNewObjective(RandomString, RandomString);
            objective2.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            WOOD_SWORD.setItemMeta(itemMeta);
            forplayer.getInventory().addItem(WOOD_SWORD);
            Game item = new Game();
            forplayer.getInventory().setItem(8, item.getItem("指南针"));
            if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") || Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                Team green = getcoreboard().getTeam("绿队");
                Team yellow = getcoreboard().getTeam("黄队");
                if (i % playerList.size() == 0) {
                    getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    red.setDisplayName("yes");
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    BedWars.sharp.put(red.getName(), false);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(255, 0, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 0, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 0, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 0, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 1) {
                    getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    blue.setDisplayName("yes");
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(0, 0, 255));
                    s.setItemMeta(lch);
                    BedWars.sharp.put(blue.getName(), false);
                    lch.spigot().setUnbreakable(true);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 0, 255));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(0, 0, 255));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 0, 255));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                    //getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    //String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    //forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                }
                if (i % playerList.size() == 2) {
                    getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    green.setDisplayName("yes");
                    BedWars.sharp.put(green.getName(), false);
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(0, 255, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.spigot().setUnbreakable(true);
                    lcl.setColor(Color.fromRGB(0, 255, 0));
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 3) {
                    getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    yellow.setDisplayName("yes");
                    BedWars.sharp.put(yellow.getName(), false);
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(255, 255, 0));
                    lch.spigot().setUnbreakable(true);
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 255, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 4) {
                    getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);

                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(255, 0, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 0, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 0, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 0, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 5) {
                    getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(0, 0, 255));
                    s.setItemMeta(lch);

                    lch.spigot().setUnbreakable(true);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 0, 255));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(0, 0, 255));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 0, 255));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                    //getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    //String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    //forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                }
                if (i % playerList.size() == 6) {
                    getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(0, 255, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.spigot().setUnbreakable(true);
                    lcl.setColor(Color.fromRGB(0, 255, 0));
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 7) {
                    getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(255, 255, 0));
                    lch.spigot().setUnbreakable(true);
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 255, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 4) {
                    getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);

                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(255, 0, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 0, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 0, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 0, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 5) {
                    getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(0, 0, 255));
                    s.setItemMeta(lch);

                    lch.spigot().setUnbreakable(true);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 0, 255));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(0, 0, 255));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 0, 255));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);

                }
                if (i % playerList.size() == 6) {
                    getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(0, 255, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.spigot().setUnbreakable(true);
                    lcl.setColor(Color.fromRGB(0, 255, 0));
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 7) {
                    getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(255, 255, 0));
                    lch.spigot().setUnbreakable(true);
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 255, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 8) {
                    getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);

                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(255, 0, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 0, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 0, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 0, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 9) {
                    getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(0, 0, 255));
                    s.setItemMeta(lch);

                    lch.spigot().setUnbreakable(true);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 0, 255));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(0, 0, 255));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 0, 255));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                    //getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    //String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    //forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                }
                if (i % playerList.size() == 10) {
                    getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(0, 255, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.spigot().setUnbreakable(true);
                    lcl.setColor(Color.fromRGB(0, 255, 0));
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 11) {
                    getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(255, 255, 0));
                    lch.spigot().setUnbreakable(true);
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 255, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 12) {
                    getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);

                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(255, 0, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 0, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 0, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 0, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 13) {
                    getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(0, 0, 255));
                    s.setItemMeta(lch);

                    lch.spigot().setUnbreakable(true);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 0, 255));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(0, 0, 255));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 0, 255));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                    //getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    //String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                    //forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                }
                if (i % playerList.size() == 14) {
                    getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.spigot().setUnbreakable(true);
                    lch.setColor(Color.fromRGB(0, 255, 0));
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(0, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.spigot().setUnbreakable(true);
                    lcl.setColor(Color.fromRGB(0, 255, 0));
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(0, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
                if (i % playerList.size() == 15) {
                    getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                    String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                    LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                    lch.setColor(Color.fromRGB(255, 255, 0));
                    lch.spigot().setUnbreakable(true);
                    s.setItemMeta(lch);
                    ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                    LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                    lcc.setColor(Color.fromRGB(255, 255, 0));
                    lcc.spigot().setUnbreakable(true);
                    d.setItemMeta(lcc);
                    ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                    LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                    lcl.setColor(Color.fromRGB(255, 255, 0));
                    lcl.spigot().setUnbreakable(true);
                    f.setItemMeta(lcl);
                    ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                    LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                    lcb.spigot().setUnbreakable(true);
                    lcb.setColor(Color.fromRGB(255, 255, 0));
                    g.setItemMeta(lcb);
                    forplayer.getInventory().setBoots(g);
                    forplayer.getInventory().setLeggings(f);
                    forplayer.getInventory().setChestplate(d);
                    forplayer.getInventory().setHelmet(s);
                }
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Integer xp = new Random().nextInt(20) + 10;
                    Integer coin = new Random().nextInt(5) + 10;
                    player.sendMessage("§b+" + xp + " 起床战争经验 (时长奖励)");
                    player.sendMessage("§6+" + coin + " 硬币 (时长奖励)");

                    PlayerDataManage playerDataManage = new PlayerDataManage();
                    playerDataManage.addPlayerXP(player, xp);
                    playerDataManage.addPlayerCoins(player, coin);
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 1200L, new Random().nextInt(15000));
        new BukkitRunnable() {


            @Override
            public void run() {
                if (new Random().nextInt(2) == 1) {
                    Bukkit.broadcastMessage("§c§l如果你在游玩过程中断开连接，输入/rejoin来重新进入！");
                } else {
                    Bukkit.broadcastMessage("§c§l游戏内禁止队伍联合，输入/report来举报违规玩家！");
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), new Random().nextInt(30000), new Random().nextInt(300000));


        //Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
        BedWarsListener.start();
        Generator.start();
        for (Player p : Bukkit.getOnlinePlayers()) {
            NameTAG.setTagPrefix(p.getName(), getcoreboard().getEntryTeam(p.getName()).getName(), getcoreboard().getEntryTeam(p.getName()).getPrefix());
            new BukkitRunnable() {
                @Override
                public void run() {
                    TAB.set(p, "     §b§l你正在§e§lLemonCraft.cn§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(p.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(p.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(p.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store.LemonCraft.cn");

                }
            }.runTaskTimer(plugin,0L,80L);
            new BukkitRunnable() {
                Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective objective = scoreboard1.registerNewObjective(p.getName(), p.getName());

                @Override
                public void run() {
                    ArrayList<String> Board = new ArrayList<>();

                    try {
                        objective.unregister();
                        objective = scoreboard1.registerNewObjective(p.getName(), p.getName());
                    } catch (NullPointerException n) {

                    }

                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);


                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String date = "§7" + formatter.format(calendar.getTime());  //日期
                    Board.add(date + " §8m" + config.getString("Map.mini"));
                    Board.add("§b ");
                    int fen = BedWars.Listenertime / 60;
                    int miao = fen * 60;
                    miao = BedWars.Listenertime - miao;
                    String fenstr = String.valueOf(fen);
                    String miaostr = String.valueOf(miao);
                    if (fen == 9) {
                        fenstr = "09";
                    }
                    if (fen == 8) {
                        fenstr = "08";
                    }
                    if (fen == 7) {
                        fenstr = "07";
                    }
                    if (fen == 6) {
                        fenstr = "06";
                    }
                    if (fen == 5) {
                        fenstr = "05";
                    }
                    if (fen == 4) {
                        fenstr = "04";
                    }
                    if (fen == 3) {
                        fenstr = "03";
                    }
                    if (fen == 2) {
                        fenstr = "02";
                    }
                    if (fen == 1) {
                        fenstr = "01";
                    }
                    if (fen == 0) {
                        fenstr = "00";
                    }
                    if (miao == 9) {
                        miaostr = "09";
                    }
                    if (miao == 8) {
                        miaostr = "08";
                    }
                    if (miao == 7) {
                        miaostr = "07";
                    }
                    if (miao == 6) {
                        miaostr = "06";
                    }
                    if (miao == 5) {
                        miaostr = "05";
                    }
                    if (miao == 4) {
                        miaostr = "04";
                    }
                    if (miao == 3) {
                        miaostr = "03";
                    }
                    if (miao == 2) {
                        miaostr = "02";
                    }
                    if (miao == 1) {
                        miaostr = "01";
                    }
                    if (miao == 0) {
                        miaostr = "00";
                    }

                    Board.add("§f" + BedWars.Listenername + " - §a" + fenstr + ":" + miaostr);
                    Board.add("§1 ");
                    Team red = GameStart.getcoreboard().getTeam("红队");
                    Team blue = GameStart.getcoreboard().getTeam("蓝队");
                    if (Objects.equals(red.getDisplayName(), "yes")) {
                        Board.add(red.getPrefix() + "§f" + red.getName() + ": §a✔ " + isyou(red, p.getName()));
                    } else if (Objects.equals(red.getDisplayName(), "0")) {
                        Board.add(red.getPrefix() + "§f" + red.getName() + ": §c✖" + " " + isyou(red, p.getName()));
                    } else {
                        Board.add(red.getPrefix() + "§f" + red.getName() + ": §a" + red.getDisplayName() + " " + isyou(red, p.getName()));
                    }
                    if (Objects.equals(blue.getDisplayName(), "yes")) {
                        Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a✔ " + isyou(blue, p.getName()));
                    } else if (Objects.equals(blue.getDisplayName(), "0")) {
                        Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §c✖" + " " + isyou(blue, p.getName()));
                    } else {
                        Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a" + blue.getDisplayName() + " " + isyou(blue, p.getName()));
                    }
                    if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否为4v4
                        Team green = GameStart.getcoreboard().getTeam("绿队");
                        Team yellow = GameStart.getcoreboard().getTeam("黄队");
                        if (Objects.equals(green.getDisplayName(), "yes")) {
                            Board.add(green.getPrefix() + "§f" + green.getName() + ": §a✔ " + isyou(green, p.getName()));
                        } else if (Objects.equals(green.getDisplayName(), "0")) {
                            Board.add(green.getPrefix() + "§f" + green.getName() + ": §c✖ " + isyou(green, p.getName()));
                        } else {
                            Board.add(green.getPrefix() + "§f" + green.getName() + ": §a" + green.getDisplayName() + " " + isyou(green, p.getName()));
                        }
                        if (Objects.equals(yellow.getDisplayName(), "yes")) {
                            Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a✔ " + isyou(yellow, p.getName()));
                        } else if (Objects.equals(yellow.getDisplayName(), "0")) {
                            Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §c✖" + " " + isyou(yellow, p.getName()));
                        } else {
                            Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a" + yellow.getDisplayName() + " " + isyou(yellow, p.getName()));
                        }
                        if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4")) {//判断是否为4v4v4v4
                            Team arua = GameStart.getcoreboard().getTeam("青队");
                            Team white = GameStart.getcoreboard().getTeam("白队");
                            Team pink = GameStart.getcoreboard().getTeam("粉队");
                            Team gray = GameStart.getcoreboard().getTeam("灰队");
                            
                            if (Objects.equals(arua.getDisplayName(), "yes")) {
                                Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a✔ " + isyou(arua, p.getName()));
                            } else if (Objects.equals(arua.getDisplayName(), "0")) {
                                Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §c✖ " + isyou(arua, p.getName()));
                            } else {
                                Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a" + arua.getDisplayName() + " " + isyou(arua, p.getName()));
                            }
                            if (Objects.equals(white.getDisplayName(), "yes")) {
                                Board.add(white.getPrefix() + "§f" + white.getName() + ": §a✔ " + isyou(white, p.getName()));
                            } else if (Objects.equals(white.getDisplayName(), "0")) {
                                Board.add(white.getPrefix() + "§f" + white.getName() + ": §c✖ " + isyou(white, p.getName()));
                            } else {
                                Board.add(white.getPrefix() + "§f" + white.getName() + ": §a" + white.getDisplayName() + " " + isyou(white, p.getName()));
                            }
                            if (Objects.equals(pink.getDisplayName(), "yes")) {
                                Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a✔ " + isyou(pink, p.getName()));
                            } else if (Objects.equals(pink.getDisplayName(), "0")) {
                                Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §c✖ " + isyou(pink, p.getName()));
                            } else {
                                Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a" + pink.getDisplayName() + " " + isyou(pink, p.getName()));
                            }
                            if (Objects.equals(gray.getDisplayName(), "yes")) {
                                Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a✔ " + isyou(gray, p.getName()));
                            } else if (Objects.equals(gray.getDisplayName(), "0")) {
                                Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §c✖ " + isyou(gray, p.getName()));
                            } else {
                                Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a" + gray.getDisplayName() + " " + isyou(gray, p.getName()));
                            }
                        }
                    }
                    
                    if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") || Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                        Board.add("§a ");
                        Board.add("§f击杀数: §a" + BedWars.kill.get(p.getName()));
                        Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(p.getName()));
                        Board.add("§f破坏床数: §a" + BedWars.breakbed.get(p.getName()));
                    }
                    if (Objects.equals(config.getString("Map.ModeType"), "4v4")) {
                        Board.add("§f击杀数: §a" + BedWars.kill.get(p.getName()));
                        Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(p.getName()));
                    }
                    Board.add("§f ");
                    Board.add("§eLemonCraft.cn");

                    objective.setDisplayName("§e§l起床战争");
                    for (int i = 0; i < Board.size(); i++) {
                        objective.getScore(Board.get(i)).setScore(-i + Board.size());

                    }
                    p.setScoreboard(scoreboard1);

                }


            }.runTaskTimer(plugin, 15L, 15L);
            try {

                if (plugin.getConfig().getString("Map.SpecialMode").equalsIgnoreCase("Rush")) {
                    RushMode.start();
                }
            } catch (NullPointerException e){

            }
            for (Team t : getcoreboard().getTeams()) {
                if (!t.getDisplayName().equalsIgnoreCase("yes")) {
                    if (!t.getName().equalsIgnoreCase("旁观者")) {
                        p.sendMessage("");
                        p.sendMessage("§f§l团灭 > " + t.getSuffix() + t.getName() + " §c已被淘汰！");
                        p.sendMessage("");

                    }

                }
            }

        }
    }
}