package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Objects;

public class ChatFormat implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {

        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                if (!event.isCancelled()) {
                    event.setCancelled(true);
                    Player player = event.getPlayer();
                    int dengji = PlayerDataManage.getLevel(player);
                    String message = event.getMessage();
                    HashMap<String, Boolean> cishu = new HashMap<>();
                    for (String p : GameStart.getcoreboard().getEntryTeam(player.getName()).getEntries()) {
                        if (cishu.get(p) == null) {
                            Bukkit.getPlayer(p).sendMessage("§7[" + dengji + "✫" + "] " + GameStart.getcoreboard().getEntryTeam(player.getName()).getSuffix() + "[" + GameStart.getcoreboard().getEntryTeam(player.getName()).getName() + "] " + BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix() + player.getName() + "§f: " + message);
                            cishu.put(p, true);
                        }
                    }
                }

            }
        }
    }
}

