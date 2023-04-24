package cn.lemoncraft.bedwars.Utils;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class PlayerDataManage {
    static Plugin plugin = BedWars.getPlugin(BedWars.class);
    public static int getPlayerXP(Player player) {
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_data WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);

            if (!rs.next()) {
                sql = "INSERT INTO player_data (uuid, coins, xp) VALUES ('" + player.getUniqueId() + "', 5000, 0)";
                statement.executeUpdate(sql);
            }

            int xp = rs.getInt("xp");
            conn.close();
            return xp;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static int getPlayerCoins(Player player) {
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_data WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next()) {
                sql = "INSERT INTO player_data (uuid, coins, xp) VALUES ('" + player.getUniqueId() + "', 5000, 0)";
                statement.executeUpdate(sql);
            }
            int coins = rs.getInt("coins");
            conn.close();
            return coins;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getLevel(Player player) {
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        int xp = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_data WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            xp = rs.getInt("xp");
            int dengjiint = rs.getInt("xp") / 5000;
            return dengjiint + 1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        int dengjiint = (int) Math.floor(((float) xp / 5000));
        return dengjiint + 1;
    }
    public static Boolean ifitemquickshop(String itemname, Player player) {
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_shop WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            return rs.getString("i"+"19").equalsIgnoreCase(itemname) || rs.getString("i"+"20").equalsIgnoreCase(itemname) || rs.getString("i"+"21").equalsIgnoreCase(itemname) || rs.getString("i"+"22").equalsIgnoreCase(itemname) || rs.getString("i"+"23").equalsIgnoreCase(itemname) || rs.getString("i"+"24").equalsIgnoreCase(itemname) || rs.getString("i"+"25").equalsIgnoreCase(itemname) || rs.getString("i"+"28").equalsIgnoreCase(itemname) || rs.getString("i"+"29").equalsIgnoreCase(itemname) || rs.getString("i"+"30").equalsIgnoreCase(itemname) || rs.getString("i"+"31").equalsIgnoreCase(itemname) || rs.getString("i"+"32").equalsIgnoreCase(itemname) || rs.getString("i"+"33").equalsIgnoreCase(itemname) || rs.getString("i"+"34").equalsIgnoreCase(itemname) || rs.getString("i"+"37").equalsIgnoreCase(itemname) || rs.getString("i"+"38").equalsIgnoreCase(itemname) || rs.getString("i"+"39").equalsIgnoreCase(itemname) || rs.getString("i"+"40").equalsIgnoreCase(itemname) || rs.getString("i"+"41").equalsIgnoreCase(itemname) || rs.getString("i"+"42").equalsIgnoreCase(itemname) || rs.getString("i"+"43").equalsIgnoreCase(itemname);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static void addPlayerCoins(Player player, int coins) {
        BedWars.coins.replace(player.getName(), BedWars.coins.get(player.getName())+coins);
        ActionBar.sendMessage(player,"§6+ "+coins+" 硬币!");

    }
    public static void GameEnd(Team winteam){
        Connection conn;
        String url = "jdbc:mysql://"+plugin.getConfig().getString("MySQL.url")+":"+plugin.getConfig().getString("MySQL.port")+"/"+plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();

            for (Player player : Bukkit.getOnlinePlayers()) {
                String name = player.getUniqueId().toString();

                String sql = "SELECT * FROM player_data WHERE uuid = '" + name + "'";
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                    int wins = rs.getInt("wins");
                    int looses = rs.getInt("looses");
                    if (winteam != null) {
                        if (winteam.getEntries().contains(player.getName())) {
                            wins++;
                        } else {
                            looses++;
                        }
                    }
                    int coins = rs.getInt("coins");
                    coins = coins + BedWars.coins.get(player.getName());
                    int xp = rs.getInt("xp");
                    xp = xp + BedWars.xp.get(player.getName());
                    sql = "UPDATE player_data SET xp = " + xp + ", coins = " + coins + " WHERE uuid = '" + name + "'";
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
                    int coins = 5000 + BedWars.coins.get(player.getName());
                    int xp = BedWars.xp.get(player.getName());
                    sql = "INSERT INTO player_data (uuid, coins, xp) VALUES ('" + name + "', " + coins + ", " + xp+")";
                    statement.executeUpdate(sql);
                    sql = "INSERT INTO player_"+plugin.getConfig().getString("Map.Mode")+"_data (uuid, kills, final_kills, win, looses, deaths, final_deaths, beds_destroyed, mode_played) VALUES ('" + name + "', 0, 0, "+wins+", "+looses+", 0, 0, 0, 0, 1)";
                }
                statement.executeUpdate(sql);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddQuickShopItem() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Connection conn;
            String url = "jdbc:mysql://"+plugin.getConfig().getString("MySQL.url")+":"+plugin.getConfig().getString("MySQL.port")+"/"+plugin.getConfig().getString("MySQL.db");
            String user = plugin.getConfig().getString("MySQL.username");
            String password = plugin.getConfig().getString("MySQL.password");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                Statement statement = conn.createStatement();

                    String name = player.getUniqueId().toString();

                    String sql = "SELECT * FROM player_shop WHERE uuid = '" + name + "'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        sql = "INSERT INTO player_shop (uuid, i19, i20, i21, i22, i23, i24, i25, i28, i29, i30, i31, i32, i33, i34, i37, i38, i39, i40, i41, i42, i43)\n" +
                                "VALUES ('"+player.getUniqueId().toString()+"', '羊毛', '石剑', '锁链护甲', '镐', '弓', '速度药水', 'TNT', '木板', '铁剑', '铁护甲', '斧', '箭', '跳跃药水', '金苹果', '空', '空', '空', '空', '空', '空', '空');";
                        statement.executeUpdate(sql);
                    }

                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addPlayerXP(Player player, int xp) {
        BedWars.xp.replace(player.getName(), BedWars.xp.get(player.getName()) + xp);

    }

    public static void addPlayerKill(Player killer, Player player, int kill, String mode) {
        String name = killer.getUniqueId().toString();
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                int kills = rs.getInt("kills");
                kills = kills + kill;

                sql = "UPDATE player_" + mode + "_data" + " SET kills = " + kills + " WHERE uuid = '" + name + "'";
            } else {
// 如果不存在相同name的记录，则插入新记录
                sql = "INSERT INTO player_" + mode + "_data (uuid, wins, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + name + "', 0, 0, "+kill+", 0, 0, 0, 0, 0)";

            }
            statement.executeUpdate(sql);
            sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
            ResultSet rs1 = statement.executeQuery(sql);
            if (rs1.next()) {
                int deaths = rs.getInt("deaths") + 1;
                sql = "UPDATE player_" + mode + "_data" + " SET deaths = " + deaths + " WHERE uuid = '" + player.getUniqueId().toString() + "'";
            } else {
                sql = "INSERT INTO player_" + mode + "_data (uuid, wins, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + killer.getUniqueId().toString() + "', 0" + kill + ", 0, 0, 0, 0, 0, 0)";

            }
            statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addPlayerFinalKill(Player killer, Player player, int finalkill, String mode) {
        String name = killer.getUniqueId().toString();
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + killer.getUniqueId().toString() + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                int kills = rs.getInt("final_kills");
                kills = kills + finalkill;

                sql = "UPDATE player_"+mode+"_data"+" SET final_kills = " + kills + " WHERE uuid = '" + name + "'";
                } else {
// 如果不存在相同name的记录，则插入新记录
                sql = "INSERT INTO player_"+mode+"_data (uuid, wins, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + name + "', 0, 0, 0, 0, 0, 0, 1, 0, 0)";

            }
            statement.executeUpdate(sql);
            sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + name + "'";
            ResultSet rs1 = statement.executeQuery(sql);
            if (rs1.next()) {
                int deaths = rs.getInt("final_deaths") + 1;
                sql = "UPDATE player_"+mode+"_data"+" SET final_deaths = " + deaths + " WHERE uuid = '" + player.getUniqueId().toString() + "'";
            } else {
                sql = "INSERT INTO player_"+mode+"_data (uuid, wins, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + killer.getUniqueId().toString() + "', 0, 0, 0, " + finalkill + ", 0, 0, 0, 0, 0)";

            }
            statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerBreakBed(Player player, int BreakBed, String mode) {
        String name = player.getUniqueId().toString();
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
// 如果已经存在相同name的记录，则更新该记录
                int beds_destroyed = rs.getInt("beds_destroyed");
                beds_destroyed = beds_destroyed + BreakBed;

                sql = "UPDATE player_" + mode + "_data" + " SET beds_destroyed = " + beds_destroyed + " WHERE uuid = '" + name + "'";
            } else {
// 如果不存在相同name的记录，则插入新记录
                sql = "INSERT INTO player_" + mode + "_data (uuid, wins, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('" + name + "', 0, 0, 0, 0, 0, 0, "+BreakBed+", 0)";

            }
            statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public static int getPlayerKill(Player player, String mode) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + "." + mode + ".Kill");
    }

    public static int getPlayerFinalKill(Player player, String mode) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + "." + mode + ".FinalKill");
    }

    public static int getPlayerModeData(Player player, String mode, String data) {
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        int dataint = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM player_"+mode+"_data WHERE uuid = '"+player.getUniqueId().toString()+"'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            dataint = rs.getInt(data);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dataint;
    }

    public static int getPlayerALLData(Player player, String data) {
        Connection conn;
        String url = "jdbc:mysql://" + plugin.getConfig().getString("MySQL.url") + ":" + plugin.getConfig().getString("MySQL.port") + "/" + plugin.getConfig().getString("MySQL.db");
        String user = plugin.getConfig().getString("MySQL.username");
        String password = plugin.getConfig().getString("MySQL.password");
        int dataint = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            for (String mode : plugin.getConfig().getStringList("Group")) {
                String sql = "SELECT * FROM player_" + mode + "_data WHERE uuid = '" + player.getUniqueId().toString() + "'";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                dataint = dataint + rs.getInt(data);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dataint;
    }


    public static boolean getSpectatorSettings(Player player, String settings) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getBoolean(player.getName() + ".Spectator." + settings);
    }

    public static void setSpectatorSettings(Player player, String settings, boolean value) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".Spectator." + settings, value);

        try {
            PlayerData.save(file);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static int getSpectatorSettingsint(Player player, String settings) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + ".Spectator." + settings);
    }

    public static void setSpectatorSettingsint(Player player, String settings, int value) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".Spectator." + settings, value);

        try {
            PlayerData.save(file);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }
}
