package cn.lemoncraft.bedwars.waiting;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Waiting;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import cn.lemoncraft.bedwars.Utils.NameTAG;
import cn.lemoncraft.bedwars.Utils.TAB;
import cn.lemoncraft.bedwars.Utils.WaitingScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PlayerJoin implements Listener {
    public boolean isExistColumn(ResultSet rs, String columnName) {
        try {
            if (rs.findColumn(columnName) > 0 ) {
                return true;
            }
        }
        catch (SQLException | NullPointerException e) {
            return false;
        }

        return false;
    }
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FileConfiguration config = plugin.getConfig();
        Waiting items = new Waiting();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "waiting")) {
                ItemStack air = new ItemStack(Material.AIR);
                player.getInventory().setHelmet(air);
                player.getInventory().setChestplate(air);
                player.getInventory().setLeggings(air);
                player.getInventory().setBoots(air);
                player.getInventory().clear();
                player.getEnderChest().clear();
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                player.removePotionEffect(PotionEffectType.SPEED);
                BedWars.backlobby.put(event.getPlayer().getName(), false);
                TAB.set(event.getPlayer(), "      §b§l你正在§e§lLemonCraft.cn§b§l上进行游戏", "    §a§lRank以及更多！§c§l请访问Store.LemonCraft.cn");
                NameTAG.setTagPrefix(player.getName(), player.getName(), BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix());
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map.Spawn"));
                player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                String prefix = BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().substring(0, 2);
                prefix = prefix.substring(0, 2);
                Bukkit.broadcastMessage(prefix+event.getPlayer().getName()+" §e加入了游戏 (§b"+Bukkit.getOnlinePlayers().size()+"§e/§b"+config.getString("Map.MaxPlayer")+"§e)");
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String date = "§7" + formatter.format(calendar.getTime());  //日期
                int online_players = Bukkit.getOnlinePlayers().size();  //获取在线玩家
                player.getInventory().setItem(8, items.getItem("返回大厅"));

                if (online_players < config.getInt("Map.NeedPlayer")) {

                    ArrayList<String> scoreboard = new ArrayList<>();
                    scoreboard.add(date + " §8mini" + config.getString("Map.mini"));
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
                    for (String entry : WaitingScoreBoard.scoreboard.getEntries()) {
                        WaitingScoreBoard.scoreboard.resetScores(entry);
                    }
                    for(int i = 0; i < scoreboard.size(); ++i) {
                        WaitingScoreBoard.getObjective().getScore(scoreboard.get(i)).setScore(-i + scoreboard.size());
                    }
                } else {

                if (online_players == config.getInt("Map.NeedPlayer") || online_players == config.getInt("Map.NeedPlayer")) {
                    Cd.start();




                    }
                    if (online_players > config.getInt("Map.NeedPlayer")) {
                        if (BedWars.time != 5 &&BedWars.time != 4 &&BedWars.time != 3 &&BedWars.time != 2 &&BedWars.time != 1 &&BedWars.time != 20 &&BedWars.time != 15 &&BedWars.time != 10) {
                            player.sendMessage("§e游戏将在 §b"+BedWars.time+" §e秒后开始！");
                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}

