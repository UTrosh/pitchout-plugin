package fr.utrosh.pitchoutv2.events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import fr.utrosh.pitchoutv2.Main;

public class PlayerMove implements Listener {
	Main main;

	public PlayerMove(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		if (this.main.utils.getGameState() == 1) {
			if (this.main.utils.players.contains(p) && this.main.utils.getPosA().getBlockY() - 5 == p.getLocation().getBlockY()) {
				if (p.getHealthScale() == 2.0D) {
					p.setHealthScale(20.0D);
					p.setHealth(20.0D);
					this.main.utils.players.remove(p);
					this.main.utils.spectators.add(p);
					--this.main.utils.one;
					++this.main.utils.death;
					if (this.main.language.equalsIgnoreCase("en")) {
						Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §4" + p.getName() + " §7fell into the void.");
					}

					if (this.main.language.equalsIgnoreCase("fr")) {
						Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §4" + p.getName() + " §7est tombé dans le vide.");
					}

					Bukkit.getOnlinePlayers().forEach(all -> all.playSound(all.getLocation(), Sound.WITHER_SPAWN, 5.0F, 1.0F));

					p.setGameMode(GameMode.SPECTATOR);
					p.getInventory().clear();
					p.setPlayerListName("§f" + p.getName());
					p.teleport(new Location((World) Bukkit.getWorlds().get(0), this.main.x, this.main.y, this.main.z, (float) this.main.yaw, (float) this.main.pitch));
					if (this.main.utils.players.size() == 1) {
						for (Player all : this.main.utils.players) {
							if (this.main.language.equalsIgnoreCase("en")) {
								Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §a§l§k!§e§l§k!§b§l§k! §a" + p.getName() + " §ewins. §6§lCongratulations ! §a§l§k!§e§l§k!§b§l§k!");
							}

							if (this.main.language.equalsIgnoreCase("fr")) {
								Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §a§l§k!§e§l§k!§b§l§k! §a" + p.getName() + " §egagne. §6§lFélicitations ! §a§l§k!§e§l§k!§b§l§k!");
							}

							all.setHealthScale(20.0D);
							all.setHealth(20.0D);
							this.main.utils.setGameState(2);
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main.getInstance(), () -> {
								for(Player player : Bukkit.getOnlinePlayers())  {
									if (!main.backhub) {
										if (main.language.equalsIgnoreCase("en")) {
											player.kickPlayer("§6§lThe game is over\nCongratulations to §e" + p.getName());
										}

										if (main.language.equalsIgnoreCase("fr")) {
											player.kickPlayer("§6§lLa partie est terminée\nFélicitations à §e" + p.getName());
										}

										Bukkit.getServer().reload();
									} else {
										main.sendToHub(all);
										Bukkit.getServer().reload();
									}
								}
							}, 150L);
						}
					}
				} else {
					Bukkit.getOnlinePlayers().forEach(all -> all.playSound(all.getLocation(), Sound.FIREWORK_BLAST, 5.0F, 2.0F));

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
					Location loc = pos.get(ran.nextInt(pos.size()));
					p.teleport(loc);
					p.setHealthScale(p.getHealthScale() - 2.0D);
					if (p.getHealthScale() == 8.0D) {
						p.setPlayerListName("§a" + p.getName());
						--this.main.utils.five;
						++this.main.utils.four;
					}

					if (p.getHealthScale() == 6.0D) {
						p.setPlayerListName("§e" + p.getName());
						--this.main.utils.four;
						++this.main.utils.three;
					}

					if (p.getHealthScale() == 4.0D) {
						p.setPlayerListName("§6" + p.getName());
						--this.main.utils.three;
						++this.main.utils.two;
					}

					if (p.getHealthScale() == 2.0D) {
						p.setPlayerListName("§c" + p.getName());
						--this.main.utils.two;
						++this.main.utils.one;
					}

					if (this.main.language.equalsIgnoreCase("en")) {
						this.main.utils.sendActionBar(p, "§6Anti spawn kill : 3 seconds");
					}

					if (this.main.language.equalsIgnoreCase("fr")) {
						this.main.utils.sendActionBar(p, "§6Anti spawn kill : 3 secondes");
					}

					p.getInventory().setItem(4, new ItemStack(Material.REDSTONE, -3));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main.getInstance(), new Runnable() {
						public void run() {
							if (main.language.equalsIgnoreCase("en")) {
								main.utils.sendActionBar(p, "§6Anti spawn kill : 2 seconds");
							}

							if (main.language.equalsIgnoreCase("fr")) {
								main.utils.sendActionBar(p, "§6Anti spawn kill : 2 secondes");
							}

							p.getInventory().setItem(4, new ItemStack(Material.REDSTONE, -2));
						}
					}, 20L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main.getInstance(), new Runnable() {
						public void run() {
							if (main.language.equalsIgnoreCase("en")) {
								main.utils.sendActionBar(p, "§6Anti spawn kill : 1 second");
							}

							if (main.language.equalsIgnoreCase("fr")) {
								main.utils.sendActionBar(p, "§6Anti spawn kill : 1 seconde");
							}

							p.getInventory().setItem(4, new ItemStack(Material.REDSTONE, -1));
						}
					}, 40L);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main.getInstance(), new Runnable() {
						public void run() {
							main.utils.sendActionBar(p, "");
							p.getInventory().remove(Material.REDSTONE);
						}
					}, 60L);
				}
			}
		} else if (this.main.y - 5.0D == (double) p.getLocation().getBlockY() || this.main.y - 10.0D == (double) p.getLocation().getBlockY()
				|| this.main.y - 15.0D == (double) p.getLocation().getBlockY() || this.main.y - 20.0D == (double) p.getLocation().getBlockY()) {
			if (this.main.language.equalsIgnoreCase("en")) {
				p.sendMessage(this.main.utils.getPrefix() + " §cDo not go that far! :O");
			}

			if (this.main.language.equalsIgnoreCase("fr")) {
				p.sendMessage(this.main.utils.getPrefix() + " §cNe pars pas si loin! :O");
			}

			p.teleport(new Location((World) Bukkit.getWorlds().get(0), this.main.x, this.main.y, this.main.z, (float) this.main.yaw, (float) this.main.pitch));
		}

	}
}
