package cn.lemonnetwork.lemonbedwars.Utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TAB {
    public static void set(Player p, String abovelist, String underlist) {
        IChatBaseComponent tabheader = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + abovelist + "\"}");
        IChatBaseComponent tabfooter = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + underlist + "\"}");
        PacketPlayOutPlayerListHeaderFooter THpacket = new PacketPlayOutPlayerListHeaderFooter(tabheader);

        try {
            Field f = THpacket.getClass().getDeclaredField("b");
            f.setAccessible(true);
            f.set(THpacket, tabfooter);
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(THpacket);
        }
    }

}
