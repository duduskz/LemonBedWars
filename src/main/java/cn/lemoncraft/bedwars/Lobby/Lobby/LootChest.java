package cn.lemoncraft.bedwars.Lobby.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
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
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
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
    private void sendArmorStandPacket(Player player, EntityArmorStand armorStand) {
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armorStand);

        ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
    private void spawnFakePlayer(Location location) {
        LivingEntity fakePlayer = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.PLAYER);
        fakePlayer.setCustomName("FakePlayer");
        fakePlayer.setCustomNameVisible(true);
        fakePlayer.setMaxHealth(20);
        fakePlayer.setHealth(20);

        // You can set other properties and equipment for the fake player here
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
        WorldServer worldServer = ((CraftWorld) openlocation.getWorld()).getHandle();
        EntityArmorStand open = new EntityArmorStand(worldServer);
        PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(open.getId(), 4, CraftItemStack.asNMSCopy(getSkull("https://www.minecraftskins.com/uploads/skins/2020/12/15/hypixel-skywars-loot-chest--only-head--16056882.png")));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        open.setCustomName("§e点击打开！");
        spawnFakePlayer( openlocation);
        open.setLocation(openlocation.getX(), openlocation.getY(), openlocation.getZ(), openlocation.getPitch(), openlocation.getYaw());
        //open.setInvisible(true);
        //open.setBasePlate(false);
        //open.setGravity(false);

        try {
            sendArmorStandPacket(player, open);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
