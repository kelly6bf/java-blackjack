package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;

import java.util.List;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(final Hand hand) {
        this.hand = hand;
    }

    abstract public boolean isDrawable();

    public void draw(final Deck deck) {
        hand.addCard(deck.drawn());
    }

    public Score getTotalScore() {
        return hand.getTotalScore();
    }

    public List<PlayingCard> getHandCards() {
        return hand.getPlayingCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}
