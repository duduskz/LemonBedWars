package cn.lemonnetwork.lemonbedwars.Game.Prop;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BridgeEgg implements Listener {
    @EventHandler
    public void onThrowEgg(ProjectileLaunchEvent e) {
        Projectile p = e.getEntity();
        if (p instanceof Egg) {
            ProjectileSource source = p.getShooter();
            if (source instanceof Player) {
                Player player = (Player) source;

                new BukkitRunnable() {
                    int i;
                    Block a, b, c, d;

                    @Override
                    public void run() {
                        i++;
                        if (i < 25) {
                        if (!p.isDead()) {
                            int woolcolor = 0;
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("红队")) {
                                woolcolor = 14;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("蓝队")) {
                                woolcolor = 11;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("绿队")) {
                                woolcolor = 5;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("黄队")) {
                                woolcolor = 4;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("白队")) {
                                woolcolor = 0;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("青队")) {
                                woolcolor = 3;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("粉队")) {
                                woolcolor = 6;
                            }
                            if (GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("灰队")) {
                                woolcolor = 8;
                            }
                            if (a != null) {
                                a.setType(Material.WOOL);
                                a.setData((byte) woolcolor);
                                LemonBedWars.changedBlocks.add(a.getLocation());
                            }
                            if (b != null) {
                                b.setType(Material.WOOL);
                                b.setData((byte) woolcolor);
                                LemonBedWars.changedBlocks.add(b.getLocation());
                            }
                            if (c != null) {
                                c.setType(Material.WOOL);
                                c.setData((byte) woolcolor);
                                LemonBedWars.changedBlocks.add(c.getLocation());
                            }
                            if (d != null) {
                                d.setType(Material.WOOL);
                                d.setData((byte) woolcolor);
                                LemonBedWars.changedBlocks.add(d.getLocation());
                            }


                            if (p.getLocation().add(p.getVelocity().multiply(-1)).getBlock().getType().equals(Material.AIR)) {
                                a = p.getLocation().add(p.getVelocity().multiply(-1)).getBlock();//.setType(Material.WOOL);//这行写放置方块
                            }
                            double x = p.getVelocity().getX(), z = p.getVelocity().getZ();
                            if (p.getLocation().add(p.getVelocity().multiply(-1).add(new Vector(x / Math.abs(x), 0, z / Math.abs(z)))).getBlock().getType().equals(Material.AIR)) {
                                b = p.getLocation().add(p.getVelocity().multiply(-1).add(new Vector(x / Math.abs(x), 0, z / Math.abs(z)))).getBlock();//.setType(Material.WOOL);//这行写放置方块
                            }
                            if (p.getLocation().add(p.getVelocity().multiply(-1).add(new Vector(x / Math.abs(x), 0, 0))).getBlock().getType().equals(Material.AIR)) {
                                c = p.getLocation().add(p.getVelocity().multiply(-1).add(new Vector(x / Math.abs(x), 0, 0))).getBlock();//.setType(Material.WOOL);//这行写放置方块
                            }
                            if (p.getLocation().add(p.getVelocity().multiply(-1).add(new Vector(0, 0, z / Math.abs(z)))).getBlock().getType().equals(Material.AIR)) {
                                d = p.getLocation().add(p.getVelocity().multiply(-1).add(new Vector(0, 0, z / Math.abs(z)))).getBlock();//.setType(Material.WOOL);//这行写放置方
                            }
                        }
                        } else cancel();
                    }
                }.runTaskTimer(LemonBedWars.getPlugin(LemonBedWars.class), 5L, 1L);
            }
        }

    }
}