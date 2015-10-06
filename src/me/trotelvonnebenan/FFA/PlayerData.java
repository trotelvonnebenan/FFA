package me.trotelvonnebenan.FFA;

import java.text.DecimalFormat;

public class PlayerData
{
  String name;

  public PlayerData(String player)
  {
    this.name = player.toLowerCase();
  }

  public void setName(String s) {
    PlayerDataConfig.cfg.set(this.name + ".name", s);
    PlayerDataConfig.reloadCfg();
  }

  public String getName() {
    String s = this.name;
    if (PlayerDataConfig.cfg.getString(this.name + ".name") != null) {
      s = PlayerDataConfig.cfg.getString(this.name + ".name");
    }
    return s;
  }

  public String getCC() {
    return PlayerDataConfig.cfg.getString(this.name + ".cc");
  }

  public void setCC(String s) {
    PlayerDataConfig.cfg.set(this.name + ".cc", s);
    PlayerDataConfig.reloadCfg();
  }

  public int getKills()
  {
    return PlayerDataConfig.cfg.getInt(this.name + ".kills");
  }

  public int getKs() {
    return PlayerDataConfig.cfg.getInt(this.name + ".ks");
  }

  public void addKs() {
    PlayerDataConfig.cfg.set(this.name + ".ks", Integer.valueOf(getKs() + 1));
    PlayerDataConfig.reloadCfg();
  }

  public void removeKs() {
    PlayerDataConfig.cfg.set(this.name + ".ks", Integer.valueOf(0));
    PlayerDataConfig.reloadCfg();
  }

  public int getDeaths() {
    return PlayerDataConfig.cfg.getInt(this.name + ".deaths");
  }

  public void setKills(int ki) {
    PlayerDataConfig.cfg.set(this.name + ".kills", Integer.valueOf(ki));
    PlayerDataConfig.reloadCfg();
  }

  public void setDeaths(int de) {
    PlayerDataConfig.cfg.set(this.name + ".deaths", Integer.valueOf(de));
    PlayerDataConfig.reloadCfg();
  }

  public void addKills(int ki) {
    PlayerDataConfig.cfg.set(this.name + ".kills", Integer.valueOf(getKills() + ki));
    PlayerDataConfig.reloadCfg();
  }

  public void addDeaths(int de) {
    PlayerDataConfig.cfg.set(this.name + ".deaths", Integer.valueOf(getDeaths() + de));
    PlayerDataConfig.reloadCfg();
  }
  public double getKDR() {
    double kdr = 0.0D;
    int ki = getKills();
    int de = getDeaths();
    if (de == 0) {
      de = 1;
    }
    kdr = Double.parseDouble(Integer.toString(ki)) / Double.parseDouble(Integer.toString(de));
    DecimalFormat df = new DecimalFormat("0.000");
    String s = df.format(kdr).replace(",", ".");
    if (s.endsWith("0")) {
      s = s.substring(0, s.length() - 1);
    }
    if (s.endsWith("0")) {
      s = s.substring(0, s.length() - 1);
    }
    if (s.endsWith("0")) {
      s = s.substring(0, s.length() - 1);
    }
    if (s.endsWith(",")) {
      s = s.substring(0, s.length() - 1);
    }
    kdr = Double.parseDouble(s);
    return kdr;
  }

  public String getClan()
  {
    return PlayerDataConfig.cfg.getString(this.name + ".clan");
  }

  public void setClan(String s) {
    PlayerDataConfig.cfg.set(this.name + ".clan", s);
    PlayerDataConfig.reloadCfg();
  }
}