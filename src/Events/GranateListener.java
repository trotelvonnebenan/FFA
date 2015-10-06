package Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import me.trotelvonnebenan.FFA.FFA;

public class GranateListener implements Listener {
	
	
	
	@EventHandler
	   public void on(PlayerEggThrowEvent e)
	   {
	     Player p = e.getPlayer();
	    if (FFA.imspiel.contains(p.getName()))
	     {
	       Bukkit.getWorld(p.getWorld().getName()).createExplosion(e.getEgg().getLocation(), 3.0F);
	       //((CraftWorld)p.getWorld()).getHandle().("hugeexplosion", e.getEgg().getLocation().getX(), e.getEgg().getLocation().getY() + 2.0D, e.getEgg().getLocation().getZ(), 2, 2.0D, 2.0D, 2.0D, 0.1000000014901161D);
	     }
	   }
	 }

