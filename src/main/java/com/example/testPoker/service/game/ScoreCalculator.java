package com.example.testPoker.service.game;

import com.example.testPoker.model.game.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScoreCalculator {

    public int checkHandScore(List<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            if ("J".equals(card.getNumber()) || "Q".equals(card.getNumber()) || "K".equals(card.getNumber())) {
                score = score + 10;
            } else if ("A".equals(card.getNumber())) {
                score = score + 11;
            } else {
                score = score + Integer.valueOf(card.getNumber());
            }
        }

        return evaluateAces(hand, score);
    }

    public boolean isBlackJack(List<Card> playersHand) {
        boolean containsCourtCard = false;
        boolean containsAce = false;

        if (2 == playersHand.size()) {
            for (Card card : playersHand) {
                if (card.isCourtCard()) {
                    containsCourtCard = true;
                } else if (card.isAce()) {
                    containsAce = true;
                }
            }
        }

        return (containsAce && containsCourtCard);
    }

    private int evaluateAces(List<Card> hand, int score) {
        int numberOfAces = 0;
        int actualScore = score;
        for (Card card : hand) {
            if (card.isAce()) {
                numberOfAces++;
            }
        }
        for (int i = 0; i < numberOfAces; i++) {
            if (actualScore > 21) {
                actualScore = actualScore - 10;
            }
        }

        return actualScore;
    }
}
