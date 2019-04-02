package com.example.testPoker.model;

import com.example.testPoker.model.game.Card;
import com.example.testPoker.model.game.PlayerStatus;
import com.example.testPoker.service.game.ScoreCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Player {

    @Autowired
    private ScoreCalculator scoreCalculator;

    private List<Card> hand = new ArrayList<>();
    private int score;
    private PlayerStatus playerStatus;

    public void addCardToHand(Card card) {
        hand.add(card);
        score = scoreCalculator.checkHandScore(hand);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void cleanHand() {
        hand.clear();
        score = 0;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
}
