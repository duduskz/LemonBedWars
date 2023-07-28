package cn.lemoncraft.bedwars.Utils;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeamBase;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

public class NameTAG {
    public static void setTagPrefix(String playerName, String group, String prefix) {

        PacketPlayOutScoreboardTeam plPacket = getPacket(playerName, group, prefix);

        for(Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(plPacket);
        }
    }

    private static PacketPlayOutScoreboardTeam getPacket(String playerName, String group, String prefix) {
        PacketPlayOutScoreboardTeam plPacket = new PacketPlayOutScoreboardTeam();

        if(group.length() > 4) {
            group = group.substring(0, 4);
        }

        try {
            Field scoreName = plPacket.getClass().getDeclaredField("a");
            scoreName.setAccessible(true);
            char[] random = "ACDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
            scoreName.set(plPacket, "zzzz" + group + random[new Random().nextInt(random.length)]);
            scoreName.setAccessible(false);

            Field tMode = plPacket.getClass().getDeclaredField("i");
            tMode.setAccessible(true);
            tMode.set(plPacket, 0);
            tMode.setAccessible(false);

            Field ntVisibility = plPacket.getClass().getDeclaredField("e");
            ntVisibility.setAccessible(true);
            ntVisibility.set(plPacket, ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e);
            ntVisibility.setAccessible(false);

            Field tPList = plPacket.getClass().getDeclaredField("g");
            tPList.setAccessible(true);
            tPList.set(plPacket, Arrays.asList(new String[] {playerName}));
            tPList.setAccessible(false);

            Field tPrefix = plPacket.getClass().getDeclaredField("c");
            tPrefix.setAccessible(true);
            tPrefix.set(plPacket, prefix);
            tPrefix.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plPacket;
    }
}
