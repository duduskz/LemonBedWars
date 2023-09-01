package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import cn.lemoncraft.bedwars.Utils.PlayerDataManage;
import cn.lemoncraft.bedwars.Utils.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PlayerJoin implements Listener {
    Plugin plugin = BedWars.getPlugin(BedWars.class);

    @EventHandler
    public void Death(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Lobby")) {
            FileConfiguration config = plugin.getConfig();
            String[] spawn = LocationUtil.getStringLocation(config.getString( "Lobby.Spawn"));
            player.teleport(new Location(Bukkit.getWorld(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Double.parseDouble(spawn[3]), Integer.parseInt(spawn[4]), Integer.parseInt(spawn[5])));
            player.getInventory().clear();
            String lang = PlayerDataManage.getPlayerLang(player);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            Calendar calendar = Calendar.getInstance();
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                ArrayList<String> Board = new ArrayList<>();
                if (lang.equalsIgnoreCase("zhcn")) {
                    Board.add("&e&l起床战争");
                    Board.add("&7" + formatter.format(calendar.getTime()) + " &8" + config.getString("Lobby.Mini"));
                    Board.add("&7");
                    Board.add("&f等级: &7" + PlayerDataManage.getLevel(player)+"✫");
                    int xp = (PlayerDataManage.getPlayerXP(player) - ((PlayerDataManage.getLevel(player) - 1) * 5000));
                    int dengji = PlayerDataManage.getLevel(player);
                    int dqjy = xp - ((dengji - 1) * 5000);
                    int progress = dqjy % 5000;
                    StringBuilder expbar = new StringBuilder();
                    for (int i = 0; i < 30; i++) { // 经验条总长度为50
                        if (i < progress * 100 / 5000) {
                            expbar.append("§b■"); // 已经获得的经验用■表示
                        } else {
                            expbar.append("§7■"); // 尚未获得的经验用▲表示
                        }
                    }
                    Board.add("&8");
                    Board.add("&f进度: &a"+(PlayerDataManage.getPlayerXP(player) - ((PlayerDataManage.getLevel(player) - 1) * 5000))+"&7/&b5000");
                    Board.add("&8[" + expbar + "&8]");
                    Board.add("&1");
                    Board.add("&f硬币: &6" + PlayerDataManage.getPlayerCoins(player));
                    Board.add("&2");
                    Board.add("&f战利宝箱: &e0");
                    Board.add("&3");
                    Board.add("&f总击杀数: &a" + PlayerDataManage.getPlayerALLData(player, "kills"));
                    Board.add("&f总胜利数: &a" + PlayerDataManage.getPlayerALLData(player, "win"));
                    Board.add("&4");
                    Board.add("&e" + BedWars.serverip);
                } else if (lang.equalsIgnoreCase("en")) {
                    Board.add("&e&lBED WARS");
                    Board.add("&7" + formatter.format(calendar.getTime()) + " &8" + config.getString("Lobby.Mini"));
                    Board.add("&7");
                    Board.add("&fLevel: &7" + PlayerDataManage.getLevel(player)+"✫");
                    int xp = (PlayerDataManage.getPlayerXP(player) - ((PlayerDataManage.getLevel(player) - 1) * 5000));
                    int dengji = PlayerDataManage.getLevel(player);
                    int dqjy = xp - ((dengji - 1) * 5000);
                    int progress = dqjy % 5000;
                    StringBuilder expbar = new StringBuilder();
                    for (int i = 0; i < 30; i++) { // 经验条总长度为50
                        if (i < progress * 100 / 5000) {
                            expbar.append("§b■"); // 已经获得的经验用■表示
                        } else {
                            expbar.append("§7■"); // 尚未获得的经验用▲表示
                        }
                    }
                    Board.add("&8");
                    Board.add("&fProgress: &a"+(PlayerDataManage.getPlayerXP(player) - ((PlayerDataManage.getLevel(player) - 1) * 5000))+"&7/&b5000");
                    Board.add("&8[" + expbar + "&8]");
                    Board.add("&1");
                    Board.add("&fCoins: &6" + PlayerDataManage.getPlayerCoins(player));
                    Board.add("&2");
                    Board.add("&fLoot Chest: &e0");
                    Board.add("&3");
                    Board.add("&fTotal Kills: &a" + PlayerDataManage.getPlayerALLData(player, "kills"));
                    Board.add("&fTotal Wins: &a" + PlayerDataManage.getPlayerALLData(player, "win"));
                    Board.add("&4");
                    Board.add("&e" + BedWars.serverip);


                }
                Board.replaceAll(s -> s.replace("&", "§"));
                String title = Board.get(0);
                Board.remove(0);
                ScoreboardManager sm = new ScoreboardManager(plugin, title, Board.toArray(new String[0]));
                sm.display(player);
            }, 0, 20);
        }
    }
}