package Events;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Commands.Points;
import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class SPEEDBOWI implements Listener {

	
	
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
	public void SPEEDBOW(InventoryClickEvent e){
		
		if (e.getCurrentItem() == null){
			e.setCancelled(true);
		}
		
		Player p  = (Player)e.getWhoClicked();
		if(FFA.imspiel.contains(p.getName())){
		if(e.getInventory().getName().equals("§4§lKILLSTREAK")){
			
			ItemStack Speedbow = new ItemStack(Material.IRON_HOE);
			ItemMeta SpeedbowMeta = Speedbow.getItemMeta();
			SpeedbowMeta.setDisplayName("§8§lSpeedBow");
			Speedbow.setItemMeta(SpeedbowMeta);
			
			ItemStack arrow = new ItemStack(Material.ARROW, 128);
			ItemMeta arrowMeta = arrow.getItemMeta();
			arrowMeta.setDisplayName("§8§lArrow");
			arrow.setItemMeta(arrowMeta);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8§lSpeedBow")){
				int points = Points.cfg.getInt("Points." + p.getName());
				if(points > 14){
		              Points.cfg.set("Points." + p.getName(), Integer.valueOf(Points.cfg.getInt("Points." + p.getName()) - 15));
					p.getInventory().addItem(Speedbow);
					p.getInventory().addItem(arrow);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
					p.closeInventory();
				e.setCancelled(true);
				p.sendMessage(FFA.prefix + "§eDu hast §8§lSpeedBow §efür §c15 §e Punkte gekauft");
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
