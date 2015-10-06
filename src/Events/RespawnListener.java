package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class RespawnListener implements Listener{
	
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
	public void on(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		
		
		ItemStack kits = new ItemStack(Material.BED);
		ItemMeta kitsMeta = kits.getItemMeta();
		kitsMeta.setDisplayName("§3Wähle dein Kit");
		kits.setItemMeta(kitsMeta);
		
		
		ItemStack shop = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta shopMeta = kits.getItemMeta();
		shopMeta.setDisplayName("§4§lKILLSTREAK");
		shop.setItemMeta(shopMeta);
		
		
		World w = Bukkit.getWorld(FFA.spawncfg.getString("Spawn.World"));
		double x = FFA.spawncfg.getDouble("Spawn.X");
		double y = FFA.spawncfg.getDouble("Spawn.Y");
		double z = FFA.spawncfg.getDouble("Spawn.Z");
		double yaw	=FFA.spawncfg.getDouble("Spawn.Yaw");
		double pitch= FFA.spawncfg.getDouble("Spawn.Pitch");
		
		Location loc = new Location(w, x, y ,z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		
		e.setRespawnLocation(loc);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(4, addGlow(kits));
		p.getInventory().setItem(7, addGlow(shop));
		p.setHealth(20D);
		p.setFoodLevel(20);
		FFA.kitauswahl.add(p.getName());
		FFA.imspiel.remove(p.getName());
		
		
	}

}
