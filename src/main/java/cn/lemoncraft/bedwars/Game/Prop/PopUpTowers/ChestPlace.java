/*    */ package cn.lemoncraft.bedwars.Game.Prop.PopUpTowers;
/*    */ 
/*    */

import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scoreboard.Team;

/*    */
/*    */ public class ChestPlace
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(priority = EventPriority.HIGH)
/*    */   public void onPlace(BlockPlaceEvent e) {
/* 19 */     Player player = e.getPlayer();
/* 20 */     if (e.getBlockPlaced().getType() == Material.CHEST &&
/* 22 */       !e.isCancelled()) {
/* 23 */       e.setCancelled(true);
/*    */       
/* 25 */       Location loc = e.getBlockPlaced().getLocation();
/* 26 */       Block chest = e.getBlockPlaced();
/* 27 */       Team playerteam = GameStart.getcoreboard().getEntryTeam(player.getName());
/* 28 */       double rotation = ((player.getLocation().getYaw() - 90.0F) % 360.0F);
/* 29 */       if (rotation < 0.0D) {
/* 30 */         rotation += 360.0D;
/*    */       }
/* 32 */       if (45.0D <= rotation && rotation < 135.0D) {
/* 33 */         new TowerSouth(loc, chest, playerteam, player);
/*    */       }
/* 35 */       else if (225.0D <= rotation && rotation < 315.0D) {
/* 36 */         new TowerNorth(loc, chest, playerteam, player);
/*    */       }
/* 38 */       else if (135.0D <= rotation && rotation < 225.0D) {
/* 39 */         new TowerWest(loc, chest, playerteam, player);
/*    */       }
/* 41 */       else if (0.0D <= rotation && rotation < 45.0D) {
/* 42 */         new TowerEast(loc, chest, playerteam, player);
/*    */       }
/* 44 */       else if (315.0D <= rotation && rotation < 360.0D) {
/* 45 */         new TowerEast(loc, chest, playerteam, player);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\duduskz\Desktop\BedWars1058-PopUpTowers.jar!\me\kimovoid\ChestPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */