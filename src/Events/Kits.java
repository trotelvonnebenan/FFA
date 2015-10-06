package Events;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class Kits implements Listener {
	
	
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
	public void on1(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
if(e.getAction() == Action.RIGHT_CLICK_AIR) {
	if(e.getItem().getType() != Material.BED){	
				return;
	}
			}
		
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")){
				e.setCancelled(true);
				p.closeInventory();
			}
			
			if(e.getItem().getType() == Material.BED){
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Wähle dein Kit")){
				
				
				ItemStack krieger = new ItemStack(Material.SKULL_ITEM ,1, (short)2);
				ItemMeta kriegerMeta = krieger.getItemMeta();
				kriegerMeta.setDisplayName("§2§lZombie");
				krieger.setItemMeta(kriegerMeta);
				
				ItemStack creeper = new ItemStack(Material.SKULL_ITEM ,1, (short)4);
				ItemMeta creeperMeta = creeper.getItemMeta();
				 List<String> creeperLore = new ArrayList();
	       creeperLore.add(" ");
	         creeperLore.add("§6§lPremium Kit");
	          creeperLore.add("§7Um Premium zu werden Kontaktiere bitte");
	          creeperLore.add("§7den Owner");
	         creeperMeta.setLore(creeperLore);
				creeperMeta.setDisplayName("§6§lCreeper");
				creeper.setItemMeta(creeperMeta);
				
				ItemStack Bogens = new ItemStack(Material.SKULL_ITEM);
				ItemMeta BogensMeta = Bogens.getItemMeta();
				BogensMeta.setDisplayName("§f§lSkeleton");
				Bogens.setItemMeta(BogensMeta);
				
				ItemStack PLATZ = new ItemStack(Material.STAINED_GLASS_PANE,1, (short)7);
				ItemMeta PLATZMeta = PLATZ.getItemMeta();
				PLATZMeta.setDisplayName("§3");
				PLATZ.setItemMeta(PLATZMeta);
				
				Inventory inv = Bukkit.createInventory(null, 27, "§3Wähle dein Kit");
				inv.setItem(0, PLATZ);
				inv.setItem(1, PLATZ);
				inv.setItem(2, PLATZ);
				inv.setItem(3, PLATZ);
				inv.setItem(4, PLATZ);
				inv.setItem(5, PLATZ);
				inv.setItem(6, PLATZ);
				inv.setItem(7, PLATZ);
				inv.setItem(8, PLATZ);
				inv.setItem(9, PLATZ);
				inv.setItem(10, addGlow(krieger));
				inv.setItem(11, PLATZ);
				inv.setItem(12, addGlow(Bogens));
				inv.setItem(13, PLATZ);
				inv.setItem(14, addGlow(creeper));
				inv.setItem(15, PLATZ);
				inv.setItem(16, PLATZ);
				inv.setItem(17, PLATZ);
				inv.setItem(18, PLATZ);
				inv.setItem(19, PLATZ);
				inv.setItem(20, PLATZ);
				inv.setItem(21, PLATZ);
				inv.setItem(22, PLATZ);
				inv.setItem(23, PLATZ);
				inv.setItem(24, PLATZ);
				inv.setItem(25, PLATZ);
				inv.setItem(26, PLATZ);
				p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
			    p.openInventory(inv);
			   // FFA.setScoreboard(p);
				}
			}
		}
	}
}
		