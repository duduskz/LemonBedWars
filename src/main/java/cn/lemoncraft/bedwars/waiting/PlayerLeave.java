package cn.lemoncraft.bedwars.waiting;

import cn.hpnetwork.lemonnick.API.LemonNickAPI;
import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
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
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "waiting")) {
                String prefix = LemonNickAPI.getPlayerRank(player).substring(0, 2);
                for (Player forplayer : Bukkit.getOnlinePlayers()) {
                    if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                        forplayer.sendMessage(prefix + LemonNickAPI.getPlayerNick(player) + " §e已退出！");

                    } else {
                        forplayer.sendMessage(prefix + LemonNickAPI.getPlayerNick(player) + " §ehas quit!");

                    }
                }

            }
        }
    }
}
