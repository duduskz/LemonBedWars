package cn.lemoncraft.bedwars.waiting;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class PlayerLeave implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void Death(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "waiting")) {
                String prefix = BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix();
                prefix = prefix.substring(0, 2);
                for (Player forplayer : Bukkit.getOnlinePlayers()) {
                    if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                        forplayer.sendMessage(prefix + player.getName() + " §e已退出！");

                    } else {
                        forplayer.sendMessage(prefix + player.getName() + " §ehas quit!");

                    }
                }

            }
        }
    }
}
