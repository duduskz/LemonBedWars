package cn.lemonnetwork.lemonbedwars.API;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public class Game {
    public static Set<Team> getTeams() {
        return GameStart.getScoreboard().getTeams();
    }

    public static Scoreboard getTeamScoreboard() {
        return GameStart.getScoreboard();
    }

    public static Team getPlayerTeam(Player player) {
        return GameStart.getScoreboard().getEntryTeam(player.getName());
    }

    public static String getMode() {
        return LemonBedWars.getPlugin(LemonBedWars.class).getConfig().getString("Map.Mode");
    }

    public static String getModeType() {
        return LemonBedWars.getPlugin(LemonBedWars.class).getConfig().getString("Map.ModeType");
    }
}
