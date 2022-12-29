package fr.utrosh.pitchoutv2.events;

import java.util.Iterator;
import fr.utrosh.pitchoutv2.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
   Main main;

   public Chat(Main main) {
      this.main = main;
   }

   @EventHandler
   public void chat(AsyncPlayerChatEvent e) {
      Player p = e.getPlayer();
      String msg = e.getMessage();
      if (this.main.utils.players.contains(p)) {
         if (p.getHealthScale() == 20.0D) {
            e.setFormat(p.getName() + " §7» §f" + msg);
         }

         if (p.getHealthScale() == 10.0D) {
            e.setFormat("§b" + p.getName() + " §7» §f" + msg);
         }

         if (p.getHealthScale() == 8.0D) {
            e.setFormat("§a" + p.getName() + " §7» §f" + msg);
         }

         if (p.getHealthScale() == 6.0D) {
            e.setFormat("§e" + p.getName() + " §7» §f" + msg);
         }

         if (p.getHealthScale() == 4.0D) {
            e.setFormat("§6" + p.getName() + " §7» §f" + msg);
         }

         if (p.getHealthScale() == 2.0D) {
            e.setFormat("§c" + p.getName() + " §7» §f" + msg);
         }
      } else if (!this.main.spectatorChat) {
         e.setFormat("§7§o" + p.getName() + " §7» §f" + msg);
      } else {
         Iterator var5 = this.main.utils.spectators.iterator();

         while(var5.hasNext()) {
            Player specs = (Player)var5.next();
            e.setCancelled(true);
            specs.sendMessage("[SPEC] §7§o" + p.getName() + " §7» §f" + msg);
         }
      }

   }
}
