 package cn.lemoncraft.bedwars.Game.Prop.PopUpTowers;


 import cn.lemoncraft.bedwars.BedWars;
 import cn.lemoncraft.bedwars.Game.Arena.GameStart;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.entity.Player;
 import org.bukkit.scoreboard.Team;


 public class NewPlaceBlock
 {
   public NewPlaceBlock(Block b, String xyz, Team color, Player p, boolean ladder, int ladderdata) {
     int x = Integer.parseInt(xyz.split(", ")[0]);
     int y = Integer.parseInt(xyz.split(", ")[1]);
     int z = Integer.parseInt(xyz.split(", ")[2]);
     int woolcolor = 0;

       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("红队")){
           woolcolor = 14;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("蓝队")){
           woolcolor = 11;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("绿队")){
           woolcolor = 5;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("黄队")){
           woolcolor = 4;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("白队")){
           woolcolor = 0;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("青队")){
           woolcolor = 9;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("粉队")){
           woolcolor = 6;
       }
       if (GameStart.getcoreboard().getEntryTeam(p.getName()).getName().equalsIgnoreCase("灰队")){
           woolcolor = 8;
       }
     if (b.getRelative(x, y, z).getType().equals(Material.AIR)) {

       if (!ladder) {
           b.getRelative(x, y, z).setType(Material.WOOL);

           b.getRelative(x, y, z).setData((byte) woolcolor);
            BedWars.changedBlocks.add(b.getRelative(x, y, z).getLocation());
         }
       } else {
         b.getRelative(x, y, z).setType(Material.WOOL);
         b.getRelative(x, y, z).setType(Material.LADDER);
         b.getRelative(x, y, z).setData((byte)ladderdata);
                 BedWars.changedBlocks.add(b.getRelative(x, y, z).getLocation());
       }
     } 
   }

