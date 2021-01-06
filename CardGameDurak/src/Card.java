import java.util.Objects;

public class Card {
    private CardRank card_rank;
    private Suit suit;


    public Card(CardRank card_rank, Suit suit) {
        this.card_rank = card_rank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardRank getCard_rank() {
        return card_rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return card_rank == card.card_rank &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_rank, suit);
    }

    @Override
    public String toString() {
        return "card {" +
                "card_rank=" + card_rank +
                ", suit=" + suit +
                '}' + "\n";
    }
}

