package cn.lemoncraft.bedwars.Game.Arena;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GamePlayer {
    public static List<String> getplayer(){
        List<String> aplayer = new ArrayList<>();;
        for (Player player : Bukkit.getOnlinePlayers()){
            if (!Objects.equals(GameStart.getScoreboard().getEntryTeam(player.getName()).getName(), "旁观者")){

                aplayer.add(player.getName());
            }
        }
        return aplayer;
    }
}
