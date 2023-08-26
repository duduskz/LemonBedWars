package cn.lemoncraft.bedwars.Game.Prop;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TNTListener implements Listener {
    @EventHandler
    public void onTNTDamage(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        if (entity instanceof Player && damager instanceof TNTPrimed) {
            e.setDamage(5.0D);
        }

    }
    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof TNTPrimed) {
            List<Block> blocks = e.blockList();

            for (Block block : blocks) {
                if (BedWars.changedBlocks.contains(block.getLocation())) {
                    if (block.getType().equals(Material.STAINED_GLASS)) {
                        e.blockList().clear();
                    } else {
                        block.getWorld().dropItem(block.getLocation(), new ItemStack(block.getType(), 1, block.getData()));
                        block.setType(Material.AIR);
                    }
                }
            }
            e.blockList().clear();
            TNTPrimed tnt = (TNTPrimed) e.getEntity();
            Iterator var5 = Bukkit.getOnlinePlayers().iterator();

            while(var5.hasNext()) {
                Player player = (Player)var5.next();
                if (!GameStart.getScoreboard().getEntryTeam(player.getName()).getName().equalsIgnoreCase("旁观者") && player.getGameMode() != GameMode.CREATIVE && e.getEntity().getWorld() == player.getWorld() && player.getLocation().distanceSquared(e.getEntity().getLocation()) <= Math.pow((double)(tnt.getYield() + 1.0F), 2.0D)) {

                    player.damage(5.0D, tnt);
                    player.setVelocity(LocationUtil.getPosition(player.getLocation(), tnt.getLocation(), 2.2D).multiply(0.8));
                }
            }
        }

    }
    @EventHandler
    public void place(BlockPlaceEvent e) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        if (Objects.equals(plugin.getConfig().getString("BungeeMode"), "Game")) {
            if (Objects.equals(BedWars.state, "Play")) {
                //e.getBlock().setData(new );
                if (e.getBlock().getType() == Material.TNT) {
                    e.setCancelled(true);
                    TNTPrimed t = BedWars.playworld.spawn(e.getBlock().getLocation(), TNTPrimed.class);
                    t.setFuseTicks(45);
                    BedWars.changedBlocks.remove(e.getBlock().getLocation());
                    e.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT, 1));
                }
            }
        }
    }
}
