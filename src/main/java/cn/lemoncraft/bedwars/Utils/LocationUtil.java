package cn.lemoncraft.bedwars.Utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocationUtil {
    public LocationUtil() {
    }

    public static Location getLocation(Location location, int x, int y, int z) {
        Location loc = location.getBlock().getLocation();
        loc.add((double)x, (double)y, (double)z);
        return loc;
    }
    public static Vector getPosition(Location location1, Location location2, double Y) {
        double X = location1.getX() - location2.getX();
        double Z = location1.getZ() - location2.getZ();
        return new Vector(X, Y, Z);
    }
    public static String[] getStringLocation(String loc) {
        String substring = loc.substring(0, loc.length());
        return substring.split(",");//以逗号分割
    }
}
