package blackjack;

public class Player {

    private String name;
    private int score = 0;
    private Card[] playerCards = new Card[11];
    boolean blackjack = false;
    boolean busted = false;
    private int v = 0;

    public Card[] getPlayerCards() {
        return playerCards;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void takeCards(Card s) {
//        first we need to put card s into the array
        playerCards[v] = new Card(s);
        v++;
//        increase the score
        score += s.getValue();
    }

}