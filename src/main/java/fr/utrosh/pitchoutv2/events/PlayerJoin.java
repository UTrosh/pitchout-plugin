package fr.utrosh.pitchoutv2.events;

import java.util.Iterator;
import fr.utrosh.pitchoutv2.Main;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener {
   Main main;
   public int task;
   private int timeUntilStart;

   public PlayerJoin(Main main) {
      this.main = main;
   }

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent e) {
      Player p = e.getPlayer();
      int playersOnline = Bukkit.getServer().getOnlinePlayers().size();
      Iterator var8;
      if (this.main.utils.getGameState() == 0) {
         if (this.main.language.equalsIgnoreCase("en")) {
            e.setJoinMessage(this.main.utils.getPrefix() + " §e" + p.getName() + " §7has joined the game §a(" + playersOnline + "/" + this.main.maxplayers + ")");
         }

         if (this.main.language.equalsIgnoreCase("fr")) {
            e.setJoinMessage(this.main.utils.getPrefix() + " §e" + p.getName() + " §7a rejoint la partie §a(" + playersOnline + "/" + this.main.maxplayers + ")");
         }

         this.main.utils.players.add(p);
         p.getInventory().clear();
         p.setGameMode(GameMode.ADVENTURE);
         p.setHealthScale(20.0D);
         p.setHealth(20.0D);
         p.teleport(new Location((World)Bukkit.getWorlds().get(0), this.main.x, this.main.y, this.main.z, (float)this.main.yaw, (float)this.main.pitch));
         ++this.main.utils.onlinePlayers;
         if (this.main.backhub) {
            ItemStack hub = new ItemStack(Material.BED);
            ItemMeta hubMeta = hub.getItemMeta();
            if (this.main.language.equalsIgnoreCase("en")) {
               hubMeta.setDisplayName("§6Back to hub");
            }

            if (this.main.language.equalsIgnoreCase("fr")) {
               hubMeta.setDisplayName("§6Retour au hub");
            }

            hub.setItemMeta(hubMeta);
            p.getInventory().setItem(8, hub);
         }

         if (playersOnline == 1) {
            var8 = Bukkit.getWorlds().iterator();

            while(var8.hasNext()) {
               World worlds = (World)var8.next();
               worlds.setDifficulty(Difficulty.PEACEFUL);
            }
         }



         if (playersOnline >= this.main.playersforstart) {
            this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("pitchout"), new Runnable() {
              
               public void run() {
                  --PlayerJoin.this.main.utils.countdown;
                  if (PlayerJoin.this.main.utils.countdown == 10 || PlayerJoin.this.main.utils.countdown <= 5 || PlayerJoin.this.main.utils.countdown == 30 && PlayerJoin.this.main.utils.countdown > 1) {
                     if (PlayerJoin.this.main.language.equalsIgnoreCase("en")) {
                        Bukkit.broadcastMessage(PlayerJoin.this.main.utils.getPrefix() + " §6The game starts in §e" + PlayerJoin.this.main.utils.countdown + " seconds");
                     }

                     if (PlayerJoin.this.main.language.equalsIgnoreCase("fr")) {
                        Bukkit.broadcastMessage(PlayerJoin.this.main.utils.getPrefix() + " §6La partie commence dans §e" + PlayerJoin.this.main.utils.countdown + " secondes");
                     }

                     if (PlayerJoin.this.main.utils.countdown == 30 || PlayerJoin.this.main.utils.countdown == 10 || PlayerJoin.this.main.utils.countdown <= 5) {
                        Iterator var2 = Bukkit.getOnlinePlayers().iterator();

                        while(var2.hasNext()) {
                           Player all = (Player)var2.next();
                           all.playSound(all.getLocation(), Sound.LEVEL_UP, 1.0F, 2.0F);
                        }

                        if (PlayerJoin.this.main.language.equalsIgnoreCase("en")) {
                           PlayerJoin.this.main.utils.sendFullTitle((Player)Bukkit.getOnlinePlayers(), 5, 20, 5, "§e" + PlayerJoin.this.main.utils.countdown + " §6seconds", "");
                        }

                        if (PlayerJoin.this.main.language.equalsIgnoreCase("fr")) {
                           PlayerJoin.this.main.utils.sendFullTitle((Player)Bukkit.getOnlinePlayers(), 5, 20, 5, "§e" + PlayerJoin.this.main.utils.countdown + " §6secondes", "");
                        }
                     }
                  }

                  if (PlayerJoin.this.main.utils.countdown == 0) {
                     Bukkit.getScheduler().cancelTask(PlayerJoin.this.task);
                     if (PlayerJoin.this.main.language.equalsIgnoreCase("en")) {
                        Bukkit.broadcastMessage(PlayerJoin.this.main.utils.getPrefix() + " §aGood luck to all and may the best win !");
                     }

                     if (PlayerJoin.this.main.language.equalsIgnoreCase("fr")) {
                        Bukkit.broadcastMessage(PlayerJoin.this.main.utils.getPrefix() + " §aBonne chance à tous et que le meilleur gagne !");
                     }

                     PlayerJoin.this.main.utils.start();
                  }

               }
            }, 0L, 20L);
            if (playersOnline == this.main.maxplayers && this.main.utils.countdown > 10) {
               this.main.utils.countdown = 10;
            } 
         }
      } else {
         e.setJoinMessage((String)null);
         p.getInventory().clear();
         p.setGameMode(GameMode.SPECTATOR);
         this.main.utils.spectators.add(p);
         p.setHealthScale(20.0D);
         p.setHealth(20.0D);
         p.teleport(new Location((World)Bukkit.getWorlds().get(0), this.main.x, this.main.y, this.main.z, (float)this.main.yaw, (float)this.main.pitch));
         var8 = Bukkit.getOnlinePlayers().iterator();

         while(var8.hasNext()) {
            Player all = (Player)var8.next();
            if (this.main.utils.players.contains(all)) {
               if (all.getHealth() == 10.0D) {
                  all.setPlayerListName("§b" + all.getName());
               }

               if (all.getHealth() == 8.0D) {
                  all.setPlayerListName("§a" + all.getName());
               }

               if (all.getHealth() == 6.0D) {
                  all.setPlayerListName("§e" + all.getName());
               }

               if (all.getHealth() == 4.0D) {
                  all.setPlayerListName("§6" + all.getName());
               }

               if (all.getHealth() == 2.0D) {
                  all.setPlayerListName("§c" + all.getName());
               }
            } else {
               all.setPlayerListName("§7§o" + all.getName());
            }
         }
      }

   }
}
