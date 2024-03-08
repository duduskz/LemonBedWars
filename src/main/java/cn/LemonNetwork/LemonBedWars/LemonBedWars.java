package cn.lemonnetwork.lemonbedwars;

import cn.lemonnetwork.lemonbedwars.Command.MainCommand;
import cn.lemonnetwork.lemonbedwars.Command.RejoinCommand;
import cn.lemonnetwork.lemonbedwars.Command.ShoutCommand;
import cn.lemonnetwork.lemonbedwars.Command.StartCommand;
import cn.lemonnetwork.lemonbedwars.Game.Arena.*;
import cn.lemonnetwork.lemonbedwars.Game.Arena.Menu.Shop;
import cn.lemonnetwork.lemonbedwars.Game.Arena.Menu.ShopItem;
import cn.lemonnetwork.lemonbedwars.Game.Arena.Menu.TeamShop;
import cn.lemonnetwork.lemonbedwars.Game.Arena.SpecialMode.RushModeListeners;
import cn.lemonnetwork.lemonbedwars.Game.Prop.*;
import cn.lemonnetwork.lemonbedwars.Game.Prop.PopUpTowers.ChestPlace;
import cn.lemonnetwork.lemonbedwars.Game.Protect.*;
import cn.lemonnetwork.lemonbedwars.Lobby.Quest;
import cn.lemonnetwork.lemonbedwars.Lobby.Menu.Stats;
import cn.lemonnetwork.lemonbedwars.Menu.bwmenu;
import cn.lemonnetwork.lemonbedwars.Menu.bwmenuListener;
import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.Utils.Menu.InventoryListener;
import cn.lemonnetwork.lemonbedwars.Utils.Menu.InventorySession;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.lemonbedwars.Utils.UseBackLobbyItem;
import cn.lemonnetwork.lemonbedwars.Utils.setPlayerHeadName;
import cn.lemonnetwork.lemonbedwars.waiting.Block;
import cn.lemonnetwork.lemonbedwars.waiting.ChatFormat;
import cn.lemonnetwork.lemonbedwars.waiting.DropItem;
import cn.lemonnetwork.lemonbedwars.waiting.EntityDamage;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class LemonBedWars extends JavaPlugin {
//    public static HashMap<Integer, ShopItem> shopItems=new HashMap<>();
    public static ArrayList<ShopItem> items=new ArrayList<>();
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
    public static boolean adminStart = false;
    public static HashMap<String, Boolean> sharp = new HashMap<>();
    public static HashMap<String, Boolean> Dragon = new HashMap<>();
    public static HashMap<String, Integer> pickaxe = new HashMap<>();
    public static HashMap<String, ArrayList<Integer>> Trap = new HashMap<>();
    public static ArrayList<String> Milk = new ArrayList<>();
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
    public static HashMap<String, Integer> ShopCd = new HashMap<>();
    public static ArrayList<InventorySession> inventorySessions = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("   §bLemon§aBedWars");
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
        saveDefaultConfig();
        if (Objects.equals(getConfig().getString("BungeeMode"), "Game")) {
            if (getConfig().get("Map") != null) {
                getCommand("shout").setExecutor(new ShoutCommand());
                getCommand("start").setExecutor(new StartCommand());
                getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
                getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.waiting.PlayerJoin(), this);
                getServer().getPluginManager().registerEvents(new ChatFormat(), this);
                getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.Game.Arena.PlayerLeave(), this);
                getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.waiting.PlayerLeave(), this);
                getServer().getPluginManager().registerEvents(new NoMob(), this);
                getServer().getPluginManager().registerEvents(new Block(), this);
                getServer().getPluginManager().registerEvents(new EntityDamage(), this);
                getServer().getPluginManager().registerEvents(new UseBackLobbyItem(), this);
                getServer().getPluginManager().registerEvents(new PlayerMove(), this);
                getServer().getPluginManager().registerEvents(new BreakEvent(), this);
                getServer().getPluginManager().registerEvents(new Trip(), this);
                getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
                getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.Game.Arena.ChatFormat(), this);
                getServer().getPluginManager().registerEvents(new WeatherChange(), this);
                getServer().getPluginManager().registerEvents(new SpectatorListener(), this);
                getServer().getPluginManager().registerEvents(new BlockPlace(), this);
                getServer().getPluginManager().registerEvents(new TNTListener(), this);
                getServer().getPluginManager().registerEvents(new FireballListener(), this);
                getServer().getPluginManager().registerEvents(new Shop(), this);
                getServer().getPluginManager().registerEvents(new setPlayerHeadName(), this);
                getServer().getPluginManager().registerEvents(new ChestPlace(), this);
                getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
                getServer().getPluginManager().registerEvents(new ResetDamage(), this);
                getServer().getPluginManager().registerEvents(new DoubleResources(), this);
                getServer().getPluginManager().registerEvents(new NoPickupBed(), this);
                getServer().getPluginManager().registerEvents(new DropItem(), this);
                getServer().getPluginManager().registerEvents(new DragonTargetEvent(), this);
                getServer().getPluginManager().registerEvents(new NoBedMessage(), this);
                getServer().getPluginManager().registerEvents(new GameEnd(), this);
                getServer().getPluginManager().registerEvents(new BridgeEgg(), this);
                getServer().getPluginManager().registerEvents(new InventoryListener(), this);
                getServer().getPluginManager().registerEvents(new ItemListener(), this);
                getServer().getPluginManager().registerEvents(new TeamShop(), this);
                getServer().getPluginManager().registerEvents(new ArmorStandListener(), this);
                getServer().getPluginManager().registerEvents(new NoEntityDamageTeamPlayer(), this);
                getServer().getPluginManager().registerEvents(new PickupItemClearTag(), this);
                getServer().getPluginManager().registerEvents(new IronGolemItem(), this);
                getServer().getPluginManager().registerEvents(new NoBoomBlock(), this);
                getServer().getPluginManager().registerEvents(new NoDropItem(), this);
                getServer().getPluginManager().registerEvents(new PlayerDataManage(), this);
                getServer().getPluginManager().registerEvents(new NoItemCraft(), this);
                getServer().getPluginManager().registerEvents(new Invisibility_Potion(), this);
                state = "waiting";
                WorldCreator seed = new WorldCreator(getConfig().getString("Map.WorldName"));
                playworld = seed.createWorld();
                playworld.setAutoSave(false);
                String[] spawn = LocationUtil.getStringLocation(getConfig().getString("Map.Spawn"));
                playworld.getWorldBorder().setCenter(Double.parseDouble(spawn[0]), Double.parseDouble(spawn[2]));
                playworld.getWorldBorder().setSize(getConfig().getDouble("Map.Size"));
                MinecraftServer.getServer().setMotd("Waiting");
                LemonBedWars.Listeners.put("emerald2", false);
                LemonBedWars.Listeners.put("diamond2", false);
                LemonBedWars.Listeners.put("emerald3", false);
                LemonBedWars.Listeners.put("diamond3", false);
                LemonBedWars.Listeners.put("BedDestroy", false);
                LemonBedWars.Listeners.put("lore", false);
                LemonBedWars.Listeners.put("gameend", false);
                LemonBedWars.Listenername = "钻石生成点II级";
                LemonBedWars.Listenertime = 360;
                CreateTeam.create();
                try {
                    if (getConfig().getString("Map.SpecialMode").equalsIgnoreCase("Rush")) {
                        getServer().getPluginManager().registerEvents(new RushModeListeners(), this);
                        LemonBedWars.Listeners.replace("emerald2", true);
                        LemonBedWars.Listeners.replace("diamond2", true);
                        LemonBedWars.Listeners.replace("emerald3", true);
                        LemonBedWars.Listeners.replace("diamond3", true);
                        LemonBedWars.Listenername = "床自毁";
                        LemonBedWars.Listenertime = 840;
                    }
                } catch (NullPointerException ignored) {
                }


            } else {
                getServer().getPluginManager().registerEvents(new EditMap(), this);
                state = "EditMap";
                MinecraftServer.getServer().setMotd("EditMap");

            }


        } else {

            state = "Lobby";
            getCommand("playmenu").setExecutor(new bwmenu());
            getCommand("rejoin").setExecutor(new RejoinCommand());
            getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.Lobby.PlayerJoin(), this);
            getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.Lobby.ChatFormat(), this);
            getServer().getPluginManager().registerEvents(new bwmenuListener(), this);
            getServer().getPluginManager().registerEvents(new Stats(), this);
            getServer().getPluginManager().registerEvents(new Quest(), this);
            getServer().getPluginManager().registerEvents(new cn.lemonnetwork.lemonbedwars.Lobby.Menu.Shop(), this);
            //getServer().getPluginManager().registerEvents(new Lobby.cn.lemonnetwork.lemonbedwars.BlockBreak(), this);
        }
        HikariConfig APIconfig = new HikariConfig();
        APIconfig.setJdbcUrl("jdbc:mysql://" + getConfig().getString("APIMySQL.url") + ":" + getConfig().getString("APIMySQL.port") + "/" + getConfig().getString("APIMySQL.db"));
        APIconfig.setUsername(getConfig().getString("APIMySQL.username"));
        APIconfig.setPassword(getConfig().getString("APIMySQL.password"));
        APIconfig.setMaximumPoolSize(20);
        APIconfig.addDataSourceProperty("verifyServerCertificate", String.valueOf(false));
        APIconfig.addDataSourceProperty("characterEncoding", "utf8");
        APIconfig.addDataSourceProperty("encoding", "UTF-8");
        APIconfig.addDataSourceProperty("useUnicode", "true");
        APIconfig.addDataSourceProperty("rewriteBatchedStatements", "true");
        APIconfig.addDataSourceProperty("jdbcCompliantTruncation", "false");
        APIconfig.addDataSourceProperty("cachePrepStmts", "true");
        APIconfig.addDataSourceProperty("prepStmtCacheSize", "275");
        APIconfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        APIconfig.addDataSourceProperty("socketTimeout", String.valueOf(TimeUnit.SECONDS.toMillis(30)));
        PlayerDataManage.APIdataSource = new HikariDataSource(APIconfig);
        HikariConfig BedWarsConfig = new HikariConfig();
        BedWarsConfig.setJdbcUrl("jdbc:mysql://" + getConfig().getString("MySQL.url") + ":" + getConfig().getString("MySQL.port") + "/" + getConfig().getString("MySQL.db"));
        BedWarsConfig.setUsername(getConfig().getString("MySQL.username"));
        BedWarsConfig.setPassword(getConfig().getString("MySQL.password"));
        BedWarsConfig.setMaximumPoolSize(40);
        BedWarsConfig.addDataSourceProperty("verifyServerCertificate", String.valueOf(false));
        BedWarsConfig.addDataSourceProperty("characterEncoding", "utf8");
        BedWarsConfig.addDataSourceProperty("encoding", "UTF-8");
        BedWarsConfig.addDataSourceProperty("useUnicode", "true");
        BedWarsConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
        BedWarsConfig.addDataSourceProperty("jdbcCompliantTruncation", "false");
        BedWarsConfig.addDataSourceProperty("cachePrepStmts", "true");
        BedWarsConfig.addDataSourceProperty("prepStmtCacheSize", "275");
        BedWarsConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        BedWarsConfig.addDataSourceProperty("socketTimeout", String.valueOf(TimeUnit.SECONDS.toMillis(30)));
        PlayerDataManage.BedWarsdataSource = new HikariDataSource(BedWarsConfig);
        try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS player_data (" +
                    "uuid TEXT(200)," +
                    "coins INT(200), xp INT(200));";
            statement.executeUpdate(sql);
            if (getConfig().getString("BungeeMode").equalsIgnoreCase("Game")) {
                sql = "CREATE TABLE IF NOT EXISTS player_" + getConfig().getString("Map.Mode") + "_data (" +
                        "uuid TEXT(200)," +
                        "kills INT(200), final_kills INT(200), win INT(200), looses INT(200), deaths INT(200), final_deaths INT(200), beds_destroyed INT(200), games_played INT(200));";
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
            sql = "CREATE TABLE IF NOT EXISTS player_karma (" +
                    "uuid TEXT(200)," +
                    "karma INT(200));";
            apistatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (!getConfig().getString("BungeeMode").equalsIgnoreCase("Lobby")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer("§bLemon§aBedwars §e>> §c服务器即将重置地图, 您没有退出服务器, 因此将您踢出");
                for (Player player1 : Bukkit.getOnlinePlayers()) {

                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    List<String> lobby = JavaPlugin.getPlugin(LemonBedWars.class).getConfig().getStringList("LobbyServer");
                    out.writeUTF(lobby.get(new Random().nextInt(lobby.size())));
                    player1.sendPluginMessage(JavaPlugin.getPlugin(LemonBedWars.class), "BungeeCord", out.toByteArray());
                }
            }
            Bukkit.unloadWorld(getConfig().getString("Map.WorldName"), false);
        }
        PlayerDataManage.BedWarsdataSource.close();
        PlayerDataManage.APIdataSource.close();


        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("   §bLemon§aBedWars");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§b已卸载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
    }

    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);

            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }

            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }

        return classes;
    }


}
