package cn.lemoncraft.bedwars.Command;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
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
            if (Objects.equals(JavaPlugin.getPlugin(BedWars.class).getConfig().getString("BungeeMode"), "Game")) {
                if (Objects.equals(BedWars.state, "Play")) {
                    if (!BedWars.getPlugin(BedWars.class).getConfig().getString("Map.ModeType").equalsIgnoreCase("单挑")) {
                        if (BedWars.shoutcd.get(player.getName()) == 0) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    BedWars.shoutcd.replace(player.getName(), BedWars.shoutcd.get(player.getName()) - 1);
                                    if (BedWars.shoutcd.get(player.getName()) == 0) {
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 0L, 20L);
                            BedWars.shoutcd.put(player.getName(), 180);
                            BedWars.shoutcd.replace(player.getName(), 180);
                            String message = null;
                            for (int x = 0; x < args.length; x = x + 1) {
                                if (message == null) {
                                    message = args[x] + " ";
                                } else {
                                    message = message + args[x] + " ";
                                }

                            }
                            for (Player p : Bukkit.getOnlinePlayers()) {

                                p.sendMessage("§6[喊话] " + GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix() + "[" + GameStart.getScoreboard().getEntryTeam(player.getName()).getName() + "] " + BedWars.api.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix() + player.getName() + "§f: " + message);
                            }
                        } else {
                            player.sendMessage("§c你还需要等待 " + BedWars.shoutcd.get(player.getName()) + " 秒");
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