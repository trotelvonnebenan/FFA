package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class JoinListener implements Listener {
	
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
	public void on(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission("FFA.TEAM") || p.isOp()){
			e.setJoinMessage("�7[�6Team�7] �a" + p.getName() + "�e ist Beigetreten");
		}else{
			e.setJoinMessage(FFA.prefix +"�2" + p.getName() +  " �eist FFA beigetreten");
		}
		p.sendMessage("�eWillkommen bei �6FFA!");
		p.sendMessage("�eAuf dem Server l�uft die Version �a1.1");
		p.sendMessage("�eViel Spa�!");
	
		//BarAPI.setMessage(p, "�4�lBis Mittwoch um 18:00 Uhr DOUBLE POINTS");
		
		ItemStack kits = new ItemStack(Material.BED);
		ItemMeta kitsMeta = kits.getItemMeta();
		kitsMeta.setDisplayName("�3W�hle dein Kit");
		kits.setItemMeta(kitsMeta);
		
		
		ItemStack shop = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta shopMeta = kits.getItemMeta();
		shopMeta.setDisplayName("�4�lKILLSTREAK");
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
		
		FFA.kitauswahl.add(p.getName());
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(4, addGlow(kits));
		p.getInventory().setItem(7, addGlow(shop));
		p.setHealth(20D);
		p.setFoodLevel(20);
		p.teleport(loc);
		//FFA.setScoreboard(p);
		FFA.imspiel.remove(p.getName());	
		
	}

}
