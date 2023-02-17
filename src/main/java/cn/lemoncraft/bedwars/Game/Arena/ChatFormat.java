package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Objects;

public class ChatFormat implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {

        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                File file = new File(plugin.getDataFolder(), "C:/Bedwars/PlayerData.yml");
                int xp = YamlConfiguration.loadConfiguration(file).getInt(player.getName() + ".XP");
                DecimalFormat dF = new DecimalFormat("0");
                String dengji = dF.format((float) xp / 5000);
                int dengjiint = Integer.parseInt(dengji);
                int dengjijiayi = dengjiint + 1;
                String message = event.getMessage();

                for (String p : GameStart.getcoreboard().getEntryTeam(player.getName()).getEntries()) {
                    Bukkit.getPlayer(p).sendMessage("§7[" + dengjijiayi + "✫" + "] " + GameStart.getcoreboard().getEntryTeam(player.getName()).getSuffix() + "[" + GameStart.getcoreboard().getEntryTeam(player.getName()).getName() + "] " + BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix() + player.getName() + "§f: " + message);
                }
            }
        }
    }
}

