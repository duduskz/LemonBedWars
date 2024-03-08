
package cn.lemonnetwork.lemonbedwars.Game.Prop;

import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
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
import org.bukkit.inventory.ItemStack;
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
        try {

            Player player = e.getPlayer();
            if (LemonBedWars.Fireballcooldown.get(player) == 0 && e.getItem() != null && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
                if (e.getItem().getType() == Material.FIREBALL) {

                    e.setCancelled(true);
                    LemonBedWars.Fireballcooldown.put(player, 3);
                    Fireball fireball = player.launchProjectile(Fireball.class);
                    e.getPlayer().getInventory().removeItem(new ItemStack(Material.FIREBALL, 1));
                    fireball.setYield(1.0F);
                    fireball.setBounce(false);
                    fireball.setShooter(player);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            LemonBedWars.Fireballcooldown.replace(player, LemonBedWars.Fireballcooldown.get(player) - 1);
                            if (LemonBedWars.Fireballcooldown.get(player) == 0) {
                                cancel();
                            }
                        }
                    }.runTaskTimer(JavaPlugin.getPlugin(LemonBedWars.class), 20L, 20L);


                }
            }
        } catch (NullPointerException ignored) {

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
                if (LemonBedWars.changedBlocks.contains(block.getLocation())) {
                    if (block.getType().equals(Material.STAINED_GLASS)) {
                        e.blockList().clear();
                    } else {
                        block.getWorld().dropItem(block.getLocation(), new ItemStack(block.getType(), 1, block.getData()));
                        block.setType(Material.AIR);
                    }
                }
            }
            e.blockList().clear();

            Fireball fireball = (Fireball) e.getEntity();
            Iterator var5 = Bukkit.getOnlinePlayers().iterator();

            while (var5.hasNext()) {
                Player player = (Player) var5.next();
                if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("旁观者") && player.getGameMode() != GameMode.CREATIVE && e.getEntity().getWorld() == player.getWorld() && player.getLocation().distanceSquared(e.getEntity().getLocation()) <= Math.pow((double) (fireball.getYield() + 1.0F), 2.0D)) {
                    if (fireball.getShooter() != null && ((Player) fireball.getShooter()).getUniqueId().equals(player.getUniqueId())) {
                        player.damage(3.0D, fireball);
                    }

                    player.setVelocity(LocationUtil.getPosition(player.getLocation(), fireball.getLocation(), 1.7D).multiply(0.9));
                }
            }
        }

    }
}