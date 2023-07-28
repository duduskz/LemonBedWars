package cn.lemoncraft.bedwars.Utils;

import cn.lemoncraft.bedwars.BedWars;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Hologram implements Listener {
    public static ArrayList<Integer> holograms = new ArrayList<>();
    public static Plugin plugin = BedWars.getPlugin(BedWars.class);

    public static void removeHologram(Player player, int hologramEntityId) {
        PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(hologramEntityId);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
    }
    public static void updateHologramLine(int id, World world, String newLine) {
        ArmorStand armorStand = (ArmorStand) world.getEntities().get(id);
        armorStand.setCustomName(newLine);
    }
    public static ArrayList<Integer> createHologram(Player player, ArrayList<String> lines, String location, double height) {
        String[] spawn = LocationUtil.getStringLocation(location);
        Location location1 = new Location(Bukkit.getWorld(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Double.parseDouble(spawn[3]));
        ArrayList<Integer> hologramsid = new ArrayList<>();
        double y = location1.getY() + height;
        for (String line : lines) {
            line = line.replace("&", "§");
            WorldServer worldServer = ((CraftWorld) location1.getWorld()).getHandle();
            EntityArmorStand armorStand = new EntityArmorStand(worldServer);
            y = y - height;
            // 设置全息文本的位置
            armorStand.setLocation(location1.getX(), y, location1.getZ(), 0, 0);
            armorStand.setCustomNameVisible(true);
            armorStand.setInvisible(true);
            armorStand.setCustomName(line);
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armorStand);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            hologramsid.add(armorStand.getId());
            holograms.add(armorStand.getId());
        }
        return hologramsid;

        // 发送SpawnEntityLiving包给玩家

    }
}
