import java.util.Objects;

public class Card {
    private CARD_RANK card_rank;
    private SUIT suit;


    public Card(CARD_RANK card_rank, SUIT suit) {
        this.card_rank = card_rank;
        this.suit = suit;
    }

    public SUIT getSuit() {
        return suit;
    }

    public CARD_RANK getCard_rank() {
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

