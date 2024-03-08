package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatFormat implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {

        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "waiting")) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                List<Player> playerList = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
                String message = event.getMessage();
                for (Player p : playerList) {
                    p.sendMessage(PlayerDataManage.getPlayerPrefix(player) + player.getName() + "§f: " + message);
                }
            }
        }
    }
}