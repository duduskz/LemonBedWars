package cn.lemonnetwork.lemonbedwars.Utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

public class setPlayerHeadName implements Listener {
    public static HashMap<Player, String> FakeName = new HashMap<>();

    public static UUID getPlayerUUID(String playerName) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                String uuidStr = json.get("id").getAsString();
                return convertToUUID(uuidStr);


            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String getSkinURL(UUID playerUUID) {
        try {
            String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + playerUUID.toString() + "?unsigned=false";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            if (connection.getResponseCode() == 200) {
                JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                String properties = json.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
                return new JsonParser().parse(new String(Base64.getDecoder().decode(properties))).getAsJsonObject().get("textures").getAsJsonObject().get("SKIN").getAsJsonObject().get("url").getAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static UUID convertToUUID(String uuidString) {
        try {
            if (uuidString.length() == 32) {
                String formattedUUID =
                        uuidString.substring(0, 8) + "-" +
                                uuidString.substring(8, 12) + "-" +
                                uuidString.substring(12, 16) + "-" +
                                uuidString.substring(16, 20) + "-" +
                                uuidString.substring(20);

                return UUID.fromString(formattedUUID);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setPlayerName(Player player, String newName, String skin) {
        try {
            EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

            GameProfile profile = entityPlayer.getProfile();
//            PropertyMap propertyMap = profile.getProperties();
//            propertyMap.get("textures").clear();
//            UUID uuid = getPlayerUUID(skin);
//            System.out.println("获取到皮肤玩家名: " + skin);
//            System.out.println("获取到皮肤玩家名: " + uuid.toString());
//            String skinUrl = getSkinURL(uuid);
//            System.out.println("获取到皮肤链接: " + skinUrl);
//            propertyMap.put("textures", new Property("textures", Base64.getEncoder().encodeToString(("{\"textures\":{\"SKIN\":{\"url\":\"" + skinUrl + "\"}}}").getBytes())));
            Field nameField = GameProfile.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(profile, newName);

            MinecraftServer.getServer().getUserCache().a(profile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    @EventHandler
//    public void onJoin(PlayerJoinEvent event) {
//        for (Player player : Bukkit.getOnlinePlayers()) {
//            if (!player.getName().equals(event.getPlayer().getName())) {
//                setPlayerName(player, FakeName.get(player));
//            }
//        }
//    }
}
