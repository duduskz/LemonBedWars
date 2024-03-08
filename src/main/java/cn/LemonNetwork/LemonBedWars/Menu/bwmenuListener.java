package cn.lemonnetwork.lemonbedwars.Menu;

import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.lemonbedwars.Utils.getBungeeServerInfo;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class bwmenuListener implements Listener {
    @EventHandler
    public void useitem(InventoryClickEvent event) {
        try {

            Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
            FileConfiguration config = plugin.getConfig();
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getTitle().equalsIgnoreCase("游玩起床战争")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§c关闭")) {

                    player.closeInventory();
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§c点击这里重新加入！")) {
                    player.performCommand("rejoin");
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§a练习")) {
                    player.performCommand("bwp");
                }
                if (event.getCurrentItem().getType() == Material.BED) {
                    String strInfo = event.getCurrentItem().getItemMeta().getDisplayName();
                    String group = strInfo.substring(strInfo.indexOf("(") + 1, strInfo.indexOf(")"));
                    if (plugin.getConfig().getConfigurationSection("ServerGroups." + group) == null) {
                        player.sendMessage("§c这个模式没有可用的房间!");
                        return;
                    }
                    player.sendMessage("§a正在传送至 起床战争" + group);
                    for (String server : plugin.getConfig().getConfigurationSection("ServerGroups." + group).getKeys(false)) {
                        String address = plugin.getConfig().getString("ServerGroups." + group + "." + server + ".ip");
                        if (getBungeeServerInfo.getMotd(address) != null) {
                            if (getBungeeServerInfo.getMotd(address).equalsIgnoreCase("Waiting")) {

                                try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
                                     Statement statement = connection.createStatement()) {
                                    String sql = "SELECT * FROM player_rejoin WHERE uuid = '" + player.getUniqueId().toString() + "'";
                                    ResultSet rs = statement.executeQuery(sql);
                                    if (rs.next()) {
                                        sql = "UPDATE player_rejoin" + " SET ServerName = " + server + " WHERE uuid = '" + player.getUniqueId().toString() + "'";
                                        statement.executeUpdate(sql);
                                        sql = "UPDATE player_rejoin" + " SET Mode = '" + group + "' WHERE uuid = '" + player.getUniqueId().toString() + "'";
                                    } else {
// 如果不存在相同name的记录，则插入新记录
                                        sql = "INSERT INTO player_rejoin (uuid, ServerName, Mode) VALUES ('" + player.getUniqueId().toString() + "', '" + server + "', '" + group + "')";

                                    }
                                    statement.executeUpdate(sql);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                                out.writeUTF("Connect");
                                out.writeUTF(server);
                                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                                return;
                            }
                        }
                    }
                    player.sendMessage("§c这个模式没有可用的房间!");
                }
                player.closeInventory();
            }
            if (event.getCurrentItem().getType() == Material.SIGN) {
                String strInfo = event.getCurrentItem().getItemMeta().getDisplayName();
                String group = strInfo.substring(strInfo.indexOf("(") + 1, strInfo.indexOf(")"));
                player.sendMessage("§c敬请期待");
                //player.performCommand("bw mapsel " + group);
                event.setCancelled(false);
                player.closeInventory();
            }
        } catch (NullPointerException n) {

        }
    }
}