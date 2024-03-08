package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class PlayerMove implements Listener {
    @EventHandler(
            ignoreCancelled = true,
            priority = EventPriority.HIGHEST
    )
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "Play")) {
                if (player.getLocation().getY() < -20.0D) {
                    if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equals("旁观者")) {

                        player.setHealth(0.0D);
                        player.setHealth(20.0D);
                        //            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));


                    } else {
                        String[] spawn = LocationUtil.getStringLocation(config.getString("Map.Spectator"));
                        player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                    }
                }
            }
        }
    }
}