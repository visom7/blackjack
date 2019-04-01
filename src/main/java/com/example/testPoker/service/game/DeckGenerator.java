package com.example.testPoker.service.game;

import com.example.testPoker.model.game.Card;
import com.example.testPoker.model.game.Kind;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeckGenerator {

    public List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Integer i = 0; i < 4; i++) {
            for (Integer j = 1; j < 14; j++) {
                switch (i) {
                    case 0:
                        if (j > 1 && j < 11) {
                            addCardWith(deck, j.toString(), Kind.PICAS);
                        } else if (j.equals(11)) {
                            addCardWith(deck, "J", Kind.PICAS);
                        } else if (j.equals(12)) {
                            addCardWith(deck, "Q", Kind.PICAS);
                        } else if (j.equals(13)) {
                            addCardWith(deck, "K", Kind.PICAS);
                        } else if (j.equals(1)) {
                            addCardWith(deck, "A", Kind.PICAS);
                        }
                        break;
                    case 1:
                        if (j > 1 && j < 11) {
                            addCardWith(deck, j.toString(), Kind.CORAZONES);
                        } else if (j.equals(11)) {
                            addCardWith(deck, "J", Kind.CORAZONES);
                        } else if (j.equals(12)) {
                            addCardWith(deck, "Q", Kind.CORAZONES);
                        } else if (j.equals(13)) {
                            addCardWith(deck, "K", Kind.CORAZONES);
                        } else if (j.equals(1)) {
                            addCardWith(deck, "A", Kind.CORAZONES);
                        }
                        break;
                    case 2:
                        if (j > 1 && j < 11) {
                            addCardWith(deck, j.toString(), Kind.DIAMANTES);
                        } else if (j.equals(11)) {
                            addCardWith(deck, "J", Kind.DIAMANTES);
                        } else if (j.equals(12)) {
                            addCardWith(deck, "Q", Kind.DIAMANTES);
                        } else if (j.equals(13)) {
                            addCardWith(deck, "K", Kind.DIAMANTES);
                        } else if (j.equals(1)) {
                            addCardWith(deck, "A", Kind.DIAMANTES);
                        }
                        break;
                    case 3:
                        if (j > 1 && j < 11) {
                            addCardWith(deck, j.toString(), Kind.TREBOLES);
                        } else if (j.equals(11)) {
                            addCardWith(deck, "J", Kind.TREBOLES);
                        } else if (j.equals(12)) {
                            addCardWith(deck, "Q", Kind.TREBOLES);
                        } else if (j.equals(13)) {
                            addCardWith(deck, "K", Kind.TREBOLES);
                        } else if (j.equals(1)) {
                            addCardWith(deck, "A", Kind.TREBOLES);
                        }
                        break;
                }
            }
        }
        return deck;
    }

    private List<Card> addCardWith(List<Card> cards, String number, Kind kind) {
        cards.add(new Card(number, kind));
        return cards;
    }
}
