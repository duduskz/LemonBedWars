package cn.lemoncraft.bedwars.Game.Arena.SpecialMode;

import cn.lemoncraft.bedwars.BedWars;
import cn.lemoncraft.bedwars.Game.Arena.GameStart;
import cn.lemoncraft.bedwars.Utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Team;

public class RushMode {

    public static Plugin plugin = BedWars.getPlugin(BedWars.class);
    public static FileConfiguration config = plugin.getConfig();

    public static void start() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            BedWars.onSpeed.put(player.getName(), true);
        }
        for (Team team : GameStart.getScoreboard().getTeams()) {
            if (!team.getName().equalsIgnoreCase("旁观者")) {
                World world = Bukkit.getWorld(config.getString("Map.WorldName"));
                String[] spawn = LocationUtil.getStringLocation(config.getString("Map." + team.getName() + ".Bed"));
                Location bedloc1 = new Location(world, Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]));
                BlockFace targetFace = ((Bed) bedloc1.getBlock().getState().getData()).getFacing();
                if (targetFace == BlockFace.SOUTH || targetFace == BlockFace.NORTH) {
                    placersn(team, bedloc1);

                } else if (targetFace == BlockFace.EAST || targetFace == BlockFace.WEST) {
                    placerwe(team, bedloc1);
                }
            }
        }
    }

    public static void placersn(Team team, Location location) {
        if (location.getBlock().getRelative(-1, 0, 0).getType().equals(Material.BED_BLOCK)) {
            location.subtract(0, 0, -1);
        }
        if (location.getBlock().getRelative(0, 0, -1).getType().equals(Material.BED_BLOCK)) {
            location.subtract(-1, 0, 0);
        }
        if (location.getBlock().getRelative(1, 0, 0).getType().equals(Material.BED_BLOCK)) {
            location.subtract(0, 0, 1);
        }
        if (location.getBlock().getRelative(0, 0, 1).getType().equals(Material.BED_BLOCK)) {
            location.subtract(1, 0, 0);
        }
        Block bed = location.getBlock();
        int TOX = bed.getZ();
        int TOY = bed.getY();
        int TOZ = bed.getX();
        int AX = TOX - 1;
        int BX = AX + 1;
        int BZ = TOZ + 1;
        int CX = BX + 1;
        int DZ = BZ - 1;
        int DX = AX + 3;
        int EX = AX + 1;
        int EZ = TOZ - 1;
        int FX = EX + 1;
        int FZ = EZ - 1;
        int BDY = TOY + 1;
        int AXX = TOX - 2;
        int BXX = AX;
        int BZZ = TOZ + 1;
        int CXX = AX + 1;
        int CZZ = TOZ + 2;
        int DXX = BX + 1;
        int EXX = AX + 3;
        int EZZ = BZZ;
        int FXX = EXX + 1;
        int FZZ = TOZ;
        int GXX = FXX - 1;
        int GZZ = FZZ - 1;
        int HXX = GXX - 1;
        int HZZ = GZZ - 1;
        int IXX = HXX - 1;
        int JXX = IXX - 1;
        int JZZ = HZZ + 1;
        int KYY = BDY + 1;
        int AXXX = AXX - 1;
        int BXXX = AXX;
        int BZZZ = BZZ;
        int XXXX = BXX;
        int ZZZZ = BZZ + 1;
        int CXXX = CXX;
        int CZZZ = CZZ + 1;
        int DXXX = DXX;
        int DZZZ = CZZ + 1;
        int EXXX = EXX;
        int EZZZ = EZZ + 1;
        int FXXX = FXX;
        int FZZZ = FZZ + 1;
        int GXXX = FXX + 1;
        int GZZZ = FZZ;
        int HXXX = FXX;
        int HZZZ = FZZ - 1;
        int IXXX = GXX;
        int IZZZ = GZZ - 1;
        int JXXX = HXX;
        int JZZZ = HZZ - 1;
        int KXXX = HXX - 1;
        int KZZZ = HZZ - 1;
        int LXXX = JXX;
        int LZZZ = JZZ - 1;
        int MXXX = AXX;
        int MZZZ = BZZ - 2;
        int NYYY = TOY + 3;
        int OYYY = TOY + 1;
        int PYYY = TOY + 2;
        int color = 0;
        if (team.getName().equalsIgnoreCase("红队")) {
            color = 14;
        }
        if (team.getName().equalsIgnoreCase("蓝队")) {
            color = 11;
        }
        if (team.getName().equalsIgnoreCase("绿队")) {
            color = 5;
        }
        if (team.getName().equalsIgnoreCase("黄队")) {
            color = 4;
        }
        if (team.getName().equalsIgnoreCase("白队")) {
            color = 0;
        }
        if (team.getName().equalsIgnoreCase("青队")) {
            color = 9;
        }
        if (team.getName().equalsIgnoreCase("粉队")) {
            color = 6;
        }
        if (team.getName().equalsIgnoreCase("灰队")) {
            color = 8;
        }

        bed.getWorld().getBlockAt(TOZ, TOY, AX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, TOY, AX).getLocation());
        bed.getWorld().getBlockAt(BZ, TOY, BX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZ, TOY, BX).getLocation());
        bed.getWorld().getBlockAt(BZ, TOY, CX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZ, TOY, CX).getLocation());
        bed.getWorld().getBlockAt(DZ, TOY, DX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(DZ, TOY, DX).getLocation());
        bed.getWorld().getBlockAt(EZ, TOY, EX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZ, TOY, EX).getLocation());
        bed.getWorld().getBlockAt(EZ, TOY, FX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZ, TOY, FX).getLocation());
        bed.getWorld().getBlockAt(TOZ, BDY, TOX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, BDY, TOX).getLocation());
        bed.getWorld().getBlockAt(TOZ, BDY, FX).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, BDY, FX).getLocation());
        bed.getWorld().getBlockAt(TOZ, TOY, AXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(TOZ, TOY, AXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, TOY, AXX).getLocation());
        bed.getWorld().getBlockAt(BZZ, TOY, BXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(BZZ, TOY, BXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZZ, TOY, BXX).getLocation());
        bed.getWorld().getBlockAt(CZZ, TOY, CXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(CZZ, TOY, CXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(CZZ, TOY, CXX).getLocation());
        bed.getWorld().getBlockAt(CZZ, TOY, DXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(CZZ, TOY, DXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(CZZ, TOY, DXX).getLocation());
        bed.getWorld().getBlockAt(EZZ, TOY, EXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(EZZ, TOY, EXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZZ, TOY, EXX).getLocation());
        bed.getWorld().getBlockAt(FZZ, TOY, FXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(FZZ, TOY, FXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(FZZ, TOY, FXX).getLocation());
        bed.getWorld().getBlockAt(GZZ, TOY, GXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(GZZ, TOY, GXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(GZZ, TOY, GXX).getLocation());
        bed.getWorld().getBlockAt(HZZ, TOY, HXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(HZZ, TOY, HXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(HZZ, TOY, HXX).getLocation());
        bed.getWorld().getBlockAt(HZZ, TOY, IXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(HZZ, TOY, IXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(HZZ, TOY, IXX).getLocation());
        bed.getWorld().getBlockAt(JZZ, TOY, JXX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(JZZ, TOY, JXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(JZZ, TOY, JXX).getLocation());
        bed.getWorld().getBlockAt(TOZ, BDY, AX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(TOZ, BDY, AX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, BDY, AX).getLocation());
        bed.getWorld().getBlockAt(BZ, BDY, BX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(BZ, BDY, BX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZ, BDY, BX).getLocation());
        bed.getWorld().getBlockAt(BZ, BDY, CX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(BZ, BDY, CX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZ, BDY, CX).getLocation());
        bed.getWorld().getBlockAt(DZ, BDY, DX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(DZ, BDY, DX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(DZ, BDY, DX).getLocation());
        bed.getWorld().getBlockAt(EZ, BDY, EX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(EZ, BDY, EX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZ, BDY, EX).getLocation());
        bed.getWorld().getBlockAt(EZ, BDY, FX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(EZ, BDY, FX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZ, BDY, FX).getLocation());
        bed.getWorld().getBlockAt(TOZ, KYY, TOX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(TOZ, KYY, TOX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, KYY, TOX).getLocation());
        bed.getWorld().getBlockAt(TOZ, KYY, FX).setType(Material.WOOL);
        bed.getWorld().getBlockAt(TOZ, KYY, FX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, KYY, FX).getLocation());
        bed.getWorld().getBlockAt(TOZ, TOY, AXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(TOZ, TOY, AXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, TOY, AXXX).getLocation());
        bed.getWorld().getBlockAt(BZZZ, TOY, BXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(BZZZ, TOY, BXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZZZ, TOY, BXXX).getLocation());
        bed.getWorld().getBlockAt(ZZZZ, TOY, XXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(ZZZZ, TOY, XXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(ZZZZ, TOY, XXXX).getLocation());
        bed.getWorld().getBlockAt(CZZZ, TOY, CXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(CZZZ, TOY, CXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(CZZZ, TOY, CXXX).getLocation());
        bed.getWorld().getBlockAt(DZZZ, TOY, DXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(DZZZ, TOY, DXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(DZZZ, TOY, DXXX).getLocation());
        bed.getWorld().getBlockAt(EZZZ, TOY, EXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(EZZZ, TOY, EXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZZZ, TOY, EXXX).getLocation());
        bed.getWorld().getBlockAt(FZZZ, TOY, FXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(FZZZ, TOY, FXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(FZZZ, TOY, FXXX).getLocation());
        bed.getWorld().getBlockAt(GZZZ, TOY, GXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(GZZZ, TOY, GXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(GZZZ, TOY, GXXX).getLocation());
        bed.getWorld().getBlockAt(HZZZ, TOY, HXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(HZZZ, TOY, HXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(HZZZ, TOY, HXXX).getLocation());
        bed.getWorld().getBlockAt(IZZZ, TOY, IXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(IZZZ, TOY, IXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(IZZZ, TOY, IXXX).getLocation());
        bed.getWorld().getBlockAt(JZZZ, TOY, JXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(JZZZ, TOY, JXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(JZZZ, TOY, JXXX).getLocation());
        bed.getWorld().getBlockAt(KZZZ, TOY, KXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(KZZZ, TOY, KXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(KZZZ, TOY, KXXX).getLocation());
        bed.getWorld().getBlockAt(LZZZ, TOY, LXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(LZZZ, TOY, LXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(LZZZ, TOY, LXXX).getLocation());
        bed.getWorld().getBlockAt(MZZZ, TOY, MXXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(MZZZ, TOY, MXXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(MZZZ, TOY, MXXX).getLocation());
        bed.getWorld().getBlockAt(TOZ, OYYY, AXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(TOZ, OYYY, AXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, OYYY, AXX).getLocation());
        bed.getWorld().getBlockAt(BZZ, OYYY, BXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(BZZ, OYYY, BXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZZ, OYYY, BXX).getLocation());
        bed.getWorld().getBlockAt(CZZ, OYYY, CXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(CZZ, OYYY, CXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(CZZ, OYYY, CXX).getLocation());
        bed.getWorld().getBlockAt(CZZ, OYYY, DXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(CZZ, OYYY, DXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(CZZ, OYYY, DXX).getLocation());
        bed.getWorld().getBlockAt(EZZ, OYYY, EXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(EZZ, OYYY, EXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZZ, OYYY, EXX).getLocation());
        bed.getWorld().getBlockAt(FZZ, OYYY, FXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(FZZ, OYYY, FXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(FZZ, OYYY, FXX).getLocation());
        bed.getWorld().getBlockAt(GZZ, OYYY, GXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(GZZ, OYYY, GXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(GZZ, OYYY, GXX).getLocation());
        bed.getWorld().getBlockAt(HZZ, OYYY, HXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(HZZ, OYYY, HXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(HZZ, OYYY, HXX).getLocation());
        bed.getWorld().getBlockAt(HZZ, OYYY, IXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(HZZ, OYYY, IXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(HZZ, OYYY, IXX).getLocation());
        bed.getWorld().getBlockAt(JZZ, OYYY, JXX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(JZZ, OYYY, JXX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(JZZ, OYYY, JXX).getLocation());
        bed.getWorld().getBlockAt(TOZ, PYYY, AX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(TOZ, PYYY, AX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, PYYY, AX).getLocation());
        bed.getWorld().getBlockAt(BZ, PYYY, BX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(BZ, PYYY, BX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZ, PYYY, BX).getLocation());
        bed.getWorld().getBlockAt(BZ, PYYY, CX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(BZ, PYYY, CX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(BZ, PYYY, CX).getLocation());
        bed.getWorld().getBlockAt(DZ, PYYY, DX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(DZ, PYYY, DX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(DZ, PYYY, DX).getLocation());
        bed.getWorld().getBlockAt(EZ, PYYY, EX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(EZ, PYYY, EX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZ, PYYY, EX).getLocation());
        bed.getWorld().getBlockAt(EZ, PYYY, FX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(EZ, PYYY, FX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(EZ, PYYY, FX).getLocation());
        bed.getWorld().getBlockAt(TOZ, NYYY, TOX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(TOZ, NYYY, TOX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, NYYY, TOX).getLocation());
        bed.getWorld().getBlockAt(TOZ, NYYY, FX).setType(Material.STAINED_GLASS);
        bed.getWorld().getBlockAt(TOZ, NYYY, FX).setData((byte) color);
        BedWars.changedBlocks.add(bed.getWorld().getBlockAt(TOZ, NYYY, FX).getLocation());
    }

    public static void placerwe(Team team, Location location) {
        if (location.getBlock().getRelative(-1, 0, 0).getType().equals(Material.BED_BLOCK)) {
            location.subtract(0, 0, -1);
        }
        if (location.getBlock().getRelative(0, 0, -1).getType().equals(Material.BED_BLOCK)) {
            location.subtract(-1, 0, 0);
        }
        if (location.getBlock().getRelative(1, 0, 0).getType().equals(Material.BED_BLOCK)) {
            location.subtract(0, 0, 1);
        }
        if (location.getBlock().getRelative(0, 0, 1).getType().equals(Material.BED_BLOCK)) {
            location.subtract(1, 0, 0);
        }
        Block bed1 = location.getBlock();
        int woolcolor = 0;
        if (team.getName().equalsIgnoreCase("红队")) {
            woolcolor = 14;
        }
        if (team.getName().equalsIgnoreCase("蓝队")) {
            woolcolor = 11;
        }
        if (team.getName().equalsIgnoreCase("绿队")) {
            woolcolor = 5;
        }
        if (team.getName().equalsIgnoreCase("黄队")) {
            woolcolor = 4;
        }
        if (team.getName().equalsIgnoreCase("白队")) {
            woolcolor = 0;
        }
        if (team.getName().equalsIgnoreCase("青队")) {
            woolcolor = 9;
        }
        if (team.getName().equalsIgnoreCase("粉队")) {
            woolcolor = 6;
        }
        if (team.getName().equalsIgnoreCase("灰队")) {
            woolcolor = 8;
        }
        int TOX = bed1.getX();
        int TOY = bed1.getY();
        int TOZ = bed1.getZ();
        int AX = TOX - 1;
        int BX = AX + 1;
        int BZ = TOZ + 1;
        int CX = BX + 1;
        int DZ = BZ - 1;
        int DX = AX + 3;
        int EX = AX + 1;
        int EZ = TOZ - 1;
        int FX = EX + 1;
        int FZ = EZ - 1;
        int BDY = TOY + 1;
        int AXX = TOX - 2;
        int BXX = AX;
        int BZZ = TOZ + 1;
        int CXX = AX + 1;
        int CZZ = TOZ + 2;
        int DXX = BX + 1;
        int EXX = AX + 3;
        int EZZ = BZZ;
        int FXX = EXX + 1;
        int FZZ = TOZ;
        int GXX = FXX - 1;
        int GZZ = FZZ - 1;
        int HXX = GXX - 1;
        int HZZ = GZZ - 1;
        int IXX = HXX - 1;
        int JXX = IXX - 1;
        int JZZ = HZZ + 1;
        int KYY = BDY + 1;
        int AXXX = AXX - 1;
        int BXXX = AXX;
        int BZZZ = BZZ;
        int XXXX = BXX;
        int ZZZZ = BZZ + 1;
        int CXXX = CXX;
        int CZZZ = CZZ + 1;
        int DXXX = DXX;
        int DZZZ = CZZ + 1;
        int EXXX = EXX;
        int EZZZ = EZZ + 1;
        int FXXX = FXX;
        int FZZZ = FZZ + 1;
        int GXXX = FXX + 1;
        int GZZZ = FZZ;
        int HXXX = FXX;
        int HZZZ = FZZ - 1;
        int IXXX = GXX;
        int IZZZ = GZZ - 1;
        int JXXX = HXX;
        int JZZZ = HZZ - 1;
        int KXXX = HXX - 1;
        int KZZZ = HZZ - 1;
        int LXXX = JXX;
        int LZZZ = JZZ - 1;
        int MXXX = AXX;
        int MZZZ = BZZ - 2;
        int NYYY = TOY + 3;
        int OYYY = TOY + 1;
        int PYYY = TOY + 2;
        bed1.getWorld().getBlockAt(AX, TOY, TOZ).setType(Material.WOOD);
        
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(AX, TOY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(BX, TOY, BZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(BX, TOY, BZ).getLocation());
        bed1.getWorld().getBlockAt(CX, TOY, BZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(CX, TOY, BZ).getLocation());
        bed1.getWorld().getBlockAt(DX, TOY, DZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(DX, TOY, DZ).getLocation());
        bed1.getWorld().getBlockAt(EX, TOY, EZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(EX, TOY, EZ).getLocation());
        bed1.getWorld().getBlockAt(FX, TOY, EZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FX, TOY, EZ).getLocation());
        bed1.getWorld().getBlockAt(TOX, BDY, TOZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(TOX, BDY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(FX, BDY, TOZ).setType(Material.WOOD);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FX, BDY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(AXX, TOY, TOZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(AXX, TOY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(AXX, TOY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(BXX, TOY, BZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(BXX, TOY, BZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(BXX, TOY, BZZ).getLocation());
        bed1.getWorld().getBlockAt(CXX, TOY, CZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(CXX, TOY, CZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(CXX, TOY, CZZ).getLocation());
        bed1.getWorld().getBlockAt(DXX, TOY, CZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(DXX, TOY, CZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(DXX, TOY, CZZ).getLocation());
        bed1.getWorld().getBlockAt(EXX, TOY, EZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(EXX, TOY, EZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(EXX, TOY, EZZ).getLocation());
        bed1.getWorld().getBlockAt(FXX, TOY, FZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(FXX, TOY, FZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FXX, TOY, FZZ).getLocation());
        bed1.getWorld().getBlockAt(GXX, TOY, GZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(GXX, TOY, GZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(GXX, TOY, GZZ).getLocation());
        bed1.getWorld().getBlockAt(HXX, TOY, HZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(HXX, TOY, HZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(HXX, TOY, HZZ).getLocation());
        bed1.getWorld().getBlockAt(IXX, TOY, HZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(IXX, TOY, HZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(IXX, TOY, HZZ).getLocation());
        bed1.getWorld().getBlockAt(JXX, TOY, JZZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(JXX, TOY, JZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(JXX, TOY, JZZ).getLocation());
        bed1.getWorld().getBlockAt(AX, BDY, TOZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(AX, BDY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(AX, BDY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(BX, BDY, BZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(BX, BDY, BZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(BX, BDY, BZ).getLocation());
        bed1.getWorld().getBlockAt(CX, BDY, BZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(CX, BDY, BZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(CX, BDY, BZ).getLocation());
        bed1.getWorld().getBlockAt(DX, BDY, DZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(DX, BDY, DZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(DX, BDY, DZ).getLocation());
        bed1.getWorld().getBlockAt(EX, BDY, EZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(EX, BDY, EZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(EX, BDY, EZ).getLocation());
        bed1.getWorld().getBlockAt(FX, BDY, EZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(FX, BDY, EZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FX, BDY, EZ).getLocation());
        bed1.getWorld().getBlockAt(TOX, KYY, TOZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(TOX, KYY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(TOX, KYY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(FX, KYY, TOZ).setType(Material.WOOL);
        bed1.getWorld().getBlockAt(FX, KYY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FX, KYY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(AXXX, TOY, TOZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(AXXX, TOY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(AXXX, TOY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(BXXX, TOY, BZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(BXXX, TOY, BZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(BXXX, TOY, BZZZ).getLocation());
        bed1.getWorld().getBlockAt(XXXX, TOY, ZZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(XXXX, TOY, ZZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(XXXX, TOY, ZZZZ).getLocation());
        bed1.getWorld().getBlockAt(CXXX, TOY, CZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(CXXX, TOY, CZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(CXXX, TOY, CZZZ).getLocation());
        bed1.getWorld().getBlockAt(DXXX, TOY, DZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(DXXX, TOY, DZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(DXXX, TOY, DZZZ).getLocation());
        bed1.getWorld().getBlockAt(EXXX, TOY, EZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(EXXX, TOY, EZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(EXXX, TOY, EZZZ).getLocation());
        bed1.getWorld().getBlockAt(FXXX, TOY, FZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(FXXX, TOY, FZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FXXX, TOY, FZZZ).getLocation());
        bed1.getWorld().getBlockAt(GXXX, TOY, GZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(GXXX, TOY, GZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(GXXX, TOY, GZZZ).getLocation());
        bed1.getWorld().getBlockAt(HXXX, TOY, HZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(HXXX, TOY, HZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(HXXX, TOY, HZZZ).getLocation());
        bed1.getWorld().getBlockAt(IXXX, TOY, IZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(IXXX, TOY, IZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(IXXX, TOY, IZZZ).getLocation());
        bed1.getWorld().getBlockAt(JXXX, TOY, JZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(JXXX, TOY, JZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(JXXX, TOY, JZZZ).getLocation());
        bed1.getWorld().getBlockAt(KXXX, TOY, KZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(KXXX, TOY, KZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(KXXX, TOY, KZZZ).getLocation());
        bed1.getWorld().getBlockAt(LXXX, TOY, LZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(LXXX, TOY, LZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(LXXX, TOY, LZZZ).getLocation());
        bed1.getWorld().getBlockAt(MXXX, TOY, MZZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(MXXX, TOY, MZZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(MXXX, TOY, MZZZ).getLocation());
        bed1.getWorld().getBlockAt(AXX, OYYY, TOZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(AXX, OYYY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(AXX, OYYY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(BXX, OYYY, BZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(BXX, OYYY, BZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(BXX, OYYY, BZZ).getLocation());
        bed1.getWorld().getBlockAt(CXX, OYYY, CZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(CXX, OYYY, CZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(CXX, OYYY, CZZ).getLocation());
        bed1.getWorld().getBlockAt(DXX, OYYY, CZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(DXX, OYYY, CZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(DXX, OYYY, CZZ).getLocation());
        bed1.getWorld().getBlockAt(EXX, OYYY, EZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(EXX, OYYY, EZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(EXX, OYYY, EZZ).getLocation());
        bed1.getWorld().getBlockAt(FXX, OYYY, FZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(FXX, OYYY, FZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FXX, OYYY, FZZ).getLocation());
        bed1.getWorld().getBlockAt(GXX, OYYY, GZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(GXX, OYYY, GZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(GXX, OYYY, GZZ).getLocation());
        bed1.getWorld().getBlockAt(HXX, OYYY, HZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(HXX, OYYY, HZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(HXX, OYYY, HZZ).getLocation());
        bed1.getWorld().getBlockAt(IXX, OYYY, HZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(IXX, OYYY, HZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(IXX, OYYY, HZZ).getLocation());
        bed1.getWorld().getBlockAt(JXX, OYYY, JZZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(JXX, OYYY, JZZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(JXX, OYYY, JZZ).getLocation());
        bed1.getWorld().getBlockAt(AX, PYYY, TOZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(AX, PYYY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(AX, PYYY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(BX, PYYY, BZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(BX, PYYY, BZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(BX, PYYY, BZ).getLocation());
        bed1.getWorld().getBlockAt(CX, PYYY, BZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(CX, PYYY, BZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(CX, PYYY, BZ).getLocation());
        bed1.getWorld().getBlockAt(DX, PYYY, DZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(DX, PYYY, DZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(DX, PYYY, DZ).getLocation());
        bed1.getWorld().getBlockAt(EX, PYYY, EZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(EX, PYYY, EZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(EX, PYYY, EZ).getLocation());
        bed1.getWorld().getBlockAt(FX, PYYY, EZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(FX, PYYY, EZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FX, PYYY, EZ).getLocation());
        bed1.getWorld().getBlockAt(TOX, NYYY, TOZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(TOX, NYYY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(TOX, NYYY, TOZ).getLocation());
        bed1.getWorld().getBlockAt(FX, NYYY, TOZ).setType(Material.STAINED_GLASS);
        bed1.getWorld().getBlockAt(FX, NYYY, TOZ).setData((byte)(int)woolcolor);
        BedWars.changedBlocks.add(bed1.getWorld().getBlockAt(FX, NYYY, TOZ).getLocation());
    }
}
