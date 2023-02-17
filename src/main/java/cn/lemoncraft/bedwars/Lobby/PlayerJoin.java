package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Lobby;
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

import java.text.DecimalFormat;
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

            Location location = new Location(Bukkit.getWorld("Spawn"), 0.5, 101, 0.5, 180, 0);
            player.teleport(location);

            PlayerDataManage playerDataManage = new PlayerDataManage();
            if (playerDataManage.getPlayerCoins(player) == 0) {
                playerDataManage.addPlayerCoins(player, 5000);
            }
            Lobby items = new Lobby();
            Scoreboard manager = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective objective = manager.registerNewObjective("§e起床战争", "dummy");
            List<Player> playerList = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            FileConfiguration config = plugin.getConfig();
            for (String str : lastStr) {
                manager.resetScores(str);
            }

            int xp = playerDataManage.getPlayerXP(player);
            int dengji = playerDataManage.getLevel(player);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            objective.getScore("§7" + formatter.format(calendar.getTime()) + " §8miniZj2K").setScore(13);
            objective.getScore("§5 ").setScore(12);
            String level = "§f等级: §7" + dengji + "✫";
            lastStr.add(level);
            objective.getScore(level).setScore(11);
            int dqjy = xp - dengji * 5000;
            objective.getScore("§1 ").setScore(10);
            String bar = "§f进度: §a" + dqjy + "§7/§b5000";
            DecimalFormat dF = new DecimalFormat("0.00");
            int jingyanbaifenbi = Double.valueOf(dF.format((float) dqjy / 5000)).intValue() * 100;
            lastStr.add(bar);
            objective.getScore(bar).setScore(9);
            DecimalFormat dF1 = new DecimalFormat("0");
            String gezi = dF1.format((float) dqjy / 500);
            int gezishu = Integer.parseInt(gezi);
            String zzgz = null;
            for (int i = 0; i < gezishu; i++) {
                if (zzgz != null) {


                    zzgz = zzgz + ("§b■");
                } else {
                    zzgz = "§b■";
                }
            }
            for (int i = 0; i < 10 - gezishu; i++) {
                if (zzgz != null) {
                    zzgz = zzgz + ("§7■");
                } else {
                    zzgz = "§7■";
                }
            }
            String xpbar = "§8[" + zzgz + "§8]";
            objective.getScore(xpbar).setScore(8);
            lastStr.add(xpbar);
            objective.getScore("§b ").setScore(7);
            String coins = "§f硬币: §6" + playerDataManage.getPlayerCoins(player);
            objective.getScore(coins).setScore(6);
            objective.getScore("§8 ").setScore(5);
            lastStr.add(coins);
            String kill = "§f总击杀数: §a" + playerDataManage.getPlayerALLKill(player);
            lastStr.add(kill);
            objective.getScore(kill).setScore(3);
            String liankill = "§f总胜利数: §a" + "§f总击杀数: §a" + playerDataManage.getPlayerALLWin(player);
            lastStr.add(liankill);
            objective.getScore(liankill).setScore(2);
            objective.getScore("§f ").setScore(1);
            objective.getScore("§eLemonCraft.cn").setScore(0);
            player.setScoreboard(manager);
            player.setLevel(dengji);
            player.setExp(jingyanbaifenbi);
            player.getInventory().clear();

            player.getInventory().setItem(0, items.getItem("游戏菜单"));

            player.getInventory().setItem(1, items.getItem("个人档案"));

            player.getInventory().setItem(2, items.getItem("商店与特效"));

            player.getInventory().setItem(8, items.getItem("选择大厅"));

            //player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));

        }
    }
}

