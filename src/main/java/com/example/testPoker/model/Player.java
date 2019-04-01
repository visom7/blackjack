package com.example.testPoker.model;

import com.example.testPoker.model.game.Card;
import com.example.testPoker.model.game.PlayerStatus;
import com.example.testPoker.model.utils.SpringContext;
import com.example.testPoker.service.game.ScoreCalculator;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand;
    private int score;
    private PlayerStatus playerStatus;

    public Player() {
        hand = new ArrayList<>();
        score = 0;
    }

    public void addCardToHand(Card card) {
        ApplicationContext context = SpringContext.getApplicationContext();
        ScoreCalculator scoreCalculator = (ScoreCalculator) context.getBean("scoreCalculator");
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
