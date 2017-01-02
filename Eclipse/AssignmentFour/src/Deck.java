
public class Deck {
	Card[] contents = new Card[54];
	
	public Deck() {
		int n = 0;
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				if ((s != Suit.NONE) && (r != Rank.JOKER)) {
					Card created = new Card(r, s);
					contents[n] = created;
					n = n + 1;
				}
			}
		}
		Card joker = new Card(Rank.JOKER, Suit.NONE);
		contents[52] = joker; 
		Card jokertwo = new Card(Rank.JOKER, Suit.NONE);
		contents[53] = jokertwo;
	}

	public String toString() {
		String output = "";
		for (Card c : contents) {
			c.toString();
			output = output + c.toString() + "\n";
		}
		return output;
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		Card.main(args);
		System.out.println("");
		System.out.println(deck.toString());
	}

}
// !=