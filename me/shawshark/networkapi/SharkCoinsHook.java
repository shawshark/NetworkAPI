package me.shawshark.networkapi;

import java.sql.SQLException;

import me.shawshark.sharkcoinsapi.SharkCoinsAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SharkCoinsHook implements Listener {
	
	@EventHandler
	public void PJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String UUID = player.getUniqueId().toString().replace("-", "");
		
		if(!SharkCoinsAPI.hasAccount(UUID)) {
			try {
				SharkCoinsAPI.setBalance(UUID, 0, player.getName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(player.getName().equalsIgnoreCase("shawshark")) {
			player.sendMessage("This is a debug message!");
			player.sendMessage("sharkcoins: " + SharkCoinsAPI.getBalance(UUID));
		}
 	}
}
