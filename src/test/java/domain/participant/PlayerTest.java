package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.playingcard.PlayingCardShape.DIAMOND;
import static domain.playingcard.PlayingCardValue.KING;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("플레이어의 이름을 입력하면 참가자 인스턴스를 생성한다.")
    @Test
    void createPlayerTest() {
        // Given
        PlayerName playerName = new PlayerName("kelly");

        // When
        PlayingCards playingCards = PlayingCards.init();
        Player player = Player.of(playerName, Deck.init(playingCards.value()));

        // Then
        assertThat(player).isNotNull();
    }

    @DisplayName("플레이어가 카드를 뽑을 수 있는 상태이면 true를 반환한다.")
    @Test
    void isDrawableTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING));
        Hand hand = new Hand(playingCards);
        Player player = new Player(new PlayerName("kelly"), hand);

        // When
        Boolean isBust = player.isDrawable();

        // Then
        assertThat(isBust).isTrue();
    }

    @DisplayName("플레이어는 덱으로부터 카드를 한 장 뽑는다.")
    @Test
    void drawTest() {
        // Given
        Deck deck = Deck.init(PlayingCards.init().value());
        Hand initHand = Hand.init();
        int totalScore = initHand.getTotalScore().value();
        Player player = new Player(new PlayerName("kelly"), initHand);

        // When
        player.draw(deck);

        // Then
        assertThat(totalScore).isNotEqualTo(initHand.getTotalScore());
    }
}
