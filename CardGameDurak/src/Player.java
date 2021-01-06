import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {
    private int id;
    private Set<Card> hand;

    public Player(int id) {
        this.id = id;
        this.hand = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id &&
                Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hand);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", hand=" + hand +
                '}';
    }
}
