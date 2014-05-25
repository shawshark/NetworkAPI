package me.shawshark.networkapi;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API {
	
	public static void networkBroadcast(String message) {
		LilySend.messageRequest("NetworkAPI-networkBroadcast", message);
	}
	
  	@SuppressWarnings("resource")
	public static String getUUID(String player) {
        String uuid = null;
        try {
            URL url = new URL("http://uuid.craftshark.net/uuid/" + player);
            URLConnection uc = url.openConnection();
            uc.setUseCaches(false);
            uc.setDefaultUseCaches(false);
            uc.addRequestProperty("User-Agent", "Mozilla/5.0");
            uc.addRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
            uc.addRequestProperty("Pragma", "no-cache");

            // Parse it
            String json = new Scanner(uc.getInputStream(), "UTF-8").useDelimiter("\\A").next();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json);
            uuid = (String) ((JSONObject) ((JSONArray) ((JSONObject) obj).get("profiles")).get(0)).get("id");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uuid;
    }
  	
  	
	public static void debug(String message) {
		if(NetworkAPI.debug) {
			System.out.println(message);
		}
	}
	
	public static String barAPIMessageChannel = "barAPIMessageChannel";
	public static void barAPIMessageRequest(String message) {
		LilySend.messageRequest(barAPIMessageChannel, message);
	}
	
	public static void barAPIMessageBroadcast(String message) {
		for (Player player : Bukkit.getOnlinePlayers() ) {
			if(BarAPI.hasBar(player)) {
				BarAPI.removeBar(player);
			}
			int seconds = 10;
			BarAPI.setMessage(player, colourMessage(message), seconds);
		}
	}
	
	public static String colourMessage(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
