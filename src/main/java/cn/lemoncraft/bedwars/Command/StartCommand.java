package cn.lemoncraft.bedwars.Command;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.waiting.Cd;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Plugin plugin = JavaPlugin.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        if (player.hasPermission("lemonbedwars.start")) {


            if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
                if (Objects.equals(BedWars.state, "waiting")) {
                    if (!have) {
                        BedWars.time = 6;
                        if (Bukkit.getOnlinePlayers().size() < config.getInt("Map.NeedPlayer")) {
                            BedWars.adminStart = true;
                            Cd.start();
                        }
                        have = true;
                        Bukkit.broadcastMessage("§a有一位玩家进行了倒计时缩减！");
                    }else {
                        player.sendMessage("§c倒计时已被其他玩家缩减！");
                    }
                }
            } else {
                player.sendMessage("§c你当前不在等待大厅中！");
            }
        } else {
            player.sendMessage("§c你需要 §bMVP§d+ §c或以上身份才能强制开启游戏！");
        }
        return false;

    }
}