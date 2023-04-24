package cn.lemoncraft.bedwars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class WaitingScoreBoard {
    public WaitingScoreBoard() {
    }
    public static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    public static Objective getObjective() {
        Objective objective = scoreboard.getObjective("waiting");
        if (objective != null) {
            return objective;
        } else {
            objective = scoreboard.registerNewObjective("waiting", "waiting");
            objective.setDisplayName("§e§l起床战争");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            return objective;
        }
    }
}

