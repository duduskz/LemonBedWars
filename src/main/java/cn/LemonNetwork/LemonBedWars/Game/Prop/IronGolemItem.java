package cn.lemonnetwork.lemonbedwars.Game.Prop;

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
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

public class IronGolemItem implements Listener {
    private static final int IRON_GOLEM_LIFESPAN = 120;
    private static final int HEALTH_BAR_REFRESH_RATE = 5;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        try {
            event.getPlayer().sendMessage(event.getItem().getType().name());
            if (event.getItem() != null && event.getItem().getType() == Material.MOB_SPAWNER &&
                    event.getAction() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Plugin plugin = LemonBedWars.getPlugin(LemonBedWars.class);
                Player player = event.getPlayer();
                ItemStack spawnerItem = new ItemStack(Material.MOB_SPAWNER, 1);
                player.getInventory().remove(spawnerItem);

                IronGolem ironGolem = (IronGolem) LemonBedWars.playworld.spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
                initializeIronGolem(ironGolem, player);

                handleIronGolemLifetime(ironGolem, plugin, player);
                updateHealthBar(ironGolem, player, plugin);
            }
        } catch (NullPointerException ignored) {
            // Ignoring NullPointerException
        }
    }

    private void initializeIronGolem(IronGolem ironGolem, Player player) {
        ironGolem.setPlayerCreated(false);
        ironGolem.setCustomNameVisible(false);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase(GameStart.getScoreboard().getEntryTeam(onlinePlayer.getName()).getName()) &&
                    !GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("旁观者")) {
                ironGolem.setTarget(onlinePlayer);
            }
        }
    }

    int timeLeft = IRON_GOLEM_LIFESPAN;

    private void handleIronGolemLifetime(IronGolem ironGolem, Plugin plugin, Player player) {
        new BukkitRunnable() {


            @Override
            public void run() {
                if (timeLeft <= 0) {
                    cancel();
                    ironGolem.setHealth(0);
                    Bukkit.broadcastMessage(GameStart.getScoreboard().getEntryTeam(player.getName()).getPrefix() + "的铁傀儡 §7终究没逃过时间的终结!");
                } else {
                    timeLeft--;
                }
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    private void updateHealthBar(IronGolem ironGolem, Player player, Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (ironGolem.isValid()) {
                    double maxHealth = ironGolem.getMaxHealth();
                    double currentHealth = ironGolem.getHealth();
                    int healthPercentage = (int) ((currentHealth / maxHealth) * 100);
                    int filledProgressBarLength = healthPercentage / 10;

                    StringBuilder progressBar = new StringBuilder();
                    for (int i = 0; i < filledProgressBarLength; i++) {
                        progressBar.append("■");
                    }
                    for (int i = filledProgressBarLength; i < 10; i++) {
                        progressBar.append("§7■");
                    }

                    ironGolem.setCustomName(GameStart.getScoreboard().getEntryTeam(player.getName()).getPrefix() + "铁傀儡 [ " + GameStart.getScoreboard().getEntryTeam(player.getName()).getSuffix() + progressBar + " " + timeLeft + " 秒 ]");
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, HEALTH_BAR_REFRESH_RATE * 20L);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof IronGolem && event.getEntity() instanceof Player) {
            IronGolem ironGolem = (IronGolem) event.getDamager();
            Player player = (Player) event.getEntity();

            if (ironGolem.getCustomName() != null && ironGolem.getCustomName().contains(GameStart.getScoreboard().getEntryTeam(player.getName()).getPrefix())) {
                event.setCancelled(true);
            }
        }
    }
}
