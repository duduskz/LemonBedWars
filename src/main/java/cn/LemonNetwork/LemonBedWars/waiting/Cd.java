package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.lemonbedwars.Utils.TitleUtil;
import cn.lemonnetwork.lemonbedwars.Utils.WaitingScoreBoard;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cd {
    public static void start(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Plugin plugin = JavaPlugin.getPlugin(LemonBedWars.class);
        String date = "§7" + formatter.format(calendar.getTime());  //日期
        FileConfiguration config = plugin.getConfig();
        if (!LemonBedWars.adminStart) {
            for (Player player : Bukkit.getOnlinePlayers()) {

                String lang = PlayerDataManage.getPlayerLang(player);
                if (lang.equalsIgnoreCase("zhcn")) {
                    player.sendMessage("§e游戏将在 20 秒后开始！");
                } else if (lang.equalsIgnoreCase("en")) {
                    player.sendMessage("§eThe game starts in 20 seconds!");
                }
            }
        }

        List<Player> playerList = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
        new BukkitRunnable() {
            @Override
            public void run() {

                int online_players1 = Bukkit.getOnlinePlayers().size();  //获取在线玩家
                LemonBedWars.time = LemonBedWars.time - 1;
                ArrayList<String> zhcnscoreboard = new ArrayList<>();
                zhcnscoreboard.add(date + " §8mini" + config.getString("Map.mini"));
                zhcnscoreboard.add("§5 ");
                zhcnscoreboard.add("§f地图: §a" + config.getString("Map.Name"));
                zhcnscoreboard.add("§f玩家: §a" + online_players1 + "/" + config.getInt("Map.MaxPlayer"));
                zhcnscoreboard.add("§4 ");
                zhcnscoreboard.add("§f即将开始: §a" + LemonBedWars.time + "秒");
                zhcnscoreboard.add("§b ");
                zhcnscoreboard.add("§f模式: §a" + config.getString("Map.Mode"));
                zhcnscoreboard.add("§f版本: §7v1.0");
                zhcnscoreboard.add("§f ");
                zhcnscoreboard.add("§e" + LemonBedWars.serverip + "");
                ArrayList<String> enscoreboard = new ArrayList<>();
                enscoreboard.add(date + " §8mini" + config.getString("Map.mini"));
                enscoreboard.add("§5 ");
                enscoreboard.add("§fMap: §a" + config.getString("Map.Name"));
                enscoreboard.add("§fPlayers: §a" + online_players1 + "/" + config.getInt("Map.MaxPlayer"));
                enscoreboard.add("§4 ");
                enscoreboard.add("§fStarting in §a" + LemonBedWars.time + "s");
                enscoreboard.add("§b ");
                enscoreboard.add("§fMode: §a" + config.getString("Map.Mode"));
                enscoreboard.add("§fVersion: §7v1.0");
                enscoreboard.add("§f ");
                enscoreboard.add("§e" + LemonBedWars.serverip + "");
                for (String entry : WaitingScoreBoard.zhcnscoreboard.getEntries()) {
                    WaitingScoreBoard.zhcnscoreboard.resetScores(entry);
                }
                for (String entry : WaitingScoreBoard.enscoreboard.getEntries()) {
                    WaitingScoreBoard.enscoreboard.resetScores(entry);
                }
                for (int i = 0; i < zhcnscoreboard.size(); ++i) {
                    WaitingScoreBoard.getObjective("zhcn").getScore(zhcnscoreboard.get(i)).setScore(-i + zhcnscoreboard.size());
                }
                for (int i = 0; i < enscoreboard.size(); ++i) {
                    WaitingScoreBoard.getObjective("en").getScore(enscoreboard.get(i)).setScore(-i + enscoreboard.size());
                }
                if (!LemonBedWars.adminStart) {
                    if (Bukkit.getOnlinePlayers().size() == config.getInt("Map.NeedPlayer") - 1) {
                        ArrayList<String> zhcnscoreboard1 = new ArrayList<>();
                        ArrayList<String> enscoreboard1 = new ArrayList<>();
                        zhcnscoreboard1.add(date + " §8mini" + config.getString("Map.mini"));
                        zhcnscoreboard1.add("§5 ");
                        zhcnscoreboard1.add("§f地图: §a" + config.getString("Map.Name"));
                        zhcnscoreboard1.add("§f玩家: §a" + Bukkit.getOnlinePlayers().size() + "/" + config.getInt("Map.MaxPlayer"));
                        zhcnscoreboard1.add("§4 ");
                        zhcnscoreboard1.add("§f等待中...");
                        zhcnscoreboard1.add("§b ");
                        zhcnscoreboard1.add("§f模式: §a" + config.getString("Map.Mode"));
                        zhcnscoreboard1.add("§f版本: §7v1.0");
                        zhcnscoreboard1.add("§f ");
                        zhcnscoreboard1.add("§e" + LemonBedWars.serverip + "");
                        enscoreboard1.add(date + " §8mini" + config.getString("Map.mini"));
                        enscoreboard1.add("§5 ");
                        enscoreboard1.add("§fMap: §a" + config.getString("Map.Name"));
                        enscoreboard1.add("§fPlayers: §a" + Bukkit.getOnlinePlayers().size() + "/" + config.getInt("Map.MaxPlayer"));
                        enscoreboard1.add("§4 ");
                        enscoreboard1.add("§fWaiting...");
                        enscoreboard1.add("§b ");
                        enscoreboard1.add("§fMode: §a" + config.getString("Map.Mode"));
                        enscoreboard1.add("§fVersion: §7v1.0");
                        enscoreboard1.add("§f ");
                        enscoreboard1.add("§e" + LemonBedWars.serverip + "");
                        for (String entry : WaitingScoreBoard.zhcnscoreboard.getEntries()) {
                            WaitingScoreBoard.zhcnscoreboard.resetScores(entry);
                        }
                        for (String entry : WaitingScoreBoard.enscoreboard.getEntries()) {
                            WaitingScoreBoard.enscoreboard.resetScores(entry);
                        }
                        for (int i = 0; i < zhcnscoreboard1.size(); ++i) {
                            WaitingScoreBoard.getObjective("zhcn").getScore(zhcnscoreboard1.get(i)).setScore(-i + zhcnscoreboard1.size());

                        }
                        for (int i = 0; i < enscoreboard1.size(); ++i) {
                            WaitingScoreBoard.getObjective("en").getScore(enscoreboard1.get(i)).setScore(-i + enscoreboard1.size());
                        }
                        for (Player p : playerList) {
                            String lang = PlayerDataManage.getPlayerLang(p);
                            if (lang.equalsIgnoreCase("zhcn")) {
                                TitleUtil.sendTitle(p, 0, 60, 0, "§c等待更多玩家进入...", "");

                                p.sendMessage("§c有人在等待过程中离开了，倒计时已取消！");
                            } else if (lang.equalsIgnoreCase("en")) {
                                TitleUtil.sendTitle(p, 0, 60, 0, "§cWaiting for more players...", "");

                                p.sendMessage("§cSomeone left while waiting, the countdown has been cancelled!");
                            }
                            LemonBedWars.time = 20;
                            p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                            cancel(); //取消事件
                        }
                    }
                }
                TitleUtil titleUtil = new TitleUtil();
                if (LemonBedWars.time == 10) { //判断倒计时时间为 10 秒


                    for (Player player : playerList) {
                        String lang = PlayerDataManage.getPlayerLang(player);
                        if (lang.equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§e游戏将在 §610 §e秒后开始！");
                        } else if (lang.equalsIgnoreCase("en")) {
                            player.sendMessage("§eThe game starts in §610 §eseconds!");
                        }
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                    }
                }

                if (LemonBedWars.time == 5) { //判断倒计时时间为 5 秒
                    for (Player player : playerList) {
                        String lang = PlayerDataManage.getPlayerLang(player);
                        if (lang.equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§e游戏将在 §c5 §e秒后开始！");
                        } else if (lang.equalsIgnoreCase("en")) {
                            player.sendMessage("§eThe game starts in §c5 §eseconds!");
                        }
                        TitleUtil.sendTitle(player, 0, 30, 0, "§e5", "");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                    }
                }
                if (LemonBedWars.time == 4) { //判断倒计时时间为 4 秒
                    for (Player player : playerList) {
                        String lang = PlayerDataManage.getPlayerLang(player);
                        if (lang.equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§e游戏将在 §c4 §e秒后开始！");
                        } else if (lang.equalsIgnoreCase("en")) {
                            player.sendMessage("§eThe game starts in §c4 §eseconds!");
                        }
                        TitleUtil.sendTitle(player, 0, 30, 0, "§e4", "");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                    }
                }
                if (LemonBedWars.time == 3) { //判断倒计时时间为 3 秒
                    for (Player player : playerList) {
                        String lang = PlayerDataManage.getPlayerLang(player);
                        if (lang.equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§e游戏将在 §c3 §e秒后开始！");
                        } else if (lang.equalsIgnoreCase("en")) {
                            player.sendMessage("§eThe game starts in §c3 §eseconds!");
                        }
                        TitleUtil.sendTitle(player, 0, 30, 0, "§c3", "");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                    }
                }
                if (LemonBedWars.time == 2) { //判断倒计时时间为 2 秒
                    for (Player player : playerList) {
                        String lang = PlayerDataManage.getPlayerLang(player);
                        if (lang.equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§e游戏将在 §c2 §e秒后开始！");
                        } else if (lang.equalsIgnoreCase("en")) {
                            player.sendMessage("§eThe game starts in §c2 §eseconds!");
                        }
                        TitleUtil.sendTitle(player, 0, 30, 0, "§c2", "");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                    }
                }
                if (LemonBedWars.time == 1) {//判断倒计时时间为 1 秒
                    for (Player player : playerList) {
                        String lang = PlayerDataManage.getPlayerLang(player);
                        if (lang.equalsIgnoreCase("zhcn")) {
                            player.sendMessage("§e游戏将在 §c1 §e秒后开始！");
                        } else if (lang.equalsIgnoreCase("en")) {
                            player.sendMessage("§eThe game starts in §c1 §eseconds!");
                        }
                        TitleUtil.sendTitle(player, 0, 20, 0, "§c1", "");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                    }
                }
                if (LemonBedWars.time == 0) { //判断倒计时时间为 0 秒
                    GameStart.start();
                    cancel(); //取消循环


                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}