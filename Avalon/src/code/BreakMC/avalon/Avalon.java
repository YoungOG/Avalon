package code.BreakMC.avalon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import code.BreakMC.avalon.commands.ClearCommand;
import code.BreakMC.avalon.commands.FeedCommand;
import code.BreakMC.avalon.commands.FlyCommand;
import code.BreakMC.avalon.commands.GamemodeCommand;
import code.BreakMC.avalon.commands.HealCommand;
import code.BreakMC.avalon.commands.InvseeCommand;
import code.BreakMC.avalon.commands.LagCommand;
import code.BreakMC.avalon.commands.SpawnCommand;
import code.BreakMC.avalon.commands.SpeedCommand;
import code.BreakMC.avalon.commands.TestCommand;
import code.BreakMC.avalon.events.MineEvents;
import code.BreakMC.avalon.events.MushroomCowEvents;
import code.BreakMC.avalon.events.StrenghtFix;
import code.BreakMC.avalon.events.WorldEvents;
import code.BreakMC.avalon.util.Lag;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Avalon extends JavaPlugin {

	private static Avalon instance;
	private DatabaseManager dm;
	private SpawnManager sm;
	
	private boolean isLocked = true;

	public void onEnable() {
		instance = this;

		dm = new DatabaseManager();
		sm = new SpawnManager();
		
		getServer().getPluginManager().registerEvents(new StrenghtFix(), this);
		getServer().getPluginManager().registerEvents(new WorldEvents(), this);
		getServer().getPluginManager().registerEvents(new MushroomCowEvents(), this);
		getServer().getPluginManager().registerEvents(new MineEvents(), this);

		getCommand("test").setExecutor(new TestCommand());
		getCommand("invsee").setExecutor(new InvseeCommand());
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("clear").setExecutor(new ClearCommand());
		getCommand("speed").setExecutor(new SpeedCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("lag").setExecutor(new LagCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
		
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {
			public void run() {
				isLocked = false;
			}
		}, 5*20);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if (Bukkit.getWorld("world").getTime() >= 13000) {
					Bukkit.getWorld("world").setTime(0);
				}
			}
		}, 0, 5*20);
	}

	public static Avalon getInstance() {
		return instance;
	}

	public DatabaseManager getDatabaseManager() {
		return dm;
	}

	public SpawnManager getSpawnManager() {
		return sm;
	}
	
	public Boolean isServerLocked() {
		return isLocked;
	}
	
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
			Bukkit.getPluginManager().disablePlugin(this);
			return null;
        }
        return (WorldGuardPlugin)plugin;
    }
}
