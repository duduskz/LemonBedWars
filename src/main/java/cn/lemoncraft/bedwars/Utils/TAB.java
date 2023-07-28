package cn.lemoncraft.bedwars.Utils;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TAB {
    public static void set(Player p, String abovelist, String underlist) {
        EntityPlayer pl = (((CraftPlayer)p).getHandle());
        PlayerConnection c = pl.playerConnection;
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'text': '" + abovelist+ "'}");
        IChatBaseComponent msg = IChatBaseComponent.ChatSerializer.a("{'text': '" + underlist + "'}");
        PacketPlayOutPlayerListHeaderFooter l = new PacketPlayOutPlayerListHeaderFooter(header);

        c.sendPacket(l);

        try {
            Field field = l.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(l, msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.sendPacket(l);
        }
    }

}
