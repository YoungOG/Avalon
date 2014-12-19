package code.BreakMC.avalon.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class MushroomCowEvents implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		if (ent.getType() == EntityType.MUSHROOM_COW) {
			if (p.getItemInHand().getType() == Material.GLASS_BOTTLE) {
				int count = 0;
				for (ItemStack i : p.getInventory()) {
					if (i == null) {
						count++;
					}
				}
				if (count >= 1) {
					if (p.getItemInHand().getAmount() > 1) {
						ItemStack potion = new ItemStack(Material.POTION, 1);
						potion.setDurability((short) 8229);
						p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
						p.getInventory().addItem(potion);
						p.updateInventory();
					} else {
						ItemStack potion = new ItemStack(Material.POTION, 1);
						potion.setDurability((short) 8229);
						p.setItemInHand(potion);
						p.updateInventory();
					}
				} else {
					Location loc = p.getLocation();
					ItemStack potion = new ItemStack(Material.POTION, 1);
					potion.setDurability((short) 8229);
					loc.getWorld().dropItemNaturally(loc, potion);
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
					p.updateInventory();
				}
			}
		}
	}
}