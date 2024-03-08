package cn.lemonnetwork.lemonbedwars.Command;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.EditMap;
import cn.lemonnetwork.lemonbedwars.Lobby.Menu.Stats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
        if (args.length == 0){
            sender.sendMessage("§bLemon§aBedWars §70.83");
            sender.sendMessage("§a作者: §bLemonNetwork(duduskz)");
        }
        if (sender instanceof ConsoleCommandSender || sender.hasPermission("LemonBedWars.admin")){
            if (args.length == 1){
                if (Objects.equals(args[0], "reload")){
                    plugin.reloadConfig();
                    sender.sendMessage("§a插件配置已重载");
                }
                if (Objects.equals(args[0], "test")){
                    //所有测试节省时间的都写这
                    //下面的代码为：
                    //强开绝杀模式
                    LemonBedWars.Listenername = "绝杀模式";
                    LemonBedWars.Listenertime = 5;
                    LemonBedWars.Listeners.replace("emerald2", true);
                    LemonBedWars.Listeners.replace("diamond2", true);
                    LemonBedWars.Listeners.replace("emerald3", true);
                    LemonBedWars.Listeners.replace("diamond3", true);
                    LemonBedWars.Listeners.replace("BedDestroy", true);
                }
                if (args[0].equalsIgnoreCase("setLobbyStatsNPC")){
                    Stats.add((Player) sender);
                }
                if (Objects.equals(args[0], "save")){
                    plugin.saveConfig();
                    sender.sendMessage("§a插件配置已保存");
                }
                if (Objects.equals(args[0], "build")){
                    if (sender instanceof Player) {
                        if (plugin.getConfig().getString("BungeeMode").equalsIgnoreCase("Lobby")) {
                            if (LemonBedWars.Build.contains(sender.getName())) {
                                sender.sendMessage("§7已关闭 §c建筑模式");
                                LemonBedWars.Build.add(sender.getName());
                            } else {
                                sender.sendMessage("§7已打开 §a建筑模式");
                                LemonBedWars.Build.remove(sender.getName());
                            }
                        } else {
                            sender.sendMessage("§c请在大厅输入此指令！");
                        }
                    } else {
                        sender.sendMessage("§c请不要在控制台输入此指令！");
                    }
                } else {

                }
            }
            if (args.length == 2){
                if (Objects.equals(args[0], "join")){
                    
                }
                if (args[0].equalsIgnoreCase("editteam") && sender instanceof Player){
                    Player player = (Player) sender;
                    EditMap.editteam(player, args);
                }

            }
        }
        return false;
    }
}
