package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class EntityDamage implements Listener {

    @EventHandler
    public void Nofalldamage(EntityDamageEvent event) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (event.getEntity() instanceof Player) {
            if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
                event.setCancelled(true);
            }

        }

    }
}
