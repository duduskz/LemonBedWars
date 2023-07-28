package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ChatFormat implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {

        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                if (!event.isCancelled()) {
                    Player player = event.getPlayer();
                    int dengji = PlayerDataManage.getLevel(player);
                    String message = event.getMessage();
                    String Format = "§7[" + dengji + "✫" + "] " + GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix() + "[" + GameStart.getScoreboard().getEntryTeam(player.getName()).getName() + "] " + BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix() + player.getName() + "§f: " + message;
                    if (plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("单挑")) {
                        event.setFormat(Format);
                    } else {
                        for (String p : GameStart.getScoreboard().getEntryTeam(player.getName()).getEntries()) {
                            event.setCancelled(true);

                            Bukkit.getPlayer(p).sendMessage(Format);
                        }
                    }
                }
            }

        }
    }
}

