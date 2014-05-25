package me.shawshark.networkapi;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void PJoin(PlayerJoinEvent event) {
	}
	
	@EventHandler
	public void PCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String[] Message = event.getMessage().split(" ");
		
		if(Message[0].equalsIgnoreCase("/barsend")) {
			
			event.setCancelled(true);
			
			if(!player.hasPermission("barsend.send")) {
				player.sendMessage(ChatColor.RED + "You don't have permissions!");
				return;
			}
			
			String m = event.getMessage().replace("/barsend", "");
			String message = m;
			
			// send.
			API.barAPIMessageRequest(message);
			
			player.sendMessage(ChatColor.YELLOW + "Message sent over network!");
			
		}
		
	}
}
