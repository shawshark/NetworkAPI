package me.shawshark.networkapi;

import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import lilypad.client.connect.api.event.EventListener;
import lilypad.client.connect.api.event.MessageEvent;

public class LilyListener {
	
	public static String split = ":.:";
	
	@EventListener
    public void onMessage(MessageEvent event) {
		if(event.getChannel().equalsIgnoreCase("NetworkAPI-networkBroadcast")) {
			try {
				Bukkit.broadcastMessage(API.colourMessage(event.getMessageAsString()));
				return;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		else if(event.getChannel().equalsIgnoreCase(API.barAPIMessageChannel)) {
			try {
				API.barAPIMessageBroadcast(event.getMessageAsString());
				return;
			} catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
		//vote listener.
		else if(event.getChannel().equalsIgnoreCase(API.VOTE_LISTENING_CHANNEL_MESSAGE)) {
			
			try {
				
				String username = event.getMessageAsString();
				
				String VOTING_MESSAGE = ChatColor.YELLOW + username + ChatColor.GREEN + " has just voted at " + 
						ChatColor.YELLOW + "www.craftshark.net" + ChatColor.GREEN + "!";
				
				
				if(NetworkAPI.serverUsername.equalsIgnoreCase("pvp")) {
					
					//do pvp vote.
					Bukkit.broadcastMessage(VOTING_MESSAGE);
					
					Bukkit.broadcastMessage(ChatColor.GREEN + username + ChatColor.YELLOW + " can use their SharkCoins received in the SharkCoins Shop, "
							+ "To use this shop type " + ChatColor.GREEN + "/shop");
					
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "money grant " + username + " 500");
					
					return;
				}
				
				if(NetworkAPI.serverUsername.equalsIgnoreCase("creative")) {
					
					//do creative vote.
					Bukkit.broadcastMessage(VOTING_MESSAGE);
					return;
				}
				
				if(NetworkAPI.serverUsername.equalsIgnoreCase("hub")) {
					
					//do hub.
					Bukkit.broadcastMessage(VOTING_MESSAGE);
					
					// add hub.talk
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + username + " add hub.talk");
					Bukkit.broadcastMessage(ChatColor.GREEN + username + " has just voted and received permissions to talk in the hub. They have also received many many more prizes on all our servers!");
					
					//add vote.
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "addvote " + username + " 1");
					return;
				}
				
				if(NetworkAPI.serverUsername.equalsIgnoreCase("survival")) {
					
					//do survival message.
					Bukkit.broadcastMessage(VOTING_MESSAGE);
					return;
				}
				
				if(NetworkAPI.serverUsername.equalsIgnoreCase("skyblock")) {
					
					//do skyblock.
					Bukkit.broadcastMessage(VOTING_MESSAGE);
					
					//grant money.
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "money grant " + username + " 500");
					
					//another message.
					Bukkit.broadcastMessage(ChatColor.GREEN + username + ChatColor.YELLOW + " can use their SharkCoins received in the SharkCoins Shop, "
							+ "To use this shop type " + ChatColor.GREEN + "/shop");
					return;
				}
				
			} catch (UnsupportedEncodingException e) {
				//ignore.
			}
		}
		
	}
}
