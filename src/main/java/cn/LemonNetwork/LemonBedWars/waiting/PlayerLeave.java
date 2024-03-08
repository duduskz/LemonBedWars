package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.Game.Arena.Menu.Selector;
import cn.hpnetwork.lemonnick.API.LemonNickAPI;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class PlayerLeave implements Listener {
    Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
    @EventHandler
    public void Death(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "waiting")) {
                String prefix = PlayerDataManage.getPlayerPrefix(player).substring(0, 2);
                for (Player forplayer : Bukkit.getOnlinePlayers()) {
                    if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                        forplayer.sendMessage(prefix + player.getName() + " §e已退出！");

                    } else {
                        forplayer.sendMessage(prefix + player.getName() + " §ehas quit!");

                    }
                }
                Selector.selectorTeamPlayer.remove(player.getName());
                player.getInventory().clear();

            }
        }
    }
}
