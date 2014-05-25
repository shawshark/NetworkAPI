package me.shawshark.networkapi;

import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;

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
	}
}
