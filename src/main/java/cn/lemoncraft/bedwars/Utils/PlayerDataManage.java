package cn.lemoncraft.bedwars.Utils;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataManage {
    public static int getPlayerXP(Player player) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + ".XP");
    }

    public static int getPlayerCoins(Player player) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + ".Coins");
    }

    public static int getLevel(Player player) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        int xp = PlayerData.getInt(player.getName() + ".XP");
        int dengjiint = (int) Math.floor(((float) xp / 5000));
        return dengjiint + 1;
    }
    public static Boolean ifitemquickshop(String itemname, Player player) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        if (PlayerData.getString(player.getName() + ".Shop.19").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.20").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.21").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.22").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.23").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.24").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.25").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.28").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.29").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.30").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.31").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.32").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.33").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.34").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.37").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.38").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.39").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.40").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.41").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.42").equalsIgnoreCase(itemname) || PlayerData.getString(player.getName() + ".Shop.43").equalsIgnoreCase(itemname)) {
            return true;

        } else {
            return false;
        }

    }
    public static void addPlayerCoins(Player player, int coins) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".Coins", PlayerData.getInt(player.getName() + ".Coins") + coins);
        BedWars.coins.replace(player.getName(), BedWars.coins.get(player.getName())+coins);
        ActionBar.sendMessage(player,"§6+ "+coins+"硬币！");
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setplayershop(Player player, int Solt, String itemname) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".Shop." + Solt, itemname);

        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createplayershop(Player player) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        if (PlayerData.getString(player.getName() + ".Shop.19") == null) {
            PlayerData.set(player.getName() + ".Shop.19", "羊毛");
            PlayerData.set(player.getName() + ".Shop.20", "石剑");
            PlayerData.set(player.getName() + ".Shop.21", "锁链护甲");
            PlayerData.set(player.getName() + ".Shop.22", "镐");
            PlayerData.set(player.getName() + ".Shop.23", "弓");
            PlayerData.set(player.getName() + ".Shop.24", "速度药水");
            PlayerData.set(player.getName() + ".Shop.25", "TNT");
            PlayerData.set(player.getName() + ".Shop.28", "木板");
            PlayerData.set(player.getName() + ".Shop.29", "铁剑");
            PlayerData.set(player.getName() + ".Shop.30", "铁护甲");
            PlayerData.set(player.getName() + ".Shop.31", "斧");
            PlayerData.set(player.getName() + ".Shop.32", "箭");
            PlayerData.set(player.getName() + ".Shop.33", "跳跃药水");
            PlayerData.set(player.getName() + ".Shop.34", "金苹果");
            PlayerData.set(player.getName() + ".Shop.37", "空");
            PlayerData.set(player.getName() + ".Shop.38", "空");
            PlayerData.set(player.getName() + ".Shop.39", "空");
            PlayerData.set(player.getName() + ".Shop.40", "空");
            PlayerData.set(player.getName() + ".Shop.41", "空");
            PlayerData.set(player.getName() + ".Shop.42", "空");
            PlayerData.set(player.getName() + ".Shop.43", "空");

            try {
                PlayerData.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static String getplayershop(Player player, int slot) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getString(player.getName() + ".Shop."+slot);

    }
    public static void addPlayerXP(Player player, int xp) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".XP", PlayerData.getInt(player.getName() + ".XP") + xp);
        BedWars.xp.replace(player.getName(), BedWars.xp.get(player.getName())+xp);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerKill(Player player, int kill, String mode) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + "." + mode + ".Kill", PlayerData.getInt(player.getName() + "." + mode + ".Kill") + kill);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addPlayerFinalKill(Player player, int finalkill, String mode) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + "." + mode + ".FinalKill", PlayerData.getInt(player.getName() + "." + mode + ".FinalKill") + finalkill);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addPlayerBreakBed(Player player, int BreakBed, String mode) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + "." + mode + ".BreakBed", PlayerData.getInt(player.getName() + "." + mode + ".BreakBed") + BreakBed);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addPlayerWin(Player player, int BreakBed, String mode) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + "." + mode + ".BreakBed", PlayerData.getInt(player.getName() + "." + mode + ".BreakBed") + BreakBed);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getPlayerKill(Player player, String mode){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + "."+mode+".Kill");
    }
    public static int getPlayerFinalKill(Player player, String mode){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + "."+mode+".FinalKill");
    }
    public static int getPlayerBreakBed(Player player, String mode){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + "."+mode+".BreakBed");
    }
    public static int getPlayerWin(Player player, String mode){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + "."+mode+".Win");
    }
    public static int getPlayerALLKill(Player player){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        int all = 0;
        for (String mode : PlayerData.getStringList("ModeList")){
            all = all + PlayerData.getInt(player.getName() + "."+mode+"."+"Kill");
        }
        return all;
    }
    public static int getPlayerALLFinalKill(Player player){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        int all = 0;
        for (String mode : PlayerData.getStringList("ModeList")){
            all = all + PlayerData.getInt(player.getName() + "."+mode+"."+"FinalKill");
        }
        return all;
    }
    public static int getPlayerALLBreakBed(Player player){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        int all = 0;
        for (String mode : PlayerData.getStringList("ModeList")){
            all = all + PlayerData.getInt(player.getName() + "."+mode+"."+"BreakBed");
        }
        return all;
    }
    public static int getPlayerALLWin(Player player){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        int all = 0;
        for (String mode : PlayerData.getStringList("ModeList")){
            all = all + PlayerData.getInt(player.getName() + "."+mode+"."+"Win");
        }
        return all;
    }
    public static boolean getSpectatorSettings(Player player, String settings){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getBoolean(player.getName() + ".Spectator."+settings);
    }
    public static void setSpectatorSettings(Player player, String settings, boolean value) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".Spectator."+settings, value);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getSpectatorSettingsint(Player player, String settings){
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        return PlayerData.getInt(player.getName() + ".Spectator."+settings);
    }
    public static void setSpectatorSettingsint(Player player, String settings, int value) {
        File file = new File("C:/Bedwars/PlayerData.yml");
        FileConfiguration PlayerData = YamlConfiguration.loadConfiguration(file);
        PlayerData.set(player.getName() + ".Spectator."+settings, value);
        try {
            PlayerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
