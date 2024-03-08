package cn.lemonnetwork.lemonbedwars.waiting;

import cn.lemonnetwork.lemonbedwars.Item.Waiting;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.*;
import cn.hpnetwork.lemonnick.API.LemonNickAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PlayerJoin implements Listener {
    Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (!(event.getPlayer().getName() == null)) {
            if (Bukkit.getPluginManager().getPlugin("LemonNick") != null) {
                setPlayerHeadName.setPlayerName(event.getPlayer(), LemonNickAPI.getPlayerNick(event.getPlayer()), LemonNickAPI.getPlayerSkinName(event.getPlayer()));
            }
        }
    }


    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        FileConfiguration config = plugin.getConfig();
        Waiting items = new Waiting();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(LemonBedWars.state, "waiting")) {
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
                LemonBedWars.backlobby.put(event.getPlayer().getName(), false);
                if (lang.equalsIgnoreCase("zhcn")) {
                    player.setScoreboard(WaitingScoreBoard.zhcnscoreboard);
                } else if (lang.equalsIgnoreCase("en")) {
                    player.setScoreboard(WaitingScoreBoard.enscoreboard);
                }
                event.setJoinMessage(null);
                for (Player forplayer : Bukkit.getOnlinePlayers()) {
                    NameTAG.setTagPrefix(forplayer.getName(), forplayer.getName(), PlayerDataManage.getPlayerPrefix(player));

                }
                player.setPlayerListName(PlayerDataManage.getPlayerPrefix(player) + player.getName());
                player.setDisplayName(PlayerDataManage.getPlayerPrefix(player) + player.getName());

                String[] spawn = LocationUtil.getStringLocation(config.getString("Map.Spawn"));
                player.teleport(new Location(Bukkit.getWorld(config.getString("Map.WorldName")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));
                String prefix = PlayerDataManage.getPlayerPrefix(player).substring(0, 2);
                prefix = prefix.substring(0, 2);
                for (Player forplayer : Bukkit.getOnlinePlayers()) {
                    if (PlayerDataManage.getPlayerLang(forplayer).equalsIgnoreCase("zhcn")) {
                        forplayer.sendMessage(prefix + player.getName() + " §e加入了游戏 (§b" + Bukkit.getOnlinePlayers().size() + "§e/§b" + config.getString("Map.MaxPlayer") + "§e)");

                    } else {
                        forplayer.sendMessage(prefix + player.getName() + " §ehas joined (§b" + Bukkit.getOnlinePlayers().size() + "§e/§b" + config.getString("Map.MaxPlayer") + "§e)");

                    }
                }
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

                String date = "§7" + formatter.format(calendar.getTime());  //日期
                int online_players = Bukkit.getOnlinePlayers().size();  //获取在线玩家
                player.getInventory().setItem(0, items.getItem("选择队伍", lang));
                player.getInventory().setItem(8, items.getItem("返回大厅", lang));
//                if (LemonPartyAPI.getPlayerParty(player) != null) {
//
//                    Party party = LemonPartyAPI.getPlayerParty(player);
//                    Team selTeam = null;
//                    for (Team team : GameStart.getScoreboard().getTeams()) {
//
//                        if (team.getEntries().size() == 0) {
//                            selTeam = team;
//                        }
//                    }
//                    if (selTeam != null) {
//                        if ((party.getPlayers().size() + party.getAdmins().size() + 1) < CreateTeam.teamPlayer) {
//                            GameStart.addPlayerToTeam(selTeam, player, CreateTeam.teamColor.get(selTeam.getName()));
//                            if (!selectorTeamPlayer.contains(player.getName())) {
//                                selectorTeamPlayer.add(player.getName());
//                            }
//                        } else {
//                            for (cn.lemonnetwork.lemonparty.Manager.Player forPlayer : party.getPlayers()) {
//                                if (LemonPartyAPI.toBukkitPlayer(forPlayer) != null) {
//                                    Player forPlayerBukkit = LemonPartyAPI.toBukkitPlayer(forPlayer);
//                                    forPlayerBukkit.sendMessage("§7为保障游戏平衡, 你的队伍将在这局游戏被拆散!");
//                                }
//                            }
//                            for (cn.lemonnetwork.lemonparty.Manager.Player forPlayer : party.getPlayers()) {
//                                Player forPlayerBukkit = LemonPartyAPI.toBukkitPlayer(forPlayer);
//                                forPlayerBukkit.sendMessage("§7为保障游戏平衡, 你的队伍将在这局游戏被拆散!");
//                            }
//                            if (LemonPartyAPI.toBukkitPlayer(party.getOwner()) != null) {
//                                LemonPartyAPI.toBukkitPlayer(party.getOwner()).sendMessage("§7为保障游戏平衡, 你的队伍将在这局游戏被拆散!");
//                            }
//                        }
//                    } else {
//                        for (cn.lemonnetwork.lemonparty.Manager.Player forPlayer : party.getPlayers()) {
//                            if (LemonPartyAPI.toBukkitPlayer(forPlayer) != null) {
//                                Player forPlayerBukkit = LemonPartyAPI.toBukkitPlayer(forPlayer);
//                                forPlayerBukkit.sendMessage("§7此游戏空闲的队伍已满, 你的队伍将在这局游戏被拆散!");
//                            }
//                        }
//                        for (cn.lemonnetwork.lemonparty.Manager.Player forPlayer : party.getPlayers()) {
//                            Player forPlayerBukkit = LemonPartyAPI.toBukkitPlayer(forPlayer);
//                            forPlayerBukkit.sendMessage("§7此游戏空闲的队伍已满, 你的队伍将在这局游戏被拆散!");
//                        }
//                        if (LemonPartyAPI.toBukkitPlayer(party.getOwner()) != null) {
//                            LemonPartyAPI.toBukkitPlayer(party.getOwner()).sendMessage("§7此游戏空闲的队伍已满, 你的队伍将在这局游戏被拆散!");
//                        }
//                    }
//                }

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
                        scoreboard.add("§e" + LemonBedWars.serverip);
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
                        scoreboard.add("§e" + LemonBedWars.serverip);
                    }
                    for (int i = 0; i < scoreboard.size(); ++i) {
                        WaitingScoreBoard.getObjective(lang).getScore(scoreboard.get(i)).setScore(-i + scoreboard.size());
                    }
                } else {

                    if (online_players == config.getInt("Map.NeedPlayer") || online_players == config.getInt("Map.NeedPlayer")) {
                        Cd.start();


                    }
                    if (online_players > config.getInt("Map.NeedPlayer")) {
                        if (LemonBedWars.time != 20) {

                            player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
                        }
                    }
                }

            }
        }
    }
}

