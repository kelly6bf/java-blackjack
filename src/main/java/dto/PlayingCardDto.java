package dto;

import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCardShape;
import domain.playingcard.PlayingCardValue;

public record PlayingCardDto(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {

    public static PlayingCardDto of(final PlayingCard playingCard) {
        return new PlayingCardDto(playingCard.playingCardShape(), playingCard.playingCardValue());
    }
}
