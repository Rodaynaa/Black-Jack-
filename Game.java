package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Game {

    Scanner r = new Scanner(System.in);
    String playerName;
    Player[] players = new Player[4];
    Card[] cards = new Card[52];
    int Max_Valid_Score = 0;

    public void generateDeck() {
        int i = 0;
        for (int suit = 0; suit < 4; suit++) {
            int value = 1;
            for (int rank = 0; rank < 13; rank++) {
                cards[i] = new Card(suit, rank, value);
                i++;
                value++;
                if (value > 10) {
                    value = 10;
                }
            }
        }
    }

    public Card drawCard() {
        Random rand = new Random();
        int randomCard = rand.nextInt(52);
        while (cards[randomCard] == null) {
            randomCard = rand.nextInt(52);
        }
        Card takenCard = new Card(cards[randomCard]);
        cards[randomCard] = null;
        return takenCard;
    }

    public void playerInfo() {
//      first we need to assign name to our players
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter player " + (i + 1) + " name: ");
            playerName = r.next();
            players[i] = new Player(playerName);

            //       we need to draw 2 cards for each player
            players[i].takeCards(drawCard());
            MaxScore();
            players[i].takeCards(drawCard());
            MaxScore();
            System.out.println(playerName + "'s Score: ");
            System.out.println(players[i].getScore());
        }
        players[3] = new Player("Dealer");

        //       we need to draw 2 cards for dealer
        players[3].takeCards(drawCard());
        MaxScore();
        players[3].takeCards(drawCard());
        MaxScore();
        System.out.println("Dealers's Score: ");
        System.out.println(players[3].getScore());
    }

    public void MaxScore() {
        int Max = -1;
        for (int i = 0; i < 4; i++) {
            if (players[i] == null) {
                continue;
            }
            if (players[i].getScore() <= 21) {
                if (players[i].getScore() > Max) {
                    Max = players[i].getScore();
                }
            }
        }
        Max_Valid_Score = Max;
    }

}