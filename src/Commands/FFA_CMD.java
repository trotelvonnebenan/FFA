package Commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.trotelvonnebenan.FFA.FFA;

public class FFA_CMD implements CommandExecutor {

	/*Permissions
	 * 
	 *    -FFA.admin
	 * 
	 */
	
	
	
	public static double RandomDouble(double low, double high) {
        return Math.random() * (high - low) + low;
}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("rl") || cmd.getName().equalsIgnoreCase("Reload")){
			if(p.hasPermission("FFA.Admin")){
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
					
					
					all.teleport(loc);
					all.sendMessage(FFA.prefix + "§eDer Server wurde Reloaded. Alle Spieler wurden zum Spawn teleportiert!");
					
					
					
				}
				Bukkit.reload();
				
			}
		}
		 if(cmd.getName().equalsIgnoreCase("spray")){
             
             if(args.length == 0){
                    
                     for(int i = 0; i<30;i++){
                             double x = RandomDouble(3, -3);
                             double y = RandomDouble(3, -3);
                             double z = RandomDouble(3, -3);
                            
                             FallingBlock fb = p.getWorld().spawnFallingBlock(p.getLocation(), Material.WOOL, (byte)0);
                             FallingBlock fb1 = p.getWorld().spawnFallingBlock(p.getLocation(), Material.TNT, (byte)0);
                             FallingBlock fb2 = p.getWorld().spawnFallingBlock(p.getLocation(), Material.ANVIL, (byte)0);
                             
                             fb.setDropItem(false);
                             fb1.setDropItem(false);
                             fb2.setDropItem(false);
                             Vector v = new Vector(x,y,z);
                            
                             fb.setVelocity(v);
                             fb1.setVelocity(v);
                             fb2.setVelocity(v);
                     }
                     for(int i = 0; i<30;i++){
                         double x = RandomDouble(3, -3);
                         double y = RandomDouble(3, -3);
                         double z = RandomDouble(3, -3);
                        
                         FallingBlock fb1 = p.getWorld().spawnFallingBlock(p.getLocation(), Material.TNT, (byte)0);
                         
                         fb1.setDropItem(false);
                         Vector v = new Vector(x,y,z);
                        
                         fb1.setVelocity(v);
                 }
                     for(int i = 0; i<30;i++){
                         double x = RandomDouble(3, -3);
                         double y = RandomDouble(3, -3);
                         double z = RandomDouble(3, -3);
                        
                         FallingBlock fb1 = p.getWorld().spawnFallingBlock(p.getLocation(), Material.ANVIL, (byte)0);
                         
                         fb1.setDropItem(false);
                         Vector v = new Vector(x,y,z);
                        
                         fb1.setVelocity(v);
                 }
             }
            
     }
		
		if(!p.hasPermission("FFA.Admin")){
			p.sendMessage(FFA.noperms);
		}else {
			if(args.length == 0){
				p.sendMessage(FFA.prefix + "§c/ffa help");
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
			p.sendMessage("§7<>§a========§7<§c FFA §7 >§a========§7<>");
			p.sendMessage("§e/ffa help §7 | §a Zeigt alle Befehle");
			p.sendMessage("§e/ffa setspawn §7| §a Setzt den Spawn");
			p.sendMessage("§e/ffa setarena §7| §a Setzt die Arena");
			p.sendMessage("§e/ffa spawn §7| §a Teleportiert dich zum Spawn");
			p.sendMessage("§e/ffa arena §7| §a Teleportiert dich zur Arena");
			p.sendMessage("§e/ffa getperms §7| §a Zeigt alle Permissions");
			p.sendMessage("§7<>§a========§7<§c FFA §7 >§a========§7<>");
				}else if(args[0].equalsIgnoreCase("setspawn")){
					
					FFA.spawncfg.set("Spawn.World", p.getWorld().getName());
					FFA.spawncfg.set("Spawn.X", p.getLocation().getX());
					FFA.spawncfg.set("Spawn.Y", p.getLocation().getY());
					FFA.spawncfg.set("Spawn.Z", p.getLocation().getZ());
					FFA.spawncfg.set("Spawn.Yaw", p.getLocation().getYaw());
					FFA.spawncfg.set("Spawn.Pitch", p.getLocation().getPitch());
					try {
						FFA.spawncfg.save(FFA.spawn);
						p.sendMessage(FFA.prefix + "§eDer Spawn wurde gesetzt");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}else if(args[0].equalsIgnoreCase("getPerms")){
					p.sendMessage("§7<>§a========§7<§c FFA §7 >§a========§7<>");
					p.sendMessage("§e- ffa.admin");
					p.sendMessage("§7<>§a========§7<§c FFA §7 >§a========§7<>");
				
				}else if(args[0].equalsIgnoreCase("setarena")){
					
					FFA.arenacfg.set("Arena.World", p.getWorld().getName());
					FFA.arenacfg.set("Arena.X", p.getLocation().getX());
					FFA.arenacfg.set("Arena.Y", p.getLocation().getY());
					FFA.arenacfg.set("Arena.Z", p.getLocation().getZ());
					FFA.arenacfg.set("Arena.Yaw", p.getLocation().getYaw());
					FFA.arenacfg.set("Arena.Pitch", p.getLocation().getPitch());
					try {
						FFA.arenacfg.save(FFA.arena);
						p.sendMessage(FFA.prefix + "§eDie Arena wurde gesetzt");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}else if(args[0].equalsIgnoreCase("Spawn")){
					if(p.hasPermission("ffa.admin")){
						
						World w = Bukkit.getWorld(FFA.spawncfg.getString("Spawn.World"));
						double x = FFA.spawncfg.getDouble("Spawn.X");
						double y = FFA.spawncfg.getDouble("Spawn.Y");
						double z = FFA.spawncfg.getDouble("Spawn.Z");
						double yaw	=FFA.spawncfg.getDouble("Spawn.Yaw");
						double pitch= FFA.spawncfg.getDouble("Spawn.Pitch");
						
						Location loc = new Location(w, x, y ,z);
						loc.setYaw((float) yaw);
						loc.setPitch((float) pitch);
						
						p.teleport(loc);
						
					}
					else if(args[0].equalsIgnoreCase("Arena")){
						if(p.hasPermission("ffa.admin")){
							
							World w = Bukkit.getWorld(FFA.arenacfg.getString("Arena.World"));
							double x = FFA.arenacfg.getDouble("Arena.X");
							double y = FFA.arenacfg.getDouble("Arena.Y");
							double z = FFA.arenacfg.getDouble("Arena.Z");
							double yaw	=FFA.arenacfg.getDouble("Arena.Yaw");
							double pitch= FFA.arenacfg.getDouble("Arena.Pitch");
							
							Location loc = new Location(w, x, y ,z);
							loc.setYaw((float) yaw);
							loc.setPitch((float) pitch);
							
							p.teleport(loc);
							
					
						}
				
					}else if(args[0].equalsIgnoreCase("punktegive")){
						if(p.hasPermission("ffa.Admin")){
								
							 int gpo = FFA.playerdatacfg.getInt("Tokens." + p.getName());
							 gpo += 500;
						 		FFA.playerdatacfg.set(".Tokens", gpo);
					              FFA.playerdatacfg.set("Tokens." + p.getName(), Integer.valueOf(gpo + 500));
						 		try {
									FFA.playerdatacfg.save(FFA.playerdata);
						              FFA.playerdatacfg.set("Tokens." + p.getName(), Integer.valueOf(gpo + 500));
								} catch (IOException e) {
									e.printStackTrace();
								}
						 		//FFA.setScoreboard(p);
							}
						}
						
					}
					
			}
		}
		
		return true;

	
	
	}
	}

