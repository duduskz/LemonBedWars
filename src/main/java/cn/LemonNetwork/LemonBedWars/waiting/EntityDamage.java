package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class EntityDamage implements Listener {

    @EventHandler
    public void Nofalldamage(EntityDamageEvent event) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (event.getEntity() instanceof Player) {
            if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
                if (Objects.equals(LemonBedWars.state, "waiting")) {
                    event.setCancelled(true);
                }

            }

        }
    }
}