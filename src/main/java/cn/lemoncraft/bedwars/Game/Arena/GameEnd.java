package cn.lemoncraft.bedwars.Game.Arena;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

public class GameEnd {
    public static void gameend(String winteam) {
        for (Player player : Bukkit.getOnlinePlayers()){

            String TeamPlayers = null;
            if (winteam != null) {
                for (String TeamPlayer : GameStart.getcoreboard().getTeam(winteam).getEntries()) {
                    if (TeamPlayers != null) {
                        TeamPlayers = "§7, " + TeamPlayers + BedWars.api.getUserManager().getUser(Bukkit.getPlayer(TeamPlayer).getUniqueId()).getCachedData().getMetaData().getPrefix() + TeamPlayer;
                    } else {
                        TeamPlayers = BedWars.api.getUserManager().getUser(Bukkit.getPlayer(TeamPlayer).getUniqueId()).getCachedData().getMetaData().getPrefix() + TeamPlayer;
                    }

                }
            } else {
                TeamPlayers = "没有队获得胜利.";
            }
            Set set = BedWars.kill.keySet();
            Object[] arr = set.toArray();
            Arrays.sort(arr);
            //value-sort
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(BedWars.kill.entrySet());
            //list.sort()
            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            //collections.sort()
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            //for
            int xp = PlayerDataManage.getPlayerXP(player);
            int dengji = PlayerDataManage.getLevel(player);
            DecimalFormat dF = new DecimalFormat("0.00");
            int dqjy = xp - dengji * 5000;
            int jingyanbaifenbi = Double.valueOf(dF.format((float) dqjy / 5000)).intValue() * 100;
            DecimalFormat dF1 = new DecimalFormat("0");

            String gezi = dF1.format((float) dqjy / 125);
            int gezishu = Integer.parseInt(gezi);
            String zzgz = null;
            for (int i = 0; i < gezishu; i++) {
                if (zzgz != null) {


                    zzgz = zzgz + ("§b■");
                } else {
                    zzgz = "§b■";
                }
            }
            for (int i = 0; i < 10 - gezishu; i++) {
                if (zzgz != null) {
                    zzgz = zzgz + ("§7■");
                } else {
                    zzgz = "§7■";
                }
            }
            if (winteam == null){
                for (Player player1 : Bukkit.getOnlinePlayers()){
                    player1.sendTitle("§c§l游戏结束", "");
                }
            } else {
                player.sendMessage("      "+ GameStart.getcoreboard().getTeam(winteam).getSuffix()+GameStart.getcoreboard().getTeam(winteam).getName()+" §7- "+TeamPlayers);
                for (Player player1 : Bukkit.getOnlinePlayers()){
                    if (GameStart.getcoreboard().getEntryTeam(player1.getName()).getName().equals(winteam)){
                        player1.sendTitle("§c§l胜利", "");
                        player1.sendMessage("§b+25 起床战争经验 (时长奖励)");
                        player1.sendMessage("§6+10 硬币 (时长奖励)");
                        PlayerDataManage playerDataManage = new PlayerDataManage();
                        playerDataManage.addPlayerXP(player1, 25);
                        playerDataManage.addPlayerCoins(player1, 10);
                    } else {
                        player1.sendTitle("§c§l游戏结束", "");
                    }
                }
            }
            String xpbar = "§8[ " + zzgz + " §8]";
            int nextlevel = PlayerDataManage.getLevel(player)+1;
            player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            player.sendMessage("                                   §f§l奖励总览");
            player.sendMessage("");
            player.sendMessage("   §7你获得了");
            player.sendMessage("    §f• §6"+BedWars.coins.get(player.getName())+" 起床战争硬币");
            player.sendMessage("");
            player.sendMessage("                            §b起床战争经验值");
            player.sendMessage("              §b"+ PlayerDataManage.getLevel(player) +"级                                        §b"+ nextlevel +"级");
            player.sendMessage("    "+xpbar);
            player.sendMessage("                            §b"+dqjy + " §7/ §a5000"+" §7("+jingyanbaifenbi+"%)");
            player.sendMessage("");
            player.sendMessage("§7你获得了§b"+BedWars.xp.get(player.getName())+"起床战争经验");
            player.sendMessage("");
            player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

            player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            player.sendMessage("                              §f§l起床战争");
            player.sendMessage("");
            if (winteam == null){
                player.sendMessage("                     §7这场游戏没有队伍获得胜利。");
            }
            player.sendMessage("");
            player.sendMessage("               §e击杀第一名 §7 - "+BedWars.api.getUserManager().getUser(Bukkit.getPlayer(list.get(0).getKey()).getUniqueId()).getCachedData().getMetaData().getPrefix()+list.get(0).getKey()+" §7- "+list.get(0).getValue());
            if (list.size() < 2){
                player.sendMessage("               §6击杀第二名 §7 - 没有人 §7- 0");
            } else {
                player.sendMessage("               §6击杀第二名 §7 - "+BedWars.api.getUserManager().getUser(Bukkit.getPlayer(list.get(1).getKey()).getUniqueId()).getCachedData().getMetaData().getPrefix()+list.get(1).getKey()+" §7- "+list.get(1).getValue());
            }
            if (list.size() < 3){
                player.sendMessage("               §c击杀第三名 §7 - 没有人 §7- 0");
            } else {
                player.sendMessage("               §c击杀第三名 §7 - "+BedWars.api.getUserManager().getUser(Bukkit.getPlayer(list.get(2).getKey()).getUniqueId()).getCachedData().getMetaData().getPrefix()+list.get(2).getKey()+" §7- "+list.get(2).getValue());

            }
            player.sendMessage("");
            player.sendMessage("§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            TextComponent tc = new TextComponent("§6点击这里");
            //tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tellraw "));
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a点击查看不了回放").create()));
            player.sendMessage("§a本场游戏技术不足记录不下来");
            player.spigot().sendMessage(tc);
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(Location state : BedWars.changedBlocks) {
                        state.getBlock().setType(Material.AIR);

                    }


                    for (Player player1 : Bukkit.getOnlinePlayers()){
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        out.writeUTF("Connect");
                        out.writeUTF(player1.getName());
                        List<String> lobby = JavaPlugin.getPlugin(BedWars.class).getConfig().getStringList("LobbyServer");
                        out.writeUTF(lobby.get(new Random().nextInt(lobby.size())));
                        player1.sendPluginMessage(JavaPlugin.getPlugin(BedWars.class), "BungeeCord", out.toByteArray());
                    }

                    Bukkit.shutdown();
                }
            }.runTaskLater(JavaPlugin.getPlugin(BedWars.class), 200L);
        }

    }
}
