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
import org.bukkit.material.Bed;
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
                player.sendMessage(BedWars.getLanguageConfig().getString("startcreate"));
                player.sendMessage(BedWars.getLanguageConfig().getString("nocreate"));
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("inputroomname"));
                jd = 1;
            } else {
                player.sendMessage(BedWars.getLanguageConfig().getString("errercontent").replace("{errecode}",RandomStringUtils.random(4)));
            }
        }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (BedWars.state.equalsIgnoreCase(BedWars.getLanguageConfig().getString("state"))) {

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
                player.sendMessage(BedWars.getLanguageConfig().getString("setok").replace("{editteam}",editteam));
                config.set("Map."+editteam+".teamshop", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
            }
            if (jd == 19 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 20;
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("setitemshop").replace("{editteam}",editteam));
                config.set("Map."+editteam+".shop", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
            }
            if (jd == 18 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 19;
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("GoodsShop").replace("editteam",editteam));
                config.set("Map."+editteam+".Spawn", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
            }
            if (jd == 12) {
                config.set("Map.NeedPlayer", Integer.parseInt(e.getMessage()));
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("mapsavecg"));
                jd = 13;
            }
            if (jd == 11) {
                config.set("Map.MaxPlayer", Integer.parseInt(e.getMessage()));
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("inputmini"));
                jd = 12;
            }
            if (jd == 10) {
                config.set("Map.mini", e.getMessage());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("inputmax"));
                jd = 11;
            }
            if (jd == 9) {
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("roomid"));
                jd = 10;
            }

            if (jd == 8 && e.getMessage().equalsIgnoreCase("OK")) {
                jd = 9;
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("click-diamond-gen"));
            }


            if (jd == 7 && e.getMessage().equalsIgnoreCase("OK")) {
                config.set("Map.Spectator", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
                jd = 8;
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("click-emerald-gen"));
            }


            if (jd == 6) {

                config.set("Map.Size", Integer.parseInt(e.getMessage()));
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("Spectator"));
                jd = 7;
            }
            if (jd == 5 && e.getMessage().equalsIgnoreCase("OK")) {

                config.set("Map.Spawn", player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ() + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());

                jd = 6;
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("SpawnToBorderRadius"));
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
                            player.sendMessage(BedWars.getLanguageConfig().getString("importwc"));
                            player.teleport(BedWars.playworld.getSpawnLocation());
                            player.sendMessage("");
                            player.sendMessage(BedWars.getLanguageConfig().getString("waitlobbyset"));
                            jd = 5;
                    }
                }.runTaskLater(plugin, 80L);

            }
            if (jd == 3) {
                config.set("Map.ModeType", e.getMessage());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("roomworld"));
                jd = 4;
            }
            if (jd == 2) {
                config.set("Map.Mode", e.getMessage());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("roomtype"));
                jd = 3;
            }
            if (jd == 1) {
                config.set("Map.Name", e.getMessage());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("roommode"));
                jd = 2;
            }
        }
    }
    List<String> diamond = config.getStringList("Map.generator.Diamond");
    List<String> emerald = config.getStringList("Map.generator.Emerald");
    @EventHandler
    public void block(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if (BedWars.state.equalsIgnoreCase(BedWars.getLanguageConfig().getString("state"))) {
            e.setCancelled(true);
            if (jd == 17) {
                config.set("Map."+editteam+".resources", e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ());
                player.sendMessage(BedWars.getLanguageConfig().getString("edititem").replace("{edititem}",editteam));
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("zzedititem").replace("{edititem}",editteam));
                jd = 18;
            }
            if (jd == 16) {
                config.set("Map."+editteam+".chest", e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ());
                player.sendMessage(BedWars.getLanguageConfig().getString("teamboxadd").replace("{edititem}",editteam));
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("zydxblock").replace("{edititem}",editteam));
                jd = 17;
            }

            if (jd == 15) {
                config.set("Map."+editteam+".Bed", (bed+","+e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ()));
                player.sendMessage(BedWars.getLanguageConfig().getString("editteam").replace("{editteam}",editteam));
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                player.sendMessage("");
                player.sendMessage(BedWars.getLanguageConfig().getString("click-teambox").replace("{edititem}",editteam));
                jd = 16;
            }
            if (jd == 14) {
                bed = e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ();
                player.sendMessage(BedWars.getLanguageConfig().getString("addcgbedt").replace("{editteam}",editteam));
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + e.getBlock().getY() + "  Z:" + e.getBlock().getZ());
                jd = 15;
            }
            if (jd == 9) {
                int y = e.getBlock().getY() + 2;

                diamond.add(e.getBlock().getX()+".5," + y + "," + e.getBlock().getZ()+".5");
                player.sendMessage(BedWars.getLanguageConfig().getString("cgadd-diamond-ziyd"));
                config.set("Map.generator.Diamond",diamond);
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + y + "  Z:" + e.getBlock().getZ());
            }
            if (jd == 8) {
                int y = e.getBlock().getY() + 2;

                emerald.add(e.getBlock().getX() + ".5," + y + "," + e.getBlock().getZ()+".5");
                config.set("Map.generator.Emerald",emerald );
                player.sendMessage(BedWars.getLanguageConfig().getString("cgadd-emerald-ziyd"));
                player.sendMessage("§eX: " + e.getBlock().getX() + "  Y: " + y + "  Z:" + e.getBlock().getZ());
            }



        }
    }

    public static void editteam(Player player, String[] args) {
        if (player.hasPermission("lemonbedwars.editmap") && BedWars.state.equalsIgnoreCase("nomap") && jd == 13) {

            player.sendMessage("");
            player.sendMessage(BedWars.getLanguageConfig().getString("click-bed-ct-cw").replace("args[1]",args[1]));
            editteam = args[1];
            jd = 14;
        }
    }

}
