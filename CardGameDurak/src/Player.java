import java.util.HashSet;
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

    public void deleteCardFromHand(Card card) {
        hand.remove(card);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", hand=" + hand +
                '}';
    }
}
