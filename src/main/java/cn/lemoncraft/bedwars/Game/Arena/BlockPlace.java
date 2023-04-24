package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class BlockPlace implements Listener {
    @EventHandler
    public void place(BlockPlaceEvent e){
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            World world = Bukkit.getWorld(plugin.getConfig().getString("Map.WorldName"));
            if (Objects.equals(BedWars.state, "Play")) {
                //e.getBlock().setData(new );
                if (e.getBlock().getType() == Material.FIRE){
                    e.setCancelled(true);
                } else {
                    for (Team team : GameStart.getcoreboard().getTeams()) {
                        if (!team.getName().equalsIgnoreCase("旁观者")) {
                            String spawn1 = plugin.getConfig().getString("Map."+team.getName()+".Spawn");
                            String[] spawn2 = LocationUtil.getStringLocation(spawn1);
                            Location spawn3 = new Location(world, Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2]));
                            if (spawn3.distance(e.getBlock().getLocation()) < 4.0) {
                                e.setCancelled(true);
                                e.getPlayer().sendMessage("§c你不能在这里放置方块!");
                            }
                        }

                    }
                    for (String emerald : plugin.getConfig().getStringList("Map.generator.Emerald")) {
                        String[] spawn22 = LocationUtil.getStringLocation(emerald);
                        Location spawn33 = new Location(world, Double.parseDouble(spawn22[0]), Double.parseDouble(spawn22[1]), Double.parseDouble(spawn22[2]));
                        if (spawn33.distance(e.getBlock().getLocation()) < 3.0) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage("§c你不能在这里放置方块!");
                        } else {
                            for (String diamond : plugin.getConfig().getStringList("Map.generator.Diamond")) {
                                String[] spawn222 = LocationUtil.getStringLocation(diamond);
                                Location spawn333 = new Location(world, Double.parseDouble(spawn222[0]), Double.parseDouble(spawn222[1]), Double.parseDouble(spawn222[2]));
                                if (spawn333.distance(e.getBlock().getLocation()) < 3.0) {
                                    e.setCancelled(true);
                                    e.getPlayer().sendMessage("§c你不能在这里放置方块!");
                                } else {
                                    BedWars.changedBlocks.add(e.getBlock().getLocation());
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}
