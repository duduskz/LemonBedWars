package cn.lemoncraft.bedwars.Game.Prop;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Iron_Puppet implements Listener {
    @EventHandler
    public void inv(PlayerInteractEvent e) {
        try {

            if (e.getItem().getType() == Material.MOB_SPAWNER && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Plugin plugin = BedWars.getPlugin(BedWars.class);
                e.getPlayer().getInventory().remove(new ItemStack(Material.MOB_SPAWNER, 1));
                IronGolem ironGolem = (IronGolem) BedWars.playworld.spawnEntity(e.getPlayer().getLocation(), EntityType.IRON_GOLEM);
                ironGolem.setPlayerCreated(false);
                ironGolem.setCustomNameVisible(false);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getName().equalsIgnoreCase(GameStart.getScoreboard().getEntryTeam(player.getName()).getName())) {

                        ironGolem.setTarget(player);
                    }
                }
                final int[] time = {120};
                final boolean[] ig = {true};


                new BukkitRunnable() {
                    @Override
                    public void run() {
                        time[0]--;
                        if (time[0] == 0) {
                            ig[0] = false;
                            ironGolem.setHealth(0);
                            Bukkit.broadcastMessage(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getPrefix() + "的铁傀儡 §7终究没逃过时间的终结!");
                        }
                    }
                }.runTaskTimer(plugin, 20L, 20L);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (ig[0]) {
                            Double maxHealth = ironGolem.getMaxHealth();
                            Double currentHealth = ironGolem.getHealth();
                            int healthPercentage = (int) (((double) currentHealth / maxHealth) * 100);
                            int progressBarLength = 10;
                            int filledProgressBarLength = (int) (((double) healthPercentage / 200) * progressBarLength);

                            StringBuilder progressBar = new StringBuilder();
                            for (int i = 0; i < filledProgressBarLength; i++) {
                                progressBar.append("■");
                            }
                            for (int i = filledProgressBarLength; i < progressBarLength; i++) {
                                progressBar.append("§7■");
                            }
                            ironGolem.setCustomName(GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getPrefix() + "铁傀儡 [ " + GameStart.getScoreboard().getEntryTeam(e.getPlayer().getName()).getSuffix() + progressBar + " " + time[0] + " 秒 ]");
                        }
                    }
                }.runTaskTimer(plugin, 0L, 5L);
            }
        } catch (NullPointerException error) {

        }
    }
    @EventHandler
    public void kill(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof IronGolem && e.getEntity() instanceof Player){
            if (e.getDamager().getCustomName().contains(GameStart.getScoreboard().getEntryTeam(e.getEntity().getName()).getPrefix())) {
                e.setCancelled(true);
            }
        }
    }
}
