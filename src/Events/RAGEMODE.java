package Events;

import java.io.IOException;

import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Commands.Points;
import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class RAGEMODE implements Listener {
	
	
	public static ItemStack addGlow(ItemStack item){ 
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		  NBTTagCompound tag = null;
		  if (!nmsStack.hasTag()) {
		      tag = new NBTTagCompound();
		      nmsStack.setTag(tag);
		  }
		  if (tag == null) tag = nmsStack.getTag();
		  NBTTagList ench = new NBTTagList();
		  tag.set("ench", ench);
		  nmsStack.setTag(tag);
		  return CraftItemStack.asCraftMirror(nmsStack);
		}
	
	
	
	@EventHandler
	public void RAGEMODE(InventoryClickEvent e){
		
		if (e.getCurrentItem() == null){
			e.setCancelled(true);
		}
		
		Player p  = (Player)e.getWhoClicked();
		if(FFA.imspiel.contains(p.getName())){
		if(e.getInventory().getName().equals("§4§lKILLSTREAK")){
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRage-Mode")){
				int points = Points.cfg.getInt("Points." + p.getName());
				if(points > 29){
		              Points.cfg.set("Points." + p.getName(), Integer.valueOf(Points.cfg.getInt("Points." + p.getName()) - 30));
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60*10, 3));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
					p.closeInventory();
				e.setCancelled(true);
				p.sendMessage(FFA.prefix + "§eDu hast §c§lRage-Mode §efür §c30 §e Punkte gekauft");
				try {
					FFA.playerdatacfg.save(FFA.playerdata);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				}else {
					e.setCancelled(true);
					p.sendMessage(FFA.prefix + "§eDu hast nicht genug Punkte");
					p.closeInventory();
				}
			}
		}
	}
	}
	

}
