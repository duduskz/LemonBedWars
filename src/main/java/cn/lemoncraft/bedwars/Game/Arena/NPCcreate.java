package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
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

public class NPCcreate {
    public static void NPCstart(){
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (config.getString("Map.ModeType").equalsIgnoreCase("4v4v4v4")) {
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§a");
            npc.setAlwaysUseNameHologram(true);
            String[] spawn = BedWars.getLocaton(config.getString("Map.红队.shop"));
            Location l = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1])+2.65, Double.parseDouble(spawn[2]));
            Hologram hologram = DHAPI.createHologram("dj1", l);
            BedWars.Holograms.add(hologram);
            DHAPI.addHologramLine(hologram,"§b道具商店");
            DHAPI.addHologramLine(hologram,"§e右键点击");
            npc.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
            NPC npc1 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§a");
            String[] spawn1 = BedWars.getLocaton(config.getString("Map.蓝队.shop"));
            Location l3 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1])+2.65, Double.parseDouble(spawn1[2]));
            Hologram hologram3 = DHAPI.createHologram("dj2", l3);
            BedWars.Holograms.add(hologram3);
            DHAPI.addHologramLine(hologram3,"§b道具商店");
            DHAPI.addHologramLine(hologram3,"§e右键点击");
            npc1.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn1[0]), Double.parseDouble(spawn1[1]), Double.parseDouble(spawn1[2]), Integer.parseInt(spawn1[3]), Integer.parseInt(spawn1[4])));
            NPC npc2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§a");
            String[] spawn2 = BedWars.getLocaton(config.getString("Map.绿队.shop"));
            Location l4 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1])+2.65, Double.parseDouble(spawn2[2]));
            Hologram hologram4 = DHAPI.createHologram("dj3", l4);
            BedWars.Holograms.add(hologram4);
            DHAPI.addHologramLine(hologram4,"§b道具商店");
            DHAPI.addHologramLine(hologram4,"§e右键点击");
            npc2.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn2[0]), Double.parseDouble(spawn2[1]), Double.parseDouble(spawn2[2]), Integer.parseInt(spawn2[3]), Integer.parseInt(spawn2[4])));
            NPC npc3 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§a");
            String[] spawn3 = BedWars.getLocaton(config.getString("Map.黄队.shop"));
            Location l5 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1])+2.65, Double.parseDouble(spawn3[2]));
            Hologram hologram5 = DHAPI.createHologram("dj4", l5);
            BedWars.Holograms.add(hologram5);
            DHAPI.addHologramLine(hologram5,"§b道具商店");
            DHAPI.addHologramLine(hologram5,"§e右键点击");
            npc3.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn3[0]), Double.parseDouble(spawn3[1]), Double.parseDouble(spawn3[2]), Integer.parseInt(spawn3[3]), Integer.parseInt(spawn3[4])));
            NPC npc4 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§c");
            String[] spawn4 = BedWars.getLocaton(config.getString("Map.红队.teamshop"));
            Location l2 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn4[0]), Double.parseDouble(spawn4[1])+2.9, Double.parseDouble(spawn4[2]));
            Hologram hologram2 = DHAPI.createHologram("td1", l2);
            BedWars.Holograms.add(hologram2);
            DHAPI.addHologramLine(hologram2,"§b团队模式");
            DHAPI.addHologramLine(hologram2,"§b升级");
            DHAPI.addHologramLine(hologram2,"§e右键点击");
            npc4.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn4[0]), Double.parseDouble(spawn4[1]), Double.parseDouble(spawn4[2]), Integer.parseInt(spawn4[3]), Integer.parseInt(spawn4[4])));

            NPC npc5 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§c");
            String[] spawn5 = BedWars.getLocaton(config.getString("Map.蓝队.teamshop"));
            Location l6 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn5[0]), Double.parseDouble(spawn5[1])+2.9, Double.parseDouble(spawn5[2]));
            Hologram hologram6 = DHAPI.createHologram("td2", l6);
            BedWars.Holograms.add(hologram6);
            DHAPI.addHologramLine(hologram6,"§b团队模式");
            DHAPI.addHologramLine(hologram6,"§b升级");
            DHAPI.addHologramLine(hologram6,"§e右键点击");
            npc4.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn4[0]), Double.parseDouble(spawn4[1]), Double.parseDouble(spawn4[2]), Integer.parseInt(spawn4[3]), Integer.parseInt(spawn4[4])));

            npc5.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn5[0]), Double.parseDouble(spawn5[1]), Double.parseDouble(spawn5[2]), Integer.parseInt(spawn5[3]), Integer.parseInt(spawn5[4])));
            NPC npc6 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§c");
            String[] spawn6 = BedWars.getLocaton(config.getString("Map.绿队.teamshop"));
            Location l7 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn6[0]), Double.parseDouble(spawn6[1])+2.9, Double.parseDouble(spawn6[2]));
            Hologram hologram7 = DHAPI.createHologram("td3", l7);
            BedWars.Holograms.add(hologram7);
            DHAPI.addHologramLine(hologram7,"§b团队模式");
            DHAPI.addHologramLine(hologram7,"§b升级");
            DHAPI.addHologramLine(hologram7,"§e右键点击");
            npc6.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn6[0]), Double.parseDouble(spawn6[1]), Double.parseDouble(spawn6[2]), Integer.parseInt(spawn6[3]), Integer.parseInt(spawn6[4])));
            NPC npc7 = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§c");
            String[] spawn7 = BedWars.getLocaton(config.getString("Map.黄队.teamshop"));
            Location l8 = new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn7[0]), Double.parseDouble(spawn7[1])+2.9, Double.parseDouble(spawn7[2]));
            Hologram hologram8 = DHAPI.createHologram("td4", l8);
            BedWars.Holograms.add(hologram8);
            DHAPI.addHologramLine(hologram8,"§b团队模式");
            DHAPI.addHologramLine(hologram8,"§b升级");
            DHAPI.addHologramLine(hologram8,"§e右键点击");
            npc7.spawn(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn7[0]), Double.parseDouble(spawn7[1]), Double.parseDouble(spawn7[2]), Integer.parseInt(spawn7[3]), Integer.parseInt(spawn7[4])));
        }
    }
}
