package cn.lemoncraft.bedwars.Game.Prop;

import cn.lemoncraft.bedwars.BedWars;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Invisibility_Potion implements Listener {
    public static ArrayList<String> invisibility = new ArrayList<>();
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().getType() == Material.POTION) {
            // 检查喝下的是否是隐身药水
            // 请注意，这里的药水效果类型（PotionEffectType）可能需要根据实际情况进行调整
            if (event.getItem().getDurability() == 8192) {
                // 给玩家添加隐身药水效果，持续时间根据需要进行调整
                for (Player forplayer : Bukkit.getOnlinePlayers()) {
                    PacketPlayOutEntityEquipment packetHelmet = new PacketPlayOutEntityEquipment(player.getEntityId(), 4, null);
                    PacketPlayOutEntityEquipment packetChestplate = new PacketPlayOutEntityEquipment(player.getEntityId(), 3, null);
                    PacketPlayOutEntityEquipment packetLeggings = new PacketPlayOutEntityEquipment(player.getEntityId(), 2, null);
                    PacketPlayOutEntityEquipment packetBoots = new PacketPlayOutEntityEquipment(player.getEntityId(), 1, null);
                    ((CraftPlayer) forplayer).getHandle().playerConnection.sendPacket(packetHelmet);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetChestplate);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetLeggings);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetBoots);
                    invisibility.add(player.getName());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            invisibility.remove(player.getName());
                        }
                    }.runTaskLater(BedWars.getPlugin(BedWars.class), 600);
                }
            }
        }
    }
}
