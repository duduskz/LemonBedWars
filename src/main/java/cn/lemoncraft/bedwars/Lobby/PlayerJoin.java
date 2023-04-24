package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Lobby;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    private static List<String> lastStr = new ArrayList<>();

    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            FileConfiguration config = plugin.getConfig();
            String[] spawn = LocationUtil.getStringLocation(config.getString("Spawn"));
            player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
            PlayerDataManage playerDataManage = new PlayerDataManage();

            Lobby items = new Lobby();
            Scoreboard manager = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective objective = manager.registerNewObjective("§e§l起床战争", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            for (String str : lastStr) {
                manager.resetScores(str);
            }

            int xp = PlayerDataManage.getPlayerXP(player);
            int dengji = PlayerDataManage.getLevel(player);
            int dqjy = xp - ((dengji - 1) * 5000);
            int progress = dqjy % 5000;
            StringBuilder expbar = new StringBuilder();
            for (int i = 0; i < 10; i++) { // 经验条总长度为50
                if (i < progress * 10 / 5000) {
                    expbar.append("§b■"); // 已经获得的经验用■表示
                } else {
                    expbar.append("§7■"); // 尚未获得的经验用▲表示
                }
            }

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            objective.getScore("§7" + formatter.format(calendar.getTime()) + " §8miniZj2K").setScore(13);
            objective.getScore("§5 ").setScore(12);
            String level = "§f等级: §7" + dengji + "✫";
            lastStr.add(level);
            objective.getScore(level).setScore(11);
            objective.getScore("§1 ").setScore(10);

            player.sendMessage(String.valueOf(dqjy));
            String bar = "§f进度: §a" + dqjy + "§7/§b5000";
            lastStr.add(bar);
            objective.getScore(bar).setScore(9);

            String xpbar = "§8[" + expbar + "§8]";
            objective.getScore(xpbar).setScore(8);
            lastStr.add(xpbar);
            objective.getScore("§b ").setScore(7);
            String coins = "§f硬币: §6" + PlayerDataManage.getPlayerCoins(player);
            objective.getScore(coins).setScore(6);
            objective.getScore("§8 ").setScore(5);
            lastStr.add(coins);
            String kill = "§f总击杀数: §a" + PlayerDataManage.getPlayerALLData(player, "kills");
            lastStr.add(kill);
            objective.getScore(kill).setScore(3);
            String liankill = "§f总胜利数: §a" + PlayerDataManage.getPlayerALLData(player, "wins");
            lastStr.add(liankill);
            objective.getScore(liankill).setScore(2);
            objective.getScore("§f ").setScore(1);
            objective.getScore("§eLemonCraft.cn").setScore(0);
            player.setScoreboard(manager);
            player.setLevel(dengji);
            player.getInventory().clear();

            player.getInventory().setItem(0, items.getItem("游戏菜单"));

            player.getInventory().setItem(1, items.getItem("个人档案"));

            player.getInventory().setItem(2, items.getItem("商店与特效"));

            player.getInventory().setItem(8, items.getItem("选择大厅"));

            //player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));

        }
    }
}

