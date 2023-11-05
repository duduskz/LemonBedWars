package cn.lemoncraft.bedwars.Lobby.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.Blocks;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class LootChest implements CommandExecutor {
    public static ItemStack getSkull(String url) {
        ItemStack skull= new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        if (url == null || url.isEmpty())
            return skull;

        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        String[] spawn = LocationUtil.getStringLocation(BedWars.getPlugin(BedWars.class).getConfig().getString("Lobby.LootChest"));
        Location location = (new Location(Bukkit.getWorld(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Double.parseDouble(spawn[3]), Float.parseFloat(spawn[4]), Float.parseFloat(spawn[5])));
        ArmorStand entity = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        player.teleport(location);
        player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 1.0F, 1.0F);
        entity.setPassenger(player);
        entity.setVisible(false);
        Location openlocation = (new Location(Bukkit.getWorld(spawn[0]), Double.parseDouble(spawn[1]) - 2, Double.parseDouble(spawn[2]), Double.parseDouble(spawn[3]), Float.parseFloat(spawn[4]) - 120, Float.parseFloat(spawn[5])));
        WorldServer worldServer = ((CraftWorld) player.getWorld()).getHandle();
        EntityArmorStand armorStand = new EntityArmorStand(worldServer);


        // Set the armor stand's position

        // Set the custom name to make it visible only to the specified player
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(player.getName() + "'s Armor Stand");

        // Add the diamond block as a helmet
        net.minecraft.server.v1_8_R3.ItemStack diamondBlock = new net.minecraft.server.v1_8_R3.ItemStack(Blocks.DIAMOND_BLOCK);
        armorStand.setEquipment(4, diamondBlock);
        armorStand.setInvisible(true);
        armorStand.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());

        //
//        // Add the armor stand to the world
//        worldServer.addEntity(armorStand);

        // Send the spawn packet to the player
        PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawnPacket);        //open.setInvisible(true);
        //open.setBasePlate(false);
        //open.setGravity(false);

        return false;
    }
}
