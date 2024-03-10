package domain.participant;

public class Player extends Participant {
    private final PlayerName playerName;

    public Player(final PlayerName playerName, final Hand hand) {
        super(hand);
        this.playerName = playerName;
    }

    public static Player of(final PlayerName playerName) {
        return new Player(playerName, Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.isLowerToBlackjackConditionValue();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }
}
