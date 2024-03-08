package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.Hologram;
import cn.lemonnetwork.lemonbedwars.Utils.ItemUtil;
import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.Utils.RotatingIcon;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    private static final Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
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
                    if (!LemonBedWars.Listeners.get("diamond2")) {
                        generatorInt.replace("d", 30);
                    } else if (LemonBedWars.Listeners.get("diamond2") && !LemonBedWars.Listeners.get("diamond3")) {
                        generatorInt.replace("d", 20);
                    } else if (LemonBedWars.Listeners.get("diamond3")) {
                        generatorInt.replace("d", 15);
                    }

                    for (String fordiamond : diamond) {
                        String[] spawn = LocationUtil.getStringLocation(fordiamond);
                        ItemStack drop = new ItemStack(Material.DIAMOND, 1);
                        drop = ItemUtil.tag(drop, "item", "diamondGeneratorItem");
                        int number = 0;
                        for (Entity e : Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).getNearbyEntities(
                                new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                        Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 1, 5, 1)) {
                            if (e.getType() == EntityType.DROPPED_ITEM) {
                                String tag = ItemUtil.getTag(((Item)e).getItemStack(), "item", String.class);
                                if (tag != null) {
                                    if (tag.equalsIgnoreCase("diamondGeneratorItem")) {
                                        number+=((Item)e).getItemStack().getAmount();
                                    }
                                }

                            }
                        }
                        int zhi = 3;
                        if (plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")
                                || plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("3v3v3v3")
                                || plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("4v4")) {
                            zhi = 7;
                        }
                         if (!(number > zhi)) {
                            Item item = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).dropItem(
                                    new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                            Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), drop);
                            item.setVelocity(new Vector(0, 0, 0));
                        }
                    }
                }
                if (generatorInt.get("e") == 0) {
                    if (!LemonBedWars.Listeners.get("emerald2")) {
                        generatorInt.replace("e", 70);
                    } else if (LemonBedWars.Listeners.get("emerald2") && !LemonBedWars.Listeners.get("emerald3")) {
                        generatorInt.replace("e", 50);
                    } else if (LemonBedWars.Listeners.get("emerald3")) {
                        generatorInt.replace("e", 30);
                    }

                    for (String foremerald : emerald) {
                        String[] spawn = foremerald.split(",");
                        ItemStack drop = new ItemStack(Material.EMERALD, 1);
                        drop = ItemUtil.tag(drop, "item", "emeraldGeneratorItem");
                        int number = 0;
                        for (Entity e : Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")).getNearbyEntities(
                                new Location(Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName")),
                                        Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])), 1, 5, 1)) {
                            if (e.getType() == EntityType.DROPPED_ITEM) {
                                String tag = ItemUtil.getTag(((Item)e).getItemStack(), "item", String.class);
                                if (tag != null) {
                                    if (tag.equalsIgnoreCase("emeraldGeneratorItem")) {
                                        number+=((Item)e).getItemStack().getAmount();
                                    }
                                }
                            }
                        }
                        int zhi = 3;
                        if (!(number > zhi)) {
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

    public static void armorStand(Location location, Material material) {
        RotatingIcon rotatingIcon = new RotatingIcon(location.clone().add(0, 1, 0), material);
        rotatingIcon.start();
        new BukkitRunnable() {
            final HashMap<String, ArrayList<Integer>> holograms = new HashMap<>();
            @Override
            public void run() {
                String level = "不知道";
                List<String> lines = new ArrayList<>();

                if (material.equals(Material.DIAMOND_BLOCK)) {
                    if (!LemonBedWars.Listeners.get("diamond2")) {
                        level = "I";
                    } else if (LemonBedWars.Listeners.get("diamond2") && !LemonBedWars.Listeners.get("diamond3")) {
                        level = "II";
                    } else if (LemonBedWars.Listeners.get("diamond3")) {
                        level = "III";
                    }

                    lines.add("§e等级 §c" + level);
                    lines.add("§b钻石");
                    lines.add("§e将在 §c" + generatorInt.get("d") + "§e 秒后生成");
                } else if (material.equals(Material.EMERALD_BLOCK)) {
                    if (!LemonBedWars.Listeners.get("emerald2")) {
                        level = "I";
                    } else if (LemonBedWars.Listeners.get("emerald2") && !LemonBedWars.Listeners.get("emerald3")) {
                        level = "II";
                    } else if (LemonBedWars.Listeners.get("emerald3")) {
                        level = "III";
                    }

                    lines.add("§e等级 §c" + level);
                    lines.add("§2绿宝石");
                    lines.add("§e将在 §c" + generatorInt.get("e") + "§e 秒后生成");
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ArrayList<Integer> hologram = Hologram.createHologram(player, new ArrayList<>(lines), player.getWorld().getName()+","+location.getX()+","+(location.getY() + 1.1)+","+location.getZ(), 0.3);

                    if (holograms.get(player.getName()) != null) {
                        for (int hologramid : holograms.get(player.getName())) {
                            Hologram.removeHologram(player, hologramid);
                        }
                    }
                    holograms.put(player.getName(), hologram);


                }

            }
        }.runTaskTimer(LemonBedWars.getPlugin(LemonBedWars.class), 0L, 10L);
    }
}
