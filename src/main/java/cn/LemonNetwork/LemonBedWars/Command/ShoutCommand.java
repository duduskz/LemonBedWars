package cn.lemonnetwork.lemonbedwars.Command;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import cn.lemonnetwork.lemonbedwars.Utils.PlayerDataManage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ShoutCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("§c参数错误，正确参数为/shout <消息>");
        } else {
            if (Objects.equals(JavaPlugin.getPlugin(LemonBedWars.class).getConfig().getString("BungeeMode"), "Game")) {
                if (Objects.equals(LemonBedWars.state, "Play")) {
                    if (!LemonBedWars.getPlugin(LemonBedWars.class).getConfig().getString("Map.ModeType").equalsIgnoreCase("单挑")) {
                        if (LemonBedWars.shoutcd.get(player.getName()) == 0) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    LemonBedWars.shoutcd.replace(player.getName(), LemonBedWars.shoutcd.get(player.getName()) - 1);
                                    if (LemonBedWars.shoutcd.get(player.getName()) == 0) {
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(JavaPlugin.getPlugin(LemonBedWars.class), 0L, 20L);
                            LemonBedWars.shoutcd.put(player.getName(), 180);
                            LemonBedWars.shoutcd.replace(player.getName(), 180);
                            String message = null;
                            for (int x = 0; x < args.length; x = x + 1) {
                                if (message == null) {
                                    message = args[x] + " ";
                                } else {
                                    message = message + args[x] + " ";
                                }

                            };
                            for (Player p : Bukkit.getOnlinePlayers()) {

                                p.sendMessage("§6[喊话] " + GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix() + "[" + GameStart.getScoreboard().getEntryTeam(player.getName()).getName() + "] " + PlayerDataManage.getPlayerPrefix(player) + player.getName() + "§f: " + message);
                            }
                        } else {
                            player.sendMessage("§c你还需要等待 " + LemonBedWars.shoutcd.get(player.getName()) + " 秒");
                        }
                    } else {
                        player.sendMessage("§c此模式不支持喊话!");
                    }
                } else {
                    player.sendMessage("§c游戏还没开始！");
                }
            } else {
                player.sendMessage("§c你不在游戏中！");
            }
        }

        return false;

    }

}