package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemDrop {


    public static void start(){
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (config.getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")){
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack drop = new ItemStack(Material.IRON_INGOT, 1);
                    World world = BedWars.playworld;
                    String[] spawn = BedWars.getLocaton(config.getString("Map.红队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                    String[] spawn1 = BedWars.getLocaton(config.getString("Map.蓝队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2])), drop);
                    String[] spawn2 = BedWars.getLocaton(config.getString("Map.绿队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2])), drop);
                    String[] spawn3 = BedWars.getLocaton(config.getString("Map.黄队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2])), drop);

                }
             }.runTaskTimer(plugin, 20L,20L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack drop = new ItemStack(Material.GOLD_INGOT, 1);
                    World world = BedWars.playworld;
                    String[] spawn = BedWars.getLocaton(config.getString("Map.红队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                    String[] spawn1 = BedWars.getLocaton(config.getString("Map.蓝队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2])), drop);
                    String[] spawn2 = BedWars.getLocaton(config.getString("Map.绿队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2])), drop);
                    String[] spawn3 = BedWars.getLocaton(config.getString("Map.黄队.resources"));
                    world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2])), drop);

                }
            }.runTaskTimer(plugin, 20L,80L);
        }
    }
}
