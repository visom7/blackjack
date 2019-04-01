package com.example.testPoker.model.game;

public class Card {
    private String number;
    private Kind kind;

    public Card(String number, Kind kind) {
        this.number = number;
        this.kind = kind;
    }

    public String getNumber() {
        return number;
    }

    public String getKind() {
        return kind.kindSymbol();
    }

    public boolean isCourtCard() {
        return ("J".equals(number) || "Q".equals(number) || "K".equals(number));
    }

    public boolean isAce() {
        return "A".equals(number);
    }

    @Override
    public String toString() {
        return number + kind.kindSymbol();
    }
}
