package fr.utrosh.pitchoutv2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop implements Listener {
   @EventHandler
   public void onDrop(PlayerDropItemEvent e) {
      e.setCancelled(true);
   }
}
