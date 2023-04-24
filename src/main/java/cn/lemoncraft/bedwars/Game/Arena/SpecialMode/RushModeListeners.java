package cn.lemoncraft.bedwars.Game.Arena.SpecialMode;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Utils.ActionBar;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class RushModeListeners implements Listener {
    @EventHandler
    public void onSpeedBlock(PlayerInteractEvent event) {
        try {
            if (event.getItem().getType().equals(Material.WOOL)) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
                    if (BedWars.onSpeed.get(event.getPlayer().getName())) {
                        ActionBar.sendMessage(event.getPlayer(), "§c§l搭路模式已关闭");
                        BedWars.onSpeed.replace(event.getPlayer().getName(), false);
                    } else {
                        ActionBar.sendMessage(event.getPlayer(), "§a§l搭路模式已开启");
                        BedWars.onSpeed.replace(event.getPlayer().getName(), true);
                    }
                }
            }
        } catch (NullPointerException e) {

        }
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (BedWars.onSpeed.get(event.getPlayer().getName())) {
            BlockFace face = event.getBlockPlaced().getFace(event.getBlockAgainst());
            final Vector vector = new Vector(-face.getModX(), -face.getModY(), -face.getModZ());
            final Location nextBlock = event.getBlock().getLocation().clone().add(vector);
            if (!event.isCancelled())
                (new BukkitRunnable() {
                    int place = 0;

                    public void run() {
                        if (this.place <= 4 && nextBlock
                                .getWorld().getNearbyEntities(nextBlock, 0.45D, 0.5D, 0.45D).size() <= 0 && nextBlock
                                .getBlock().getType() == Material.AIR
                        ) {
                            BedWars.changedBlocks.add(nextBlock.getBlock().getLocation());
                            nextBlock.getBlock().setTypeIdAndData(event.getBlock().getType().getId(), event.getBlock().getData(), true);
                            nextBlock.getWorld().playSound(nextBlock, Sound.STEP_WOOL, 1.0F, 1.0F);
                            nextBlock.add(vector);
                            this.place++;
                        } else {
                            cancel();
                        }
                    }

                }).runTaskTimer(BedWars.getPlugin(BedWars.class), 2L, 3L);
        }
    }
}