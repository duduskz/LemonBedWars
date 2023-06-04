package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.API.Event.GameStartEvent;
import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.SpecialMode.RushMode;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
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
    private static final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

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


        for (NPCRegistry r : CitizensAPI.getNPCRegistries()) {
            r.deregisterAll();
        }
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


        List<Player> playerList = new ArrayList<>(Bukkit.getServer().getOnlinePlayers()); //获取所有在线玩家


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

            if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && !Objects.equals(config.getString("Map.ModeType"), "3v3v3v3") && !Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否不为4v4v4v4
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
        for (int i = 0; i < playerList.size(); i++)

        {
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
            forplayer.setNoDamageTicks(120);
            BedWars.playeradditem.put(forplayer.getName(), "空");

            forplayer.getInventory().clear();
            ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
            ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
            itemMeta.spigot().setUnbreakable(true);

            WOOD_SWORD.setItemMeta(itemMeta);
            forplayer.getInventory().addItem(WOOD_SWORD);
            Game item = new Game();
            forplayer.getInventory().setItem(8, item.getItem("指南针"));
            if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                Team green = getcoreboard().getTeam("绿队");
                Team yellow = getcoreboard().getTeam("黄队");
                    if (i % 4 == 0) {
                        getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        red.setDisplayName("yes");
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        BedWars.sharp.put(red.getName(), false);
                        BedWars.protectUpgrade.put(red.getName(), 0);
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
                    if (i % 4 == 1) {
                        getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        blue.setDisplayName("yes");
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                        lch.setColor(Color.fromRGB(0, 0, 255));
                        s.setItemMeta(lch);
                        BedWars.sharp.put(blue.getName(), false);
                        BedWars.protectUpgrade.put(blue.getName(), 0);
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
                    if (i % 4 == 2) {
                        getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        green.setDisplayName("yes");
                        BedWars.sharp.put(green.getName(), false);
                        BedWars.protectUpgrade.put(green.getName(), 0);
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
                    if (i % 4 == 3) {
                        getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        yellow.setDisplayName("yes");
                        BedWars.sharp.put(yellow.getName(), false);
                        BedWars.protectUpgrade.put(yellow.getName(), 0);
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
            } else {
                if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && !Objects.equals(config.getString("Map.ModeType"), "4v4") && !Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                    Team green = getcoreboard().getTeam("绿队");
                    Team yellow = getcoreboard().getTeam("黄队");
                    Team arua = getcoreboard().getTeam("青队");
                    Team white = getcoreboard().getTeam("白队");
                    Team pink = getcoreboard().getTeam("粉队");
                    Team gray = getcoreboard().getTeam("灰队");
                    if (i % 8 == 0) {
                        getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        red.setDisplayName("yes");
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        BedWars.sharp.put(red.getName(), false);
                        BedWars.protectUpgrade.put(red.getName(), 0);
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
                    if (i % 8 == 1) {
                        getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.蓝队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        blue.setDisplayName("yes");
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                        lch.setColor(Color.fromRGB(0, 0, 255));
                        s.setItemMeta(lch);
                        BedWars.sharp.put(blue.getName(), false);
                        BedWars.protectUpgrade.put(blue.getName(), 0);
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
                    if (i % 8 == 2) {
                        getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.绿队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        green.setDisplayName("yes");
                        BedWars.sharp.put(green.getName(), false);
                        BedWars.protectUpgrade.put(green.getName(), 0);
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
                    if (i % 8 == 3) {
                        getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.黄队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        yellow.setDisplayName("yes");
                        BedWars.sharp.put(yellow.getName(), false);
                        BedWars.protectUpgrade.put(yellow.getName(), 0);
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
                    if (i % 8 == 4) {
                        getcoreboard().getTeam("青队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.青队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        arua.setDisplayName("yes");
                        BedWars.sharp.put(arua.getName(), false);
                        BedWars.protectUpgrade.put(arua.getName(), 0);
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                        lch.setColor(Color.fromRGB(0, 255, 255));
                        lch.spigot().setUnbreakable(true);
                        s.setItemMeta(lch);
                        ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                        LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                        lcc.setColor(Color.fromRGB(0, 255, 255));
                        lcc.spigot().setUnbreakable(true);
                        d.setItemMeta(lcc);
                        ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                        LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                        lcl.setColor(Color.fromRGB(0, 255, 255));
                        lcl.spigot().setUnbreakable(true);
                        f.setItemMeta(lcl);
                        ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                        LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                        lcb.spigot().setUnbreakable(true);
                        lcb.setColor(Color.fromRGB(0, 255, 255));
                        g.setItemMeta(lcb);
                        forplayer.getInventory().setBoots(g);
                        forplayer.getInventory().setLeggings(f);
                        forplayer.getInventory().setChestplate(d);
                        forplayer.getInventory().setHelmet(s);
                    }
                    if (i % 8 == 5) {
                        getcoreboard().getTeam("白队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.白队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        white.setDisplayName("yes");
                        BedWars.sharp.put(white.getName(), false);
                        BedWars.protectUpgrade.put(white.getName(), 0);
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                        lch.setColor(Color.fromRGB(255, 255, 255));
                        lch.spigot().setUnbreakable(true);
                        s.setItemMeta(lch);
                        ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                        LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                        lcc.setColor(Color.fromRGB(255, 255, 255));
                        lcc.spigot().setUnbreakable(true);
                        d.setItemMeta(lcc);
                        ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                        LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                        lcl.setColor(Color.fromRGB(255, 255, 255));
                        lcl.spigot().setUnbreakable(true);
                        f.setItemMeta(lcl);
                        ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                        LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                        lcb.spigot().setUnbreakable(true);
                        lcb.setColor(Color.fromRGB(255, 255, 255));
                        g.setItemMeta(lcb);
                        forplayer.getInventory().setBoots(g);
                        forplayer.getInventory().setLeggings(f);
                        forplayer.getInventory().setChestplate(d);
                        forplayer.getInventory().setHelmet(s);
                    }
                    if (i % 8 == 6) {
                        getcoreboard().getTeam("粉队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.粉队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        pink.setDisplayName("yes");
                        BedWars.sharp.put(pink.getName(), false);
                        BedWars.protectUpgrade.put(pink.getName(), 0);
                        ItemStack s = new ItemStack(Material.LEATHER_HELMET);
                        LeatherArmorMeta lch = (LeatherArmorMeta) s.getItemMeta();
                        lch.setColor(Color.fromRGB(255, 200, 200));
                        lch.spigot().setUnbreakable(true);
                        s.setItemMeta(lch);
                        ItemStack d = new ItemStack(Material.LEATHER_CHESTPLATE);
                        LeatherArmorMeta lcc = (LeatherArmorMeta) s.getItemMeta();
                        lcc.setColor(Color.fromRGB(255, 200, 200));
                        lcc.spigot().setUnbreakable(true);
                        d.setItemMeta(lcc);
                        ItemStack f = new ItemStack(Material.LEATHER_LEGGINGS);
                        LeatherArmorMeta lcl = (LeatherArmorMeta) s.getItemMeta();
                        lcl.setColor(Color.fromRGB(255, 200, 200));
                        lcl.spigot().setUnbreakable(true);
                        f.setItemMeta(lcl);
                        ItemStack g = new ItemStack(Material.LEATHER_BOOTS);
                        LeatherArmorMeta lcb = (LeatherArmorMeta) s.getItemMeta();
                        lcb.spigot().setUnbreakable(true);
                        lcb.setColor(Color.fromRGB(255, 200, 200));
                        g.setItemMeta(lcb);
                        forplayer.getInventory().setBoots(g);
                        forplayer.getInventory().setLeggings(f);
                        forplayer.getInventory().setChestplate(d);
                        forplayer.getInventory().setHelmet(s);
                    }
                    if (i % 8 == 7) {
                        getcoreboard().getTeam("灰队").addEntry(forplayer.getName());
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.灰队.Spawn"));
                        forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                        gray.setDisplayName("yes");
                        BedWars.sharp.put(gray.getName(), false);
                        BedWars.protectUpgrade.put(gray.getName(), 0);
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
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage("§b+25 起床战争经验 (时长奖励)");
                        player.sendMessage("§6+10 硬币 (时长奖励)");
                    } else {
                        player.sendMessage("§b+25 BedWars XP (Time Reward)");
                        player.sendMessage("§6+10 Coins (Time Reward)");
                    }

                    PlayerDataManage.addPlayerXP(player, 25);
                    PlayerDataManage.addPlayerCoins(player, 10);
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 5000 ,5000);
        new BukkitRunnable() {


            @Override
            public void run() {
                if (new Random().nextInt(2) == 1) {
                    Bukkit.broadcastMessage("§c§l如果你在游玩过程中断开连接，输入/rejoin来重新进入！");
                } else {
                    Bukkit.broadcastMessage("§c§l游戏内禁止队伍联合，输入/report来举报违规玩家！");
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 200 + new Random().nextInt(3000), new Random().nextInt(6500));


        //Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
        BedWarsListener.start();
        Generator.start();
        for (Player p : Bukkit.getOnlinePlayers()) {
            NameTAG.setTagPrefix(p.getName(), getcoreboard().getEntryTeam(p.getName()).getName(), getcoreboard().getEntryTeam(p.getName()).getPrefix());
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (PlayerDataManage.getPlayerLang(p).equalsIgnoreCase("zhcn")) {
                        TAB.set(p, "     §b§l你正在§e§l" + BedWars.serverip + "§b§l上进行游戏\n", "\n§b击杀数: §e" + BedWars.kill.get(p.getName()) + " §b最终击杀数: §e" + BedWars.finalkill.get(p.getName()) + " §b破坏床数: §e" + BedWars.breakbed.get(p.getName()) + "\n\n     §a§lRank以及更多！§c§l请访问Store." + BedWars.serverip + "");
                    } else {
                        TAB.set(p, "     §b§lYou are playing on §e§l" + BedWars.serverip + "\n", "\n§bKills: §e" + BedWars.kill.get(p.getName()) + " §bFlial Kills: §e" + BedWars.finalkill.get(p.getName()) + " §bDestroyed Beds: §e" + BedWars.breakbed.get(p.getName()) + "\n\n     §a§lRank & More! §c§lStore." + BedWars.serverip + "");
                    }
                }
            }.runTaskTimer(plugin,0L,80L);

            for (Team t : getcoreboard().getTeams()) {
                if (!t.getDisplayName().equalsIgnoreCase("yes")) {
                    if (!t.getName().equalsIgnoreCase("旁观者")) {
                        p.sendMessage("");
                        if (PlayerDataManage.getPlayerLang(p).equalsIgnoreCase("zhcn")) {
                            p.sendMessage("§f§l团灭 > " + t.getSuffix() + t.getName() + " §c已被淘汰！");
                        } else if (PlayerDataManage.getPlayerLang(p).equalsIgnoreCase("en")) {
                            p.sendMessage("§f§lTEAM ELIMINATEQ > " + t.getSuffix() + t.getName() + " §chas been eliminated!");
                        }
                        p.sendMessage("");

                    }

                }
            }
            ShowScoreBoard(p);
                if (PlayerDataManage.getPlayerLang(p).equalsIgnoreCase("zhcn")) {
                    p.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    p.sendMessage("§f                                §l起床战争");
                    p.sendMessage("");
                    p.sendMessage("§e§l      保护你的床并破坏敌人的床。通过从资源刷新点获取铁锭，金锭");
                    p.sendMessage("§e§l              绿宝石和钻石来升级，使自身和队伍变得更强。");
                    p.sendMessage("");
                    p.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                } else if (PlayerDataManage.getPlayerLang(p).equalsIgnoreCase("en")) {
                    p.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                    p.sendMessage("§f                                §lBED WARS");
                    p.sendMessage("");
                    p.sendMessage("§e§l    Protect your bed and destroy the enemy beds.");
                    p.sendMessage("§e§l      Upgrade yourself and your team by collecting");
                    p.sendMessage("§e§l   Iron, Gold, Emerald, and Diamond from generators");
                    p.sendMessage("§e§l             to access powerful upgrades.");
                    p.sendMessage("");
                    p.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                }
            try {

                if (plugin.getConfig().getString("Map.SpecialMode").equalsIgnoreCase("Rush")) {
                    RushMode.start();
                }
            } catch (NullPointerException ignored){

                }
            }
            Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(config.getString("Map.Name")));
            ItemDrop.start();
            NPCcreate.NPCstart();
        }
    public static void ShowScoreBoard(Player player) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {

            ArrayList<String> Board = new ArrayList<>();

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
                Board.add(red.getPrefix() + "§f" + red.getName() + ": §a✔ " + isyou(red, player.getName()));
            } else if (Objects.equals(red.getDisplayName(), "0")) {
                Board.add(red.getPrefix() + "§f" + red.getName() + ": §c✖" + " " + isyou(red, player.getName()));
            } else {
                Board.add(red.getPrefix() + "§f" + red.getName() + ": §a" + red.getDisplayName() + " " + isyou(red, player.getName()));
            }
            if (Objects.equals(blue.getDisplayName(), "yes")) {
                Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a✔ " + isyou(blue, player.getName()));
            } else if (Objects.equals(blue.getDisplayName(), "0")) {
                Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §c✖" + " " + isyou(blue, player.getName()));
            } else {
                Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a" + blue.getDisplayName() + " " + isyou(blue, player.getName()));
            }
            if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否为4v4
                Team green = GameStart.getcoreboard().getTeam("绿队");
                Team yellow = GameStart.getcoreboard().getTeam("黄队");
                if (Objects.equals(green.getDisplayName(), "yes")) {
                    Board.add(green.getPrefix() + "§f" + green.getName() + ": §a✔ " + isyou(green, player.getName()));
                } else if (Objects.equals(green.getDisplayName(), "0")) {
                    Board.add(green.getPrefix() + "§f" + green.getName() + ": §c✖ " + isyou(green, player.getName()));
                } else {
                    Board.add(green.getPrefix() + "§f" + green.getName() + ": §a" + green.getDisplayName() + " " + isyou(green, player.getName()));
                }
                if (Objects.equals(yellow.getDisplayName(), "yes")) {
                    Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a✔ " + isyou(yellow, player.getName()));
                } else if (Objects.equals(yellow.getDisplayName(), "0")) {
                    Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §c✖" + " " + isyou(yellow, player.getName()));
                } else {
                    Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a" + yellow.getDisplayName() + " " + isyou(yellow, player.getName()));
                }
                if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && !Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                    Team arua = GameStart.getcoreboard().getTeam("青队");
                    Team white = GameStart.getcoreboard().getTeam("白队");
                    Team pink = GameStart.getcoreboard().getTeam("粉队");
                    Team gray = GameStart.getcoreboard().getTeam("灰队");

                    if (Objects.equals(arua.getDisplayName(), "yes")) {
                        Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a✔ " + isyou(arua, player.getName()));
                    } else if (Objects.equals(arua.getDisplayName(), "0")) {
                        Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §c✖ " + isyou(arua, player.getName()));
                    } else {
                        Board.add(arua.getPrefix() + "§f" + arua.getName() + ": §a" + arua.getDisplayName() + " " + isyou(arua, player.getName()));
                    }
                    if (Objects.equals(white.getDisplayName(), "yes")) {
                        Board.add(white.getPrefix() + "§f" + white.getName() + ": §a✔ " + isyou(white, player.getName()));
                    } else if (Objects.equals(white.getDisplayName(), "0")) {
                        Board.add(white.getPrefix() + "§f" + white.getName() + ": §c✖ " + isyou(white, player.getName()));
                    } else {
                        Board.add(white.getPrefix() + "§f" + white.getName() + ": §a" + white.getDisplayName() + " " + isyou(white, player.getName()));
                    }
                    if (Objects.equals(pink.getDisplayName(), "yes")) {
                        Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a✔ " + isyou(pink, player.getName()));
                    } else if (Objects.equals(pink.getDisplayName(), "0")) {
                        Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §c✖ " + isyou(pink, player.getName()));
                    } else {
                        Board.add(pink.getPrefix() + "§f" + pink.getName() + ": §a" + pink.getDisplayName() + " " + isyou(pink, player.getName()));
                    }
                    if (Objects.equals(gray.getDisplayName(), "yes")) {
                        Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a✔ " + isyou(gray, player.getName()));
                    } else if (Objects.equals(gray.getDisplayName(), "0")) {
                        Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §c✖ " + isyou(gray, player.getName()));
                    } else {
                        Board.add(gray.getPrefix() + "§f" + gray.getName() + ": §a" + gray.getDisplayName() + " " + isyou(gray, player.getName()));
                    }
                }
            }

            if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && Objects.equals(config.getString("Map.ModeType"), "3v3v3v3")) {//判断是否为4v4v4v4
                Board.add("§a ");

                if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {

                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                    Board.add("§f破坏床数: §a" + BedWars.breakbed.get(player.getName()));
                } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")){
                    Board.add("§fKills: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§fFinal Kills: §a" + BedWars.finalkill.get(player.getName()));
                    Board.add("§fBeds Broken: §a" + BedWars.breakbed.get(player.getName()));
                }
            }
            if (Objects.equals(config.getString("Map.ModeType"), "4v4")) {
                Board.add("§a ");

                if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    Board.add("§f击杀数: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§f最终击杀数: §a" + BedWars.finalkill.get(player.getName()));
                } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")) {
                    Board.add("§fKills: §a" + BedWars.kill.get(player.getName()));
                    Board.add("§fFinal Kills: §a" + BedWars.finalkill.get(player.getName()));

                }
            }
            ScoreboardManager sm = null;
            if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("en")) {
                Board.replaceAll(s -> s.replace("红队", "Red").replace("红 ", "R ").replace("蓝队", "Blue").replace("蓝 ", "B ").replace("绿队", "Green").replace("绿 ", "G ").replace("黄队", "Yellow").replace("黄 ", "Y ").replace("青队", "Aura").replace("青 ", "A ").replace("白队", "White").replace("白 ", "W ").replace("粉 ", "P ").replace("粉队", "Pink").replace("粉 ", "P ").replace("灰队", "Gray").replace("灰 ", "G ").replace("你", "YOU").replace("钻石生成点II级 -", "Diamond II in").replace("钻石生成点III级 -", "Diamond III in").replace("绿宝石生成点II级 -", "Emerald II in").replace("绿宝石生成点III级 -", "Emerald III in").replace("床自毁 -", "Bed Gone in").replace("绝杀模式 -", "Sudden Death in").replace("游戏结束 -", "Game End in"));
                Board.add("§f ");
                Board.add("§e" + BedWars.serverip + "");

                sm = new ScoreboardManager(plugin, "§e§lBED WARS",Board.toArray(new String[0]));
            } else if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                Board.add("§f ");
                Board.add("§e" + BedWars.serverip + "");
                sm = new ScoreboardManager(plugin, "§e§l起床战争",Board.toArray(new String[0]));
            }
            assert sm != null;
            sm.display(player);


        }, 0, 15L);

    }
}