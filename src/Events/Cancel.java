package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class Cancel implements Listener {
	
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
	public void on(EntityDamageEvent e){
		Player p = (Player) e.getEntity();
		if(e.getEntity() != p){
			e.setCancelled(true);
		}
		if(e.getCause() == DamageCause.FALL){
			e.setCancelled(true);
		}else if(FFA.kitauswahl.contains(p.getName())){
			
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void on(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(!p.isOp()){
		if(FFA.kitauswahl.contains(p.getName())){
			e.setCancelled(true);
		}
		}
		
	}
	
	
    @EventHandler
    public static void in (EntityChangeBlockEvent ev){
            ev.setCancelled(true);
           

           
    }
	
	/*@EventHandler
	public void on(InventoryClickEvent  e){
		
		Player p = (Player)e.getWhoClicked();
		if(FFA.kitauswahl.contains(p.getName())){
			
		if(e.getInventory().getName().equalsIgnoreCase(p.getInventory().getName())){
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Wähle dein Kit")){
				e.setCancelled(true);
			}
		}
	}
	}
*/
@EventHandler
public void on(PlayerDropItemEvent e){
	
	if(FFA.kitauswahl.contains(e.getPlayer().getName())){
		e.setCancelled(true);
	}
}

@EventHandler
public void onPlugin2(PlayerCommandPreprocessEvent event) {
  if (((event.getMessage().toLowerCase().split(" ")[0].equalsIgnoreCase("/pl")) || (event.getMessage().toLowerCase().split(" ")[0].equalsIgnoreCase("/?")) || (event.getMessage().toLowerCase().split(" ")[0].equalsIgnoreCase("/Bukkit:help")))){
    event.setCancelled(true);
    event.getPlayer().sendMessage("§fPlugins (1): §a FFA-System");
  }
}
@EventHandler
public void onPlugin(PlayerCommandPreprocessEvent event) {
	Player p = event.getPlayer();
	if(p.hasPermission("FFA.Admin")){
  if (((event.getMessage().toLowerCase().split("/reload")[0].equalsIgnoreCase("/rl")))){
    event.setCancelled(true);
    for(Player all : Bukkit.getOnlinePlayers()){
		World w = Bukkit.getWorld(FFA.spawncfg.getString("Spawn.World"));
		double x = FFA.spawncfg.getDouble("Spawn.X");
		double y = FFA.spawncfg.getDouble("Spawn.Y");
		double z = FFA.spawncfg.getDouble("Spawn.Z");
		double yaw	=FFA.spawncfg.getDouble("Spawn.Yaw");
		double pitch= FFA.spawncfg.getDouble("Spawn.Pitch");
		
		Location loc = new Location(w, x, y ,z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		
		ItemStack kits = new ItemStack(Material.BED);
		ItemMeta kitsMeta = kits.getItemMeta();
		kitsMeta.setDisplayName("§3Wähle dein Kit");
		kits.setItemMeta(kitsMeta);
		
		
		ItemStack shop = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta shopMeta = kits.getItemMeta();
		shopMeta.setDisplayName("§eShop");
		shop.setItemMeta(shopMeta);
		
		
		
		all.teleport(loc);
		all.sendMessage(FFA.prefix + "§eDer Server wurde Reloaded. Alle Spieler wurden zum Spawn teleportiert!");
		
		all.getInventory().clear();
		all.getInventory().setArmorContents(null);
		all.getInventory().setItem(4, addGlow(kits));
		all.getInventory().setItem(7, addGlow(shop));
		all.setHealth(20D);
		all.setFoodLevel(20);
		Bukkit.reload();
		FFA.kitauswahl.add(all.getName());
	}
	}
  }
    }
  }
