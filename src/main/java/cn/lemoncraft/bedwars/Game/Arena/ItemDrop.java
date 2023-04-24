package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemDrop {


    public static void start(){
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();

        if (config.getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")){
            String[] spawn = LocationUtil.getStringLocation(config.getString("Map.红队.resources"));
            String[] spawn1 = LocationUtil.getStringLocation(config.getString("Map.蓝队.resources"));
            String[] spawn2 = LocationUtil.getStringLocation(config.getString("Map.绿队.resources"));
            String[] spawn3 = LocationUtil.getStringLocation(config.getString("Map.黄队.resources"));

            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack drop = new ItemStack(Material.IRON_INGOT, 1);
                    World world = BedWars.playworld;

                    int oreCount = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 3, 2, 3)) {
                        if (e instanceof Item) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.IRON_INGOT) {
                                oreCount = oreCount + 1;
                            }
                        }
                    }

                    int oreCount1 = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.IRON_INGOT) {
                                oreCount1 = oreCount1 + 1;
                            }
                        }
                    }

                    int oreCount2 = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.IRON_INGOT) {
                                oreCount2 = oreCount2 + 1;
                            }
                        }
                    }

                    int oreCount3 = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.IRON_INGOT) {
                                oreCount3 = oreCount3 + 1;
                            }
                        }
                    }
                    if (!(oreCount3 >44)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2])), drop);
                    }
                    if (!(oreCount2 >44)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2])), drop);
                    }
                    if (!(oreCount1 >44)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2])), drop);
                    }
                    if (!(oreCount >44)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                    }
                }
             }.runTaskTimer(plugin, 20L,20L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    ItemStack drop = new ItemStack(Material.GOLD_INGOT, 1);
                    World world = BedWars.playworld;
                    int oreCount = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.GOLD_INGOT) {
                                oreCount = oreCount + 1;
                            }
                        }
                    }
                    int oreCount1 = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.GOLD_INGOT) {
                                oreCount1 = oreCount1 + 1;
                            }
                        }
                    }
                    int oreCount2 = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.GOLD_INGOT) {
                                oreCount2 = oreCount2 + 1;
                            }
                        }
                    }
                    int oreCount3 = 0;

                    for (Entity e : Bukkit.getWorld(config.getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2])), 3, 2, 3)) {
                        if (e.getType() == EntityType.DROPPED_ITEM) {
                            Item i = (Item) e;
                            if (i.getItemStack().getType() == Material.GOLD_INGOT) {
                                oreCount3 = oreCount3 + 1;
                            }

                        }
                    }

                    if (!(oreCount3 >6)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2])), drop);
                    }
                    if (!(oreCount2 >6)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2])), drop);
                    }
                    if (!(oreCount1 >6)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2])), drop);
                    }
                    if (!(oreCount >6)) {
                        world.dropItem(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                    }
                }

            }.runTaskTimer(plugin, 20L,100L);
        }
    }
}
