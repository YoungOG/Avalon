package code.BreakMC.avalon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import code.BreakMC.avalon.Avalon;

public class SpawnCommand implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cOnly players can use this command.");
			return false;
		}

		Player p = (Player) sender;

		if (!p.hasPermission("avalon.spawn")) {
			p.sendMessage("§fUnknown command. Type \"/help\" for help.");
			return false;
		}

		p.sendMessage("§aYou have teleported to spawn.");
		Avalon.getInstance().getSpawnManager().teleport(p, Avalon.getInstance().getSpawnManager().getSpawn());
		return false;
	}
}
