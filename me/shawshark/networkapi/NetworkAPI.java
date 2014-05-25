package me.shawshark.networkapi;

import lilypad.client.connect.api.Connect;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkAPI extends JavaPlugin {
	
	public static Plugin plugin;
	public static PluginManager pm;
	public static Connect connect;
	public static Boolean debug = true;
	public static String serverUsername;
	
	public void onEnable() {
		plugin = this;
		
		pm = getServer().getPluginManager();
		
		/* Lilypad. */
		connect = Bukkit.getServer().getServicesManager().getRegistration(Connect.class).getProvider();
        connect.registerEvents(new LilyListener());
        
        serverUsername = connect.getSettings().getUsername();
        
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new SharkCoinsHook(), this);
	}
	
	public void onDisable() {
		
		connect.unregisterEvents(new LilyListener());
		
		plugin = null;
		pm = null;
		connect = null;
	}
	
	public Plugin getPlugin() {
		if(!(plugin != null)) {
			plugin = this;
		}
		return plugin;
	}
	
	public PluginManager getPluginManager() {
		if(!(pm != null)) {
			pm = getServer().getPluginManager();
		}
		return pm;
	}
}
