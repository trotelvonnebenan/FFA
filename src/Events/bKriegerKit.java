package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class bKriegerKit implements Listener {
	
	
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
	public void onKrieger(InventoryClickEvent e){	
		Player p  = (Player)e.getWhoClicked();
		if(e.getInventory().getName().equals("§3Wähle dein Kit")){
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2§lZombie")){
				
			        ItemStack kschwert = new ItemStack(Material.IRON_SWORD);
				        ItemMeta kschwertMeta = kschwert.getItemMeta();
				         kschwertMeta.setDisplayName("§2§lZombie");
				         kschwert.setItemMeta(kschwertMeta);
				         
				         ItemStack bfleisch = new ItemStack(Material.COOKED_BEEF, 16);
					        ItemMeta bfleischMeta = bfleisch.getItemMeta();
					        bfleischMeta.setDisplayName("§2§lZombie");
					        bfleisch.setItemMeta(bfleischMeta);
				         
					        ItemStack khelm = new ItemStack(Material.SKULL_ITEM, 1,(short)2);
					        ItemMeta khelmMeta = khelm.getItemMeta();
					        khelmMeta.setDisplayName("§2§lZombie");
					         khelm.setItemMeta(khelmMeta);
					         
						        ItemStack kbrust = new ItemStack(Material.IRON_CHESTPLATE);
						        ItemMeta kbrustMeta = kbrust.getItemMeta();
						        kbrustMeta.setDisplayName("§2§lZombie");
						         kbrust.setItemMeta(kbrustMeta);
						         
							        ItemStack khose = new ItemStack(Material.CHAINMAIL_LEGGINGS);
							        ItemMeta khoseMeta = khose.getItemMeta();
							         kschwertMeta.setDisplayName("§2§lZombie");
							         khose.setItemMeta(khoseMeta);
							         
								        ItemStack kschuhe = new ItemStack(Material.IRON_BOOTS);
								        ItemMeta kschuheMeta = kschuhe.getItemMeta();
								        kschuheMeta.setDisplayName("§2§lZombie");
								         kschuhe.setItemMeta(kschuheMeta);
								         
										    ItemStack shop = new ItemStack(Material.BLAZE_POWDER);
											ItemMeta shopMeta = shop.getItemMeta();
											shopMeta.setDisplayName("§4§lKILLSTREAK");
											shop.setItemMeta(shopMeta);
				
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
			    
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				p.getInventory().setHelmet(khelm);
				p.getInventory().setChestplate(kbrust);
				p.getInventory().setLeggings(khose); 
				p.getInventory().setBoots(kschuhe);
				p.getInventory().setItem(0, kschwert);
				p.getInventory().setItem(1, bfleisch);
				p.getInventory().setItem(8, addGlow(shop));
				e.setCancelled(true);
				p.closeInventory();
				p.teleport(loc);
				FFA.kitauswahl.remove(p.getName());
				p.sendMessage(FFA.prefix + "§eDu hast das Kit §2§lZombie §egewählt");
				}
		}
		}
	}
