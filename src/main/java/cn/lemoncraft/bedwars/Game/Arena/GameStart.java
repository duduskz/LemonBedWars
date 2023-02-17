package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Game;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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

        if (Objects.equals(config.getString("Map.ModeType"), "4v4v4v4")) {//判断是否为4v4v4v4

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

            Team spectator = scoreboard.registerNewTeam("旁观者");
            spectator.setAllowFriendlyFire(false);
            spectator.setCanSeeFriendlyInvisibles(true);
            spectator.setPrefix("§7旁观者 ");
            spectator.setSuffix("§7");
            spectator.setDisplayName("0");

            for (int i = 0; i < playerList.size(); i++) {
                Player forplayer = playerList.get(i);

                BedWars.Fireballcooldown.put(forplayer, 0);
                BedWars.kill.put(forplayer.getName(),0);
                BedWars.finalkill.put(forplayer.getName(),0);
                BedWars.breakbed.put(forplayer.getName(),0);
                BedWars.shoutcd.put(forplayer.getName(), 0);
                BedWars.xp.put(forplayer.getName(), 0);
                BedWars.coins.put(forplayer.getName(),0);
                BedWars.shears.put(forplayer.getName(), false);
                BedWars.axe.put(forplayer.getName(), 0);
                BedWars.pickaxe.put(forplayer.getName(), 0);
                BedWars.playeradditem.replace(forplayer.getName(), "空");
                PlayerDataManage.createplayershop(forplayer);
                forplayer.getInventory().clear();
                ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_SWORD);
                ItemMeta itemMeta = WOOD_SWORD.getItemMeta();
                itemMeta.spigot().setUnbreakable(true);
                Objective objective2 = scoreboard.registerNewObjective("B","B");
                objective2.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                WOOD_SWORD.setItemMeta(itemMeta);
                forplayer.getInventory().addItem(WOOD_SWORD);
                Game item = new Game();
                forplayer.getInventory().setItem(8,item.getItem("指南针"));
                if (i % playerList.size() == 0) {
                    getcoreboard().getTeam("红队").addEntry(forplayer.getName());
                    String[] spawn = BedWars.getLocaton(config.getString("Map.红队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    red.setDisplayName("yes");
                    //ItemStack RED_HELMET = new ItemStack(Material.WOOD_SWORD);
                    //ItemMeta RED_HELMET_itemMeta = WOOD_SWORD.getItemMeta();
                    //itemMeta.spigot().setUnbreakable(true);
                    //RED_HELMET_itemMeta.;
                    //RED_HELMET.setItemMeta(itemMeta);
                }
                if (i % playerList.size() == 1) {
                    getcoreboard().getTeam("蓝队").addEntry(forplayer.getName());
                    String[] spawn = BedWars.getLocaton(config.getString("Map.蓝队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    blue.setDisplayName("yes");
                }
                if (i % playerList.size() == 2) {
                    getcoreboard().getTeam("绿队").addEntry(forplayer.getName());
                    String[] spawn = BedWars.getLocaton(config.getString("Map.绿队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    green.setDisplayName("yes");
                }
                if (i % playerList.size() == 3) {
                    getcoreboard().getTeam("黄队").addEntry(forplayer.getName());
                    String[] spawn = BedWars.getLocaton(config.getString("Map.黄队.Spawn"));
                    forplayer.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    yellow.setDisplayName("yes");
                }
            }
            new BukkitRunnable(){
                @Override
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§b+25 起床战争经验 (时长奖励)");
                        player.sendMessage("§6+10 硬币 (时长奖励)");

                        PlayerDataManage playerDataManage = new PlayerDataManage();
                        playerDataManage.addPlayerXP(player, 25);
                        playerDataManage.addPlayerCoins(player, 10);
                    }
                }
            }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class),1200L,6000L);
            new BukkitRunnable(){


                @Override
                public void run() {
                    Bukkit.broadcastMessage("§c如果你在游玩过程中断开连接，输入/rejoin来重新进入！");
                }
            }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class),2000L,80000L);
            BedWarsListener.start();
            new BukkitRunnable() {
                @Override
                public void run() {


                    for (Player p : Bukkit.getOnlinePlayers()) {
                        //Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();

                        //Objective objective1 = scoreboard2.registerNewObjective(RandomString, RandomString);

                        //objective1.setDisplayName("§b§l你正在 §e§lLemonCraft §b§l上体验 §e§l起床战争");
                        //objective1.setDisplaySlot(DisplaySlot.BELOW_NAME);
                        //objective1.getScore("§b击杀数：§e"+BedWars.kill.get(p.getName())+" §b最终击杀数：§e"+BedWars.finalkill.get(p.getName())+" §b破坏床数：§e"+BedWars.breakbed.get(p.getName())).setScore(2);
                        //objective1.getScore("§b ").setScore(2);
                        //objective1.getScore("§a§lRanks以及更多，§b§l请访问STORE.LEMONCRAFT.CN").setScore(2);
                        //objective1.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                        Scoreboard scoreboard1 = Bukkit.getScoreboardManager().getNewScoreboard();
                        Objective objective = scoreboard1.registerNewObjective(RandomString, RandomString);
                        objective.setDisplayName("§e起床战争");
                        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                        ArrayList<String> Board = new ArrayList<>();
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String date = "§7" + formatter.format(calendar.getTime());  //日期fuw
                        Board.add(date + " §8" + config.getString("Map.mini"));
                        Board.add("§b ");
                        int fen = BedWars.Listenertime/60;
                        int miao = fen * 60;
                        miao = BedWars.Listenertime - miao;
                        String fenstr = String.valueOf(fen);
                        String miaostr = String.valueOf(miao);
                        if (fen == 9){
                            fenstr = "09";
                        }
                        if (fen == 8){
                            fenstr = "08";
                        }
                        if (fen == 7){
                            fenstr = "07";
                        }
                        if (fen == 6){
                            fenstr = "06";
                        }
                        if (fen == 5){
                            fenstr = "05";
                        }
                        if (fen == 4){
                            fenstr = "04";
                        }
                        if (fen == 3){
                            fenstr = "03";
                        }
                        if (fen == 2){
                            fenstr = "02";
                        }
                        if (fen == 1){
                            fenstr = "01";
                        }
                        if (fen == 0){
                            fenstr = "00";
                        }
                        if (miao == 9){
                            miaostr = "09";
                        }
                        if (miao == 8){
                            miaostr = "08";
                        }
                        if (miao == 7){
                            miaostr = "07";
                        }
                        if (miao == 6){
                            miaostr = "06";
                        }
                        if (miao == 5){
                            miaostr = "05";
                        }
                        if (miao == 4){
                            miaostr = "04";
                        }
                        if (miao == 3){
                            miaostr = "03";
                        }
                        if (miao == 2){
                            miaostr = "02";
                        }
                        if (miao == 1){
                            miaostr = "01";
                        }
                        if (miao == 0){
                            miaostr = "00";
                        }

                        Board.add("§f"+BedWars.Listenername+" - §a"+fenstr+":"+miaostr);
                        Board.add("§1 ");
                        if (Objects.equals(red.getDisplayName(), "yes")) {
                            Board.add(red.getPrefix() + "§f" + red.getName() + ": §a✔ " + isyou(red, p.getName()));
                        } else if (Objects.equals(red.getDisplayName(), "0")) {
                            Board.add(red.getPrefix() + "§f" + red.getName() + ": §c✖");
                        } else {
                            Board.add(red.getPrefix() + "§f" + red.getName() + ": §a" + red.getDisplayName());
                        }
                        if (Objects.equals(blue.getDisplayName(), "yes")) {
                            Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a✔ " + isyou(blue, p.getName()));
                        } else if (Objects.equals(blue.getDisplayName(), "0")) {
                            Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §c✖");
                        } else {
                            Board.add(blue.getPrefix() + "§f" + blue.getName() + ": §a" + blue.getDisplayName());
                        }
                        if (Objects.equals(green.getDisplayName(), "yes")) {
                            Board.add(green.getPrefix() + "§f" + green.getName() + ": §a✔ " + isyou(green, p.getName()));
                        } else if (Objects.equals(green.getDisplayName(), "0")) {
                            Board.add(green.getPrefix() + "§f" + green.getName() + ": §c✖");
                        } else {
                            Board.add(green.getPrefix() + "§f" + green.getName() + ": §a" + green.getDisplayName());
                        }
                        if (Objects.equals(yellow.getDisplayName(), "yes")) {
                            Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a✔ " + isyou(yellow, p.getName()));
                        } else if (Objects.equals(yellow.getDisplayName(), "0")) {
                            Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §c✖");
                        } else {
                            Board.add(yellow.getPrefix() + "§f" + yellow.getName() + ": §a" + yellow.getDisplayName());
                        }
                        Board.add("§a ");
                        Board.add("§f击杀数: §a"+BedWars.kill.get(p.getName()));
                        Board.add("§f最终击杀数: §a"+BedWars.finalkill.get(p.getName()));
                        Board.add("§f破坏床数: §a"+BedWars.breakbed.get(p.getName()));
                        Board.add("§f ");
                        Board.add("§eLemonCraft.cn");
                        for (int i = 0; i < Board.size(); i++) {
                            scoreboard1.resetScores(Board.get(i));
                            objective.getScore(Board.get(i)).setScore(-i + Board.size());
                        }
                        p.setScoreboard(scoreboard1);
                        //p.setScoreboard(scoreboard2);
                    }
                }

            }.runTaskTimer(plugin, 15L, 15L);
        }
    }
}