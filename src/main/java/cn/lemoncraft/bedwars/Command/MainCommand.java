package cn.lemoncraft.bedwars.Command;

import cn.lemoncraft.bedwars.BedWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Objects;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Player player = (Player) sender;
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (sender instanceof ConsoleCommandSender || sender.isOp()){
            if (args.length == 1){
                if (Objects.equals(args[0], "reload")){
                    plugin.reloadConfig();
                    sender.sendMessage("§a插件配置已重载");
                } else {

                }
            }
            if (args.length == 2){
                if (Objects.equals(args[0], "join")){
                    
                }
            }
        }
        return false;
    }
}
