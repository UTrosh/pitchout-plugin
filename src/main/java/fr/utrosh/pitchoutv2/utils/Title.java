package fr.utrosh.pitchoutv2.utils;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Title {

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
		sendTitle(player, fadeIn, stay, fadeOut, message, (String) null);
	}

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, (IChatBaseComponent) null, fadeIn, stay, fadeOut);
		connection.sendPacket(packetPlayOutTimes);
		IChatBaseComponent titlemain;
		PacketPlayOutTitle packetPlayOutTitle;
		if (subtitle != null) {
			subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			titlemain = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titlemain);
			connection.sendPacket(packetPlayOutTitle);
		}

		if (title != null) {
			title = title.replaceAll("%player%", player.getDisplayName());
			title = ChatColor.translateAlternateColorCodes('&', title);
			titlemain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
			packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titlemain);
			connection.sendPacket(packetPlayOutTitle);
		}

	}

	public static void sendTabTitle(Player player, String header, String footer) {
		if (header == null) {
			header = "";
		}

		header = ChatColor.translateAlternateColorCodes('&', header);
		if (footer == null) {
			footer = "";
		}

		footer = ChatColor.translateAlternateColorCodes('&', footer);
		header = header.replaceAll("%player%", player.getDisplayName());
		footer = footer.replaceAll("%player%", player.getDisplayName());
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);

		try {
			Field field = headerPacket.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(headerPacket, tabFoot);
		} catch (Exception var12) {
			var12.printStackTrace();
		} finally {
			connection.sendPacket(headerPacket);
		}

	}
}
