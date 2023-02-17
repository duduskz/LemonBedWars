package cn.lemoncraft.bedwars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;
import java.util.Random;

public class ScoreboardUtil {
    public ScoreboardUtil() {
    }

    public static void setScoreBoard(Player player, String ScoreBoardName, List<String> Board) {
        char[] c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        String RandomString = String.valueOf(c[new Random().nextInt(c.length)]);
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(RandomString, RandomString);
        objective.setDisplayName(ScoreBoardName);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 0; i < Board.size(); i++) {
            objective.getScore(Board.get(i)).setScore(-i + Board.size());
        }
        player.setScoreboard(scoreboard);
    }
}

