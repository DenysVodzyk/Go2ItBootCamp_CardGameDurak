public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService(4);
        gameService.startGame();






        /*Deck deck = new Deck();
        GameService gameService = new GameService(4);
        deck.setTrumpCardSuit();
        System.out.println("Trump suit: " + deck.getTrumpCardSuit());
        System.out.println("Deck before cards distribution: ");
        System.out.println(deck.getDeckSize());
        System.out.println(deck.getDeck());

        System.out.println("-------------------");
        System.out.println("Player 1 hand: ");
        Player player1 = new Player(1);
        gameService.dealFirstSixCardToPlayer(player1);
        System.out.println(player1.getHand());

        System.out.println("-------------------");
        System.out.println("Player 2 hand: ");
        Player player2 = new Player(2);
        gameService.dealFirstSixCardToPlayer(player2);
        System.out.println(player2.getHand());

        System.out.println("-------------------");
        System.out.println("Player 3 hand: ");
        Player player3 = new Player(3);
        gameService.dealFirstSixCardToPlayer(player3);
        System.out.println(player3.getHand());

        System.out.println("-------------------");
        System.out.println("Player 4 hand: ");
        Player player4 = new Player(4);
        gameService.dealFirstSixCardToPlayer(player4);
        System.out.println(player4.getHand());

        System.out.println("-------------------");

        System.out.println("Deck after cards distribution: ");
        System.out.println(deck.getDeckSize());
        System.out.println(deck.getDeck());

        System.out.println("Lowest Trump card on hand: " + gameService.getLowestTrumpCardOnHand());
*/



        /*System.out.println(deck.getDeck());
        Card card1 = deck.getDeck().iterator().next();
        System.out.println("First card in deck is: " + card1);
        deck.getDeck().remove(card1);
        System.out.println(deck.getDeck());


        System.out.println(deck.getDeckSize());
        */


//        System.out.println(SUIT.values().length);
//        System.out.println(SUIT.values()[0]);
//        System.out.println(SUIT.values()[1]);
//        System.out.println(SUIT.values()[2]);
//        System.out.println(SUIT.values()[3]);
//
//        //       ACE(9), KING(8), QUEEN(7), JACK(6), TEN(5), NINE(4), EIGHT(3), SEVEN(2), SIX(1);
//
//        System.out.println("");
//        System.out.println(CARD_RANK.values().length);
//        System.out.println(CARD_RANK.values()[0]);
//        System.out.println(CARD_RANK.values()[8]);
//        System.out.println(CARD_RANK.valueOf("SIX").ordinal());
//
//        Card card = new Card(CARD_RANK.values()[0], SUIT.values()[2]);
//        System.out.println(card);
    }
}
