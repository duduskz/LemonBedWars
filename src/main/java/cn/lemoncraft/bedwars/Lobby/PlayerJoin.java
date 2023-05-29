package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Lobby;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    private static List<String> lastStr = new ArrayList<>();

    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            FileConfiguration config = plugin.getConfig();
            String[] spawn = LocationUtil.getStringLocation(config.getString("Spawn"));
            player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

            Lobby items = new Lobby();

            player.getInventory().clear();

            //player.getInventory().setItem(0, items.getItem("游戏菜单"));

            //player.getInventory().setItem(1, items.getItem("个人档案"));

            player.getInventory().setItem(2, items.getItem("商店与特效"));

            //player.getInventory().setItem(8, items.getItem("选择大厅"));

            //player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 0));

        }
    }
}

