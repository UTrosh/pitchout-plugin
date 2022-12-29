package fr.utrosh.pitchoutv2;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.utrosh.pitchoutv2.events.Chat;
import fr.utrosh.pitchoutv2.events.Damage;
import fr.utrosh.pitchoutv2.events.DestroyBlocks;
import fr.utrosh.pitchoutv2.events.Drop;
import fr.utrosh.pitchoutv2.events.EntitySpawn;
import fr.utrosh.pitchoutv2.events.InteractWithItem;
import fr.utrosh.pitchoutv2.events.InventoryClick;
import fr.utrosh.pitchoutv2.events.PlayerJoin;
import fr.utrosh.pitchoutv2.events.PlayerMove;
import fr.utrosh.pitchoutv2.events.PlayerQuit;
import fr.utrosh.pitchoutv2.utils.Utils;

public class Main extends JavaPlugin {

	public Main main;
	public PlayerJoin playerJoin;
	public Utils utils;
	FileConfiguration config = this.getConfig();
	public double x;
	public double y;
	public double z;
	public int yaw;
	public int pitch;
	public int maxplayers;
	public int playersforstart;
	public String bungeecordhub;
	public String language;
	public boolean backhub;
	public boolean spectatorChat;
	public String setPrefix;

	public Main getInstance() {
		return this.main;
	}

	public void onEnable() {
		this.playerJoin = new PlayerJoin(this);
		this.utils = new Utils(this);
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getPluginManager().registerEvents(this.playerJoin, this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Damage(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Drop(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DestroyBlocks(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InteractWithItem(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EntitySpawn(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Chat(this), this);
		this.config.options().copyDefaults(true);
		this.config.addDefault("pitchout.maxplayers", 10);
		this.config.addDefault("pitchout.playersforstart", 6);
		this.config.addDefault("pitchout.backhub", true);
		this.config.addDefault("pitchout.bungeecordhub", "hub01");
		this.config.addDefault("pitchout.prefix", "&7[&cPitchOut&7]");
		this.config.addDefault("pitchout.spectatorChat", true);
		this.config.addDefault("pitchout.language", "EN");
		this.saveConfig();
		this.x = this.config.getDouble("pitchout.lobby.x");
		this.y = this.config.getDouble("pitchout.lobby.y");
		this.z = this.config.getDouble("pitchout.lobby.z");
		this.yaw = this.config.getInt("pitchout.lobby.yaw");
		this.pitch = this.config.getInt("pitchout.lobby.pitch");
		this.maxplayers = this.config.getInt("pitchout.maxplayers");
		this.playersforstart = this.config.getInt("pitchout.playersforstart");
		this.backhub = this.config.getBoolean("pitchout.backhub");
		this.bungeecordhub = this.config.getString("pitchout.bungeecordhub");
		this.spectatorChat = this.config.getBoolean("pitchout.spectatorChat");
		this.language = this.config.getString("pitchout.language");
		this.setPrefix = this.config.getString("pitchout.prefix");
		this.setPrefix = this.setPrefix.replace("&", "§");
		this.pos();
		this.utils.setGameState(0);
		this.utils.prefix = this.setPrefix;
		this.utils.onlinePlayers = 0;
		this.utils.five = 0;
		this.utils.four = 0;
		this.utils.three = 0;
		this.utils.two = 0;
		this.utils.one = 0;
		this.utils.death = 0;
	}

	private void pos() {
		this.utils.ax = this.config.getDouble("pitchout.positions.1.x");
		this.utils.ay = this.config.getDouble("pitchout.positions.1.y");
		this.utils.az = this.config.getDouble("pitchout.positions.1.z");
		this.utils.bx = this.config.getDouble("pitchout.positions.2.x");
		this.utils.by = this.config.getDouble("pitchout.positions.2.y");
		this.utils.bz = this.config.getDouble("pitchout.positions.2.z");
		this.utils.cx = this.config.getDouble("pitchout.positions.3.x");
		this.utils.cy = this.config.getDouble("pitchout.positions.3.y");
		this.utils.cz = this.config.getDouble("pitchout.positions.3.z");
		this.utils.dx = this.config.getDouble("pitchout.positions.4.x");
		this.utils.dy = this.config.getDouble("pitchout.positions.4.y");
		this.utils.dz = this.config.getDouble("pitchout.positions.4.z");
		this.utils.ex = this.config.getDouble("pitchout.positions.5.x");
		this.utils.ey = this.config.getDouble("pitchout.positions.5.y");
		this.utils.ez = this.config.getDouble("pitchout.positions.5.z");
		this.utils.fx = this.config.getDouble("pitchout.positions.6.x");
		this.utils.fy = this.config.getDouble("pitchout.positions.6.y");
		this.utils.fz = this.config.getDouble("pitchout.positions.6.z");
		this.utils.gx = this.config.getDouble("pitchout.positions.7.x");
		this.utils.gy = this.config.getDouble("pitchout.positions.7.y");
		this.utils.gz = this.config.getDouble("pitchout.positions.7.z");
		this.utils.hx = this.config.getDouble("pitchout.positions.8.x");
		this.utils.hy = this.config.getDouble("pitchout.positions.8.y");
		this.utils.hz = this.config.getDouble("pitchout.positions.8.z");
		this.utils.ix = this.config.getDouble("pitchout.positions.9.x");
		this.utils.iy = this.config.getDouble("pitchout.positions.9.y");
		this.utils.iz = this.config.getDouble("pitchout.positions.9.z");
		this.utils.jx = this.config.getDouble("pitchout.positions.10.x");
		this.utils.jy = this.config.getDouble("pitchout.positions.10.y");
		this.utils.jz = this.config.getDouble("pitchout.positions.10.z");
	}

	public void sendToHub(Player p) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);

		try {
			out.writeUTF("Connect");
			out.writeUTF(this.bungeecordhub);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		p.sendPluginMessage(Bukkit.getPluginManager().getPlugin("pitchout"), "BungeeCord", b.toByteArray());
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("pitchout")) {
			double x;
			double y;
			double z;
			int yaw;
			int pitch;
			int players;
			if (this.language.equalsIgnoreCase("en")) {
				if (args.length == 0) {
					if (p.hasPermission("PitchOut.help")) {
						p.sendMessage(this.utils.getPrefix() + " §6§lPlugin by §eItsAlexousd");
						p.sendMessage(this.utils.getPrefix() + " §fDo §a/pitchout help §ffor more informations.");
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help") && p.hasPermission("PitchOut.help")) {
						p.sendMessage("");
						p.sendMessage("§aHelp for PitchOut :");
						p.sendMessage("§7> §f/pitchout help §7| §eShow this message.");
						p.sendMessage("§7> §f/pitchout setlobby §7| §ePlace the lobby point.");
						p.sendMessage("§7> §f/pitchout setpos <number> §7| §ePlace spawn points.");
						p.sendMessage("§7> §f/pitchout forcestart §7| §eStart the game.");
						p.sendMessage("");
					}

					if (args[0].equalsIgnoreCase("setlobby") && p.hasPermission("PitchOut.setlobby")) {
						x = (double) p.getLocation().getBlockX();
						y = p.getLocation().getY();
						z = (double) p.getLocation().getBlockZ();
						yaw = (int) p.getLocation().getYaw();
						pitch = (int) p.getLocation().getPitch();
						this.config.set("pitchout.lobby.x", x);
						this.config.set("pitchout.lobby.y", y);
						this.config.set("pitchout.lobby.z", z);
						this.config.set("pitchout.lobby.yaw", yaw);
						this.config.set("pitchout.lobby.pitch", pitch);
						this.saveConfig();
						p.sendMessage(this.utils.getPrefix() + " §aThe lobby point has been placed");
						p.sendMessage("§6at the coordinates : §ex:" + x + " y:" + y + " z:" + z);
					}

					if (args[0].equalsIgnoreCase("setpos") && p.hasPermission("PitchOut.help")) {
						p.sendMessage(this.utils.getPrefix() + " §fDo §a/pitchout setpos <number>");
					}

					if (args[0].equalsIgnoreCase("forcestart") && p.hasPermission("PitchOut.forcestart")) {
						players = Bukkit.getOnlinePlayers().size();
						if (players > 1) {
							this.utils.start();
							Bukkit.getScheduler().cancelTask(this.playerJoin.task);
						} else {
							p.sendMessage(this.utils.getPrefix() + " §cThere are not enough players to start.");
						}
					}

					if (!args[0].equalsIgnoreCase("setlobby") && !args[0].equalsIgnoreCase("help") && !args[0].equalsIgnoreCase("setpos") && !args[0].equalsIgnoreCase("forcestart")
							&& p.hasPermission("PitchOut.help")) {
						p.sendMessage(this.utils.getPrefix() + " §fDo §a/pitchout help §ffor more informations.");
					}
				} else if (args.length == 2 && args[0].equalsIgnoreCase("setpos") && p.hasPermission("PitchOut.setpos")) {
					if (args[1] != null) {
						players = Integer.parseInt(args[1].toString());
						x = (double) p.getLocation().getBlockX();
						y = p.getLocation().getY();
						z = (double) p.getLocation().getBlockZ();
						this.config.set("pitchout.positions." + players + ".x", x);
						this.config.set("pitchout.positions." + players + ".y", y);
						this.config.set("pitchout.positions." + players + ".z", z);
						this.saveConfig();
						p.sendMessage(this.utils.getPrefix() + " §aThe spawn point number " + players + " has been placed.");
					} else {
						p.sendMessage(this.utils.getPrefix() + " §fDo §a/pitchout setpos <number>");
					}
				}
			}

			if (this.language.equalsIgnoreCase("fr")) {
				if (args.length == 0) {
					if (p.hasPermission("PitchOut.help")) {
						p.sendMessage(this.utils.getPrefix() + " §6§lPlugin par §eItsAlexousd");
						p.sendMessage(this.utils.getPrefix() + " §fFais §a/pitchout help §fpour plus d'informations");
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help") && p.hasPermission("PitchOut.help")) {
						p.sendMessage("");
						p.sendMessage("§aAide pour PitchOut :");
						p.sendMessage("§7> §f/pitchout help §7| §eMontre ce message.");
						p.sendMessage("§7> §f/pitchout setlobby §7| §ePlace le point de spawn du lobby.");
						p.sendMessage("§7> §f/pitchout setpos <nombre> §7| §ePlace les points de spawn en jeu.");
						p.sendMessage("§7> §f/pitchout forcestart §7| §eForcer la partie à se lancer.");
						p.sendMessage("");
					}

					if (args[0].equalsIgnoreCase("setlobby") && p.hasPermission("PitchOut.setlobby")) {
						x = (double) p.getLocation().getBlockX();
						y = p.getLocation().getY();
						z = (double) p.getLocation().getBlockZ();
						yaw = (int) p.getLocation().getYaw();
						pitch = (int) p.getLocation().getPitch();
						this.config.set("pitchout.lobby.x", x);
						this.config.set("pitchout.lobby.y", y);
						this.config.set("pitchout.lobby.z", z);
						this.config.set("pitchout.lobby.yaw", yaw);
						this.config.set("pitchout.lobby.pitch", pitch);
						this.saveConfig();
						p.sendMessage(this.utils.getPrefix() + " §aPoint de spawn du lobby placé!");
						p.sendMessage("§6aux coordonnées : §ex:" + x + " y:" + y + " z:" + z);
					}

					if (args[0].equalsIgnoreCase("setpos") && p.hasPermission("PitchOut.help")) {
						p.sendMessage(this.utils.getPrefix() + " §fFais §a/pitchout setpos <nombre>");
					}

					if (args[0].equalsIgnoreCase("forcestart") && p.hasPermission("PitchOut.forcestart")) {
						players = Bukkit.getOnlinePlayers().size();
						if (players > 1) {
							this.utils.start();
							Bukkit.getScheduler().cancelTask(this.playerJoin.task);
						} else {
							p.sendMessage(this.utils.getPrefix() + " §cIl n'y a pas assez de joueurs pour forcer la partie à se lancer!");
						}
					}

					if (!args[0].equalsIgnoreCase("setlobby") && !args[0].equalsIgnoreCase("help") && !args[0].equalsIgnoreCase("setpos") && !args[0].equalsIgnoreCase("forcestart")
							&& p.hasPermission("PitchOut.help")) {
						p.sendMessage(this.utils.getPrefix() + " §fFais §a/pitchout help §fpour plus d'informations.");
					}
				} else if (args.length == 2 && args[0].equalsIgnoreCase("setpos") && p.hasPermission("PitchOut.setpos")) {
					if (args[1] != null) {
						players = Integer.parseInt(args[1].toString());
						x = (double) p.getLocation().getBlockX();
						y = p.getLocation().getY();
						z = (double) p.getLocation().getBlockZ();
						this.config.set("pitchout.positions." + players + ".x", x);
						this.config.set("pitchout.positions." + players + ".y", y);
						this.config.set("pitchout.positions." + players + ".z", z);
						this.saveConfig();
						p.sendMessage(this.utils.getPrefix() + " §aLe point de spawn n°" + players + " a été placé!");
					} else {
						p.sendMessage(this.utils.getPrefix() + " §fFais §a/pitchout setpos <nombre>");
					}
				}
			}
		}

		return false;
	}
}
