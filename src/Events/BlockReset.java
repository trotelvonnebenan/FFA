package Events;

import java.util.ArrayList;
/*  5:   */ import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import me.trotelvonnebenan.FFA.FFA;

 public class BlockReset
   implements Listener
 {
   public ArrayList<Location> l = new ArrayList();
   public ArrayList<Material> m = new ArrayList();
  public ArrayList<Byte> b = new ArrayList();
   public ArrayList<String> list = new ArrayList();
   int task;
   int counter = 0;
  
   @EventHandler
   public void on(EntityExplodeEvent e)   {
     e.setCancelled(true);
     
    List<Block> blocks = e.blockList();
    if (!(e.getEntity() instanceof Player))
     {
      for (int i = 0; i < blocks.size(); i++)
       {
       this.l.add(((Block)blocks.get(i)).getLocation());
         this.m.add(((Block)blocks.get(i)).getType());
         this.b.add(Byte.valueOf(((Block)blocks.get(i)).getData()));
       }
     this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FFA.plugin, new Runnable()
      {
        public void run()
         {
          if (BlockReset.this.m.size() > BlockReset.this.counter)
           {
           ((Location)BlockReset.this.l.get(BlockReset.this.counter)).getBlock().setType((Material)BlockReset.this.m.get(BlockReset.this.counter));
            ((Location)BlockReset.this.l.get(BlockReset.this.counter)).getBlock().setData(((Byte)BlockReset.this.b.get(BlockReset.this.counter)).byteValue());
            BlockReset.this.counter += 1;
         }
          else
        {
            Bukkit.getScheduler().cancelTask(BlockReset.this.task);
            BlockReset.this.counter = 0;
           }
         }
       }, 40L, 1L);
    }
  }
 }


