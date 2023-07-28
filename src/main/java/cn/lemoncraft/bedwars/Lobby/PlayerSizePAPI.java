package cn.lemoncraft.bedwars.Lobby;

import cn.lemoncraft.bedwars.BedWars;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class PlayerSizePAPI extends PlaceholderExpansion {
        public String getAuthor() {
            return "duduskz";
        }


        public String getIdentifier() {
            return "LemonBedWarsPlayerSize";
        }


        public String getVersion() {
            return "1.0.0";
        }


        public String getRequiredPlugin() {
            return "LemonBedWars";
        }


        public boolean canRegister() {
            return true;
        }
    public String onRequest(OfflinePlayer player, String params) {
        Plugin plugin = BedWars.getPlugin(BedWars.class);
        FileConfiguration config = plugin.getConfig();
        List<String> servers = config.getStringList("LobbyGroup."+params);
        int size = 0;



        for (String server : servers) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerList");
            out.writeUTF(server);
            ByteArrayDataInput in = ByteStreams.newDataInput(out.toByteArray());
            String[] playerList = in.readUTF().split(", ");
            size= size+playerList.length;
        }

        return String.valueOf(size);
    }

}
