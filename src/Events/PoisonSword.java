package Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonSword implements Listener {
	
	
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e){
	if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
	Player p = (Player) e.getEntity();
	Player k = (Player) e.getDamager();
	
	if(k.getInventory().getItemInHand().equals(Material.WOOD_SWORD)){
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
		}
		
	}
	
	}
	}

