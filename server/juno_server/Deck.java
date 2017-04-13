package juno_server;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Deck {
	private Deque<Card> cards = new ConcurrentLinkedDeque<>();
	
	public Deck() {
		String[] colors = { "blue", "yellow", "red", "green" };
		String[] special = { "Draw 2", "Reverse", "Skip" };
		
		for (String color: colors) {
			for (int i = 0; i < 10; i++) {
				cards.add(new Card(color, i + ""));
				// All numbers except zero are represented twice
				if (i != 0) {
					cards.add(new Card(color, i + ""));
				}
			}
			
			for (String s: special) {
				cards.add(new Card(color, s));
				cards.add(new Card(color, s));
			}
		}
		
		String[] wild = { "Wild", "Wild Draw 4" };
		for (int i = 0; i < 4; i++) {
			cards.add(new Card(null, wild[0]));
			cards.add(new Card(null, wild[1]));
		}
	}
	
	/**
	 * Shuffles the deck if any cards are present
	 * @return false if there are no cards in the deck
	 */
	public boolean shuffle() {
		return true;
	}
	
	public Card draw() {
		if (cards.size() > 0) {
			return cards.pop();
		}
		
		if (!shuffle()) {
			return null;
		}
		
		return draw();
	}
}
