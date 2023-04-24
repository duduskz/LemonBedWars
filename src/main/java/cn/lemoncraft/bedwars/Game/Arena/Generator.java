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
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    static Plugin plugin = BedWars.getPlugin(BedWars.class);
    public static void start() {

        BedWars.GeneratorInt.put("d", 0);
        BedWars.GeneratorInt.put("e", 0);
        List<String> emerald = plugin.getConfig().getStringList("Map.generator.Emerald");
        List<String> diamond = plugin.getConfig().getStringList("Map.generator.Diamond");
        for (String foremerald : emerald) {
            String[] spawn = LocationUtil.getStringLocation(foremerald);
            armorstand(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), Material.EMERALD_BLOCK);
        }
        for (String fordiamond : diamond) {
            String[] spawn = LocationUtil.getStringLocation(fordiamond);
            armorstand(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), Material.DIAMOND_BLOCK);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (BedWars.GeneratorInt.get("d") == 0) {
                    if (!BedWars.Listeners.get("diamond2")) {
                        BedWars.GeneratorInt.replace("d", 30);
                    } else {
                        if (BedWars.Listeners.get("diamond2") && !BedWars.Listeners.get("diamond3")) {
                            BedWars.GeneratorInt.replace("d", 20);
                        } else {
                            if (BedWars.Listeners.get("diamond3")) {
                                BedWars.GeneratorInt.replace("d", 15);
                            }
                        }
                    }

                    for (String fordiamond : diamond) {
                        String[] spawn = LocationUtil.getStringLocation(fordiamond);
                        ItemStack drop = new ItemStack(Material.DIAMOND, 1);
                        int shu = 0;
                        for (Entity e : Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 3, 2, 3)) {
                            if (e instanceof Item) {
                                Item i = (Item) e;
                                shu++;
                            }

                        }
                        int zhi = 3;
                        if (plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("4v4v4v4") || plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("3v3v3v3") || plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("4v4")) {
                            zhi = 7;
                        }
                        if (!(shu > zhi)) {
                            Item item = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).dropItem(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                        }
                    }
                }
                if (BedWars.GeneratorInt.get("e") == 0) {
                    if (!BedWars.Listeners.get("emerald2")) {
                        BedWars.GeneratorInt.replace("e", 70);
                    } else {
                        if (BedWars.Listeners.get("emerald2") && !BedWars.Listeners.get("emerald3")) {
                            BedWars.GeneratorInt.replace("e", 50);
                        } else {
                            if (BedWars.Listeners.get("emerald3")) {
                                BedWars.GeneratorInt.replace("e", 30);
                            }
                        }
                    }

                    for (String foremerald : emerald) {
                        String[] spawn = LocationUtil.getStringLocation(foremerald);
                        ItemStack drop = new ItemStack(Material.EMERALD, 1);
                        int shu = 0;
                        for (Entity e : Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).getNearbyEntities(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 3, 2, 3)) {
                            if (e instanceof Item) {
                                Item i = (Item) e;
                                shu++;
                            }

                        }
                        int zhi = 3;
                        if (!(shu > zhi)) {
                            Item item = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).dropItem(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                        }
                    }


                }
                BedWars.GeneratorInt.replace("d", BedWars.GeneratorInt.get("d") - 1);
                BedWars.GeneratorInt.replace("e", BedWars.GeneratorInt.get("e") - 1);
            }


        }.runTaskTimer(plugin, 0L,20L);
    }
    public static int holohao = 0;
    public static void armorstand(Location location, Material material) {
        holohao++;
        ArmorStand armorStand = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).spawn(location.add(0, 0, 0), ArmorStand.class);
        armorStand.setHelmet(new ItemStack(material));
        armorStand.setVisible(false);
        armorStand.setArms(false);
        armorStand.setBasePlate(false);
        armorStand.setGravity(false);
        new BukkitRunnable() {
            @Override
            public void run() {

                armorStand.teleport(new Location(armorStand.getWorld(), location.getX(), location.getY() + 0.7, location.getZ(), armorStand.getLocation().getYaw() + 2 , location.getPitch()));
            }
        }.runTaskTimer(BedWars.getPlugin(BedWars.class), 1L, 1L);
        Hologram hologram = DHAPI.createHologram("gengrator"+holohao, new Location(armorStand.getWorld(), location.getX(), location.getY() + 3.7, location.getZ()));
        new BukkitRunnable() {
            @Override
            public void run() {

                String level = "不知道";
                List<String> lines = new ArrayList<>();
                if (material.equals(Material.DIAMOND_BLOCK)) {
                    if (!BedWars.Listeners.get("diamond2")) {
                        level = "I";
                    } else {
                        if (BedWars.Listeners.get("diamond2") && !BedWars.Listeners.get("diamond3")) {
                            level = "II";
                        } else {
                            if (BedWars.Listeners.get("diamond3")) {
                                level = "III";
                            }
                        }
                    }

                    lines.add("§e等级 §c" + level);
                    lines.add("§b钻石");
                    lines.add("§e将在 §c" + BedWars.GeneratorInt.get("d") + " §e秒后产出");
                    DHAPI.setHologramLines(hologram, lines);

                } else {
                    if (!BedWars.Listeners.get("emerald2")) {
                        level = "I";
                    } else {
                        if (BedWars.Listeners.get("emerald2") && !BedWars.Listeners.get("emerald3")) {
                            level = "II";
                        } else {
                            if (BedWars.Listeners.get("emerald3")) {
                                level = "III";
                            }
                        }
                    }
                    lines.add("§e等级 §c" + level);
                    lines.add("§2绿宝石");
                    lines.add("§e将在 §c" + BedWars.GeneratorInt.get("e") + " §e秒后产出");
                    DHAPI.setHologramLines(hologram, lines);

                }
                BedWars.Holograms.add(hologram);

            }

        }.runTaskTimer(BedWars.getPlugin(BedWars.class), 15L, 15L);


    }
}