import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Deck {
    private int cardsQuantity;
    private Set<Card> deck;
    private static Random random = new Random();
    private Suit trumpCardSuit;

    public Deck() {
        this.cardsQuantity = 36;
        this.deck = new HashSet<>();
        init();
    }

    public void init() {
        while (deck.size() < cardsQuantity) {
            addCardsToTheDeck();
        }
    }

    public void addCardsToTheDeck() {
        Card card = new Card(CardRank.values()[getRandomNumberForCardRank()], Suit.values()[getRandomNumberForCardSuit()]);
        deck.add(card);
    }

    public int getRandomNumberForCardSuit() {
        return random.nextInt(4);
    }

    public void setTrumpCardSuit() {
        this.trumpCardSuit = Suit.values()[getRandomNumberForCardSuit()];
    }

    public Suit getTrumpCardSuit() {
        return trumpCardSuit;
    }


    public int getRandomNumberForCardRank() {
        return random.nextInt(9);
    }

    public Set<Card> getDeck() {
        return deck;
    }

    public void removeFromDeck(Card card) {
        deck.remove(card);
    }

    public int getDeckSize() {
        return deck.size();
    }

}
