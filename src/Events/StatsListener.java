package Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import Commands.Points;

public class StatsListener
  implements Listener
{
  public static File stats = new File("plugins/FFA/", "Stats.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(stats);
  private Main plugin;

  public boolean contains(String o) {
    return cfg.getString("spieler").indexOf(o) != -1;
  }
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
    event.setDeathMessage(null);
    try
    {
      cfg.load(stats);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    }try {
        Points.cfg.load(Points.points);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InvalidConfigurationException e) {
        e.printStackTrace();
      }


    Player p = event.getEntity();
    Player k = event.getEntity().getKiller();
    
    if (((p instanceof Player)) && ((k instanceof Player)))
    {
      int killer = cfg.getInt("stats." + k.getName() + ".kills") + 1;
      int opfer = cfg.getInt("stats." + p.getName() + ".deaths") + 1;

      if (cfg.get("stats." + p.getName() + ".deaths") == null) {
        cfg.addDefault("stats." + p.getName() + ".deaths", Integer.valueOf(0));
      }
      if (cfg.get("stats." + k.getName() + ".kills") == null) {
        cfg.addDefault("stats." + k.getName() + ".kills", Integer.valueOf(0));
      }

      cfg.set("stats." + p.getName() + ".deaths", Integer.valueOf(opfer));
      cfg.set("stats." + k.getName() + ".kills", Integer.valueOf(killer));


      int gpo = Points.cfg.getInt("Points." + p.getName());
      int gpk = Points.cfg.getInt("Points." + k.getName());

      Points.cfg.set("Points." + k.getName(), Integer.valueOf(gpk + 2));
      if (gpo - 1 > 0)
    	  Points.cfg.set("Points." + p.getName(), Integer.valueOf(gpo - 1));
      try
      {
        cfg.save(stats);
      } catch (IOException e1) {
        e1.printStackTrace();
      }try {
    	  Points.cfg.save(Points.points);
      }
      catch (IOException localIOException1)
      {
      }
    }
  }
}