package fr.utrosh.pitchoutv2.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.utrosh.pitchoutv2.Main;

public class PlayerQuit implements Listener {
	Main main;

	public PlayerQuit(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		int playersOnline = Bukkit.getServer().getOnlinePlayers().size() - 1;
		if (this.main.main.utils.players.contains(p)) {
			this.main.main.utils.players.remove(p);
		}

		if (this.main.main.utils.spectators.contains(p)) {
			this.main.main.utils.spectators.remove(p);
		}

		if (this.main.utils.getGameState() == 0) {
			e.setQuitMessage(this.main.utils.getPrefix() + " §e" + p.getName() + " §chas left the game §a(" + playersOnline + "/" + this.main.maxplayers + ")");
			if (this.main.utils.players.contains(p)) {
				--this.main.utils.onlinePlayers;
			}

			if (playersOnline < this.main.playersforstart && this.main.main.utils.countdown != 31) {
				Bukkit.getScheduler().cancelTask(this.main.playerJoin.task);
				this.main.main.utils.countdown = 31;
				if (this.main.language.equalsIgnoreCase("en")) {
					Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §cThe countdown has stopped because there are not enough players to start.");
				}

				if (this.main.language.equalsIgnoreCase("fr")) {
					Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §cLe décompte s'est arrêté car il n'y a pas assez de joueurs pour lancer.");
				}
			}
		}

		if (this.main.utils.getGameState() == 1) {
			if (this.main.utils.players.contains(p)) {
				this.main.utils.players.remove(p);
				p.getInventory().clear();
				if (this.main.utils.players.size() <= 1) {
					for (Player w : this.main.utils.players) {
						if (this.main.language.equalsIgnoreCase("en")) {
							Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §a§l§k!§e§l§k!§b§l§k! §a" + w.getName() + " §ewins. §6§lCongratulations ! §a§l§k!§e§l§k!§b§l§k!");
						}

						if (this.main.language.equalsIgnoreCase("fr")) {
							Bukkit.broadcastMessage(this.main.utils.getPrefix() + " §a§l§k!§e§l§k!§b§l§k! §a" + w.getName() + " §egagne. §6§lFélicitations ! §a§l§k!§e§l§k!§b§l§k!");
						}

						w.setHealthScale(20.0D);
						w.setHealth(20.0D);
						this.main.utils.setGameState(2);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("PitchOut"), () -> {
							for (Player all : Bukkit.getOnlinePlayers()) {
								if (!PlayerQuit.this.main.backhub) {
									if (PlayerQuit.this.main.language.equalsIgnoreCase("en")) {
										all.kickPlayer("§6§lThe game is over\nCongratulations to §e" + w.getName());
									}

									if (PlayerQuit.this.main.language.equalsIgnoreCase("fr")) {
										all.kickPlayer("§6§lLa partie est terminée\nFélicitations à §e" + w.getName());
									}

									Bukkit.getServer().reload();
								} else {
									PlayerQuit.this.main.sendToHub(all);
									Bukkit.getServer().reload();
								}
							}
						}, 150L);
					}
				}
			} else {
				e.setQuitMessage((String) null);
			}
		}

	}
}
