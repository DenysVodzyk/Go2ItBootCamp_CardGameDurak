import java.util.ArrayList;
import java.util.List;

public class GameService {
    private Deck deck;
    private int numberOfPlayers;
    private List<Player> listOfPlayers;


    public GameService(int numberOfPlayers) {
        this.deck = new Deck();
        this.deck.setTrumpCardSuit();
        this.numberOfPlayers = numberOfPlayers;
        this.listOfPlayers = new ArrayList<>();
    }

    public void startGame() {
        // System.out.println("List of players before adding: " + getListOfPlayers());
        addPlayersToList();

        dealFirstSixCardsToPlayers();
        System.out.println("Trump suit " + getTrumpCardSuitOfDeck());
        System.out.println("Lowest trump card: " + getLowestTrumpCardOnHand());
        System.out.println("Player that attacks first: " + getPlayerThatAttacksFirst());

        System.out.println("List of players after adding: " + getListOfPlayers());
//        System.out.println(deck.getDeckSize());
//        System.out.println("Leftover: " + deck.getDeck());
    }


    public void addPlayersToList() {
        for (int i = 1; i <= numberOfPlayers; i++) {
            Player player = new Player(i);
            getListOfPlayers().add(player);
        }
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public Player getPlayerFromList(int index) {
        return getListOfPlayers().get(index);
    }

    public void dealFirstSixCardsToPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            while (getPlayerFromList(i).getHandSize() < 6) {
                Card card = deck.getDeck().iterator().next();
                getPlayerFromList(i).addCardToHand(card);
                deck.deleteCardFromTheDeck(card);
            }
        }
    }


    public SUIT getTrumpCardSuitOfDeck() {
        return deck.getTrumpCardSuit();
    }

    //return player with the lowest trump card on hand, if none of the players have trump card - return player 1.
    public Player getPlayerThatAttacksFirst() {
        Player player = getPlayerFromList(0);
        for (Player players : getListOfPlayers()) {
            if (players.getHand().contains(getLowestTrumpCardOnHand())) {
                player = players;
            }
        }
        return player;
    }

    //return a trump card with a lowest rank among players, if none of the players have trump card - return first card of player 1.
    public Card getLowestTrumpCardOnHand() {
        Card lowestTrumpCard = getPlayerFromList(0).getHand().iterator().next(); //I know it's super difficult to read this line.
        int i = CARD_RANK.valueOf("SIX").ordinal();
        boolean result = false;

        while (!result) {
            lowestTrumpCard = new Card(CARD_RANK.values()[i], getTrumpCardSuitOfDeck());
            if (!deck.getDeck().contains(lowestTrumpCard)) {
                result = true;
            }
            i--;
        }
        return lowestTrumpCard;
    }



    // 1->2; 2->3; 3->4; 4->1

}
