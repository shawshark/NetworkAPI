package me.shawshark.networkapi;

import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.RedirectRequest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Whitelist implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if(c.getName().equalsIgnoreCase("lilywhitelist")) {
			if(!s.hasPermission("lilypad.whitelist")) {
				s.sendMessage(ChatColor.RED + "You don't have permissions for this commnad!");
				return true;
			}
			
			if(NetworkAPI.whitelist) {
				s.sendMessage(ChatColor.RED + "The whitelist is now turned off!");
				NetworkAPI.whitelist = false;
				NetworkAPI.plugin.getConfig().set("whitelist", "false");
				NetworkAPI.plugin.saveConfig();
			} else {
				s.sendMessage(ChatColor.RED + "The whitelist is now turned on!");
				NetworkAPI.whitelist = true;
				NetworkAPI.plugin.getConfig().set("whitelist", "true");
				NetworkAPI.plugin.saveConfig();
			}
			return true;
		}
		return false;
	}
	
	@EventHandler
	public void PLogin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		if(NetworkAPI.whitelist) {
			if(!player.hasPermission("networkapi.whitelist.bypass")) {
				
				if(NetworkAPI.connect.getSettings().getUsername().equalsIgnoreCase("hub")) {
					
					player.kickPlayer(ChatColor.RED + 
							"The hub server is currently under going maintenance, Please check back in a few minutes!");
					
					return;
				}
				
				player.sendMessage(NetworkAPI.whitelist_Message);
				player.sendMessage(NetworkAPI.whitelist_Message);
				player.sendMessage(NetworkAPI.whitelist_Message);
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						try {
							NetworkAPI.connect.request(new RedirectRequest("hub", player.getName()));
						} catch (RequestException e) {
							e.printStackTrace();
						}
					}
				}.runTaskLater(NetworkAPI.plugin, 15);
				
			}
		}
	}
}
