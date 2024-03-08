package cn.lemonnetwork.lemonbedwars.Game.Arena.Menu;

import cn.lemonnetwork.lemonbedwars.Utils.LocationUtil;
import cn.lemonnetwork.lemonbedwars.LemonBedWars;
import cn.lemonnetwork.lemonbedwars.Game.Arena.GameStart;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Team;

public class OpenTeamChest implements Listener {
    @EventHandler
    public void open(PlayerInteractEvent e) {

        try {
            if (e.getClickedBlock().getType() == Material.CHEST && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                for (Team team : GameStart.getScoreboard().getTeams()) {
                    String[] l = LocationUtil.getStringLocation(LemonBedWars.getPlugin(LemonBedWars.class).getConfig().getString("Map."+team.getName()+".chest"));
                    if (e.getClickedBlock().getLocation().equals(new Location(e.getPlayer().getWorld(), Double.parseDouble(l[0]), Double.parseDouble(l[1]), Double.parseDouble(l[2]))) && team.getEntries().contains(e.getPlayer().getName())) {
                        if (!team.getDisplayName().equalsIgnoreCase("0")){
                            e.setCancelled(true);
                            e.getPlayer().sendMessage("§c此队伍还没有被淘汰, 你还不能打开他们的队伍箱子!");
                        }
                    }
                }
            }
        } catch (NullPointerException ignored) {

        }
    }
}
