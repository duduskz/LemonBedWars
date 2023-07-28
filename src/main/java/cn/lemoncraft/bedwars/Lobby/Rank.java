package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.plugin.Plugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Rank {
    public static void win(Statement statement, String mode) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        String[] modes = {"mode1", "mode2", "mode3"}; // replace with your modes
        ArrayList<String> lists = new ArrayList<>();
        try {
            for (String formode : modes) {
                String sql = "SELECT uuid, kills FROM player_" + formode + "_data ORDER BY kills DESC LIMIT 10;";
                ResultSet rs = statement.executeQuery(sql);

                int rank = 1;
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    int kills = rs.getInt("kills");
                    lists.add(rank + ". " + uuid + " - " + kills);
                    rank++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
