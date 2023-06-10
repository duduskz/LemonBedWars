package cn.lemoncraft.bedwars;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class NoMAP implements Listener {
    static Plugin plugin = BedWars.getPlugin(BedWars.class);
    static FileConfiguration config = plugin.getConfig();
    public static int jd = 0;
    public static String editteam;
    public static String bed;
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (BedWars.state.equalsIgnoreCase("nomap")) {
            if (player.hasPermission("LemonBedwars.editormap")) {
                player.sendMessage("§c管理员§a你好，欢迎使用§bLemon§aBedWars");
                player.sendMessage("§d此房间暂未配置地图，§e请接下来一步一步在聊天框输入指定内容");
                player.sendMessage("");
                player.sendMessage("§a请输入此房间地图名称");
                jd = 1;
            } else {
                player.sendMessage("§c连接至子游戏服务器时发送错误，错误代码：LBWNOMAP，请联系管理员 (mini" + RandomStringUtils.random(4) + ")!");
            }
        }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (BedWars.state.equalsIgnoreCase("nomap")) {

            e.setCancelled(true);
            if (e.getMessage().equalsIgnoreCase("tp")) {
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                float yaw = player.getLocation().getYaw();
                float pitch = player.getLocation().getPitch();

                // 对齐坐标项
                x = Math.floor(x);
                y = Math.floor(y);
                z = Math.floor(z);
                yaw = Math.floorMod((int) yaw, 360);
                pitch = Math.floorMod((int) pitch, 360);

                // 更新玩家位置
                player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
            }
                if (jd == 20 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 13;
                player.sendMessage("");
                player.sendMessage("§a" +editteam+" 设置完毕。输入/bedwarsgame save来保存设置, 重新输入/bedwarsgame editteam <队伍名>来配置下一个队伍");
                config.set("Map."+editteam+".teamshop", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
            }
            if (jd == 19 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 20;
                player.sendMessage("");
                player.sendMessage("§a" + "请站在 "+editteam+" 的团队商店后输入ok");
                config.set("Map."+editteam+".shop", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
            }
            if (jd == 18 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 19;
                player.sendMessage("");
                player.sendMessage("§a" + "请站在 "+editteam+" 的物品商店后输入ok");
                config.set("Map."+editteam+".Spawn", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
            }
            if (jd == 12) {
                config.set("Map.NeedPlayer", Integer.parseInt(e.getMessage()));
                player.sendMessage("");
                player.sendMessage("§a地图配置保存成功，接下来使用/bedwarsgame editteam <队伍>来进入配置队伍设置");
                jd = 13;
            }
            if (jd == 11) {
                config.set("Map.MaxPlayer", Integer.parseInt(e.getMessage()));
                player.sendMessage("");
                player.sendMessage("§a请输入此房间最小人数");
                jd = 12;
            }
            if (jd == 10) {
                config.set("Map.mini", e.getMessage());
                player.sendMessage("");
                player.sendMessage("§a请输入此房间最大人数");
                jd = 11;
            }
            if (jd == 9) {
                player.sendMessage("");
                player.sendMessage("§a请输入房间mini编号 (不带mini)");
                jd = 10;
            }

            if (jd == 8 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 9;
                player.sendMessage("");
                player.sendMessage("§b请点击所有钻石生成点下方方块输入ok");
            }


            if (jd == 7 && e.getMessage().equalsIgnoreCase("OK")) {
                config.set("Map.Spectator", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
                jd = 8;
                player.sendMessage("");
                player.sendMessage("§a请点击所有绿宝石生成点下方方块输入ok");
            }


            if (jd == 6) {

                config.set("Map.Size", Integer.parseInt(e.getMessage()));
                player.sendMessage("");
                player.sendMessage("§a请站在指定的位置设置旁观者出生点输入ok");
                jd = 7;
            }
            if (jd == 5 && e.getMessage().equalsIgnoreCase("OK")) {

                config.set("Map.Spawn", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());

                jd = 6;
                player.sendMessage("");
                player.sendMessage("§a请输入从出生点到边界的半径");
            }
            if (jd == 4) {
                player.sendMessage("§6正在导入...");
                WorldCreator seed = new WorldCreator(e.getMessage());
                BedWars.playworld = seed.createWorld();
                BedWars.playworld.setAutoSave(false);
                config.set("Map.WorldName", e.getMessage());

                new BukkitRunnable() {
                    @Override
                    public void run() {
                            cancel();
                            player.sendMessage("§d导入成功!");
                            player.teleport(BedWars.playworld.getSpawnLocation());
                            player.sendMessage("");
                            player.sendMessage("§a请站在指定的位置设置等待大厅出生点输入ok");
                            jd = 5;
                    }
                }.runTaskLater(plugin, 80L);

            }
            if (jd == 3) {
                config.set("Map.ModeType", e.getMessage());
                player.sendMessage("");
                player.sendMessage("§a请输入此房间世界名称（不能为主世界，无需装多世界插件）");
                jd = 4;
            }
            if (jd == 2) {
                config.set("Map.Mode", e.getMessage());
                player.sendMessage("");
                player.sendMessage("§a请输入此房间地图模式类型 （4队：4v4v4v4, 3队3v3v3v3, 单挑, 双人, 2队4v4, 4队极速4v4v4v4speed, 双人极速doublespeed");
                jd = 3;
            }
            if (jd == 1) {
                config.set("Map.Name", e.getMessage());
                player.sendMessage("");
                player.sendMessage("§a请输入此房间地图模式");
                jd = 2;
            }
        }
    }
    List<String> diamond = config.getStringList("Map.generator.Diamond");
    List<String> emerald = config.getStringList("Map.generator.Emerald");
    @EventHandler
    public void block(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if (BedWars.state.equalsIgnoreCase("nomap")) {
            e.setCancelled(true);
            if (jd == 17) {
                config.set("Map."+editteam+".resources", e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ());
                player.sendMessage("§d§l添加成功! §7成功添加 "+editteam+" 的资源点");
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                player.sendMessage("");
                player.sendMessage("§a请站到 "+editteam+" 的出生点输入ok");
                jd = 18;
            }
            if (jd == 16) {
                config.set("Map."+editteam+".chest", e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ());
                player.sendMessage("§d§l添加成功! §7成功添加 "+editteam+" 的团队箱子");
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                player.sendMessage("");
                player.sendMessage("§a请点击 "+editteam+" 的资源点下方方块");
                jd = 17;
            }

            if (jd == 15) {
                config.set("Map."+editteam+".Bed", (bed+","+e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ()));
                player.sendMessage("§d§l添加成功! §7成功添加 "+editteam+" 的床尾");
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                player.sendMessage("");
                player.sendMessage("§a请点击 "+editteam+" 的团队箱子");
                jd = 16;
            }
            if (jd == 14) {
                bed = e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ();
                player.sendMessage("§d§l添加成功! §7成功添加 "+editteam+" 的床头");
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                jd = 15;
            }
            if (jd == 9) {
                int y = e.getBlock().getY() + 2;

                diamond.add(e.getBlock().getX()+".5," + y + "," + e.getBlock().getZ()+".5");
                player.sendMessage("§b§l添加成功! §7成功添加一个钻石资源点于");
                config.set("Map.generator.Diamond",diamond);
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + y + "  Z:" + e.getBlock().getZ());
            }
            if (jd == 8) {
                int y = e.getBlock().getY() + 2;

                emerald.add(e.getBlock().getX() + ".5," + y + "," + e.getBlock().getZ()+".5");
                config.set("Map.generator.Emerald",emerald );
                player.sendMessage("§a§l添加成功! §7成功添加一个绿宝石资源点于");
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + y + "  Z:" + e.getBlock().getZ());
            }



        }
    }

    public static void editteam(Player player, String[] args) {
        if (player.hasPermission("lemonbedwars.editmap") && BedWars.state.equalsIgnoreCase("nomap") && jd == 13) {

            player.sendMessage("");
            player.sendMessage("§a请依次点击 " + args[1] + " 的床头床尾");
            editteam = args[1];
            jd = 14;
        }
    }

}
