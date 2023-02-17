package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class UseLobbyItem implements Listener {

    @EventHandler
    public void uselobbyitem(InventoryClickEvent event){
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 0) {
                player.performCommand("gamemenu");
            }
            if (event.getSlot() == 1) {
                player.performCommand("personalfiles");
            }

            if (event.getSlot() == 8) {
                player.performCommand("sellobby");
            }
            event.setCancelled(true);
        }
    }

}
