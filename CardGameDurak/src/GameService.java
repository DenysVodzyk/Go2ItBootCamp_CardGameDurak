import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameService {
    private Deck deck;
    private int numberOfPlayers;
    private List<Player> listOfPlayers;
    private Player currentPlayer;
    private Player nextPlayer;
    private Player winner;


    public GameService(int numberOfPlayers) {
        this.deck = new Deck();
        this.deck.setTrumpCardSuit();
        this.numberOfPlayers = numberOfPlayers;
        this.listOfPlayers = new ArrayList<>();
    }

    public void startGame() {
        addPlayersToList();

        dealFirstSixCardsToPlayers();
        System.out.println("Trump suit " + getTrumpCardSuitOfDeck());
        System.out.println("Lowest trump card: " + getLowestTrumpCardOnHand());
        System.out.println("Player that attacks first: " + getPlayerThatAttacksFirst());

        System.out.println("List of players after adding: " + getListOfPlayers());

        while (!checkForWinner()) {
            attack();
        }
        System.out.println("Number of cards in the deck: " + deck.getDeckSize());
        System.out.println("Congrats, Player " + winner.getId() + ". You are the winner!");
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

    public Card getNextCard(Set<Card> setOfCards) {
        return setOfCards.iterator().next();
    }

    public void dealFirstSixCardsToPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            while (getPlayerFromList(i).getHandSize() < 6) {
                Card card = getNextCard(deck.getDeck());
                getPlayerFromList(i).addCardToHand(card);
                deck.removeCardFromTheDeck(card);
            }
        }
    }

    public SUIT getTrumpCardSuitOfDeck() {
        return deck.getTrumpCardSuit();
    }

    //return player with the lowest trump card on hand, if none of the players have trump card - return player 1.
    public Player getPlayerThatAttacksFirst() {
        currentPlayer = getPlayerFromList(0);
        for (Player players : getListOfPlayers()) {
            if (players.getHand().contains(getLowestTrumpCardOnHand())) {
                currentPlayer = players;
            }
        }
        return currentPlayer;
    }

    //return a trump card with a lowest rank among players, if none of the players have trump card - return first card of player 1.
    public Card getLowestTrumpCardOnHand() {
        Card lowestTrumpCard = getNextCard(getPlayerFromList(0).getHand());
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


    public void takeCardFromDeck(Player player) {
        if (!deck.getDeck().isEmpty()) {
            if (player.getHandSize() < 6) {
                Card cardToTake = getNextCard(deck.getDeck());
                player.addCardToHand(cardToTake);
                deck.removeCardFromTheDeck(cardToTake);
            }
        }
    }

    public void determineNextPlayer() {
        if (listOfPlayers.get(listOfPlayers.size() - 1) == currentPlayer) {
            nextPlayer = listOfPlayers.get(0);
        } else {
            int indexOfCurrentPlayer = listOfPlayers.indexOf(currentPlayer);
            nextPlayer = listOfPlayers.get(indexOfCurrentPlayer + 1);
        }
    }


    public void attack() {
        determineNextPlayer();
        defendOrTakeCard();
        System.out.println("Player " + currentPlayer.getId() + " has " + currentPlayer.getHand().size() + " cards.");
        System.out.println("Player " + nextPlayer.getId() + " has " + nextPlayer.getHand().size() + " cards.");
        takeCardFromDeck(currentPlayer);
        takeCardFromDeck(nextPlayer);
        currentPlayer = nextPlayer;
    }


    public void defendOrTakeCard() {
        boolean result = false;
        Card attackCard = getNextCard(currentPlayer.getHand());
        currentPlayer.removeCardFromHand(attackCard);

        if (attackCard.getSuit() == getTrumpCardSuitOfDeck()) {
            result = checkIfCanBeDefeatedWithSameSuit(attackCard);
        } else {
            result = checkIfCanBeDefeatedWithSameSuit(attackCard);
            if (!result) {
                result = checkIfCanBeDefeatedWithTrumpCard(attackCard);
            }
        }
        if (!result) {
            nextPlayer.addCardToHand(attackCard);
            System.out.println("Player " + nextPlayer.getId() + " takes card " + attackCard + ". He skips the attack.");
            skipAttack();
        }
    }

    public void skipAttack() {
        if (listOfPlayers.get(listOfPlayers.size() - 1) == currentPlayer) {
            currentPlayer = listOfPlayers.get(0);
        } else {
            int indexOfCurrentPlayer = listOfPlayers.indexOf(currentPlayer);
            currentPlayer = listOfPlayers.get(indexOfCurrentPlayer + 1);
        }
    }


    public boolean checkIfCanBeDefeatedWithSameSuit(Card attackCard) {
        boolean result = false;
        SUIT attackCardSuit = attackCard.getSuit();
        int attackCardRank = attackCard.getCard_rank().getRank();
        for (Card card : nextPlayer.getHand()) {
            if (card.getSuit() == attackCardSuit) {
                if (card.getCard_rank().getRank() > attackCardRank) {
                    nextPlayer.removeCardFromHand(card);
                    result = true;
                    System.out.println("Player " + nextPlayer.getId() + " beat attacking card: " + attackCard + " with the card: " + card);
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkIfCanBeDefeatedWithTrumpCard(Card attackCard) {
        boolean result = false;
        for (Card card : nextPlayer.getHand()) {
            if (card.getSuit() == getTrumpCardSuitOfDeck()) {
                nextPlayer.removeCardFromHand(card);
                result = true;
                System.out.println("Player " + nextPlayer.getId() + " beat attacking card: " + attackCard + " with the trump card: " + card);
                break;
            }
        }
        return result;
    }

    public boolean checkForWinner() {
        boolean winnerFound = false;
        for (Player player : listOfPlayers) {
            if (player.getHand().isEmpty()) {
                winner = player;
                winnerFound = true;
            }
        }
        return winnerFound;
    }
}

