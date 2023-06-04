package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

public class ItemDrop {
    public static void start() {
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack drop = new ItemStack(Material.IRON_INGOT, 1);
                    World world = BedWars.playworld;

                    for (Team team : GameStart.getcoreboard().getTeams()) {
                        if (!team.getName().equalsIgnoreCase("旁观者")) {
                            String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".resources"));
                            int oreCount = countItems(world, spawn, Material.IRON_INGOT);

                            if (oreCount <= 44) {
                                dropItemAtLocation(world, spawn, drop);
                            }
                        }
                    }
                }
            }.runTaskTimer(plugin, 20L, 20L);

            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack drop = new ItemStack(Material.GOLD_INGOT, 1);
                    World world = BedWars.playworld;
                    for (Team team : GameStart.getcoreboard().getTeams()) {
                        if (!team.getName().equalsIgnoreCase("旁观者")) {
                            String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".resources"));
                            int oreCount = countItems(world, spawn, Material.GOLD_INGOT);

                            if (oreCount <= 44) {
                                dropItemAtLocation(world, spawn, drop);
                            }
                        }
                    }
                }
            }.runTaskTimer(plugin, 20L, 120L);
    }

    private static int countItems(World world, String[] spawn, Material itemType) {
        int oreCount = 0;

        for (Entity e : world.getNearbyEntities(getLocationFromStringArray(spawn, world), 1, 2, 1)) {
            if (e instanceof Item) {
                Item i = (Item) e;
                if (i.getItemStack().getType() == itemType) {
                    oreCount++;
                }
            }
        }

        return oreCount;
    }
    private static void dropItemAtLocation(World world, String[] spawn, ItemStack itemStack) {
        double x = Double.parseDouble(spawn[0]);
        double y = Double.parseDouble(spawn[1]);
        double z = Double.parseDouble(spawn[2]);

        world.dropItem(new Location(world, x, y, z), itemStack);
    }
    private static Location getLocationFromStringArray(String[] locationData, World world) {
        double x = Double.parseDouble(locationData[0]);
        double y = Double.parseDouble(locationData[1]);
        double z = Double.parseDouble(locationData[2]);

        return new Location(world, x, y, z);
    }
}