import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Deck {
    private int cardsQuantity;
    private Set<Card> deck;
    private Random random;

    public Deck(int cardsQuantity) {
        this.cardsQuantity = cardsQuantity;
        this.deck = new HashSet<>();
        this.random = new Random();
        makeDeck();
    }

    public void makeDeck() {
        while (deck.size() < cardsQuantity) {
            addCardsToTheDeck();
        }
    }

    public void addCardsToTheDeck() {
        Card card = new Card(CARD_RANK.values()[getRandomNumberForCardRank()], SUIT.values()[getRandomNumberForCardSuit()]);
        deck.add(card);
    }

    public int getRandomNumberForCardSuit() {
        return random.nextInt(4);
    }

    public SUIT getTrumpCardSuit() {
        return SUIT.values()[getRandomNumberForCardSuit()];
    }

    public int getRandomNumberForCardRank() {
        return random.nextInt(9);
    }

    public Set<Card> getDeck() {
        return deck;
    }

}
