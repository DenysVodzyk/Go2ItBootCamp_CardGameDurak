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
        System.out.println();

        while (!checkForWinner()) {
            attack();
        }
        printWinner();

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

    public void takeCardFromDeck() {
        for (Player player : listOfPlayers) {
            if (!deck.getDeck().isEmpty()) {
                if (player.getHandSize() < 6) {
                    Card cardToTake = getNextCard(deck.getDeck());
                    player.addCardToHand(cardToTake);
                    deck.removeCardFromTheDeck(cardToTake);
                }
            }
        }
    }

    public void printNumberOfCardsInDeck() {
        System.out.println("Number of cards in the deck: " + deck.getDeckSize());
    }

    public void determineIndexOfPlayer() {
        if (listOfPlayers.get(listOfPlayers.size() - 1) == currentPlayer) {
            nextPlayer = listOfPlayers.get(0);
        } else {
            int indexOfCurrentPlayer = listOfPlayers.indexOf(currentPlayer);
            nextPlayer = listOfPlayers.get(indexOfCurrentPlayer + 1);
        }
    }

    public void attack() {
        determineIndexOfPlayer();
        defendOrTakeCard();
        printNumberOfCardsEachPlayerHas();
        printNumberOfCardsInDeck();
        takeCardFromDeck();
        passTurnFromCurrentPlayerToNextPlayer();
        printNumberOfCardsEachPlayerHas();
        printNumberOfCardsInDeck();
    }

    public void defendOrTakeCard() {
        boolean defend = false;
        Card attackCard = getNextCard(currentPlayer.getHand());
        currentPlayer.removeCardFromHand(attackCard);
        System.out.println("Player " + currentPlayer.getId() + " attacks.");

        if (attackCard.getSuit() == getTrumpCardSuitOfDeck()) {
            defend = checkIfCanBeDefeatedWithSameSuit(attackCard);
        } else {
            defend = checkIfCanBeDefeatedWithSameSuit(attackCard);
            if (!defend) {
                defend = checkIfCanBeDefeatedWithTrumpCard(attackCard);
            }
        }
        if (!defend) {
            nextPlayer.addCardToHand(attackCard);
            System.out.println("Player " + nextPlayer.getId() + " takes " + attackCard + " Player skips the attack.");
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
        determineIndexOfPlayer();
    }

    public void passTurnFromCurrentPlayerToNextPlayer() {
        currentPlayer = nextPlayer;
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
                    System.out.println("Player " + nextPlayer.getId() + " beats attacking " + attackCard + " with the " + card);
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
                System.out.println("Player " + nextPlayer.getId() + " beat attacking " + attackCard + " with the trump " + card);
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

    public void printWinner() {
        System.out.println("Congrats, Player " + winner.getId() + ". You are the winner!");
    }

    public void printNumberOfCardsEachPlayerHas() {
        for (Player player : listOfPlayers) {
            System.out.print(" Player: " + player.getId() + " - " + player.getHand().size() + " cards");
        }
        System.out.println();
    }
}

