// James Dempsey

public class Card {
	public Rank rank;
	public Suit suit;
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	private void setRank(Rank rank) {
		this.rank = rank;
	}
	
	private void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Card(Rank rank, Suit suit) {
		this.setRank(rank);
		this.setSuit(suit);
	}
	public Card() {
		this(Rank.ACE, Suit.SPADES);
	}
	public String toString() {
		String name = rank.toString() + " of " + suit.toString();
		if (rank == Rank.JOKER) {
			name = rank.toString();
		}
				
		return name;
	}
	
	public static void main(String[] args) {
		Card initial = new Card();
		Card exampleOne = new Card(Rank.JOKER, Suit.NONE);
		Card exampleTwo = new Card(Rank.JACK, Suit.DIAMONDS);
		Card exampleThree = new Card(Rank.SEVEN, Suit.CLUBS);
		Card exampleFour = new Card(Rank.QUEEN, Suit.DIAMONDS);
		System.out.println(initial);
		System.out.println(exampleOne);
		System.out.println(exampleTwo);
		System.out.println(exampleThree);
		System.out.println(exampleFour);
		
	}

}
