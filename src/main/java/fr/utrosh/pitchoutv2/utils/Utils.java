package fr.utrosh.pitchoutv2.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.utrosh.pitchoutv2.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Utils {
   Main main;
   public String prefix;
   private int gameState;
   public int countdown = 31;
   public ArrayList<Player> players = new ArrayList<>();
   public ArrayList<Player> spectators = new ArrayList<>();
   public int onlinePlayers;
   public int five;
   public int four;
   public int three;
   public int two;
   public int one;
   public int death;
   public double ax;
   public double ay;
   public double az;
   public double bx;
   public double by;
   public double bz;
   public double cx;
   public double cy;
   public double cz;
   public double dx;
   public double dy;
   public double dz;
   public double ex;
   public double ey;
   public double ez;
   public double fx;
   public double fy;
   public double fz;
   public double gx;
   public double gy;
   public double gz;
   public double hx;
   public double hy;
   public double hz;
   public double ix;
   public double iy;
   public double iz;
   public double jx;
   public double jy;
   public double jz;

   public Utils(Main main) {
      this.main = main;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public int getGameState() {
      return this.gameState;
   }

   public void setGameState(int nombre) {
      this.gameState = nombre;
   }

   public void start() {
      for(Player all : Bukkit.getOnlinePlayers()) {
         all.setLevel(0);
         all.setExp(0.0F);
         all.getInventory().clear();
         ArrayList<Location> pos = new ArrayList<>();
         pos.add(this.main.utils.getPosA());
         pos.add(this.main.utils.getPosB());
         pos.add(this.main.utils.getPosC());
         pos.add(this.main.utils.getPosD());
         pos.add(this.main.utils.getPosE());
         pos.add(this.main.utils.getPosF());
         pos.add(this.main.utils.getPosG());
         pos.add(this.main.utils.getPosH());
         pos.add(this.main.utils.getPosI());
         pos.add(this.main.utils.getPosJ());
         Random ran = new Random();
         Location loc = (Location)pos.get(ran.nextInt(pos.size()));
         all.teleport(loc);
         pos.remove(loc);
         this.gameState = 1;
         ItemStack arc = new ItemStack(Material.BOW);
         arc.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 4);
         arc.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
         arc.addEnchantment(Enchantment.ARROW_INFINITE, 1);
         all.getInventory().setItem(0, arc);
         ItemStack pelle = new ItemStack(Material.WOOD_SPADE);
         pelle.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
         pelle.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
         all.getInventory().setItem(1, pelle);
         ItemStack fleche = new ItemStack(Material.ARROW);
         all.getInventory().setItem(9, fleche);
         all.setHealthScale(10.0D);
         all.setHealth(20.0D);
         all.setPlayerListName("§b" + all.getName());
         this.five = this.players.size();
         all.getInventory().setItem(4, new ItemStack(Material.REDSTONE, -3));
         Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("PitchOut"), () -> {
           if (Utils.this.main.language.equalsIgnoreCase("en")) {
              Utils.this.main.utils.sendActionBar(all, "§6Anti spawn kill : 2 seconds");
           }

           if (Utils.this.main.language.equalsIgnoreCase("fr")) {
              Utils.this.main.utils.sendActionBar(all, "§6Anti spawn kill : 2 secondes");
           }

           all.getInventory().setItem(4, new ItemStack(Material.REDSTONE, -2));
         }, 20L);
         Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("PitchOut"), new Runnable() {
            public void run() {
               if (Utils.this.main.language.equalsIgnoreCase("en")) {
                  Utils.this.main.utils.sendActionBar(all, "§6Anti spawn kill : 1 second");
               }

               if (Utils.this.main.language.equalsIgnoreCase("fr")) {
                  Utils.this.main.utils.sendActionBar(all, "§6Anti spawn kill : 1 seconde");
               }

               all.getInventory().setItem(4, new ItemStack(Material.REDSTONE, -1));
            }
         }, 40L);
         Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("PitchOut"), new Runnable() {
            public void run() {
               Utils.this.main.utils.sendActionBar(all, "");
               all.getInventory().remove(Material.REDSTONE);
            }
         }, 60L);
      }

   }

   public Location getPosA() {
      return new Location(Bukkit.getWorlds().get(0), this.ax, this.ay, this.az);
   }

   public Location getPosB() {
      return new Location(Bukkit.getWorlds().get(0), this.bx, this.by, this.bz);
   }

   public Location getPosC() {
      return new Location((World)Bukkit.getWorlds().get(0), this.cx, this.cy, this.cz);
   }

   public Location getPosD() {
      return new Location((World)Bukkit.getWorlds().get(0), this.dx, this.dy, this.dz);
   }

   public Location getPosE() {
      return new Location((World)Bukkit.getWorlds().get(0), this.ex, this.ey, this.ez);
   }

   public Location getPosF() {
      return new Location((World)Bukkit.getWorlds().get(0), this.fx, this.fy, this.fz);
   }

   public Location getPosG() {
      return new Location((World)Bukkit.getWorlds().get(0), this.gx, this.gy, this.gz);
   }

   public Location getPosH() {
      return new Location((World)Bukkit.getWorlds().get(0), this.hx, this.hy, this.hz);
   }

   public Location getPosI() {
      return new Location((World)Bukkit.getWorlds().get(0), this.ix, this.iy, this.iz);
   }

   public Location getPosJ() {
      return new Location((World)Bukkit.getWorlds().get(0), this.jx, this.jy, this.jz);
   }

   public void sendActionBar(Player player, String message) {
      CraftPlayer p = (CraftPlayer)player;
      IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
      PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
      p.getHandle().playerConnection.sendPacket(ppoc);
   }
}
