package cn.lemoncraft.bedwars.Command;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class RejoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            try {
                Statement statement = PlayerDataManage.BedWarsdataSource.getConnection().createStatement();
                String sql = "SELECT * FROM player_rejoin WHERE uuid = '"+player.getUniqueId().toString()+"'";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                player.sendMessage("§a正在传送至 起床战争" + rs.getString("Mode"));
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(rs.getString("ServerName"));
                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
            } catch (SQLException e) {
                e.printStackTrace();
                player.sendMessage("§c无法重新加入游戏,可能上一场游戏已经结束或你没有意外断开链接");
            }
        }
        return false;
    }
}
