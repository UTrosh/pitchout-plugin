package fr.utrosh.pitchoutv2.events;

import fr.utrosh.pitchoutv2.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Damage implements Listener {
   Main main;

   public Damage(Main main) {
      this.main = main;
   }

   @EventHandler
   public void onEntityDamage(EntityDamageByEntityEvent e) {
      Player p = (Player)e.getEntity();
      if (this.main.utils.getGameState() == 0) {
         e.setCancelled(true);
      }

      if (p.getInventory().contains(Material.REDSTONE)) {
         e.setCancelled(true);
      }

      if (e.getCause() == DamageCause.FALL) {
         e.setCancelled(true);
      }

      p.setHealth(20.0D);
   }

   @EventHandler
   public void onEntityDamage(EntityDamageEvent e) {
      Player p = (Player)e.getEntity();
      if (this.main.utils.getGameState() == 0) {
         e.setCancelled(true);
      }

      if (p.getInventory().contains(Material.REDSTONE)) {
         e.setCancelled(true);
      }

      if (e.getCause() == DamageCause.FALL) {
         e.setCancelled(true);
      }

      p.setHealth(20.0D);
   }
}
