package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    private static final Plugin plugin = BedWars.getPlugin(BedWars.class);
    private static final Map<String, Integer> generatorInt = new HashMap<>();



    public static void start() {
        generatorInt.put("d", 0);
        generatorInt.put("e", 0);

        List<String> emerald = plugin.getConfig().getStringList("Map.generator.Emerald");
        List<String> diamond = plugin.getConfig().getStringList("Map.generator.Diamond");

        for (String foremerald : emerald) {
            String[] spawn = foremerald.split(",");
            armorStand(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                    Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), Material.EMERALD_BLOCK);
        }

        for (String fordiamond : diamond) {
            String[] spawn = fordiamond.split(",");
            armorStand(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                    Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), Material.DIAMOND_BLOCK);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (generatorInt.get("d") == 0) {
                    if (!BedWars.Listeners.get("diamond2")) {
                        generatorInt.replace("d", 30);
                    } else if (BedWars.Listeners.get("diamond2") && !BedWars.Listeners.get("diamond3")) {
                        generatorInt.replace("d", 20);
                    } else if (BedWars.Listeners.get("diamond3")) {
                        generatorInt.replace("d", 15);
                    }

                    for (String fordiamond : diamond) {
                        String[] spawn = LocationUtil.getStringLocation(fordiamond);
                        ItemStack drop = new ItemStack(Material.DIAMOND, 1);
                        int shu = 0;
                        for (Entity e : Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).getNearbyEntities(
                                new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                        Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 1, 5, 1)) {
                            if (e.getType() == EntityType.DROPPED_ITEM) {
                                shu++;
                            }
                        }
                        int zhi = 3;
                        if (plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")
                                || plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")
                                || plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("4v4")) {
                            zhi = 7;
                        }
                         if (!(shu > zhi)) {
                            Item item = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).dropItem(
                                    new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                            Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                            item.setVelocity(new Vector(0, 0, 0));
                        }
                    }
                }
                if (generatorInt.get("e") == 0) {
                    if (!BedWars.Listeners.get("emerald2")) {
                        generatorInt.replace("e", 70);
                    } else if (BedWars.Listeners.get("emerald2") && !BedWars.Listeners.get("emerald3")) {
                        generatorInt.replace("e", 50);
                    } else if (BedWars.Listeners.get("emerald3")) {
                        generatorInt.replace("e", 30);
                    }

                    for (String foremerald : emerald) {
                        String[] spawn = foremerald.split(",");
                        ItemStack drop = new ItemStack(Material.EMERALD, 1);
                        int shu = 0;
                        for (Entity e : Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).getNearbyEntities(
                                new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                        Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 1, 5, 1)) {
                            if (e.getType() == EntityType.DROPPED_ITEM) {
                                shu++;
                            }
                        }
                        int zhi = 3;
                        if (!(shu > zhi)) {
                            Item item = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).dropItem(
                                    new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                            Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                            item.setVelocity(new Vector(0, 0, 0));
                        }
                    }
                }
                generatorInt.replace("d", generatorInt.get("d") - 1);
                generatorInt.replace("e", generatorInt.get("e") - 1);
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private static int holohao = 0;

    public static void armorStand(Location location, Material material) {
        holohao++;
        ArmorStand armorStand = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).spawn(location, ArmorStand.class);
        armorStand.setHelmet(new ItemStack(material));
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.teleport(new Location(armorStand.getWorld(), location.getX(), location.getY() + 0.7, location.getZ(),
                        armorStand.getLocation().getYaw() + 2, location.getPitch()));
            }
        }.runTaskTimer(BedWars.getPlugin(BedWars.class), 1L, 1L);

        Hologram hologram = DHAPI.createHologram("generator" + holohao,
                new Location(armorStand.getWorld(), location.getX(), location.getY() + 3.7, location.getZ()));
        BedWars.Holograms.add(hologram);

        new BukkitRunnable() {
            @Override
            public void run() {
                String level = "不知道";
                List<String> lines = new ArrayList<>();

                if (material.equals(Material.DIAMOND_BLOCK)) {
                    if (!BedWars.Listeners.get("diamond2")) {
                        level = "I";
                    } else if (BedWars.Listeners.get("diamond2") && !BedWars.Listeners.get("diamond3")) {
                        level = "II";
                    } else if (BedWars.Listeners.get("diamond3")) {
                        level = "III";
                    }

                    lines.add("§e等级 §c" + level);
                    lines.add("§b钻石");
                    lines.add("§e将在 §c" + generatorInt.get("d") + "§e 秒后生成");
                } else if (material.equals(Material.EMERALD_BLOCK)) {
                    if (!BedWars.Listeners.get("emerald2")) {
                        level = "I";
                    } else if (BedWars.Listeners.get("emerald2") && !BedWars.Listeners.get("emerald3")) {
                        level = "II";
                    } else if (BedWars.Listeners.get("emerald3")) {
                        level = "III";
                    }

                    lines.add("§e等级 §c" + level);
                    lines.add("§2绿宝石");
                    lines.add("§e将在 §c" + generatorInt.get("e") + "§e 秒后生成");
                }
                DHAPI.setHologramLines(hologram, lines);
            }
        }.runTaskTimer(BedWars.getPlugin(BedWars.class), 1L, 1L);
    }
}
