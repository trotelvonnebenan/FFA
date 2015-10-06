package Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.trotelvonnebenan.FFA.FFA;

public class Points
  implements CommandExecutor
{
  public static File points = new File("plugins/FFA/", "Points.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(points);

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (sender.hasPermission("FFA.punkte")) {
      if (args.length == 0) {
        sender.sendMessage(FFA.prefix + ChatColor.GREEN + "Du " + ChatColor.GREEN + "hast " + ChatColor.RED + cfg.getInt(new StringBuilder("Points.").append(sender.getName()).toString()) + ChatColor.GREEN + " Punkte!");
      }
      else if (args[0].equalsIgnoreCase("send")) {
        if (args.length < 3)
          sender.sendMessage(FFA.prefix + ChatColor.AQUA + " /punkte send <Spieler> <Punktezahl>");
        else if (args.length == 3) {
          try {
            Player p2 = Bukkit.getPlayer(args[1]);
            if (p2 != null) {
              Integer pointsep = Integer.valueOf(Integer.parseInt(args[2]));
              if (cfg.getInt("Points." + sender.getName()) >= pointsep.intValue()) {
                if (pointsep.intValue() <= 0) cfg.set("Points." + sender.getName(), Integer.valueOf(cfg.getInt("Points." + sender.getName()) - pointsep.intValue()));
                cfg.set("Points." + p2.getName(), Integer.valueOf(cfg.getInt("Points." + p2.getName()) + pointsep.intValue()));
                try {
                  cfg.save(points);
                } catch (IOException e) {
                  e.printStackTrace();
                }
                sender.sendMessage(FFA.prefix + ChatColor.GREEN + "Du hast " + ChatColor.RED + p2.getName() + ChatColor.GOLD + " " + pointsep + ChatColor.GREEN + " Punkte geschickt!");
                p2.sendMessage(FFA.prefix + ChatColor.RED + sender.getName() + " " + ChatColor.GREEN + "hat dir " + ChatColor.GOLD + pointsep + ChatColor.GREEN + " Punkte geschickt!");
                return true;
              }sender.sendMessage(FFA.prefix + ChatColor.RED + "Du hast nicht ausreichend Punkte");
              return true;
            }

            sender.sendMessage(FFA.Online);
          }
          catch (NumberFormatException nfe) {
            sender.sendMessage(ChatColor.RED + "Nur Zahlen!");
          }
        }
      }
      else if (cfg.contains("Points." + args[0])) {
        if (sender.hasPermission("FFA.punkte.other")) {
          int sterne = cfg.getInt("Points." + args[0]);
          sender.sendMessage(FFA.prefix + ChatColor.RED + args[0] + " " + ChatColor.GREEN + "hat " + ChatColor.GOLD + points + ChatColor.GREEN + " Punkte!");
          sender.sendMessage(FFA.prefix + ChatColor.AQUA + " /Punkte <Spieler>");
          sender.sendMessage(FFA.prefix + ChatColor.AQUA + " /Punkte send <Spieler> <Sternenanzahl>");
        } else {
          sender.sendMessage(ChatColor.RED + "Du darfst nur in deine Punktezahl einsehen");
        }
      }
    }
    else {
      sender.sendMessage(FFA.noPerm);
    }
    return true;
  }
}