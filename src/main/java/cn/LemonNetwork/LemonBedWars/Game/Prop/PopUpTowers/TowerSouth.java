/*     */ package cn.lemonnetwork.lemonbedwars.Game.Prop.PopUpTowers;
/*     */ 
/*     */

import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

/*     */
/*     */ 
/*     */ public class TowerSouth
/*     */ {
/*     */   private BukkitTask task;
/*     */   
/*     */   public TowerSouth(Location loc, Block chest, Team color, Player p) {
/*  21 */     ItemStack itemInHand = p.getInventory().getItemInHand();
/*  22 */     if (itemInHand.getAmount() > 1) {
/*  23 */       itemInHand.setAmount(itemInHand.getAmount() - 1);
/*     */     } else {
/*  25 */       p.getInventory().setItemInHand((ItemStack)null);
/*     */     } 
/*  27 */     List<String> relloc = new ArrayList<>();
/*  28 */     relloc.add("1, 0, 2");
/*  29 */     relloc.add("2, 0, 1");
/*  30 */     relloc.add("2, 0, 0");
/*  31 */     relloc.add("1, 0, -1");
/*  32 */     relloc.add("0, 0, -1");
/*  33 */     relloc.add("-1, 0, -1");
/*  34 */     relloc.add("-2, 0, 0");
/*  35 */     relloc.add("-2, 0, 1");
/*  36 */     relloc.add("-1, 0, 2");
/*  37 */     relloc.add("0, 0, 0, ladder3");
/*  38 */     relloc.add("1, 1, 2");
/*  39 */     relloc.add("2, 1, 1");
/*  40 */     relloc.add("2, 1, 0");
/*  41 */     relloc.add("1, 1, -1");
/*  42 */     relloc.add("0, 1, -1");
/*  43 */     relloc.add("-1, 1, -1");
/*  44 */     relloc.add("-2, 1, 0");
/*  45 */     relloc.add("-2, 1, 1");
/*  46 */     relloc.add("-1, 1, 2");
/*  47 */     relloc.add("0, 1, 0, ladder3");
/*  48 */     relloc.add("1, 2, 2");
/*  49 */     relloc.add("2, 2, 1");
/*  50 */     relloc.add("2, 2, 0");
/*  51 */     relloc.add("1, 2, -1");
/*  52 */     relloc.add("0, 2, -1");
/*  53 */     relloc.add("-1, 2, -1");
/*  54 */     relloc.add("-2, 2, 0");
/*  55 */     relloc.add("-2, 2, 1");
/*  56 */     relloc.add("-1, 2, 2");
/*  57 */     relloc.add("0, 2, 0, ladder3");
/*  58 */     relloc.add("0, 3, 2");
/*  59 */     relloc.add("1, 3, 2");
/*  60 */     relloc.add("2, 3, 1");
/*  61 */     relloc.add("2, 3, 0");
/*  62 */     relloc.add("1, 3, -1");
/*  63 */     relloc.add("0, 3, -1");
/*  64 */     relloc.add("-1, 3, -1");
/*  65 */     relloc.add("-2, 3, 0");
/*  66 */     relloc.add("-2, 3, 1");
/*  67 */     relloc.add("-1, 3, 2");
/*  68 */     relloc.add("0, 3, 0, ladder3");
/*  69 */     relloc.add("0, 4, 2");
/*  70 */     relloc.add("1, 4, 2");
/*  71 */     relloc.add("2, 4, 1");
/*  72 */     relloc.add("2, 4, 0");
/*  73 */     relloc.add("1, 4, -1");
/*  74 */     relloc.add("0, 4, -1");
/*  75 */     relloc.add("-1, 4, -1");
/*  76 */     relloc.add("-2, 4, 0");
/*  77 */     relloc.add("-2, 4, 1");
/*  78 */     relloc.add("-1, 4, 2");
/*  79 */     relloc.add("0, 4, 0, ladder3");
/*  80 */     relloc.add("2, 5, -1");
/*  81 */     relloc.add("2, 5, 0");
/*  82 */     relloc.add("2, 5, 1");
/*  83 */     relloc.add("2, 5, 2");
/*  84 */     relloc.add("1, 5, -1");
/*  85 */     relloc.add("1, 5, 0");
/*  86 */     relloc.add("1, 5, 1");
/*  87 */     relloc.add("1, 5, 2");
/*  88 */     relloc.add("0, 5, -1");
/*  89 */     relloc.add("0, 5, 1");
/*  90 */     relloc.add("0, 5, 2");
/*  91 */     relloc.add("-1, 5, -1");
/*  92 */     relloc.add("0, 5, 0, ladder3");
/*  93 */     relloc.add("-1, 5, 0");
/*  94 */     relloc.add("-1, 5, 1");
/*  95 */     relloc.add("-1, 5, 2");
/*  96 */     relloc.add("-2, 5, -1");
/*  97 */     relloc.add("-2, 5, 0");
/*  98 */     relloc.add("-2, 5, 1");
/*  99 */     relloc.add("-2, 5, 2");
/* 100 */     relloc.add("3, 5, 2");
/* 101 */     relloc.add("3, 6, 2");
/* 102 */     relloc.add("3, 7, 2");
/* 103 */     relloc.add("3, 6, 1");
/* 104 */     relloc.add("3, 6, 0");
/* 105 */     relloc.add("3, 5, -1");
/* 106 */     relloc.add("3, 6, -1");
/* 107 */     relloc.add("3, 7, -1");
/* 108 */     relloc.add("2, 5, -2");
/* 109 */     relloc.add("2, 6, -2");
/* 110 */     relloc.add("2, 7, -2");
/* 111 */     relloc.add("1, 6, -2");
/* 112 */     relloc.add("0, 5, -2");
/* 113 */     relloc.add("0, 6, -2");
/* 114 */     relloc.add("0, 7, -2");
/* 115 */     relloc.add("-1, 6, -2");
/* 116 */     relloc.add("-2, 5, -2");
/* 117 */     relloc.add("-2, 6, -2");
/* 118 */     relloc.add("-2, 7, -2");
/* 119 */     relloc.add("-3, 5, 2");
/* 120 */     relloc.add("-3, 6, 2");
/* 121 */     relloc.add("-3, 7, 2");
/* 122 */     relloc.add("-3, 6, 1");
/* 123 */     relloc.add("-3, 6, 0");
/* 124 */     relloc.add("-3, 5, -1");
/* 125 */     relloc.add("-3, 6, -1");
/* 126 */     relloc.add("-3, 7, -1");
/* 127 */     relloc.add("2, 5, 3");
/* 128 */     relloc.add("2, 6, 3");
/* 129 */     relloc.add("2, 7, 3");
/* 130 */     relloc.add("1, 6, 3");
/* 131 */     relloc.add("0, 5, 3");
/* 132 */     relloc.add("0, 6, 3");
/* 133 */     relloc.add("0, 7, 3");
/* 134 */     relloc.add("-1, 6, 3");
/* 135 */     relloc.add("-2, 5, 3");
/* 136 */     relloc.add("-2, 6, 3");
/* 137 */     relloc.add("-2, 7, 3");
/* 138 */     int[] i = { 0 };
/* 139 */     this.task = Bukkit.getScheduler().runTaskTimer(LemonBedWars.getPlugin(LemonBedWars.class), () -> { if (Bukkit.getServer().getClass().getPackage().getName().contains("v1_8")) { loc.getWorld().playSound(loc, Sound.valueOf("CHICKEN_EGG_POP"), 1.0F, 0.5F); } else { loc.getWorld().playSound(loc, Sound.valueOf("ENTITY_CHICKEN_EGG"), 1.0F, 0.5F); }  if (relloc.size() + 1 == i[0] + 1) { this.task.cancel(); return; }  String c1 = relloc.get(i[0]); if (c1.contains("ladder")) { int ldata = Integer.parseInt(c1.split("ladder")[1]); new NewPlaceBlock(chest, c1, color, p, true, ldata); } else { new NewPlaceBlock(chest, c1, color, p, false, 0); }  if (relloc.size() + 1 == i[0] + 2) { this.task.cancel(); return; }  String c2 = relloc.get(i[0] + 1); if (c2.contains("ladder")) { int ldata = Integer.parseInt(c2.split("ladder")[1]); new NewPlaceBlock(chest, c2, color, p, true, ldata); } else { new NewPlaceBlock(chest, c2, color, p, false, 0); }  i[0] = i[0] + 2; },0L, 1L);
/*     */   }
/*     */ }


/* Location:              C:\Users\duduskz\Desktop\BedWars1058-PopUpTowers.jar!\me\kimovoid\TowerSouth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */