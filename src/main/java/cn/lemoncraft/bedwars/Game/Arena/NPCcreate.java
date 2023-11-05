package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.Hologram;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class NPCcreate {
    public static void NPCstart(){
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        ArrayList<String> shophologramline = new ArrayList<>();
        ArrayList<String> teamshophologramline = new ArrayList<>();
        shophologramline.add("§b道具商店");
        shophologramline.add("§e§l右键点击");
        if (!config.getString("Map.ModeType").equalsIgnoreCase("单挑")) {
            teamshophologramline.add("§b团队模式");
        } else {
            teamshophologramline.add("§b单挑模式");
        }
        teamshophologramline.add("§b升级");
        teamshophologramline.add("§e§l右键点击");
        for (Team team : GameStart.getScoreboard().getTeams()) {
            if (!team.getName().equalsIgnoreCase("旁观者")) {

                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§a");
                npc.setAlwaysUseNameHologram(true);
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".shop"));
                Location l = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]) + 2.65, Double.parseDouble(spawn[2]));


                npc.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                NPC npc2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§c");
                String[] spawn2 = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".teamshop"));
                Location l2 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]) + 2.9, Double.parseDouble(spawn2[2]));

                for (Player player : Bukkit.getOnlinePlayers()) {
                    Hologram.createHologram(player, shophologramline, l.getWorld().getName()+","+l.getX()+","+(l.getY() - 2.5)+","+l.getZ(), 0.3);
                    Hologram.createHologram(player, teamshophologramline, l2.getWorld().getName()+","+l2.getX()+","+(l2.getY() - 2.4)+","+l2.getZ(), 0.3);
                }
                npc2.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2]), Integer.parseInt(spawn2[3]), Integer.parseInt(spawn2[4])));
            }
        }
    }
}
