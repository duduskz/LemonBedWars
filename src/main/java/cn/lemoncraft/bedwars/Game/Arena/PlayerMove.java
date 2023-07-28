package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
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
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (Objects.equals(config.getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {


                if (player.getLocation().getY() < -20.0D) {
                    player.setHealth(0.0D);
                    player.setHealth(20.0D);
                    //            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));


                }
            }
        }
    }
}