package Events;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SpeedBow implements Listener {
	
	
	
	@EventHandler
	   public void onPlayerUseTheBow(PlayerInteractEvent e) {
	     try {
	       Player p = e.getPlayer();
	       ItemStack HSB = p.getInventory().getItemInHand();

	       if ((HSB.getType() == Material.IRON_HOE) && (p.getInventory().contains(Material.ARROW)) && (e.getAction() == Action.RIGHT_CLICK_AIR)) {
	         Arrow pfeil = (Arrow)p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
	         pfeil.setShooter(p);
	         pfeil.setVelocity(p.getLocation().getDirection().multiply(2.0D));
	         p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.ARROW, 1) });
	         p.updateInventory();
	         e.setCancelled(true);
	      }
	     } catch (Exception localException) {
	     }
	   }

}
