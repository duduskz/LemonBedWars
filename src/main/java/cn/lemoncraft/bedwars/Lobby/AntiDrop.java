//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class AntiDrop implements Listener {

    @EventHandler

    public void Drop(PlayerDropItemEvent event) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            event.setCancelled(true);
        }
    }

}
