package cn.lemoncraft.bedwars.Game.Protect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChange implements Listener {
    @EventHandler
    public void weather(WeatherChangeEvent world){
        if (world.toWeatherState()){
            world.setCancelled(true);
        }
    }

}
