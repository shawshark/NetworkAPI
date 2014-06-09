package me.shawshark.networkapi.commands;

import java.util.Set;

import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.GetPlayersRequest;
import me.shawshark.networkapi.NetworkAPI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		try {
			Set<String> players = NetworkAPI.connect.request(new GetPlayersRequest(true)).await(500L).getPlayers();
			
			String online = "";
			for ( String p : players ) {
				online = online + ChatColor.YELLOW + p + ChatColor.WHITE + ", "; 
			}
			
			int count = players.size();
			s.sendMessage(ChatColor.BLUE + "There " + (count == 1 ? "is " : "are ") + count + " player" + (count == 1 ? "" : "s") + " on the Craftshark Network!");
			s.sendMessage(online);
			
		} catch (InterruptedException | RequestException e) {
			s.sendMessage(ChatColor.RED + "A error occured while attempting to collect all players online our network, Please try again later!");
			return true;
		}
		return true;
	}
}
