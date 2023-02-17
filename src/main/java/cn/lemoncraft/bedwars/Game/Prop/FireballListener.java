
package cn.lemoncraft.bedwars.Game.Prop;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public class FireballListener implements Listener {


    public FireballListener() {
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onInteractFireball(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (BedWars.Fireballcooldown.get(player) == 0 && e.getItem() != null && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            if (e.getItem().getType() == Material.FIREBALL) {


                BedWars.Fireballcooldown.put(player, 3);
                Fireball fireball = (Fireball) player.launchProjectile(Fireball.class);
                fireball.setYield(1.0F);
                fireball.setBounce(false);
                fireball.setShooter(player);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        BedWars.Fireballcooldown.replace(player, BedWars.Fireballcooldown.get(player) - 1);
                        if (BedWars.Fireballcooldown.get(player) == 0) {
                            cancel();
                        }
                    }
                }.runTaskTimer(JavaPlugin.getPlugin(BedWars.class), 20L, 20L);
            }
        }
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onBlockIgnite(BlockIgniteEvent e) {
        Entity entity = e.getIgnitingEntity();
        if (entity instanceof Fireball) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onFireballDamage(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        if (entity instanceof Player && damager instanceof Fireball) {
            Player player = (Player) entity;
            e.setDamage(3.0D);
        }

    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Fireball) {
            List<Block> blocks = e.blockList();
            for (Block block : blocks) {
                if (block.getType() == Material.GLASS) {
                    blocks.clear();
                }
                if (BedWars.changedBlocks.contains(block.getLocation())) {
                    blocks.remove(block);
                }
            }

            Fireball fireball = (Fireball) e.getEntity();
            Iterator var5 = Bukkit.getOnlinePlayers().iterator();

            while (var5.hasNext()) {
                Player player = (Player) var5.next();
                if (player.getGameMode() != GameMode.SPECTATOR && player.getGameMode() != GameMode.CREATIVE && e.getEntity().getWorld() == player.getWorld() && player.getLocation().distanceSquared(e.getEntity().getLocation()) <= Math.pow((double) (fireball.getYield() + 1.0F), 2.0D)) {
                    if (fireball.getShooter() != null && ((Player) fireball.getShooter()).getUniqueId().equals(player.getUniqueId())) {
                        player.damage(3.0D, fireball);
                    }

                    player.setVelocity(LocationUtil.getPosition(player.getLocation(), fireball.getLocation(), 0.8D).multiply(2));
                }
            }
        }

    }
}