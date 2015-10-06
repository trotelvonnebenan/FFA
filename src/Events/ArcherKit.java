package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class ArcherKit implements Listener {
	
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
	public void onArcher(InventoryClickEvent e){	
		Player p  = (Player)e.getWhoClicked();
		if(e.getInventory().getName().equals("§3Wähle dein Kit")){
		if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§f§lSkeleton")){
			
	        ItemStack bschwert = new ItemStack(Material.STONE_SWORD);
	        ItemMeta bschwertMeta = bschwert.getItemMeta();
	        bschwertMeta.setDisplayName("§f§lSkeleton");
	         bschwert.setItemMeta(bschwertMeta);
	         
		        ItemStack bfleisch = new ItemStack(Material.COOKED_BEEF, 16);
		        ItemMeta bfleischMeta = bfleisch.getItemMeta();
		        bfleischMeta.setDisplayName("§f§lSkeleton");
		        bfleisch.setItemMeta(bfleischMeta);
	         
		        ItemStack bbogen = new ItemStack(Material.BOW);
		        ItemMeta bbogenMeta = bbogen.getItemMeta();
		        bbogen.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		        bbogenMeta.setDisplayName("§f§lSkeleton");
		        bbogen.setItemMeta(bbogenMeta);
	         
		        ItemStack bpfeil = new ItemStack(Material.ARROW);
		        ItemMeta bpfeilMeta = bpfeil.getItemMeta();
		        bpfeilMeta.setDisplayName("§f§lSkeleton");
		        bpfeil.setItemMeta(bpfeilMeta);
		        
		        ItemStack bhelm = new ItemStack(Material.SKULL_ITEM);
		        ItemMeta bhelmMeta = bhelm.getItemMeta();
		        bhelmMeta.setDisplayName("§f§lSkeleton");
		        bhelm.setItemMeta(bhelmMeta);
		         
			        ItemStack bbrust = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
			        ItemMeta bbrustMets = bbrust.getItemMeta();
			        bbrustMets.setDisplayName("§f§lSkeleton");
			        bbrust.setItemMeta(bbrustMets);
			         
				        ItemStack bhose = new ItemStack(Material.LEATHER_LEGGINGS);
				        ItemMeta bhoseMeta = bhose.getItemMeta();
				        bhoseMeta.setDisplayName("§f§lSkeleton");
				        bhose.setItemMeta(bhoseMeta);
				         
					        ItemStack bschuhe = new ItemStack(Material.LEATHER_BOOTS);
					        ItemMeta bschuheMeta = bschuhe.getItemMeta();
					        bschuheMeta.setDisplayName("§f§lSkeleton");
					        bschuhe.setItemMeta(bschuheMeta);
					         
			
						    e.setCancelled(true);
						    p.closeInventory();         
							FFA.imspiel.add(p.getName());
						    World w = Bukkit.getWorld(FFA.arenacfg.getString("Arena.World"));
						    double x = FFA.arenacfg.getDouble("Arena.X");
						    double y = FFA.arenacfg.getDouble("Arena.Y");
						    double z = FFA.arenacfg.getDouble("Arena.Z");
						    double yaw = FFA.arenacfg.getDouble("Arena.Yaw");
						    double pitch = FFA.arenacfg.getDouble("Arena.Pitch");
						    
						    
						    Location loc = new Location(w,x,y,z);
						    loc.setYaw((float) yaw);
						    loc.setPitch((float) pitch);
						    ItemStack shop = new ItemStack(Material.BLAZE_POWDER);
							ItemMeta shopMeta = shop.getItemMeta();
							shopMeta.setDisplayName("§4§lKILLSTREAK");
							shop.setItemMeta(shopMeta);
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
							p.getInventory().clear();
							p.getInventory().setArmorContents(null);
							p.getInventory().setHelmet(bhelm);
							p.getInventory().setChestplate(bbrust);
							p.getInventory().setLeggings(bhose);
							p.getInventory().setBoots(bschuhe);
							p.getInventory().setItem(0, bschwert);
							p.getInventory().setItem(1, bbogen);
							p.getInventory().setItem(9, bpfeil);
							p.getInventory().setItem(2, bfleisch);
							p.getInventory().setItem(8, addGlow(shop));
							e.setCancelled(true);
							p.teleport(loc);
							p.closeInventory();	
							FFA.imspiel.add(p.getName());
							FFA.kitauswahl.remove(p.getName());
							p.sendMessage(FFA.prefix + "§eDu hast das Kit §f§lSkeleton §egewählt");
							
			}		      
		}
	}
}
