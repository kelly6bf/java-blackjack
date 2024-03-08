package domain.playingcard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.playingcard.PlayingCardValue.SMALL_ACE;
import static domain.playingcard.PlayingCardValue.values;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Deck {
    private final List<PlayingCard> playingCards;

    Deck(final List<PlayingCard> playingCards) {
        this.playingCards = playingCards;
    }

    public static Deck init() {
        return Arrays.stream(PlayingCardShape.values())
                .flatMap(playingCardShape -> generateCardByShape(playingCardShape).stream())
                .collect(collectingAndThen(toList(), Deck::new));
    }

    private static List<PlayingCard> generateCardByShape(final PlayingCardShape playingCardShape) {
        return Arrays.stream(values())
                .filter(playingCardValue -> playingCardValue != SMALL_ACE)
                .map(playingCardValue -> new PlayingCard(playingCardShape, playingCardValue))
                .toList();
    }

    public PlayingCard drawn() {
        Collections.shuffle(playingCards);

        PlayingCard card = playingCards.get(0);
        playingCards.remove(0);

        return card;
    }
}
