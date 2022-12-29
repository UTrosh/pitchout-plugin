package fr.utrosh.pitchoutv2.events;

import fr.utrosh.pitchoutv2.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractWithItem implements Listener {
   Main main;

   public InteractWithItem(Main main) {
      this.main = main;
   }

   @EventHandler
   public void onInteract(PlayerInteractEvent e) {
      Player p = e.getPlayer();
      if (p.getItemInHand().getType() != null) {
         if (this.main.backhub) {
            if (p.getItemInHand().getItemMeta().getDisplayName() == "§6Back to hub") {
               p.sendMessage(this.main.utils.getPrefix() + " §a§oTeleportation to the hub...");
               this.main.sendToHub(p);
            }

            if (p.getItemInHand().getItemMeta().getDisplayName() == "§6Retour au hub") {
               p.sendMessage(this.main.utils.getPrefix() + " §a§oTéléportation au hub...");
               this.main.sendToHub(p);
            }
         }

      }
   }
}
