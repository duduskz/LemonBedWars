package cn.lemonnetwork.lemonbedwars.Game.Protect;

import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class ResetDamage implements Listener {
    @EventHandler
    public void listener(EntityDamageEvent e) {
        if (LemonBedWars.state.equalsIgnoreCase("Play")) {
            if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                FileConfiguration config = LemonBedWars.getPlugin(LemonBedWars.class).getConfig();
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + GameStart.getScoreboard().getEntryTeam(e.getEntity().getName()).getName() + ".Spawn"));
                if (Objects.equals(e.getEntity().getLocation(), new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])))) {
                    e.setCancelled(true);
                }
            }
        }
    }
}