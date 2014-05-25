package me.shawshark.networkapi;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.MessageRequest;

public class LilySend {
	public static void messageRequest(String channel, String message) {
		try {
			
			MessageRequest request = new MessageRequest
					(Collections.<String> emptyList(), channel, message);
			
			NetworkAPI.connect.request(request); 
			
		} catch (UnsupportedEncodingException | RequestException e) {
			e.printStackTrace();
		} 
	}
}
