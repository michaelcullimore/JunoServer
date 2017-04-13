package juno_server;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import modules.Handler;

public class GameHandler extends Handler {
	private static final String MODULE = "juno";
	
	private Set<String> players = new HashSet<String>();
	private Deck deck = new Deck();

	public GameHandler(String portString) {
		super(portString);
		
		changeLogLevel("INFO", MODULE);
		log("INFO", "Created handler");
	}

	@Override
	protected void handle(JSONObject message) {
		if (!MODULE.equals(message.optString("module"))) {
			return;
		}

		if (message.opt("username") != null) {
			players.add(message.getString("username"));
		}

		if ("start".equals(message.optString("action"))) {
			deck.shuffle();
			dealHands();
		}
		else if ("reset".equals(message.optString("action"))) {
			deck = new Deck();
		}
	}

	private void dealHands() {
		log("DEBUG", "Dealing hands to all players (" + players.toString() + ")");

		for (String username: players) {
			for (int i = 0; i < 7; i++) {
				sendCard(username);
			}
		}
		
		log("DEBUG", "Leaving dealHands");
	}

	private void sendCard(String username) {
		JSONObject cardMessage = new JSONObject();
		cardMessage.put("card", deck.draw());

		netSend(cardMessage, username);
	}

	private void log(String level, String message) {
		log(level, message, MODULE);
	}
	
	private void netSend(JSONObject message, String username) {
		netSend(message, username, MODULE);
	}
	
	public static void main(String[] args) {
		new Thread(new GameHandler("8990")).start();
	}
}
