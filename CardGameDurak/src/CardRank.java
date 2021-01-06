public enum CARD_RANK {
    ACE(9), KING(8), QUEEN(7), JACK(6), TEN(5), NINE(4), EIGHT(3), SEVEN(2), SIX(1);

    private final int rank;

    private CARD_RANK(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
