package cn.lemoncraft.bedwars;

import cn.lemoncraft.bedwars.Command.MainCommand;
import cn.lemoncraft.bedwars.Command.RejoinCommand;
import cn.lemoncraft.bedwars.Command.ShoutCommand;
import cn.lemoncraft.bedwars.Command.StartCommand;
import cn.lemoncraft.bedwars.Game.Arena.*;
import cn.lemoncraft.bedwars.Game.Arena.Menu.Shop;
import cn.lemoncraft.bedwars.Game.Arena.Menu.TeamShop;
import cn.lemoncraft.bedwars.Game.Arena.SpecialMode.RushModeListeners;
import cn.lemoncraft.bedwars.Game.Prop.BridgeEgg;
import cn.lemoncraft.bedwars.Game.Prop.FireballListener;
import cn.lemoncraft.bedwars.Game.Prop.Iron_Puppet;
import cn.lemoncraft.bedwars.Game.Prop.PopUpTowers.ChestPlace;
import cn.lemoncraft.bedwars.Game.Prop.TNTListener;
import cn.lemoncraft.bedwars.Game.Protect.*;
import cn.lemoncraft.bedwars.Lobby.PlayerSizePAPI;
import cn.lemoncraft.bedwars.Lobby.Quest;
import cn.lemoncraft.bedwars.Menu.bwmenu;
import cn.lemoncraft.bedwars.Menu.bwmenuListener;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import cn.lemoncraft.bedwars.Utils.UseBackLobbyItem;
import cn.lemoncraft.bedwars.waiting.BlockBreak;
import cn.lemoncraft.bedwars.waiting.DropItem;
import cn.lemoncraft.bedwars.waiting.PlayerLeave;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public final class BedWars extends JavaPlugin {
    public static LuckPerms api;
    public static String state;
    public static int time = 20;
    public static int Listenertime = 360;
    public static String Listenername = "钻石生成点II级";
    public static String serverip = "SeabedCraft.cn";
    public static String servername = "SeabedCraft";
    public static HashMap<String, Integer> HasteUpgrade = new HashMap<>();
    public static HashMap<String, Boolean> Listeners = new HashMap<>();
    public static HashMap<String, Integer> shoutcd = new HashMap<>();
    public static HashMap<String, Integer> kill = new HashMap<>();
    public static HashMap<String, Integer> deaths = new HashMap<>();
    public static HashMap<String, Integer> finaldeaths = new HashMap<>();
    public static HashMap<String, Integer> finalkill = new HashMap<>();
    public static HashMap<String, Boolean> onSpeed = new HashMap<>();
    public static HashMap<String, Integer> breakbed = new HashMap<>();
    public static HashMap<String, Integer> coins = new HashMap<>();
    public static HashMap<String, Integer> xp = new HashMap<>();
    public static HashMap<String, Boolean> backlobby = new HashMap<>();
    public static HashMap<String, String> playeradditem = new HashMap<>();
    public static HashMap<String, Boolean> shears = new HashMap<>();
    //public static HashMap<String, Boolean> thisxianjing = new HashMap<>();
    public static HashMap<String, Boolean> sharp = new HashMap<>();
    public static HashMap<String, Integer> pickaxe = new HashMap<>();
    public static HashMap<String, Integer> axe = new HashMap<>();
    public static HashMap<String, String> spectator = new HashMap<>();
    public static List<Location> changedBlocks = new ArrayList<>();
    public static Map<Player, Integer> Fireballcooldown = new HashMap<>();
    public static Map<Entity, String> PlayerShop = new HashMap<>();
    public static Map<Entity, String> Additem = new HashMap<>();
    public static ArrayList<String> Build = new ArrayList<>();
    public static ArrayList<Player> canRejoinPlayer = new ArrayList<>();
    public static ArrayList<Player> ReSpawning = new ArrayList<>();
    public static Map<String, Integer> protectUpgrade = new HashMap<>();
    public static World playworld;
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("   §bLemon§aBedwars");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§b已加载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getCommand("lemonbedwars").setExecutor(new MainCommand());
        getCommand("bw").setExecutor(new MainCommand());
        getCommand("bedwars").setExecutor(new MainCommand());
        getCommand("bedwarsgame").setExecutor(new MainCommand());
        getConfig().options().copyDefaults();
        api = LuckPermsProvider.get();
        saveDefaultConfig();
        if (Objects.equals(getConfig().getString("BungeeMode"), "Game")){
            if (getConfig().get("Map") != null) {
                getCommand("shout").setExecutor(new ShoutCommand());
                getCommand("start").setExecutor(new StartCommand());
                getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
                getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.waiting.PlayerJoin(), this);
                getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.waiting.ChatFormat(), this);
                getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
                getServer().getPluginManager().registerEvents(new NoMob(), this);
                getServer().getPluginManager().registerEvents(new BlockBreak(), this);
                getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.waiting.EntityDamage(), this);
                getServer().getPluginManager().registerEvents(new UseBackLobbyItem(), this);
                getServer().getPluginManager().registerEvents(new PlayerMove(), this);
                getServer().getPluginManager().registerEvents(new BreakEvent(), this);
                getServer().getPluginManager().registerEvents(new Trip(), this);
                getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
                getServer().getPluginManager().registerEvents(new ChatFormat(), this);
                getServer().getPluginManager().registerEvents(new WeatherChange(), this);
                getServer().getPluginManager().registerEvents(new SpectatorListener(), this);
                getServer().getPluginManager().registerEvents(new BlockPlace(), this);
                getServer().getPluginManager().registerEvents(new TNTListener(), this);
                getServer().getPluginManager().registerEvents(new FireballListener(), this);
                getServer().getPluginManager().registerEvents(new Shop(), this);
                getServer().getPluginManager().registerEvents(new ChestPlace(), this);
                getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
                getServer().getPluginManager().registerEvents(new ResetDamage(), this);
                getServer().getPluginManager().registerEvents(new DoubleResources(), this);
                getServer().getPluginManager().registerEvents(new NoPickupBed(), this);
                getServer().getPluginManager().registerEvents(new DropItem(), this);
                getServer().getPluginManager().registerEvents(new NoBedMessage(), this);
                getServer().getPluginManager().registerEvents(new BridgeEgg(), this);
                getServer().getPluginManager().registerEvents(new ItemListener(), this);
                getServer().getPluginManager().registerEvents(new TeamShop(), this);
                getServer().getPluginManager().registerEvents(new ArmorStandListener(), this);
                getServer().getPluginManager().registerEvents(new NoEntityDamageTeamPlayer(), this);
                getServer().getPluginManager().registerEvents(new Iron_Puppet(), this);
                getServer().getPluginManager().registerEvents(new NoBoomBlock(), this);
                getServer().getPluginManager().registerEvents(new NoDropItem(), this);
                getServer().getPluginManager().registerEvents(new PlayerDataManage(), this);
                state = "waiting";
                WorldCreator seed = new WorldCreator(getConfig().getString("Map.WorldName"));
                playworld = seed.createWorld();
                playworld.setAutoSave(false);
                String[] spawn = LocationUtil.getStringLocation(getConfig().getString("Map.Spawn"));
                playworld.getWorldBorder().setCenter(Double.parseDouble(spawn[0]), Double.parseDouble(spawn[2]));
                playworld.getWorldBorder().setSize(getConfig().getDouble("Map.Size"));
                MinecraftServer.getServer().setMotd("Waiting");
                BedWars.Listeners.put("emerald2", false);
                BedWars.Listeners.put("diamond2", false);
                BedWars.Listeners.put("emerald3", false);
                BedWars.Listeners.put("diamond3", false);
                BedWars.Listeners.put("BedDestroy", false);
                BedWars.Listeners.put("lore", false);
                BedWars.Listeners.put("gameend", false);
                BedWars.Listenername = "钻石生成点II级";
                BedWars.Listenertime = 360;
                try {
                    if (getConfig().getString("Map.SpecialMode").equalsIgnoreCase("Rush")) {
                        getServer().getPluginManager().registerEvents(new RushModeListeners(), this);
                        BedWars.Listeners.replace("emerald2", true);
                        BedWars.Listeners.replace("diamond2", true);
                        BedWars.Listeners.replace("emerald3", true);
                        BedWars.Listeners.replace("diamond3", true);
                        BedWars.Listenername = "床自毁";
                        BedWars.Listenertime = 840;
                    }
                } catch (NullPointerException e){
                }


            } else {
                getServer().getPluginManager().registerEvents(new EditMap(), this);
                state = "nomap";
                MinecraftServer.getServer().setMotd("nomap");

            }


        } else {

            (new PlayerSizePAPI()).register();
            state = "Lobby";
            getCommand("playmenu").setExecutor(new bwmenu());
            getCommand("rejoin").setExecutor(new RejoinCommand());
            getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.Lobby.PlayerJoin(), this);
            //getServer().getPluginManager().registerEvents(new AntiDrop(), this);
            //getServer().getPluginManager().registerEvents(new EntityDamage(), this);
            getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.Lobby.ChatFormat(), this);
            //getServer().getPluginManager().registerEvents(new UseLobbyItem(), this);
            //getServer().getPluginManager().registerEvents(new uselobbyitem1(), this);
            getServer().getPluginManager().registerEvents(new bwmenuListener(), this);
            getServer().getPluginManager().registerEvents(new Quest(), this);
            //getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.Lobby.BlockBreak(), this);
        }
        HikariConfig APIconfig = new HikariConfig();
        APIconfig.setJdbcUrl("jdbc:mysql://"+getConfig().getString("APIMySQL.url")+":"+getConfig().getString("APIMySQL.port")+"/"+getConfig().getString("APIMySQL.db"));
        APIconfig.setUsername(getConfig().getString("APIMySQL.username"));
        APIconfig.setPassword(getConfig().getString("APIMySQL.password"));
        APIconfig.setMaximumPoolSize(100);
        PlayerDataManage.APIdataSource = new HikariDataSource(APIconfig);
        HikariConfig BedWarsConfig = new HikariConfig();
        BedWarsConfig.setJdbcUrl("jdbc:mysql://"+getConfig().getString("MySQL.url")+":"+getConfig().getString("MySQL.port")+"/"+getConfig().getString("MySQL.db"));
        BedWarsConfig.setUsername(getConfig().getString("MySQL.username"));
        BedWarsConfig.setPassword(getConfig().getString("MySQL.password"));
        BedWarsConfig.setMaximumPoolSize(2000);
        PlayerDataManage.BedWarsdataSource = new HikariDataSource(BedWarsConfig);
        try {
            Statement statement = PlayerDataManage.BedWarsdataSource.getConnection().createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS player_data (" +
                    "uuid TEXT(200)," +
                    "coins INT(200), xp INT(200));";
            statement.executeUpdate(sql);
            if (getConfig().getString("BungeeMode").equalsIgnoreCase("Game")) {
                sql = "CREATE TABLE IF NOT EXISTS player_" + getConfig().getString("Map.Mode") + "_data (" +
                        "uuid TEXT(200)," +
                        "kills INT(200), final_kills INT(200), win INT(200), looses INT(200), deaths INT(200), final_deaths INT(200), beds_destroyed INT(200), mode_played INT(200));";
                statement.executeUpdate(sql);

            }
            sql = "CREATE TABLE IF NOT EXISTS player_day_task (" +
                    "uuid TEXT(200)," +
                    "DayWin TEXT(200), DoublePlay TEXT(200));";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS player_rejoin (" +
                    "uuid TEXT(200)," +
                    "ServerName TEXT(200), Mode TEXT(200));";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS player_shop (" +
                    "uuid TEXT(200)," +
                    "i19 TEXT(200), i20 TEXT(200), i21 TEXT(200), i22 TEXT(200), i23 TEXT(200), i24 TEXT(200), i25 TEXT(200), i28 TEXT(200), i29 TEXT(200), i30 TEXT(200), i31 TEXT(200), i32 TEXT(200), i33 TEXT(200), i34 TEXT(200), i37 TEXT(200), i38 TEXT(200), i39 TEXT(200), i40 TEXT(200), i41 TEXT(200), i42 TEXT(200), i43 TEXT(200));";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS player_spectator_settings (" +
                    "uuid TEXT(200)," +
                    "FirstPerson TEXT(200), SPEED INT(200), AutoTeleport TEXT(200), NightVision TEXT(200), HideOther TEXT(200));";
            statement.executeUpdate(sql);
            Statement apistatement = PlayerDataManage.APIdataSource.getConnection().createStatement();

            sql = "CREATE TABLE IF NOT EXISTS player_lang (" +
                    "uuid TEXT(200)," +
                    "lang TEXT(200));";
            apistatement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //for (Entity e : Bukkit.getWorld(JavaPlugin.getPlugin(BedWars.class).getConfig().getString("Map.WorldName")).getEntities()){
        //    if (e.getType() == EntityType.ITEM_FRAME){
        //        e.remove();
        //    }
        //}
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("§bLemon§aBedwars §e>> §c服务器即将重置地图, 您没有退出服务器, 因此将您踢出");
        }

        PlayerDataManage.BedWarsdataSource.close();
        PlayerDataManage.APIdataSource.close();
        Bukkit.unloadWorld(getConfig().getString("Map.WorldName"), false);
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("   §bLemon§aBedwars");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§b已卸载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
    }
}
