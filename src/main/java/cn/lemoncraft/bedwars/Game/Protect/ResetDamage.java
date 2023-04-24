package cn.lemoncraft.bedwars.Game.Protect;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
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
    public void listener(EntityDamageEvent e){
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            FileConfiguration config = BedWars.getPlugin(BedWars.class).getConfig();
            String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + GameStart.getcoreboard().getEntryTeam(e.getEntity().getName()).getName() + ".Spawn"));

            if (Objects.equals(e.getEntity().getLocation(), new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])))){
                e.setCancelled(true);
            }
        }
    }
}
