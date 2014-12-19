package code.BreakMC.avalon.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldEvents implements Listener {
	
	@EventHandler
	public void weatherChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
}