package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class uselobbyitem1 implements Listener {
    @EventHandler
    public void uselobbyitem1(PlayerInteractEvent event){
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            Player player = event.getPlayer();
            if (Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§a游戏菜单 §7(右键点击)")) {
                player.performCommand("gamemenu");
            }
            if (Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§b个人档案 §7(右键点击)")) {
                player.performCommand("personalfiles");
            }
            if (Objects.equals(event.getItem().getItemMeta().getDisplayName(), "§e选择大厅 §7(右键点击)")) {
                player.performCommand("sellobby");
            }

            event.setCancelled(true);
        }
    }
}
