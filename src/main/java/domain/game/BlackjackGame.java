package domain.game;

import domain.participant.*;
import domain.playingcard.Deck;
import domain.playingcard.PlayingCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toMap;

public class BlackjackGame {
    private static final int DEALER_INDEX = 0;
    private static final List<Player> DECLINE_DRAWING_PLAYERS = new ArrayList<>();

    private final List<Participant> participants;
    private final Deck deck;

    private BlackjackGame(final List<Participant> participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame init(final Map<PlayerName, BettingMoney> playerNameAndBettingMoney) {
        Deck deck = Deck.init(PlayingCards.getValue());
        List<Participant> participants = new ArrayList<>();
        participants.add(Dealer.init(deck));
        playerNameAndBettingMoney.forEach((playerName, bettingMoney) ->
                participants.add(Player.of(playerName, bettingMoney, deck)));

        return new BlackjackGame(unmodifiableList(participants), deck);
    }

    public boolean hasDrawablePlayer() {
        return getPlayers().stream()
                .anyMatch(player ->
                        player.isDrawable() && !DECLINE_DRAWING_PLAYERS.contains(player));
    }

    public Optional<Player> findDrawablePlayer() {
        return getPlayers().stream()
                .filter(player ->
                        player.isDrawable() && !DECLINE_DRAWING_PLAYERS.contains(player))
                .findAny();
    }

    public boolean drawPlayerIfAccept(final Player player, final boolean isAccept) {
        if (isAccept) {
            player.draw(deck);
        }

        if (!isAccept) {
            DECLINE_DRAWING_PLAYERS.add(player);
        }

        return isAccept;
    }

    public boolean hasDrawableDealer() {
        return participants.get(DEALER_INDEX)
                .isDrawable();
    }

    public void playDealer() {
        Dealer dealer = getDealer();
        while (dealer.isDrawable()) {
            dealer.draw(deck);
        }
    }

    public Map<PlayerName, Integer> getPlayerRevenues() {
        Dealer dealer = getDealer();
        return getPlayers().stream()
                .collect(toMap(Player::getPlayerName, player -> player.calculateRevenue(dealer)));
    }

    public Dealer getDealer() {
        return (Dealer) participants.get(DEALER_INDEX);
    }

    public List<Player> getPlayers() {
        return IntStream.range(DEALER_INDEX + 1, participants.size())
                .mapToObj(index -> (Player) participants.get(index))
                .toList();
    }
}
