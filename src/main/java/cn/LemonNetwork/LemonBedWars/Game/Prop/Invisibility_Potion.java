package cn.lemonnetwork.lemonbedwars.Game.Prop;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Invisibility_Potion implements Listener {
    public static ArrayList<String> invisibility = new ArrayList<>();

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (isInvisibilityPotion(event.getItem())) {
            for (PotionEffect pe : player.getActivePotionEffects()) {
                if (pe.getType().toString().contains("INVISIBILITY")) {
                    // 给玩家添加隐身药水效果，持续时间根据需要进行调整
                    for (Player forplayer : Bukkit.getOnlinePlayers()) {
                        PacketPlayOutEntityEquipment helmet = new PacketPlayOutEntityEquipment(player.getEntityId(), 1, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.AIR)));
                        PacketPlayOutEntityEquipment chest = new PacketPlayOutEntityEquipment(player.getEntityId(), 2, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.AIR)));
                        PacketPlayOutEntityEquipment pants = new PacketPlayOutEntityEquipment(player.getEntityId(), 3, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.AIR)));
                        PacketPlayOutEntityEquipment boots = new PacketPlayOutEntityEquipment(player.getEntityId(), 4, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.AIR)));
                        PlayerConnection boundTo = ((CraftPlayer) forplayer).getHandle().playerConnection;
                        boundTo.sendPacket(helmet);
                        boundTo.sendPacket(chest);
                        boundTo.sendPacket(pants);
                        boundTo.sendPacket(boots);
                        invisibility.add(player.getName());
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
                                PacketPlayOutEntityEquipment helmet = new PacketPlayOutEntityEquipment(entityPlayer.getId(), 4, entityPlayer.inventory.getArmorContents()[3]);
                                PacketPlayOutEntityEquipment chest = new PacketPlayOutEntityEquipment(entityPlayer.getId(), 3, entityPlayer.inventory.getArmorContents()[2]);
                                PacketPlayOutEntityEquipment pants = new PacketPlayOutEntityEquipment(entityPlayer.getId(), 2, entityPlayer.inventory.getArmorContents()[1]);
                                PacketPlayOutEntityEquipment boots = new PacketPlayOutEntityEquipment(entityPlayer.getId(), 1, entityPlayer.inventory.getArmorContents()[0]);
                                EntityPlayer boundTo = ((CraftPlayer) forplayer).getHandle();
                                boundTo.playerConnection.sendPacket(helmet);
                                boundTo.playerConnection.sendPacket(chest);
                                boundTo.playerConnection.sendPacket(pants);
                                boundTo.playerConnection.sendPacket(boots);
                                invisibility.remove(player.getName());
                            }
                        }.runTaskLater(LemonBedWars.getPlugin(LemonBedWars.class), 600);
                    }
                }
            }
        }


    }
    public boolean isInvisibilityPotion(org.bukkit.inventory.ItemStack itemStack) {
        if (!itemStack.getType().equals(org.bukkit.Material.POTION)) return false;

        org.bukkit.inventory.meta.PotionMeta pm = (org.bukkit.inventory.meta.PotionMeta) itemStack.getItemMeta();

        if (pm != null && pm.hasCustomEffects()) {
            return pm.hasCustomEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY);
        }

        org.bukkit.potion.Potion potion = org.bukkit.potion.Potion.fromItemStack(itemStack);
        org.bukkit.potion.PotionType type = potion.getType();

        return type.getEffectType().equals(org.bukkit.potion.PotionEffectType.INVISIBILITY);
    }
}