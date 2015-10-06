package me.trotelvonnebenan.FFA;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerDataConfig
{
  public static File file = new File("plugins/FFA", "Spieler.yml");
  public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

  public PlayerDataConfig() {
    if (!file.exists())
      reloadCfg();
  }

  public static void reloadCfg()
  {
    try {
      cfg.save(file);
      cfg.load(file);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }
}