package domain.participant;

import domain.playingcard.PlayingCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.playingcard.PlayingCardShape.*;
import static domain.playingcard.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @DisplayName("손패 인스턴스가 생성된다.")
    @Test
    void createHandTest() {
        // When
        Hand hand = Hand.init();

        // Then
        assertThat(hand).isNotNull();
    }

    @DisplayName("손패에 있는 카드의 숫자합을 반환한다.")
    @Test
    void getCardsNumberSumTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(CLOVER, FOUR), new PlayingCard(DIAMOND, SEVEN));
        Hand hand = new Hand(playingCards);

        // When
        int result = hand.getTotalScore().value();

        // Then
        assertThat(result).isEqualTo(11);
    }

    @DisplayName("손패에 새로운 카드를 추가한다.")
    @Test
    void addCardTest() {
        // Given
        Hand hand = Hand.init();
        int totalScore = hand.getTotalScore().value();
        PlayingCard card = new PlayingCard(DIAMOND, NINE);

        // When
        hand.addCard(card);

        // Then
        assertThat(totalScore).isNotEqualTo(hand.getTotalScore());
    }

    @DisplayName("손패가 블랙잭이면 true를 반환한다.")
    @Test
    void isBlackJackTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING), new PlayingCard(CLOVER, QUEEN), new PlayingCard(SPADE, ACE));
        Hand hand = new Hand(playingCards);

        // When
        boolean isBlackJack = hand.isBlackJack();

        // Then
        Assertions.assertThat(isBlackJack).isTrue();
    }
}
