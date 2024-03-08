package cn.lemonnetwork.lemonbedwars.Utils;

/*
* 作者: 不撸死gi 下划↓线 一欸四
*/

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.Vector3f;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Timer;
import java.util.TimerTask;

public class RotatingIcon {

    private final Location location;
    private final Material material;
    private EntityArmorStand nmsArmorStand;

    public RotatingIcon(Location location, Material material) {
        this.location = location;
        this.material = material;
    }

    public void start() {
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setHelmet(new ItemStack(material));

        CraftArmorStand craftArmorStand = (CraftArmorStand) armorStand;
        nmsArmorStand = craftArmorStand.getHandle();

        TimerTask rotate = new TimerTask() {
            private boolean rotateLeft = true;
            private long time = 0;
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(LemonBedWars.getPlugin(LemonBedWars.class), () -> rotateHead(nmsArmorStand, rotateLeft));
                time += 5;
                if (time / 1000L == 3) {
                    time = 0;
                    rotateLeft = !rotateLeft;
                }
            }
        };
        Timer rotateTimer = new Timer();

        TimerTask upDownFloat = new TimerTask() {
            private boolean goingUp = true;
            private final double maxLoc = location.getY() + 0.5;
            private final double minLoc = location.getY() - 0.5;
            @Override
            public void run() {
                if (nmsArmorStand.getBukkitEntity().getLocation().getY() > maxLoc ||
                        nmsArmorStand.getBukkitEntity().getLocation().getY() < minLoc) goingUp = !goingUp;
                floatHead(nmsArmorStand, goingUp);
            }
        };
        Timer upDownFloatTimer = new Timer();
        rotateTimer.schedule(rotate, 0, 5L);
        upDownFloatTimer.schedule(upDownFloat, 0, 5L);
    }

    private void floatHead(EntityArmorStand armorStand, boolean up) {
        Location loc = armorStand.getBukkitEntity().getLocation();
        armorStand.setLocation(loc.getX(), loc.getY() + (up ? 0.001 : -0.001), loc.getZ(), loc.getYaw(), loc.getPitch());
    }

    private void rotateHead(EntityArmorStand armorStand, boolean left) {
        float currentYaw = armorStand.yaw;
        float newYaw = left ? currentYaw - 1.0f : currentYaw + 1.0f;
        armorStand.yaw = newYaw;
        armorStand.setHeadPose(new Vector3f(0, (float) Math.toRadians(newYaw), 0));
        PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

}
