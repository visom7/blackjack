package com.example.testPoker.service.game;

import com.example.testPoker.model.game.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CardPicker {

    private Random random = new Random();

    public Card pickCardFromDeck(List<Card> deck) {
        int maxIndex = deck.size();
        Card card = deck.get(random.nextInt(maxIndex));
        deck.remove(card);
        return card;
    }
}
