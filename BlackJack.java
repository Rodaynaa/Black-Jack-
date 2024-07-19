/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class BlackJack {

    static Game game = new Game();
    static boolean dealerWon = false;
    static GUI gui = new GUI();
    public static void main(String[] args) {

        game.generateDeck();
        game.playerInfo();
        gui.runGUI(game.cards, game.players[0].getPlayerCards(), game.players[1].getPlayerCards(), game.players[2].getPlayerCards(), game.players[3].getPlayerCards());
        startingGame();
        if (!dealerWon) {
            endGame();
        }
    }

    public static void startingGame() {
        Card taken;
        Scanner inChar = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println(" ----- Player " + (i + 1) + "turn ----");
            while (true) {
                int c = 0;
                System.out.println("Press 1 to hit or 2 to stand: ");
                c = inChar.nextInt();
                while (c != 1 && c != 2) {
                    System.out.println("Enter a correct choice: ");
                    c = inChar.nextInt();
                }
                if (c == 1) {
                    taken = game.drawCard();
                    game.players[i].takeCards(taken);
                    gui.updatePlayerHand(taken,i);
                    System.out.println(game.players[i].getName());
                    System.out.println(game.players[i].getScore());
                    game.MaxScore();
                } else if (c == 2) {
                    break;
                }
//              // check if the player is busted 
                if (game.players[i].getScore() > 21) {
                    System.out.println("Player " + (i + 1) + " Busted!");
                    game.players[i].busted = true;
                    game.players[i].setScore(0);
                    game.MaxScore();
                    break;
                } else if (game.players[i].getScore() == 21) {
                    game.players[i].blackjack = true;
                    System.out.println("Player " + (i + 1) + " got Blackjack!");
                    break;

                }
            }
        }
        // input for the dealer
        while (true) {
            // first case dealer won
            if (game.players[0].busted == true && game.players[1].busted == true && game.players[2].busted == true) {
                System.out.println("Dealer won");
                System.out.println("* Game End *");
                dealerWon = true;
                break;
            } else if (game.players[0].getScore() != game.Max_Valid_Score && game.players[1].getScore() != game.Max_Valid_Score && game.players[2].getScore() != game.Max_Valid_Score && game.players[3].getScore() == game.Max_Valid_Score) {
                System.out.println("Dealer won");
                System.out.println("* Game End *");
                dealerWon = true;
                break;
            }
            if (game.players[3].getScore() > 21) {
                System.out.println("Dealer busted!");
                game.players[3].busted = true;
                game.players[3].setScore(0);
                game.MaxScore();
                break;
            }
            if (game.players[3].getScore() <= game.Max_Valid_Score) {
                taken = game.drawCard();
                game.players[3].takeCards(taken);
                gui.updateDealerHand(taken, game.cards);
                System.out.println(game.players[3].getName());
                System.out.println(game.players[3].getScore());
                game.MaxScore();
            }
        }
    }

    public static void endGame() {
        // check the winner
        int countMxPl = 0;
        int mxPlIndex = 0;
        for (int i = 0; i < 4; i++) {
            if (game.players[i].getScore() == game.Max_Valid_Score) {
                countMxPl++;
                mxPlIndex = i;
            }
        }
        if (countMxPl == 1) {
            if (mxPlIndex == 3) {
                System.out.println("Dealer won");
                System.out.println("* Game End *");
            } else {
                System.out.println("player " + (mxPlIndex + 1) + " won");
                System.out.println("* Game End *");
            }
        }
        if (countMxPl > 1) {
            System.out.println("PUSH");
            System.out.println("* Game End *");
        }
    }
}