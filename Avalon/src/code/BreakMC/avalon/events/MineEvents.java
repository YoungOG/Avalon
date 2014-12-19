package code.BreakMC.avalon.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MineEvents implements Listener {
	
	@EventHandler
	public void onMine(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.COAL_ORE) {
			Random r = new Random();
			if (r.nextInt(100) >= 0 && r.nextInt(100) <= 29) {
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.SULPHUR, 1));
				return;
			}
		}
		if (e.getBlock().getType() == Material.SAND) {
			Random r = new Random();
			if (r.nextInt(100) >= 0 && r.nextInt(100) <= 4) {
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GLASS, 1));
				return;
			}
		}
	}
}
