package me.shawshark.networkapi;

import lilypad.client.connect.api.Connect;
import me.shawshark.networkapi.commands.ListCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkAPI extends JavaPlugin implements Listener {
	
	public static Plugin plugin;
	public static PluginManager pm;
	public static Connect connect;
	public static Boolean debug = true;
	public static String serverUsername;
	
	public static boolean whitelist;
	
	public static String whitelist_Message;
	
	public void onEnable() {
		plugin = this;
		
		pm = getServer().getPluginManager();
		
		/* Lilypad. */
		connect = Bukkit.getServer().getServicesManager().getRegistration(Connect.class).getProvider();
        connect.registerEvents(new LilyListener());
        
        serverUsername = connect.getSettings().getUsername();
        
        saveDefaultConfig();
        saveConfig();
        
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new SharkCoinsHook(), this);
        pm.registerEvents(new Whitelist(), this);
        
        getCommand("list").setExecutor(new ListCommand());
        
        whitelist = getConfig().getBoolean("whitelist");
        //whitelist_Message = getConfig().getString("whitelist.message");
        
        whitelist_Message = ChatColor.RED + "The " + connect.getSettings().getUsername() + 
    			" server is under maintenance right now, Please check back later!";
        
        getCommand("lilywhitelist").setExecutor(new Whitelist());
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
