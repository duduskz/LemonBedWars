package cn.lemoncraft.bedwars.waiting;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Item.Waiting;
import cn.lemoncraft.bedwars.Utils.*;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);
    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        FileConfiguration config = plugin.getConfig();
        Waiting items = new Waiting();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "waiting")) {
                String lang = PlayerDataManage.getPlayerLang(player);
                ItemStack air = new ItemStack(Material.AIR);
                player.getInventory().setHelmet(air);
                player.getInventory().setChestplate(air);
                player.getInventory().setLeggings(air);
                player.getInventory().setBoots(air);
                player.getInventory().clear();
                player.getEnderChest().clear();
                player.removePotionEffect(PotionEffectType.INVISIBILITY);
                player.removePotionEffect(PotionEffectType.SPEED);
                player.removePotionEffect(PotionEffectType.JUMP);
                player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                BedWars.backlobby.put(event.getPlayer().getName(), false);
                if (lang.equalsIgnoreCase("zhcn")) {
                    player.setScoreboard(WaitingScoreBoard.zhcnscoreboard);
                } else if (lang.equalsIgnoreCase("en")) {
                    player.setScoreboard(WaitingScoreBoard.enscoreboard);
                }
                event.setJoinMessage(null);
                NameTAG.setTagPrefix(player.getName(), player.getName(), BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix());
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map.Spawn"));
                player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                String prefix = BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix().substring(0, 2);
                prefix = prefix.substring(0, 2);
                for (Player forplayer : Bukkit.getOnlinePlayers()){
                    if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                        forplayer.sendMessage(prefix+event.getPlayer().getName()+" §e加入了游戏 (§b"+Bukkit.getOnlinePlayers().size()+"§e/§b"+config.getString("Map.MaxPlayer")+"§e)");

                    } else {
                        forplayer.sendMessage(prefix+event.getPlayer().getName()+" §ehas joined (§b"+Bukkit.getOnlinePlayers().size()+"§e/§b"+config.getString("Map.MaxPlayer")+"§e)");

                    }
                    }
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String date = "§7" + formatter.format(calendar.getTime());  //日期
                int online_players = Bukkit.getOnlinePlayers().size();  //获取在线玩家
                player.getInventory().setItem(8, items.getItem("返回大厅", lang));

                if (online_players < config.getInt("Map.NeedPlayer")) {

                    ArrayList<String> scoreboard = new ArrayList<>();

                    for (String entry : WaitingScoreBoard.zhcnscoreboard.getEntries()) {
                        WaitingScoreBoard.zhcnscoreboard.resetScores(entry);
                    }
                    for (String entry : WaitingScoreBoard.enscoreboard.getEntries()) {
                        WaitingScoreBoard.enscoreboard.resetScores(entry);
                    }

                    if (lang.equalsIgnoreCase("zhcn")) {
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
                        scoreboard.add("§e" + BedWars.serverip + "");
                    } else {
                        scoreboard.add(date + " §8mini" + config.getString("Map.mini"));
                        scoreboard.add("§5 ");
                        scoreboard.add("§fMap: §a" + config.getString("Map.Name"));
                        scoreboard.add("§fPlayers: §a" + online_players + "/" + config.getInt("Map.MaxPlayer"));
                        scoreboard.add("§4 ");
                        scoreboard.add("§fWaiting...");
                        scoreboard.add("§b ");
                        scoreboard.add("§fMode: §a" + config.getString("Map.Mode"));
                        scoreboard.add("§fVersion: §7v1.0");
                        scoreboard.add("§f ");
                        scoreboard.add("§e" + BedWars.serverip + "");
                    }
                    for(int i = 0; i < scoreboard.size(); ++i) {
                        WaitingScoreBoard.getObjective(lang).getScore(scoreboard.get(i)).setScore(-i + scoreboard.size());
                    }
                } else {

                if (online_players == config.getInt("Map.NeedPlayer") || online_players == config.getInt("Map.NeedPlayer")) {
                    Cd.start();




                    }
                    if (online_players > config.getInt("Map.NeedPlayer")) {
                        if (BedWars.time != 20) {

                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }
}

