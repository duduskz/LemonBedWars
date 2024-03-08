package cn.lemonnetwork.lemonbedwars.Lobby;

import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
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
            if (Objects.equals(LemonBedWars.state, "Lobby")) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                List<Player> playerList = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
                String message = event.getMessage();
                for (Player p : playerList) {
                    p.sendMessage("§7[" + PlayerDataManage.getLevel(player) + "✫" + "] " + PlayerDataManage.getPlayerPrefix(player) + player.getName() + "§f: " + message);
                }
            }
    }
}
