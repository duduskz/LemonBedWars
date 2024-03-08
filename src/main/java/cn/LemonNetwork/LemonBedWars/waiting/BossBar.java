//package cn.lemonnetwork.lemonbedwars.waiting;
//
//import net.minecraft.server.v1_8_R3.PlayerConnection;
//
//public class BossBar {
//    public static void createBossBar(String message) {
////        for (Player player : Bukkit.getOnlinePlayers()) {
////            try {
////                PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
////
////                PacketPlayOutBoss packetPlayOutBoss = new PacketPlayOutBoss();
////                setField(packetPlayOutBoss, "a", 0); // Entity ID
////                setField(packetPlayOutBoss, "b", EnumBossBarAction.ADD); // Action (ADD)
////                setField(packetPlayOutBoss, "c", new ChatComponentText(message)); // Message
////                setField(packetPlayOutBoss, "d", 1.0f); // Progress (1.0 = 100%)
////
////                connection.sendPacket(packetPlayOutBoss);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
//    }
//
//    // 使用反射设置私有字段的值
//    private static void setField(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
//        Field field = object.getClass().getDeclaredField(fieldName);
//        field.setAccessible(true);
//        field.set(object, value);
//    }
//
//    // Boss Bar动作的枚举类
//    private enum EnumBossBarAction {
//        ADD,
//        REMOVE,
//        UPDATE_PCT,
//        UPDATE_NAME,
//        UPDATE_STYLE
//    }
//}
