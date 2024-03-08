package cn.lemonnetwork.lemonbedwars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class WaitingScoreBoard {
    public WaitingScoreBoard() {
    }
    public static Scoreboard enscoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    public static Scoreboard zhcnscoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    public static Objective getObjective(String lang) {
        Objective objective = null;
        if (lang.equalsIgnoreCase("zhcn")) {
            objective = zhcnscoreboard.getObjective("zhcnwaiting");
            if (objective == null) {
                objective = zhcnscoreboard.registerNewObjective("zhcnwaiting", "zhcnwaiting");
                objective.setDisplayName("§e§l起床战争");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                return objective;
            }
        } else if(lang.equalsIgnoreCase("en")) {
            objective = enscoreboard.getObjective("enwaiting");
            if (objective == null) {
                objective = enscoreboard.registerNewObjective("enwaiting", "enwaiting");
                objective.setDisplayName("§e§lBED WARS");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            }
        }
        return objective;
    }
}

