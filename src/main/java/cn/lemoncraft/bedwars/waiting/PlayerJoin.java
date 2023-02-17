package cn.lemoncraft.bedwars.waiting;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Waiting;
import cn.lemoncraft.bedwars.Utils.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        Waiting items = new Waiting();
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "waiting")) {
                player.getInventory().clear();
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                player.removePotionEffect(PotionEffectType.SPEED);
                BedWars.backlobby.put(event.getPlayer().getName(), false);
                String[] spawn = BedWars.getLocaton(config.getString("Map.Spawn"));
                player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                String prefix = BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix();
                prefix = prefix.substring(0, 2);
                Bukkit.broadcastMessage(prefix+event.getPlayer().getName()+" §e加入了游戏 (§b"+Bukkit.getOnlinePlayers().size()+"§e/§b"+config.getString("Map.MaxPlayer")+"§e)");
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String date = "§7" + formatter.format(calendar.getTime());  //日期
                int online_players = Bukkit.getOnlinePlayers().size();  //获取在线玩家
                player.getInventory().setItem(8, items.getItem("返回大厅"));

                if (online_players < config.getInt("Map.NeedPlayer")) {

                    ArrayList<String> scoreboard = new ArrayList<>();
                    scoreboard.add(date + " §8" + config.getString("Map.mini"));
                    scoreboard.add("§5 ");
                    scoreboard.add("§f地图: §a" + config.getString("Map.Name"));
                    scoreboard.add("§f玩家: §a" + online_players + "/" + config.getInt("Map.MaxPlayer"));
                    scoreboard.add("§4 ");
                    scoreboard.add("§f等待中...");
                    scoreboard.add("§b ");
                    scoreboard.add("§f模式: §a" + config.getString("Map.Mode"));
                    scoreboard.add("§f版本: §7v1.0");
                    scoreboard.add("§f ");
                    scoreboard.add("§eLemonCraft.cn");
                    ScoreboardUtil.setScoreBoard(player, "§e起床战争", scoreboard);
                } else {

                if (online_players == config.getInt("Map.NeedPlayer") || online_players == config.getInt("Map.NeedPlayer")) {
                    Cd.start();




                    }
                }
            }
        }
    }
}

