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

import me.trotelvonnebenan.FFA.FFA;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class Shop implements Listener {
	
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
	public void on(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getItem().getType() != Material.BLAZE_POWDER){
				return;
			}
			if(e.getItem().getType() == Material.BLAZE_POWDER){
				if(FFA.imspiel.contains(p.getName())){
				
				ItemStack Granate = new ItemStack(Material.EGG);
				ItemMeta GranateMeta = Granate.getItemMeta();
		        List<String> GranateLore = new ArrayList();
		        GranateLore.add(" ");
		        GranateLore.add("§7Du bekommst 1 Ei.");
		        GranateLore.add("§7dieses Explodiert und fügt");
		        GranateLore.add("§7deinem Gegner Schaden zu.");
                GranateLore.add("§cPreis: §a5 §cPunkte");
                GranateMeta.setLore(GranateLore);
				GranateMeta.setDisplayName("§9§lGranate");
				Granate.setItemMeta(GranateMeta);
				
				ItemStack Beast = new ItemStack(Material.FIREBALL);
				ItemMeta BeastMeta = Beast.getItemMeta();
		        List<String> BeastLore = new ArrayList();
		        BeastLore.add(" ");
		        BeastLore.add("§7Du bekommst für 30 Sekunden");
		        BeastLore.add("§7Regeneration 3 und Stärke 2");
		        BeastLore.add("§cPreis: §a30 §cPunkte");
                BeastMeta.setLore(BeastLore);
                BeastMeta.setDisplayName("§c§lRage-Mode");
                Beast.setItemMeta(BeastMeta);
                
				ItemStack SpeedBow = new ItemStack(Material.IRON_HOE);
				ItemMeta SpeedBowMeta = SpeedBow.getItemMeta();
		        List<String> SpeedBowLore = new ArrayList();
		        SpeedBowLore.add(" ");
		        SpeedBowLore.add("§7Du bekommst eine Eisenhacke");
		        SpeedBowLore.add("§7die wie eine MG pfeile schießt.");
		        SpeedBowLore.add("§cPreis: §a15 §cPunkte");
		        SpeedBowMeta.setLore(SpeedBowLore);
		        SpeedBowMeta.setDisplayName("§8§lSpeedBow");
		        SpeedBow.setItemMeta(SpeedBowMeta);
		        
				ItemStack Wolf = new ItemStack(Material.MONSTER_EGG, 1 , (short)95);
				ItemMeta WolfMeta = SpeedBow.getItemMeta();
		        List<String> WolfLore = new ArrayList();
		        WolfLore.add(" ");
		        WolfLore.add("§7Du bekommst eine Eisenhacke");
		        WolfLore.add("§7die wie eine MG pfeile schießt.");
		        WolfLore.add("§cPreis: §a15 §cPunkte");
		        WolfMeta.setLore(WolfLore);
		        WolfMeta.setDisplayName("§a§lWolf");
		        Wolf.setItemMeta(WolfMeta);
				
				Inventory inv = Bukkit.createInventory(null, 27, "§4§lKILLSTREAK");
				
				ItemStack PLATZ = new ItemStack(Material.STAINED_GLASS_PANE,1, (short)7);
				ItemMeta PLATZMeta = PLATZ.getItemMeta();
				PLATZMeta.setDisplayName("§3");
				PLATZ.setItemMeta(PLATZMeta);
				
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
				inv.setItem(10, addGlow(Granate));
				inv.setItem(11, PLATZ);
				inv.setItem(12, addGlow(Beast));
				inv.setItem(13, PLATZ);
				inv.setItem(14, addGlow(SpeedBow));
				inv.setItem(15, PLATZ);
				inv.setItem(16, addGlow(Wolf));
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
			//    FFA.setScoreboard(p);
			    
				}else{
					e.setCancelled(true);
					p.sendMessage(FFA.prefix + "§eDu musst zuerst ein Kit auswählen");
				}
			
			}
			}
	}
}