package cn.lemonnetwork.lemonbedwars.Game.Arena;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import cn.lemonnetwork.lemonbedwars.Utils.TitleUtil;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GameEnd implements Listener {
    public static boolean isEnd = false;
    ArrayList<UUID> players = new ArrayList<>();
    @EventHandler
    public void goodGame(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (!players.contains(player.getUniqueId())) {
            players.add(player.getUniqueId());
            ArrayList<String> messages = new ArrayList<>() {{
                add("goodgame");
                add("gg");
                add("good game");
                add("good fight");
                add("gf");
                add("goodfight");
            }};
            if (messages.contains(message.toLowerCase()) && isEnd) {
                int karmas = 5;
                if (player.hasPermission("bedwars.twoLevelKarma")) karmas = 10;
                if (player.hasPermission("bedwars.threeLevelKarma")) karmas = 15;
                if (player.hasPermission("bedwars.fourLevelKarma")) karmas = 20;
                if (player.hasPermission("bedwars.fiveLevelKarma")) karmas = 25;
                if (player.hasPermission("bedwars.topLevelKarma")) karmas = 50;
                String karmaString = "人品值";
                if (PlayerDataManage.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    karmaString = "Karma";
                }
                player.sendMessage("§d+" + karmas + " " + karmaString);
                try (Connection connection = PlayerDataManage.APIdataSource.getConnection();
                     Statement statement = connection.createStatement()) {
                    String sql = "SELECT * FROM player_karma WHERE uuid = '" + player.getUniqueId().toString() + "'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        sql = "UPDATE player_karma" + " SET karma = " + (rs.getInt("karma") + karmas) + " WHERE uuid = '" + player.getUniqueId().toString() + "'";
                    } else {
                        sql = "INSERT INTO player_karma (uuid, karma) VALUES ('" + player.getUniqueId().toString() + "', " + karmas + ")";
                    }
                    statement.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }

        }
    }
    public static void gameend(String winteam) {
        isEnd = true;
        HashMap<String, Integer> kills = new HashMap<>();
        if (winteam == null) {
            PlayerDataManage.GameEnd(null);
        } else {
            PlayerDataManage.GameEnd(GameStart.getScoreboard().getTeam(winteam));
        }
        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("旁观者")) {
                kills.put(player.getName(), LemonBedWars.kill.get(player.getName()) + LemonBedWars.finalkill.get(player.getName()));
                Set<String> set = kills.keySet();
                Arrays.sort(set.toArray());
                //value-sort
                List<Map.Entry<String, Integer>> list = new ArrayList<>(LemonBedWars.kill.entrySet());
                //list.sort()
                list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                //collections.sort()
                list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                //for
                int xp = PlayerDataManage.getPlayerXP(player);
                int dengji = PlayerDataManage.getLevel(player);
                int dqjy = xp - ((dengji - 1) * 5000);
                int progress = dqjy % 5000;
                StringBuilder expbar = new StringBuilder();
                for (int i = 0; i < 30; i++) { // 经验条总长度为50
                    if (i < progress * 10 / 5000) {
                        expbar.append("§b■"); // 已经获得的经验用■表示
                    } else {
                        expbar.append("§7■"); // 尚未获得的经验用▲表示
                    }
                }
                double percentage = (double) progress / 5000 * 100;
                if (winteam == null) {
                    TitleUtil.sendTitle(player, 0, 200, 0, "§c§l游戏结束", "");

                } else {
                    if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equals(winteam)) {
                        TitleUtil.sendTitle(player, 0, 200, 0, "§6§l胜利", "");
                        player.sendMessage("§b+25 起床战争经验 (获胜奖励)");
                        player.sendMessage("§6+10 硬币 (获胜奖励)");
                        PlayerDataManage.addPlayerXP(player, 25);
                        PlayerDataManage.addPlayerCoins(player, 10);
                    } else {
                        TitleUtil.sendTitle(player, 0, 200, 0, "§c§l游戏结束", "");
                    }
                }
                String xpbar = "§8[ " + expbar + " §8]";

                try (Connection connection = PlayerDataManage.BedWarsdataSource.getConnection();
                     Statement statement = connection.createStatement()) {
                    if (statement.executeQuery("SELECT * FROM player_rejoin WHERE uuid = '" + player.getUniqueId().toString() + "'").next()) {
                        String sql = "DELETE FROM player_rejoin WHERE uuid = '" + player.getUniqueId().toString() + "'";
                        statement.executeUpdate(sql);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("                                   §f§l奖励总览");
                player.sendMessage("");
                player.sendMessage("   §7你获得了");
                player.sendMessage("    §f• §6" + LemonBedWars.coins.get(player.getName()) + " 起床战争硬币");
                player.sendMessage("");
                player.sendMessage("                            §b起床战争经验值");
                player.sendMessage("              §b" + PlayerDataManage.getLevel(player) + "级                                        §b" + (dengji + 1) + "级");
                player.sendMessage("    " + xpbar);
                player.sendMessage("                            §b" + dqjy + " §7/ §a5000" + " §7(" + percentage + "%)");
                player.sendMessage("");
                player.sendMessage("§7你获得了§b" + LemonBedWars.xp.get(player.getName()) + "起床战争经验");
                player.sendMessage("");
                player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

                player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("                              §f§l起床战争");
                player.sendMessage("");
                if (winteam == null) {
                    player.sendMessage("                     §7");
                } else {
//                player.sendMessage("      "+ GameStart.getScoreboard().getTeam(winteam).getSuffix()+GameStart.getScoreboard().getTeam(winteam).getName()+" §7- "+TeamPlayers);

                    StringBuilder forplayer = new StringBuilder();
                    for (String pl : GameStart.getScoreboard().getTeam(winteam).getEntries()) {
                        if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("旁观者")) {
                            forplayer.append("  ").append(PlayerDataManage.getPlayerPrefix(Objects.requireNonNull(Bukkit.getPlayer(pl)))).append(player.getName());
                        }
                    }
                    player.sendMessage("      " + GameStart.getScoreboard().getTeam(winteam).getPrefix() + " §7- " + forplayer);

                }
                player.sendMessage("");
                player.sendMessage("               §e击杀第一名 §7 - " + PlayerDataManage.getPlayerPrefix(Bukkit.getPlayer(list.get(0).getKey())) + Bukkit.getPlayer(list.get(0).getKey()).getName() + " §7- " + list.get(0).getValue());
                if (list.size() < 2) {
                    player.sendMessage("               §6击杀第二名 §7 - 没有人 §7- 0");
                } else {
                    player.sendMessage("               §6击杀第二名 §7 - " + PlayerDataManage.getPlayerPrefix(Bukkit.getPlayer(list.get(1).getKey())) + Bukkit.getPlayer(list.get(1).getKey()).getName() + " §7- " + list.get(1).getValue());
                }
                if (list.size() < 3) {
                    player.sendMessage("               §c击杀第三名 §7 - 没有人 §7- 0");
                } else {
                    player.sendMessage("               §c击杀第三名 §7 - " + PlayerDataManage.getPlayerPrefix(Bukkit.getPlayer(list.get(2).getKey())) + Bukkit.getPlayer(list.get(2).getKey()).getName() + " §7- " + list.get(2).getValue());

                }
                player.sendMessage("");
                player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                TextComponent tc = new TextComponent("§6点击这里");
                //tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tellraw "));
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a点击查看不了回放").create()));
                player.sendMessage("§a本场游戏技术不足记录不下来");
                player.spigot().sendMessage(tc);

            }
        }
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Location state : LemonBedWars.changedBlocks) {
                    state.getBlock().setType(Material.AIR);

                }


                for (Player player1 : Bukkit.getOnlinePlayers()){

                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    List<String> lobby = JavaPlugin.getPlugin(LemonBedWars.class).getConfig().getStringList("LobbyServer");
                    out.writeUTF(lobby.get(new Random().nextInt(lobby.size())));
                    player1.sendPluginMessage(JavaPlugin.getPlugin(LemonBedWars.class), "BungeeCord", out.toByteArray());
                }

                Bukkit.shutdown();
            }
        }.runTaskLater(JavaPlugin.getPlugin(LemonBedWars.class), 200L);
    }
}
