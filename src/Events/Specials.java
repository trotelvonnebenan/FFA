package Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Specials implements Listener {
	
	@EventHandler
	public void on(PlayerMoveEvent e){
		
		Player p = e.getPlayer();
		
		if(p.getLocation().getBlock().getType() == Material.STATIONARY_WATER){
			
			Vector v = p.getVelocity().setY(2D);
			
			p.setVelocity(v);
		}
		
	}

}
