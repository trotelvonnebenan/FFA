package Events;

import org.bukkit.event.Listener;

public class DeathListener implements Listener {
	
	
	
	
/*	@EventHandler
	public void on(PlayerDeathEvent e){
		Player p = e.getEntity();
		Player killer = e.getEntity().getKiller();
			
		
		//FFA.playerdatacfg.set("Tokens." + killer.getName(), Integer.valueOf(gpk + 2));
		FFA.playerdatacfg.set("Tokens." + killer.getName(), Integer.valueOf(gpk + 4));
	      if (gpo - 1 > 0)
	    	  FFA.playerdatacfg.set("Tokens." + p.getName(), Integer.valueOf(gpo - 1));
	      p.sendMessage(FFA.prefix + "§4-1 §ePunkte");
	     // killer.sendMessage(FFA.prefix + "§2+2§e Punkte");
	      killer.sendMessage(FFA.prefix + "§2+4§e Punkte §4[DOUBLE POINTS]");
			/*FFA.setScoreboard(p);
			FFA.setScoreboard(killer);
			FFA.imspiel.remove(p.getName());
	     try {
	    	  FFA.playerdatacfg.save(FFA.playerdata);
	      }
	      catch (IOException localIOException1)
	      {
	      }
		
		
		/*try {
			FFA.playerdatacfg.save(FFA.playerdata);
			FFA.kitauswahl.remove(p.getName());
			FFA.imspiel.remove(p.getName());
			killer.sendMessage(FFA.prefix + "§e Du hast §21 §e Punkt bekommen");
			p.sendMessage(FFA.prefix + "§eDu hast §41 §ePunkt verloren");
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		//	FFA.setScoreboard(p);
			//FFA.setScoreboard(killer);
		
	}
	
	

