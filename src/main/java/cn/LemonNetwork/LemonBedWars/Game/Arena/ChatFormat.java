package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.hpnetwork.lemonnick.API.LemonNickAPI;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
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

        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "Play")) {
                if (!event.isCancelled()) {
                    Player player = event.getPlayer();
                    int dengji = PlayerDataManage.getLevel(player);
                    String message = event.getMessage();
                    String Format = "§7[" + dengji + "✫" + "] " + GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix() + "[" + GameStart.getScoreboard().getEntryTeam(player.getName()).getName() + "] " + PlayerDataManage.getPlayerPrefix(player) + player.getName() + "§f: " + message;
                    if (plugin.getConfig().getString("Map.ModeType").equalsIgnoreCase("单挑")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            event.setCancelled(true);
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("旁观者")) {
                                if (GameStart.getScoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("旁观者")) {
                                    p.sendMessage(Format);
                                }
                            } else if (!GameStart.getScoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("旁观者")) {
                                p.sendMessage(Format);
                            }
                        }
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

