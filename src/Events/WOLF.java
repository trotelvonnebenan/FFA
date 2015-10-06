package Events;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Commands.Points;
import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class WOLF implements Listener {

	
	
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
	public void GRANATE(InventoryClickEvent e){
		
		if (e.getCurrentItem() == null){
			return;
		}
	
		ItemStack Wolf = new ItemStack(Material.MONSTER_EGG, 1 , (short)95);
		ItemMeta WolfMeta = Wolf.getItemMeta();
		WolfMeta.setDisplayName("§a§lWolf");
		Wolf.setItemMeta(WolfMeta);

		
		Player p  = (Player)e.getWhoClicked();
		//Player t = (Player) p.getNearbyEntities(5, 5, 5);
		if(FFA.imspiel.contains(p.getName())){
		if(e.getInventory().getName().equals("§4§lKILLSTREAK")){
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lWolf")){
				int points = Points.cfg.getInt("Points." + p.getName());
					if(points > 9){
			              Points.cfg.set("Points." + p.getName(), Integer.valueOf(Points.cfg.getInt("Points." + p.getName()) - 10));
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
						Wolf wolf = (org.bukkit.entity.Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
						wolf.setAngry(true);
						//wolf.setTarget(t);
				p.closeInventory();
				e.setCancelled(true);
				p.sendMessage(FFA.prefix + "§eDu hast einen Wachhund für §c10 §e Punkte gekauft");
				try {
					FFA.playerdatacfg.save(FFA.playerdata);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					}
				else {
					e.setCancelled(true);
					p.sendMessage(FFA.prefix + "§eDu hast nicht genug Punkte");
					p.closeInventory();
				}
			}
		}
	}
	}
	}
