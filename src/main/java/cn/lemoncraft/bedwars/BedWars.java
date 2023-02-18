package cn.lemoncraft.bedwars;

import cn.lemoncraft.bedwars.Command.MainCommand;
import cn.lemoncraft.bedwars.Command.ShoutCommand;
import cn.lemoncraft.bedwars.Command.StartCommand;
import cn.lemoncraft.bedwars.Game.Arena.*;
import cn.lemoncraft.bedwars.Game.Arena.Menu.Shop;
import cn.lemoncraft.bedwars.Game.Prop.FireballListener;
import cn.lemoncraft.bedwars.Game.Prop.PopUpTowers.ChestPlace;
import cn.lemoncraft.bedwars.Game.Prop.TNTListener;
import cn.lemoncraft.bedwars.Game.Protect.FoodChangeEvent;
import cn.lemoncraft.bedwars.Game.Protect.ResetDamage;
import cn.lemoncraft.bedwars.Game.Protect.WeatherChange;
import cn.lemoncraft.bedwars.Lobby.AntiDrop;
import cn.lemoncraft.bedwars.Lobby.EntityDamage;
import cn.lemoncraft.bedwars.Lobby.UseLobbyItem;
import cn.lemoncraft.bedwars.Lobby.uselobbyitem1;
import cn.lemoncraft.bedwars.Menu.bwmenu;
import cn.lemoncraft.bedwars.Menu.bwmenuListener;
import cn.lemoncraft.bedwars.Utils.UseBackLobbyItem;
import cn.lemoncraft.bedwars.waiting.BlockBreak;
import cn.lemoncraft.bedwars.waiting.PlayerJoin;
import cn.lemoncraft.bedwars.waiting.PlayerLeave;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class BedWars extends JavaPlugin {
    public static LuckPerms api;
    public static List<Hologram> Holograms = new ArrayList<>();
    public static String state;
    public static int time = 20;
    public static int Listenertime = 360;
    public static String Listenername = "钻石生成点II级";
    public static HashMap<String, Boolean> Listeners = new HashMap<>();
    public static HashMap<String, Integer> shoutcd = new HashMap<>();
    public static HashMap<String, Integer> kill = new HashMap<>();
    public static HashMap<String, Integer> finalkill = new HashMap<>();
    public static HashMap<String, Integer> breakbed = new HashMap<>();
    public static HashMap<String, Integer> coins = new HashMap<>();
    public static HashMap<String, Integer> xp = new HashMap<>();
    public static HashMap<String, Boolean> backlobby = new HashMap<>();
    public static HashMap<String, String> playeradditem = new HashMap<>();
    public static HashMap<String, Boolean> shears = new HashMap<>();
    public static HashMap<String, Integer> pickaxe = new HashMap<>();
    public static HashMap<String, Integer> axe = new HashMap<>();

    public static HashMap<String, String> spectator = new HashMap<>();
    public static List<Location> changedBlocks = new ArrayList<>();
    public static Map<Player, Integer> Fireballcooldown = new HashMap();
    public static World playworld;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("   §bLemon§aBedwars");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§b已加载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.Lobby.PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new AntiDrop(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new ChatFormat(), this);
        getServer().getPluginManager().registerEvents(new UseLobbyItem(), this);
        getServer().getPluginManager().registerEvents(new uselobbyitem1(), this);
        getServer().getPluginManager().registerEvents(new bwmenuListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.waiting.ChatFormat(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.waiting.EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new UseBackLobbyItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new BreakEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new ChatFormat(), this);
        getServer().getPluginManager().registerEvents(new WeatherChange(), this);
        getServer().getPluginManager().registerEvents(new SpectatorListener(), this);
        getServer().getPluginManager().registerEvents(new cn.lemoncraft.bedwars.Game.Arena.PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new TNTListener(), this);
        getServer().getPluginManager().registerEvents(new FireballListener(), this);
        getServer().getPluginManager().registerEvents(new Shop(), this);
        getServer().getPluginManager().registerEvents(new ChestPlace(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new ResetDamage(), this);
        getServer().getPluginManager().registerEvents(new ResetDamage(), this);
        WorldCreator seed = new WorldCreator(getConfig().getString("Map.WorldName"));
        playworld = seed.createWorld();
        getCommand("bedwarsgame").setExecutor(new MainCommand());
        getCommand("playmenu").setExecutor(new bwmenu());
        getCommand("shout").setExecutor(new ShoutCommand());
        getCommand("start").setExecutor(new StartCommand());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        if (Objects.equals(getConfig().getString("BungeeMode"), "Game")){
            state = "waiting";
            MinecraftServer.getServer().setMotd("ing");
        }
        api = LuckPermsProvider.get();
        playworld.setAutoSave(false);
        String[] spawn = BedWars.getLocaton(getConfig().getString("Map.Spawn"));
        playworld.getWorldBorder().setCenter(Double.parseDouble(spawn[0]), Double.parseDouble(spawn[2]));
        playworld.getWorldBorder().setSize(getConfig().getDouble("Map.Size"));
        BedWars.Listeners.put("diamond2", false);
        BedWars.Listeners.put("diamond3", false);
        BedWars.Listeners.put("emerald2", false);
        BedWars.Listeners.put("emerald3", false);
        BedWars.Listeners.put("BedDestroy", false);
        BedWars.Listeners.put("lore", false);
        BedWars.Listeners.put("gameend", false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Entity e : Bukkit.getWorld(JavaPlugin.getPlugin(BedWars.class).getConfig().getString("Map.WorldName")).getEntities()){
            if (e.getType() == EntityType.ITEM_FRAME){
                e.remove();
            }
        }
        for (Hologram h : Holograms){
            DHAPI.removeHologram(h.getName());
        }
        Bukkit.unloadWorld(JavaPlugin.getPlugin(BedWars.class).getConfig().getString("Map.WorldName"),false);

        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("   §bLemon§aBedwars");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§b已卸载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
    }
    public static String[] getLocaton(String loc) {
        String substring = loc.substring(0, loc.length());
        return substring.split(",");//以逗号分割
    }
}
