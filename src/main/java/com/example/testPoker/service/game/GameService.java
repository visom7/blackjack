package com.example.testPoker.service.game;

import com.example.testPoker.model.Player;
import com.example.testPoker.model.game.Card;
import com.example.testPoker.model.game.PlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private static final int STARTING_CARDS = 2;

    private List<Card> blackjackDeck;

    @Autowired
    private Player player;
    @Autowired
    private Player croupier;
    @Autowired
    private DeckGenerator deckGenerator;
    @Autowired
    private CardPicker cardPicker;
    @Autowired
    private ScoreCalculator scoreCalculator;


    public int initGame() {
        blackjackDeck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Card> simpleDeck = deckGenerator.createDeck();
            blackjackDeck.addAll(simpleDeck);
        }
        return blackjackDeck.size();
    }

    public void getStartingHand() {
        player.cleanHand();
        player.setPlayerStatus(PlayerStatus.UNDER_21);
        for (int i = 0; i < STARTING_CARDS; i++) {
            player.addCardToHand(cardPicker.pickCardFromDeck(blackjackDeck));
        }
        if (scoreCalculator.isBlackJack(player.getHand())) {
            player.setPlayerStatus(PlayerStatus.BLACKJACK);
        }
    }

    public void getCard() {
        if (player.getScore() < 21) {
            player.addCardToHand(cardPicker.pickCardFromDeck(blackjackDeck));
        }
        if (player.getScore() > 21) {
            player.setPlayerStatus(PlayerStatus.OVER_21);
        } else if (player.getScore() == 21) {
            player.setPlayerStatus(PlayerStatus.JUST_21);
        } else {
            player.setPlayerStatus(PlayerStatus.UNDER_21);
        }
    }

    public boolean stand() {
        return playCrupier();
    }

    private boolean playCrupier() {
        boolean croupierWins;

        croupier.cleanHand();
        for (int i = 0; i < STARTING_CARDS; i++) {
            croupier.addCardToHand(cardPicker.pickCardFromDeck(blackjackDeck));
        }

        while (croupier.getScore() < 17 && !(croupier.getScore() >= 21) && croupier.getHand().size() < 12) {
            croupier.addCardToHand(cardPicker.pickCardFromDeck(blackjackDeck));
        }

        if (croupier.getScore() <= 21 && (croupier.getScore() >= player.getScore())) {
            croupierWins = true;
        } else {
            croupierWins = false;
        }

        return croupierWins;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getCroupier() {
        return croupier;
    }
}
