package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.Color;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class CreateTeam {
    public static TreeMap<String, Color> teamColor = new TreeMap<>();
    public static List<Team> teamList = new ArrayList<>();
    public static int teamPlayer = 1;

    public static void create() {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        Configuration config = plugin.getConfig();
        Scoreboard scoreboard = GameStart.getScoreboard();
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
        if (config.getString("Map.ModeType").equalsIgnoreCase("双人")) teamPlayer = 2;
        if (config.getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")) teamPlayer = 3;
        if (config.getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")) teamPlayer = 4;
        if (config.getString("Map.ModeType").equalsIgnoreCase("4v4")) teamPlayer = 4;

        teamColor.put("红队", Color.fromRGB(255, 0, 0));
        teamList.add(red);
        teamList.add(blue);
        teamColor.put("蓝队", Color.fromRGB(0, 0, 255));
        if (!Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否不为4v4

            teamColor.put("绿队", Color.fromRGB(0, 255, 0));
            teamColor.put("黄队", Color.fromRGB(255, 255, 0));

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
            teamList.add(green);
            teamList.add(yellow);
            if (!Objects.equals(config.getString("Map.ModeType"), "4v4v4v4") && !Objects.equals(config.getString("Map.ModeType"), "3v3v3v3") && !Objects.equals(config.getString("Map.ModeType"), "4v4")) {//判断是否不为4v4v4v4

                teamColor.put("青队", Color.fromRGB(0, 255, 255));
                teamColor.put("白队", Color.fromRGB(255, 255, 255));
                teamColor.put("粉队", Color.fromRGB(242, 127, 165));
                teamColor.put("灰队", Color.fromRGB(128, 128, 128));
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
                pink.setSuffix("§d");
                pink.setDisplayName("0");
                Team gray = scoreboard.registerNewTeam("灰队");
                gray.setAllowFriendlyFire(false);
                gray.setCanSeeFriendlyInvisibles(true);
                gray.setPrefix("§8灰 ");
                gray.setSuffix("§8");
                gray.setDisplayName("0");
                teamList.add(aqua);
                teamList.add(white);
                teamList.add(pink);
                teamList.add(gray);
            }
        }
    }
}
