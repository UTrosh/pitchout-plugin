package fr.utrosh.pitchoutv2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DestroyBlocks implements Listener {
   @EventHandler
   public void onDestroyBlock(BlockBreakEvent e) {
      e.setCancelled(true);
   }
}
