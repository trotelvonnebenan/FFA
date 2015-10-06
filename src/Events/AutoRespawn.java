package Events;

import org.bukkit.event.Listener;

public class AutoRespawn implements Listener {
	
/*	
	public static ItemStack addGlow(ItemStack item){ 
		  net.minecraft.server.v1_7_R4.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
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
	    public void onPlayerDeath(PlayerDeathEvent e){
	        Player p = e.getEntity();
	        
			ItemStack kits = new ItemStack(Material.BED);
			ItemMeta kitsMeta = kits.getItemMeta();
			kitsMeta.setDisplayName("§3Wähle dein Kit");
			kits.setItemMeta(kitsMeta);
			
			
			ItemStack shop = new ItemStack(Material.BLAZE_POWDER);
			ItemMeta shopMeta = kits.getItemMeta();
			shopMeta.setDisplayName("§4§lKILLSTREAK");
			shop.setItemMeta(shopMeta);
			
	        
	        PacketPlayInClientCommand in = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN); // Gets the packet class
	        EntityPlayer cPlayer = ((CraftPlayer)p).getHandle(); // Gets the EntityPlayer class
	        cPlayer.playerConnection.a(in); // Handles the rest of it
	        FFA.kitauswahl.add(p.getName());
	        e.getDrops().clear();
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.getInventory().setItem(4, addGlow(kits));
			p.getInventory().setItem(7, addGlow(shop));
			p.setHealth(20D);
			p.setFoodLevel(20);
	    }
	*/

}
