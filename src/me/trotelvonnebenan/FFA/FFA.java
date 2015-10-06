package me.trotelvonnebenan.FFA;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.ChatClear;
import Commands.FFA_CMD;
import Commands.Kopfgeld;
import Commands.Stats;
import Events.ArcherKit;
import Events.BlockReset;
import Events.Cancel;
import Events.CreeperKit;
import Events.DeathListener;
import Events.GRANATE;
import Events.GranateListener;
import Events.JoinListener;
import Events.Kits;
import Events.QuitListener;
import Events.RAGEMODE;
import Events.RespawnListener;
import Events.SPEEDBOWI;
import Events.Shop;
import Events.Specials;
import Events.SpeedBow;
import Events.StatsListener;
import Events.WOLF;
import Events.bKriegerKit;

public class FFA extends JavaPlugin {
	
	public static File spawn = new File("plugins/FFA", "spawn.yml");
	public static FileConfiguration spawncfg = YamlConfiguration.loadConfiguration(spawn);
	
	public static File arena = new File("plugins/FFA", "arena.yml");
	public static FileConfiguration arenacfg = YamlConfiguration.loadConfiguration(arena);
	
	public static File playerdata = new File("plugins/FFA", "playerdata.yml");
	public static FileConfiguration playerdatacfg = YamlConfiguration.loadConfiguration(playerdata);
	
	public static File pstats = new File("plugins/FFA", "playerstats.yml");
    public static FileConfiguration pstatscfg = YamlConfiguration.loadConfiguration(pstats);
	
	
	public static String prefix = "§3§lFFA §8● ";
	
	
	
	public static ArrayList<String> kitauswahl = new ArrayList<String>();
	public static ArrayList<String> imspiel = new ArrayList<String>();
	public static ArrayList<String> Kit = new ArrayList<String>();
	public static ArrayList<String> KeinKit = new ArrayList<String>();
	  public static int Countdown = 1;
	  public static boolean isRunning = false;
	  private FileConfiguration ccfg = null;
	  private File ccfgf = null;
	
		public static String noPerm = "§3§lFFA §8● §4Fehler:§c Du hast keine Rechte.";
		public static String Online = "§cDieser Spieler ist zuzeit nicht Online";
		  public static String design(String text)
		  {
		    String supertext = "§7§l§m[§8§3§l§l§m-----§7[ §6§l " + text + " §7]§3§l§m-----§7§l§m]§a";
		    return supertext;
		  }
	  
	
	public static String noperms = "§c» §6FFA §c« §4Du hast keine Rechte dafür";
	
	public static FFA plugin;
	public void onEnable(){
		
		
		
		plugin = this;
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new Kits(), this);
		pm.registerEvents(new StatsListener(), this);
		pm.registerEvents(new Specials(), this);
		pm.registerEvents(new Cancel(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new GranateListener(), this);
		pm.registerEvents(new RespawnListener(), this);
		pm.registerEvents(new Shop(), this);
		pm.registerEvents(new BlockReset(), this);
		pm.registerEvents(new ArcherKit(), this);
		pm.registerEvents(new bKriegerKit(), this);
		pm.registerEvents(new SpeedBow(), this);
		pm.registerEvents(new CreeperKit(), this);
		pm.registerEvents(new GRANATE(), this);
		pm.registerEvents(new RAGEMODE(), this);
		pm.registerEvents(new SPEEDBOWI(), this);
		pm.registerEvents(new WOLF(), this);
		
		Stoplag();
	    LoadConfig();
		this.getCommand("FFa").setExecutor(new FFA_CMD());
		this.getCommand("Reload").setExecutor(new FFA_CMD());
		this.getCommand("Spray").setExecutor(new FFA_CMD());
		this.getCommand("Stats").setExecutor(new Stats());
		this.getCommand("CC").setExecutor(new ChatClear());
		//this.getCommand("Spawn").setExecutor(new Stats());
		this.getCommand("kopfgeld").setExecutor(new Kopfgeld());
		
		
		System.out.println("FFA Plugin by trotelvonnebenan");
	}
	
	public void onDisable(){
		System.out.println("FFA Plugin by trotelvonnebenan");
	}
	
	
	 public void Stoplag()
	  {
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
	      int zeit = 19200;

	      public void run() { this.zeit -= 1;
	        if (this.zeit == 18000) {
	          Bukkit.broadcastMessage(FFA.prefix + " §aIn §615 §aMinuten werden alle Items entfernt.");
	        }
	        if (this.zeit == 12000) {
	          Bukkit.broadcastMessage(FFA.prefix + " §aIn §610 §aMinuten werden alle Items entfernt.");
	        }
	        if (this.zeit == 6000) {
	          Bukkit.broadcastMessage(FFA.prefix + " §aIn §65 §aMinuten werden alle Items entfernt.");
	        }
	        if (this.zeit == 1200) {
	          Bukkit.broadcastMessage(FFA.prefix + " §aIn §61 §aMinute werden alle Items entfernt.");
	        }
	        if (this.zeit == 0) {
	          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "remove items -1");
	          Bukkit.broadcastMessage(FFA.prefix + " §aEs wurden alle Items am Boden entfernt.");
	          this.zeit = 19200;
	          this.zeit -= 1;
	        }
	      }
	    }
	    , 20L, 160L);
	  }
	
	 public void LoadConfig()
	  {
	    FileConfiguration cfg = getConfig();
	    cfg.options().copyDefaults(true);
	    saveConfig();
	  }
	 public FileConfiguration getCCC()
	  {
	    if (this.ccfgf == null) {
	      reloadCCC();
	    }
	    return this.ccfg;
	  }

	  public void reloadCCC() {
	    if (this.ccfgf == null) {
	      this.ccfgf = new File(getDataFolder(), "clans.yml");
	    }
	    this.ccfg = YamlConfiguration.loadConfiguration(this.ccfgf);

	    InputStream defConfigStream = getResource("clans.yml");
	    if (defConfigStream != null) {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      this.ccfg.setDefaults(defConfig);
	    }
	  }

	  public void saveCCC() {
	    if ((this.ccfgf == null) || (this.ccfgf == null))
	      return;
	    try
	    {
	      getCCC().save(this.ccfgf);
	    } catch (IOException ex) {
	      getLogger().log(Level.SEVERE, "Fehler beim Speichern von der Datei " + this.ccfgf, ex);
	    }
	  }
	 /* public void setScoreboard()
	  {
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	      public void run() {
	        for (Player p : Bukkit.getOnlinePlayers())
	        {
	          int online = Bukkit.getOnlinePlayers().length;
	          ScoreboardManager manager = Bukkit.getScoreboardManager();
	          Scoreboard board = manager.getNewScoreboard();

	          Objective objective = board.registerNewObjective("test", "dummy");

	          objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	          objective.setDisplayName("§c=>§3§oFluxGames§c<=");
	          int deaths = StatsListener.cfg.getInt("stats." + p.getName() + ".deaths");
	          int kills = StatsListener.cfg.getInt("stats." + p.getName() + ".kills");

	          double kd = 0.0D;
	          if (deaths != 0) {
	            kd = kills / deaths;
	            kd *= 100.0D;
	            kd /= 100.0D;
	          }
	          Score score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_RED + "§aKills:"));
	          score.setScore(kills);
	          Score score1 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "§cTode: "));
	          score1.setScore(deaths);
	          Score score2 = objective.getScore(Bukkit.getOfflinePlayer("§9KD§7/§9r: "));
	          score2.setScore((int)kd);
	          Score score3 = objective.getScore(Bukkit.getOfflinePlayer("§eOnline§7:"));
	          score3.setScore(online);
	          Score score4 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Punkte: "));
	          if (Points.cfg.contains("Points." + p.getName())) {
	            score4.setScore(Points.cfg.getInt("Points." + p.getName()));
	          } else {
	        	  Points.cfg.set("Points." + p.getName(), Integer.valueOf(0));
	            try {
	            	Points.cfg.save(Points.points);
	            } catch (IOException e) {
	              e.printStackTrace();
	              p.sendMessage(prefix + ChatColor.RED + "Du konntest nicht in der Datenbank angelegt werden!");
	            }
	          }

	          Score score5 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "§dKopfgeld: "));
	          if (Kopfgeld.cfgkopf.get(p.getName()) == null)
	            score5.setScore(0);
	          else {
	            score5.setScore(Kopfgeld.cfgkopf.getInt(p.getName()));
	          }

	          board.resetScores(p);
	          p.setScoreboard(board);
	        }
	      }
	    }
	    , 20L, 160L);
	  }     	
	
	/*@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.sendMessage("§bWillkommen bei §6FFA!");
		p.sendMessage("§bAuf dem Server läuft die Version §a1.1");
		p.sendMessage("§bViel Spaß!");
		/*Title.sendTitle(p, "§eWillkommen bei FFA.");
		Title.sendSubTitle(p, "§3Plugin by trotelvonnebenan");
		
		 int gpo = FFA.playerdatacfg.getInt("Tokens." + p.getName());
	 		FFA.playerdatacfg.set(".Tokens", gpo);
              FFA.playerdatacfg.set("Tokens." + p.getName(), Integer.valueOf(1));
	 		try {
				FFA.playerdatacfg.save(FFA.playerdata);
	              FFA.playerdatacfg.set("Tokens." + p.getName(), Integer.valueOf(1));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		this.getConfig().set("plugins/FFA", "playerdata.yml");
		this.getConfig().set("plugins/FFA", "arena.yml");
		this.getConfig().set("plugins/FFA", "spawn.yml");
		this.getConfig().set("plugins/FFA", "playerstats.yml");
		this.saveConfig();
	}	  */
	}
