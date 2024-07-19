package blackjack;


public class Card {
    private final int suit;
    private final int rank;
    private final int value;

    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }
    public Card (Card p){
        this.suit = p.suit;
        this.rank = p.rank;
        this.value = p.value;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

}