package Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.trotelvonnebenan.FFA.FFA;

public class Kopfgeld
  implements CommandExecutor, Listener
{
  public static File filekopf = new File("plugins/FFA/", "kopfgeld.yml");
  public static FileConfiguration cfgkopf = YamlConfiguration.loadConfiguration(filekopf);

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    try
    {
      Points.cfg.load(Points.points);
    } catch (FileNotFoundException e3) {
      e3.printStackTrace();
    } catch (IOException e3) {
      e3.printStackTrace();
    } catch (InvalidConfigurationException e3) {
      e3.printStackTrace();
    }

    if (cmd.getName().equalsIgnoreCase("kopfgeld"))
    {
      if (args.length != 2)
      {
        sender.sendMessage(FFA.prefix + "§cVerwendung: §b/kopfgeld <Spieler> <Anzahl>");

        return true;
      }
      Player p = (Player)sender;
      try
      {
        Player ziel = Bukkit.getPlayer(args[0]);
        try {
          int i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e2) {
          sender.sendMessage(FFA.prefix + "§aGib eine Zahl an");
          return true;
        }
        boolean genug = false;

        if ((Points.cfg.getInt("Points." + sender.getName()) >= Integer.parseInt(args[1])) || 
          (Points.cfg.get("Points." + sender.getName()) == null)) {
          genug = true;
        }

        if (!genug) { sender.sendMessage(FFA.prefix + "§4Du hast nicht ausreichend Punkte");
          return true;
        }
        if (args[0].equalsIgnoreCase(sender.getName())) { sender.sendMessage(FFA.prefix + "§4Du kannst kein Kopfgeld auf dich selbst setzen!");
          return true;
        }
        if (Integer.parseInt(args[1]) <= 0) { sender.sendMessage(FFA.prefix + "§4DU musst mindestens 1 Punkt setzten!");
          return true;
        }
        if ((cfgkopf.getInt(ziel.getName()) == 0) || (cfgkopf.get(ziel.getName()) == null))
          cfgkopf.set(ziel.getName(), Integer.valueOf(Integer.parseInt(args[1])));
        else {
          cfgkopf.set(ziel.getName(), Integer.valueOf(Integer.parseInt(args[1]) + cfgkopf.getInt(ziel.getName())));
        }

        Points.cfg.set("Points." + sender.getName(), Integer.valueOf(Points.cfg.getInt("Points." + sender.getName()) - Integer.parseInt(args[1])));
        try {
          cfgkopf.save(filekopf); } catch (IOException e) { e.printStackTrace(); } try {
        	  Points.cfg.save(Points.points); } catch (IOException e) { e.printStackTrace(); }
        if (Integer.parseInt(args[1]) == 1) {
          Bukkit.broadcastMessage(FFA.design("Kopfgeld"));
          Bukkit.broadcastMessage(FFA.prefix + "§c" + p.getName() + "§a hat Kopfgeld von " + args[1] + " Punkt");
          Bukkit.broadcastMessage(FFA.prefix + "§aauf §c" + ziel.getName() + " §agesetzt");
          Bukkit.broadcastMessage(FFA.design("Kopfgeld"));
          return true;
        }Bukkit.broadcastMessage(FFA.design("Kopfgeld"));
        Bukkit.broadcastMessage(FFA.prefix + "§c" + p.getName() + "§a hat Kopfgeld von " + args[1] + " Punkte");
        Bukkit.broadcastMessage(FFA.prefix + "§aauf §c" + ziel.getName() + " §agesetzt");
        Bukkit.broadcastMessage(FFA.design("Kopfgeld"));
      }
      catch (NullPointerException e1) {
        sender.sendMessage(FFA.prefix + "§aDieser Spieler ist nicht Online");
      }
    }
    else if (cmd.getName().equalsIgnoreCase("kgstatus")) {
      try {
        cfgkopf.load(filekopf);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InvalidConfigurationException e) {
        e.printStackTrace();
      }
      if (cfgkopf.get(sender.getName()) != null) {
        int kopfgeld = Integer.parseInt(cfgkopf.getString(sender.getName()));

        sender.sendMessage(FFA.prefix + "§aAuf dir liegen " + kopfgeld + " Sterne Kopfgeld");
      }
      else {
        sender.sendMessage(FFA.prefix + "§aAuf dir liegt kein Kopfgeld");
      }

    }

    return false;
  }
}