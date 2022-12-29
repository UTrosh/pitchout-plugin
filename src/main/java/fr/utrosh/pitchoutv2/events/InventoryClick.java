package fr.utrosh.pitchoutv2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
   @EventHandler
   public void onInventoryClick(InventoryClickEvent e) {
      e.setCancelled(true);
   }
}
