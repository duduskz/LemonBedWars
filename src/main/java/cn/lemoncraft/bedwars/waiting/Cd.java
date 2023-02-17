package cn.lemoncraft.bedwars.waiting;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import cn.lemoncraft.bedwars.Utils.ScoreboardUtil;
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
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        String date = "§7" + formatter.format(calendar.getTime());  //日期
        FileConfiguration config = plugin.getConfig();
        Bukkit.broadcastMessage("§e游戏将在 20 秒后开始！");

        List<Player> playerList = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
        for (Player p : playerList) {

            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
        }
        new BukkitRunnable() {
            @Override
            public void run() {

                int online_players1 = Bukkit.getOnlinePlayers().size();  //获取在线玩家
                BedWars.time = BedWars.time - 1;
                ArrayList<Player> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                ArrayList<String> scoreboard = new ArrayList<>();
                scoreboard.add(date + " §8" + config.getString("Map.mini"));
                scoreboard.add("§5 ");
                scoreboard.add("§f地图: §a" + config.getString("Map.Name"));
                scoreboard.add("§f玩家: §a" + online_players1 + "/" + config.getInt("Map.MaxPlayer"));
                scoreboard.add("§4 ");

                scoreboard.add("§f即将开始: §a" + BedWars.time + "秒");
                scoreboard.add("§b ");
                scoreboard.add("§f模式: §a" + config.getString("Map.Mode"));
                scoreboard.add("§f版本: §7v1.0");
                scoreboard.add("§f ");
                scoreboard.add("§eLemonCraft.cn");
                for (Player allplayers : players) {

                    ScoreboardUtil.setScoreBoard(allplayers, "§e起床战争", scoreboard);

                }
                if (Bukkit.getOnlinePlayers().size() == config.getInt("Map.NeedPlayer") - 1) {

                    for (Player p : playerList) {
                        p.sendTitle("§c等待更多玩家进入...", "");
                        p.sendMessage("§c有人在等待过程中离开了，倒计时已取消！");
                        BedWars.time = 20;
                        ArrayList<String> scoreboard1 = new ArrayList<>();

                        scoreboard1.add(date + " §8" + config.getString("Map.mini"));
                        scoreboard1.add("§5 ");
                        scoreboard1.add("§f地图: §a" + config.getString("Map.Name"));
                        scoreboard1.add("§f玩家: §a" + online_players1 + "/" + config.getInt("Map.MaxPlayer"));
                        scoreboard1.add("§4 ");
                        scoreboard1.add("§f等待中...");
                        scoreboard1.add("§b ");
                        scoreboard1.add("§f模式: §a" + config.getString("Map.Mode"));
                        scoreboard1.add("§f版本: §7v1.0");
                        scoreboard1.add("§f ");
                        scoreboard1.add("§eLemonCraft.cn");

                        ScoreboardUtil.setScoreBoard(p, "§e起床战争", scoreboard1);
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        cancel(); //取消事件
                    }
                }

                if (BedWars.time == 10) { //判断倒计时时间为 10 秒
                    Bukkit.broadcastMessage("§e游戏将在 §610 §e秒后开始！");

                    for (Player p : playerList) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                    }
                }

                if (BedWars.time == 5) { //判断倒计时时间为 5 秒
                    Bukkit.broadcastMessage("§e游戏将在 §c5 §e秒后开始！");
                    for (Player p : playerList) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        p.sendTitle("§a5", "");

                    }
                }
                if (BedWars.time == 4) { //判断倒计时时间为 4 秒
                    Bukkit.broadcastMessage("§e游戏将在 §c4 §e秒后开始！");
                    for (Player p : playerList) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        p.sendTitle("§a4", "");

                    }
                }
                if (BedWars.time == 3) { //判断倒计时时间为 3 秒
                    Bukkit.broadcastMessage("§e游戏将在 §c3 §e秒后开始！");
                    for (Player p : playerList) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        p.sendTitle("§c3", "");

                    }
                }
                if (BedWars.time == 2) { //判断倒计时时间为 2 秒
                    Bukkit.broadcastMessage("§e游戏将在 §c2 §e秒后开始！");
                    for (Player p : playerList) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        p.sendTitle("§c2", "");

                    }
                }
                if (BedWars.time == 1) {//判断倒计时时间为 1 秒
                    Bukkit.broadcastMessage("§e游戏将在 §c1 §e秒后开始！");
                    for (Player p : playerList) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                        p.sendTitle("§c1", "");


                    }
                }
                if (BedWars.time == 0) { //判断倒计时时间为 0 秒
                    GameStart.start();
                    cancel(); //取消循环


                }
            }
        }.runTaskTimer(plugin, 0L, 20L);

    }
}
