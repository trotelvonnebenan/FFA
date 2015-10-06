package Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.trotelvonnebenan.FFA.FFA;
import me.trotelvonnebenan.FFA.PlayerData;
import me.trotelvonnebenan.FFA.PlayerDataConfig;

public class Stats
  implements CommandExecutor, Listener
{
  Main plugin;
  public static Comparator<Object> comp = new Comparator()
  {
    public int compare(Object s1, Object s2)
    {
      String[] sp1 = ((String)s1).split(":");
      String[] sp2 = ((String)s2).split(":");
      double i1 = Double.parseDouble(sp1[3]);
      double i2 = Double.parseDouble(sp2[3]);
      if (i1 > i2) {
        return -1;
      }
      if (i1 < i2) {
        return 1;
      }
      return 0;
    }
  };


  public List<String> getTopList()
  {
    List ls = new ArrayList();
    for (String s : PlayerDataConfig.cfg.getKeys(false)) {
      PlayerData pd = new PlayerData(s);
      String s1 = pd.getName() + ":" + pd.getKDR() + ":" + pd.getKDR() + ":" + pd.getKDR();
      ls.add(s1);
    }
    Collections.sort(ls, comp);
    return ls;
  }

  public String getTopPlace(String s) {
    int i = 0;
    String stri = "";
    boolean b = false;
    for (String str : getTopList()) {
      String[] st = str.split(":");
      if (st[0].equalsIgnoreCase(s)) {
        b = true;
      }
      if (!b) {
        i++;
      }
    }
    if (!b) {
      stri = "§cNewbie!";
    }
    else {
      stri = stri + (i + 1);
    }
    return stri;
  }

  public List<String> getTopDeaths() {
    List ls = new ArrayList();
    for (String s : PlayerDataConfig.cfg.getKeys(false)) {
      PlayerData pd = new PlayerData(s);
      String s1 = pd.getName() + ":" + pd.getDeaths() + ":" + pd.getDeaths() + ":" + pd.getDeaths();
      ls.add(s1);
    }
    Collections.sort(ls, comp);
    return ls;
  }

  public String getTopPlaceDeaths(String s) {
    int i = 0;
    String stri = "";
    boolean b = false;
    for (String str : getTopDeaths()) {
      String[] st = str.split(":");
      if (st[0].equalsIgnoreCase(s)) {
        b = true;
      }
      if (!b) {
        i++;
      }
    }
    if (!b) {
      stri = "§70";
    }
    else {
      stri = stri + (i + 1);
    }
    return stri;
  }

  public List<String> getTopKills()
  {
    List ls = new ArrayList();
    for (String s : PlayerDataConfig.cfg.getKeys(false)) {
      PlayerData pd = new PlayerData(s);
      String s1 = pd.getName() + ":" + pd.getKills() + ":" + pd.getKills() + ":" + pd.getKills();
      ls.add(s1);
    }
    Collections.sort(ls, comp);
    return ls;
  }

  public String getTopPlaceKills(String s) {
    int i = 0;
    String stri = "";
    boolean b = false;
    for (String str : getTopKills()) {
      String[] st = str.split(":");
      if (st[0].equalsIgnoreCase(s)) {
        b = true;
      }
      if (!b) {
        i++;
      }
    }
    if (!b) {
      stri = "§70";
    }
    else {
      stri = stri + (i + 1);
    }
    return stri;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
  {
    if ((sender instanceof Player)) {
      Player p = (Player)sender;
      int gpo = Points.cfg.getInt("Points." + p.getName());
      if (cmd.getName().equalsIgnoreCase("stats")) {
        if (args.length == 0) {
          PlayerData pd = new PlayerData(p.getName());
          p.sendMessage(FFA.design(p.getName()));
          p.sendMessage("§cKills§c: §7" + pd.getKills());
          p.sendMessage("§4Tode§c: §7" + pd.getDeaths());
          p.sendMessage("§aKillreihe§c: §7" + pd.getKs());
          p.sendMessage("§9KD§7/§9r§c: §3" + pd.getKDR());
          p.sendMessage("§dPunkte:§c " + gpo);
          if (pd.getClan() != null)
            p.sendMessage("§aClan: §c" + pd.getClan());
          else {
            p.sendMessage("§aClan: §c/");
          }
          p.sendMessage("§eDein Platz im Ranking §9KD§7/§9r§c: §8{§e#§c" + getTopPlace(pd.getName()) + "§8}");
          p.sendMessage("§eDein Platz im Ranking §cKills§c: §8{§e#§c" + getTopPlaceKills(pd.getName()) + "§8}");
          p.sendMessage("§eDein Platz im Ranking §4Tode§c: §8{§e#§c" + getTopPlaceDeaths(pd.getName()) + "§8}");
          p.sendMessage(FFA.design(p.getName()));
        } else if (args.length == 1) {
          PlayerData pd = new PlayerData(args[0]);
          p.sendMessage(FFA.design(args[0]));
          p.sendMessage("§cKills§c: §7" + pd.getKills());
          p.sendMessage("§4Tode§c: §7" + pd.getDeaths());
          p.sendMessage("§aKillreihe§c: §7" + pd.getKs());
          p.sendMessage("§9KD§7/§9r§c: §3" + pd.getKDR());
          if (pd.getClan() != null)
            p.sendMessage("§aClan: §c" + pd.getClan());
          else {
            p.sendMessage("§aClan: §c/");
          }
          p.sendMessage("§eSein Platz im Ranking §9KD§7/§9r§c: §8{§e#§c" + getTopPlace(pd.getName()) + "§8}");
          p.sendMessage("§eSein Platz im Ranking §cKills§c: §8{§e#§c" + getTopPlaceKills(pd.getName()) + "§8}");
          p.sendMessage("§eSein Platz im Ranking §4Tode§c: §8{§e#§c" + getTopPlaceDeaths(pd.getName()) + "§8}");
          p.sendMessage(FFA.design(args[0]));
        }
        else if ((!args[0].equalsIgnoreCase("reset")) && (!args[0].equalsIgnoreCase("set")))
        {
          p.sendMessage(FFA.prefix + "§4ERROR");
          return true;
        }

        if (args.length == 2)
        {
          if (args[0].equalsIgnoreCase("reset"))
          {
            if (p.hasPermission("BTS.stats.reset")) {
              p.sendMessage("§cVerwendung §c/stats reset <Spieler> <kills|deaths>");
            } else {
              p.sendMessage(FFA.noPerm);
              return true;
            }
          }
          else if (args[0].equalsIgnoreCase("set")) {
            if (p.hasPermission("BTS.stats.set")) {
              p.sendMessage("§cVerwendung §b/stats set <Spieler> <kills|deaths>");
            } else {
              p.sendMessage(FFA.noPerm);
              return true;
            }

          }

        }
        else if (args.length == 3)
        {
          if (args[0].equalsIgnoreCase("reset"))
          {
            if (p.hasPermission("BTS.stats.reset"))
            {
              PlayerData pd = new PlayerData(args[1]);
              Player localPlayer1;
              if (args[2].equalsIgnoreCase("kills"))
              {
                pd.setKills(0);
                p.sendMessage("§eDu hast die Kills von §a" + pd.getName() + "§e zurück gesetzt.");

                localPlayer1 = ((CommandSender) this.plugin).getServer().getPlayer(pd.getName());
              }
              else
              {
                Player localPlayer11;
                if (args[2].equalsIgnoreCase("deaths"))
                {
                  pd.setDeaths(0);
                  p.sendMessage("§eDu hast die Deaths von §a" + pd.getName() + "§e zurück gesetzt.");

                  localPlayer11 = ((CommandSender) this.plugin).getServer().getPlayer(pd.getName());
                }
                else
                {
                  p.sendMessage("§cVerwendung: §b/stats reset <Spieler> <kills|deaths>");
                }
              }
            }
            else {
              p.sendMessage(FFA.noPerm);
            }
          }
          else if (args[0].equalsIgnoreCase("set")) {
            if (p.hasPermission("TMGS.stats.set"))
              p.sendMessage("§cVerwendung: §b/stats set <Spieler> <kills|deaths>");
            else {
              p.sendMessage(FFA.noPerm);
            }
          }
        }
        else if ((args.length == 4) && 
          (args[0].equalsIgnoreCase("set")))
        {
          if (p.hasPermission("BTS.stats.set"))
            try
            {
              PlayerData pd = new PlayerData(args[1]);
              if (args[2].equalsIgnoreCase("kills"))
              {
                pd.setKills(Integer.parseInt(args[3]));
                p.sendMessage("§eDu hast die Kills von §a" + pd.getName() + "§e auf §a" + args[3] + "§e gesetzt.");

                Player localPlayer1 = ((CommandSender) this.plugin).getServer().getPlayer(pd.getName());

                return true;
              }if (args[2].equalsIgnoreCase("deaths"))
              {
                pd.setDeaths(Integer.parseInt(args[3]));
                p.sendMessage("§eDu hast die Deaths von §a" + pd.getName() + "§e auf §a" + args[3] + "§e gesetzt.");

                Player localPlayer1 = ((CommandSender) this.plugin).getServer().getPlayer(pd.getName());

                return true;
              }

              p.sendMessage("§cVerwendung: §b/stats set <Spieler> <kills|deaths>");
            }
            catch (NumberFormatException e)
            {
              p.sendMessage("§cFehler: Das ist keine Zahl!");
            }
          else {
            p.sendMessage(FFA.noPerm);
          }
        }
      }
    }

    return true;
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent e) {
    if (((e.getEntity().getKiller() instanceof Player)) && ((e.getEntity().getPlayer() instanceof Player))) {
      Player p = e.getEntity().getPlayer();
      Player k = e.getEntity().getKiller();
      PlayerData pd = new PlayerData(p.getName());
      PlayerData pdk = new PlayerData(k.getName());
      pd.addDeaths(1);
      pdk.addKills(1);
      pdk.addKs();
      pd.removeKs();
    }
  }
}