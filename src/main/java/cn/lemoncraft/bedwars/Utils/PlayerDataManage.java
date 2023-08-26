package cn.lemoncraft.bedwars.Utils;

import cn.lemoncraft.bedwars.BedWars;
import com.alonsoaliaga.alonsolevels.api.AlonsoLevelsAPI;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Objects;

public class PlayerDataManage implements Listener {
    @EventHandler
    public void LoadPlayerData(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String name = player.getUniqueId().toString();

            String sql = "SELECT * FROM player_spectator_settings WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next()) {
                sql = "INSERT INTO player_spectator_settings (uuid, FirstPerson, SPEED, AutoTeleport, NightVision, HideOther) " +
                        "VALUES ('" + player.getUniqueId().toString() + "', 'true', 0, 'false', 'true', 'true');";
                statement.executeUpdate(sql);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    static Plugin plugin = BedWars.getPlugin(BedWars.class);
    public static HikariDataSource BedWarsdataSource;
    public static HikariDataSource APIdataSource;
    public static HashMap<Player, String> PlayerLang = new HashMap<>();

    public static int getPlayerXP(Player player) {
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
            ResultSet rs = statement.executeQuery(sql);

            if (!rs.next()) {
                sql = "INSERT INTO player_data (uuid, coins, xp) VALUES ('" + player.getUniqueId() + "', 5000, 0)";
                statement.executeUpdate(sql);
            }

            return rs.getInt("xp");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static int getPlayerCoins(Player player) {
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next()) {
                sql = "INSERT INTO player_data (uuid, coins, xp) VALUES ('" + player.getUniqueId() + "', 5000, 0)";
                statement.executeUpdate(sql);
            }
            return rs.getInt("coins");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getLevel(Player player) {
        int xp = 0;
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            xp = rs.getInt("xp");
            int dengjiint = rs.getInt("xp") / 5000;
            return dengjiint + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int dengjiint = (int) Math.floor(((float) xp / 5000));
        return dengjiint + 1;
    }

    public static String getPlayerLang(Player player) {
        if (!Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            if (PlayerLang.get(player) == null) {
                try (Connection connection = PlayerDataManage.APIdataSource.getConnection();
                     Statement statement = connection.createStatement()) {
                    String sql = "SELECT * FROM player_lang WHERE uuid = '" + player.getUniqueId().toString() + "'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        PlayerLang.put(player, rs.getString("lang"));
                        return rs.getString("lang");
                    } else {
                        sql = "INSERT INTO player_lang (uuid, lang) VALUES ('" + player.getUniqueId().toString() + "', 'zhcn')";
                        statement.executeUpdate(sql);
                        PlayerLang.put(player, "zhcn");
                        return "zhcn";

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    return "zhcn";

                }

            } else {
                return PlayerLang.get(player);
            }
        } else {

            try (Connection connection = PlayerDataManage.APIdataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM player_lang WHERE uuid = '" + player.getUniqueId().toString() + "'";
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    PlayerLang.put(player, rs.getString("lang"));
                    return rs.getString("lang");
                } else {
                    sql = "INSERT INTO player_lang (uuid, lang) VALUES ('" + player.getUniqueId().toString() + "', 'zhcn')";
                    statement.executeUpdate(sql);
                    PlayerLang.put(player, "zhcn");
                    return "zhcn";

                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return "zhcn";
    }
    public static Boolean ifitemquickshop(String itemname, Player player) {
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_shop WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            return rs.getString("i"+"19").equalsIgnoreCase(itemname) || rs.getString("i"+"20").equalsIgnoreCase(itemname) || rs.getString("i"+"21").equalsIgnoreCase(itemname) || rs.getString("i"+"22").equalsIgnoreCase(itemname) || rs.getString("i"+"23").equalsIgnoreCase(itemname) || rs.getString("i"+"24").equalsIgnoreCase(itemname) || rs.getString("i"+"25").equalsIgnoreCase(itemname) || rs.getString("i"+"28").equalsIgnoreCase(itemname) || rs.getString("i"+"29").equalsIgnoreCase(itemname) || rs.getString("i"+"30").equalsIgnoreCase(itemname) || rs.getString("i"+"31").equalsIgnoreCase(itemname) || rs.getString("i"+"32").equalsIgnoreCase(itemname) || rs.getString("i"+"33").equalsIgnoreCase(itemname) || rs.getString("i"+"34").equalsIgnoreCase(itemname) || rs.getString("i"+"37").equalsIgnoreCase(itemname) || rs.getString("i"+"38").equalsIgnoreCase(itemname) || rs.getString("i"+"39").equalsIgnoreCase(itemname) || rs.getString("i"+"40").equalsIgnoreCase(itemname) || rs.getString("i"+"41").equalsIgnoreCase(itemname) || rs.getString("i"+"42").equalsIgnoreCase(itemname) || rs.getString("i"+"43").equalsIgnoreCase(itemname);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static void addPlayerCoins(Player player, int coins) {
        BedWars.coins.replace(player.getName(), BedWars.coins.get(player.getName())+coins);
        ActionBar.sendMessage(player,"§6+ "+coins+" 硬币!");

    }
    public static void GameEnd(Team winteam){
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                String name = player.getUniqueId().toString();
                int taskxp = 0;
                String sql = "SELECT * FROM player_"+plugin.getConfig().getString("Map.Mode")+"_data WHERE uuid = '" + name + "'";
                ResultSet rs = statement.executeQuery(sql);
                if (winteam.getEntries().contains(player.getName())) {
                    String daysql = "SELECT * FROM player_day_task WHERE uuid = '" + name + "'";
                    ResultSet dayrs = statement.executeQuery(daysql);
                    if (dayrs.next() && dayrs.getInt("DayWin") == 1) {
                        player.sendMessage("");
                        player.sendMessage("§a每日任务: 每日首胜已完成!");
                        player.sendMessage(" §8+§33850 §7" + BedWars.servername + "经验");
                        player.sendMessage(" §8+§b250 §7起床战争经验");
                        player.sendMessage("");
                        taskxp = taskxp + 250;
                        AlonsoLevelsAPI.addExperience(player.getUniqueId(), 3850);
                        sql = "UPDATE player_day_task" + " SET DayWin = 2 WHERE uuid = '" + player.getUniqueId().toString() + "'";
                    }
                }
                if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                    int wins = rs.getInt("win");
                    int looses = rs.getInt("looses");
                    if (winteam != null) {
                        if (winteam.getEntries().contains(player.getName())) {
                            wins++;
                        } else {
                            looses++;
                        }
                    }




                    statement.executeUpdate(sql);
                    sql = "UPDATE player_"+plugin.getConfig().getString("Map.Mode")+"_data SET win = " + wins + ", looses = " + looses + " WHERE uuid = '" + name + "'";

                } else {
// 如果不存在相同name的记录，则插入新记录
                    int wins = 0;
                    int looses = 0;
                    if (winteam != null) {
                        if (winteam.getEntries().contains(player.getName())) {
                            wins = 1;
                        } else {
                            looses = 1;
                        }
                    }

                    statement.executeUpdate(sql);
                    sql = "INSERT INTO player_"+plugin.getConfig().getString("Map.Mode")+"_data (uuid, kills, final_kills, win, looses, deaths, final_deaths, beds_destroyed, mode_played) VALUES ('" + name + "', 0, 0, "+wins+", "+looses+", 0, 0, 0, 1)";
                }
                statement.executeUpdate(sql);
                sql = "SELECT * FROM player_data WHERE uuid = '" + name + "'";
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    int coins = BedWars.coins.get(player.getName()) + rs.getInt("coins");
                    int xp = rs.getInt("xp") + BedWars.xp.get(player.getName()) + taskxp;
                    sql = "UPDATE player_data SET xp = " + xp + ", coins = " + coins + " WHERE uuid = '" + name + "'";
                } else {
                    int coins = BedWars.coins.get(player.getName());
                    int xp = BedWars.xp.get(player.getName());
                    sql = "INSERT INTO player_data (uuid, coins, xp) VALUES ('" + name + "', " + coins + ", " + xp+")";
                }
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddQuickShopItem() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                String uuid = player.getUniqueId().toString();

                String sql = "SELECT * FROM player_shop WHERE uuid = '" + uuid + "'";
                ResultSet rs = statement.executeQuery(sql);
                if (!rs.next()) {
                    sql = "INSERT INTO player_shop (uuid, i19, i20, i21, i22, i23, i24, i25, i28, i29, i30, i31, i32, i33, i34, i37, i38, i39, i40, i41, i42, i43)\n" +
                            "VALUES ('"+player.getUniqueId().toString()+"', '羊毛', '石剑', '锁链护甲', '镐', '弓', '速度药水', 'TNT', '木板', '铁剑', '铁护甲', '斧', '箭', '跳跃药水', '金苹果', '空', '空', '空', '空', '空', '空', '空');";
                    statement.executeUpdate(sql);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addPlayerXP(Player player, int xp) {
        BedWars.xp.replace(player.getName(), BedWars.xp.get(player.getName()) + xp);

    }

    public static void addPlayerKill(Player killer, Player player, int kill, String mode) {
        String name = killer.getUniqueId().toString();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                int kills = rs.getInt("kills");
                kills = kills + kill;

                sql = "UPDATE player_" + mode + "_data" + " SET kills = " + kills + " WHERE uuid = '" + name + "'";
            } else {
// 如果不存在相同name的记录，则插入新记录
                sql = "INSERT INTO player_" + mode + "_data (uuid, win, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + name + "', 0, 0, "+kill+", 0, 0, 0, 0, 0)";

            }
            statement.executeUpdate(sql);
            sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
            ResultSet rs1 = statement.executeQuery(sql);
            if (rs1.next()) {
                int deaths = rs.getInt("deaths") + 1;
                sql = "UPDATE player_" + mode + "_data" + " SET deaths = " + deaths + " WHERE uuid = '" + player.getUniqueId().toString() + "'";
            } else {
                sql = "INSERT INTO player_" + mode + "_data (uuid, win, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + killer.getUniqueId().toString() + "', 0" + kill + ", 0, 0, 0, 0, 0, 0)";

            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addPlayerFinalKill(Player killer, Player player, int finalkill, String mode) {
        String name = killer.getUniqueId().toString();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + killer.getUniqueId().toString() + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                int kills = rs.getInt("final_kills");
                kills = kills + finalkill;

                sql = "UPDATE player_"+mode+"_data"+" SET final_kills = " + kills + " WHERE uuid = '" + name + "'";
            } else {
// 如果不存在相同name的记录，则插入新记录
                sql = "INSERT INTO player_"+mode+"_data (uuid, win, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + name + "', 0, 0, 1, 0, 0, 0, 0, 0)";

            }
            statement.executeUpdate(sql);
            sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + name + "'";
            ResultSet rs1 = statement.executeQuery(sql);
            if (rs1.next()) {
                int deaths = rs.getInt("final_deaths") + 1;
                sql = "UPDATE player_"+mode+"_data"+" SET final_deaths = " + deaths + " WHERE uuid = '" + player.getUniqueId().toString() + "'";
            } else {
                sql = "INSERT INTO player_"+mode+"_data (uuid, win, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + killer.getUniqueId().toString() + "', 0, 0, 0, " + finalkill + ", 0, 0, 0, 0, 0)";
            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerBreakBed(Player player, int BreakBed, String mode) {
        String name = player.getUniqueId().toString();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                int beds_destroyed = rs.getInt("beds_destroyed");
                beds_destroyed = beds_destroyed + BreakBed;

                sql = "UPDATE player_" + mode + "_data" + " SET beds_destroyed = " + beds_destroyed + " WHERE uuid = '" + name + "'";
            } else {
// 如果不存在相同name的记录，则插入新记录
                sql = "INSERT INTO player_" + mode + "_data (uuid, win, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + name + "', 0, 0, 0, 0, 0, 0, "+BreakBed+", 0)";

            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static int getPlayerModeData(Player player, String mode, String data) {
        int dataint = 0;
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            dataint = rs.getInt(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataint;
    }

    public static int getPlayerALLData(Player player, String data) {
        int dataint = 0;
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    for (String mode : plugin.getConfig().getStringList("Group")) {
                String sql = "SELECT * FROM player_" + mode + "_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                dataint = dataint + rs.getInt(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataint;
    }


    public static void setSpectatorSettings(Player player, String settings, int value) {
        String name = player.getUniqueId().toString();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_spectator_settings WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            sql = "UPDATE player_spectator_settings SET "+settings+" = " + value + " WHERE uuid = '" + name + "'";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void setSpectatorSettings(Player player, String settings, Boolean value) {
        String name = player.getUniqueId().toString();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_spectator_settings WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            sql = "UPDATE player_spectator_settings SET "+settings+" = '" + value + "' WHERE uuid = '" + name + "'";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean getSpectatorSettings(Player player, String s) {
        String name = player.getUniqueId().toString();
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {    String sql = "SELECT * FROM player_spectator_settings WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if (rs.getString(s).equalsIgnoreCase("true")) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
