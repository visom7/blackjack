package com.example.testPoker.model.game;

public enum Kind {
    CORAZONES("❤"),
    PICAS("♠"),
    TREBOLES("♣"),
    DIAMANTES("♦");

    private String kindSymbol;

    Kind(String kindSymbol) {
        this.kindSymbol = kindSymbol;
    }

    public String kindSymbol() {
        return kindSymbol;
    }
}
