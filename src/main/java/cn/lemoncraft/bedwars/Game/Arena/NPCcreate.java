package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public class NPCcreate {
    public static void NPCstart(){
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        for (Team team : GameStart.getScoreboard().getTeams()) {
            if (!team.getName().equalsIgnoreCase("旁观者")) {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§a");
                npc.setAlwaysUseNameHologram(true);
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".shop"));
                Location l = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]) + 2.65, Double.parseDouble(spawn[2]));
                Hologram hologram = DHAPI.createHologram(team.getName() + "dj", l);
                BedWars.Holograms.add(hologram);
                DHAPI.addHologramLine(hologram, "§b道具商店");
                DHAPI.addHologramLine(hologram, "§e右键点击");
                npc.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

                NPC npc2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§c");
                String[] spawn2 = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".teamshop"));
                Location l2 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]) + 2.9, Double.parseDouble(spawn2[2]));
                Hologram hologram2 = DHAPI.createHologram(team.getName() + "td", l2);
                BedWars.Holograms.add(hologram2);
                if (!config.getString("Map.ModeType").equalsIgnoreCase("单挑")) {
                    DHAPI.addHologramLine(hologram2, "§b团队模式");
                } else {
                    DHAPI.addHologramLine(hologram2, "§b单挑模式");
                }
                DHAPI.addHologramLine(hologram2, "§b升级");
                DHAPI.addHologramLine(hologram2, "§e右键点击");
                npc2.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2]), Integer.parseInt(spawn2[3]), Integer.parseInt(spawn2[4])));
            }
        }
    }
}
