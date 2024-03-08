package cn.lemonnetwork.lemonbedwars.Game.Protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeEvent implements Listener {
    @EventHandler
    public void foodchange(FoodLevelChangeEvent event){
        event.setFoodLevel(20);
    }
}
