package Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.trotelvonnebenan.FFA.FFA;

public class QuitListener implements Listener {
	
	
	@EventHandler
	public void on(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission("FFA.TEAM") || p.isOp()){
			e.setQuitMessage("§7[§6Team§7] §c" + p.getName() + "§e hat FFA verlassen");
			FFA.imspiel.remove(p.getName());
			FFA.kitauswahl.remove(p.getName());
		}else{
			e.setQuitMessage(FFA.prefix +"§c" + p.getName() +  " §ehat FFA verlassen");
			FFA.imspiel.remove(p.getName());
			FFA.kitauswahl.remove(p.getName());
		}
	}

}
