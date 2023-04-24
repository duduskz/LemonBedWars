package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
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

        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Lobby")) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                List<Player> playerList = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());
                String message = event.getMessage();
                for (Player p : playerList) {
                    p.sendMessage("§7[" + PlayerDataManage.getLevel(player) + "✫" + "] " + BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix() + player.getName() + "§f: " + message);
                }
            }
        }
    }
}
