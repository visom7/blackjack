package com.example.testPoker.service;

import com.example.testPoker.model.game.Card;
import com.example.testPoker.model.game.Kind;
import com.example.testPoker.service.game.ScoreCalculator;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeMethod
    private void init() {
        scoreCalculator = new ScoreCalculator();
    }

    @DataProvider(name = "checkHandScoreDataProvider")
    public Object[][] checkHandScoreDataProvider() {
        return new Object[][]{
                {"5", 5},
                {"J", 10},
                {"Q", 10},
                {"K", 10},
                {"A", 11}
        };
    }

    @Test(dataProvider = "checkHandScoreDataProvider")
    public void testCheckHandScoreSingleCard(String number, int expectedScore) {
        //Given
        Card card = new Card(number, Mockito.any(Kind.class));
        List<Card> hand = Collections.singletonList(card);

        //When
        int actualScore = scoreCalculator.checkHandScore(hand);

        //Then
        Assert.assertEquals(actualScore, expectedScore);
    }

    @Test
    public void testCheckHandScoreDifferentAceValuesWhen21() {
        //Given
        Card aceCard = new Card("A", Mockito.any(Kind.class));
        Card card1 = new Card("10", Mockito.any(Kind.class));

        List<Card> hand = Arrays.asList(aceCard, card1);

        //When
        int actualScore = scoreCalculator.checkHandScore(hand);

        //Then
        Assert.assertEquals(actualScore, 21);
    }

    @Test
    public void testCheckHandScoreDifferentAceValuesWhenOver21() {
        //Given
        Card aceCard = new Card("A", Mockito.any(Kind.class));
        Card card1 = new Card("10", Mockito.any(Kind.class));
        Card card2 = new Card("3", Mockito.any(Kind.class));

        List<Card> hand = Arrays.asList(aceCard, card2, card1);

        //When
        int actualScore = scoreCalculator.checkHandScore(hand);

        //Then
        Assert.assertEquals(actualScore, 14);
    }

    @Test
    public void testCheckHandScoreDifferentAceValuesWhenSeveralAces() {
        //Given
        Card aceCard = new Card("A", Mockito.any(Kind.class));
        Card card1 = new Card("A", Mockito.any(Kind.class));
        Card card2 = new Card("A", Mockito.any(Kind.class));
        Card card3 = new Card("9", Mockito.any(Kind.class));

        List<Card> hand = Arrays.asList(card2, card1, aceCard, card3);

        //When
        int actualScore = scoreCalculator.checkHandScore(hand);

        //Then
        Assert.assertEquals(actualScore, 12);
    }

    @DataProvider(name = "isBlackjackeDataProvider")
    public Object[][] isBlackjackeDataProvider() {
        return new Object[][]{
                {"J", "A", true},
                {"Q", "A", true},
                {"K", "A", true},
                {"K", "A", true},
                {"2", "A", false},
                {"K", "2", false},
                {"J", "J", false},
                {"A", "A", false},
                {"3", "8", false}
        };
    }

    @Test(dataProvider = "isBlackjackeDataProvider")
    public void testIsBlackjack(String number1, String number2, boolean expectedBlackjack) {
        //Given
        Card card1 = new Card(number1, Mockito.any(Kind.class));
        Card card2 = new Card(number2, Mockito.any(Kind.class));

        List<Card> hand = Arrays.asList(card1, card2);

        //When
        boolean actualBlackjack = scoreCalculator.isBlackJack(hand);

        //Then
        Assert.assertEquals(actualBlackjack, expectedBlackjack);
    }

    @Test
    public void testIsBlackjackWhenMoreThanTwoCard() {
        //Given
        Card courtCard = new Card("J", Mockito.any(Kind.class));
        Card aceCard = new Card("A", Mockito.any(Kind.class));
        Card anotherCard = new Card("2", Mockito.any(Kind.class));

        List<Card> hand = Arrays.asList(courtCard, aceCard, anotherCard);

        //When
        boolean actualBlackjack = scoreCalculator.isBlackJack(hand);

        //Then
        Assert.assertFalse(actualBlackjack);
    }
}
