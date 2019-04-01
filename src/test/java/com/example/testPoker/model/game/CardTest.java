package com.example.testPoker.model.game;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CardTest {

    @Test
    public void testCardConstruction() {
        //When
        Card actualCard = new Card("5", Kind.PICAS);
        //Then
        Assert.assertEquals(actualCard.getNumber(), "5");
        Assert.assertEquals(actualCard.getKind(), Kind.PICAS.kindSymbol());
    }

    @DataProvider(name = "testCardIsCourtCardDataProvider")
    public Object[][] testCardIsCourtCardDataProvider() {
        return new Object[][]{
                {"5", false},
                {"J", true},
                {"Q", true},
                {"K", true},
                {"A", false}
        };
    }

    @Test(dataProvider = "testCardIsCourtCardDataProvider")
    public void testCardIsCourtCard(String number, boolean isCourtCardExpected) {
        //Given
        Card actualCard = new Card(number, Kind.PICAS);
        //When
        boolean isCourtCardActual = actualCard.isCourtCard();
        //Then
        Assert.assertEquals(isCourtCardActual, isCourtCardExpected);
    }

    @DataProvider(name = "testCardIsAceCardDataProvider")
    public Object[][] testCardIsAceCardDataProvider() {
        return new Object[][]{
                {"5", false},
                {"A", true}
        };
    }

    @Test(dataProvider = "testCardIsAceCardDataProvider")
    public void testCardIsAceCard(String number, boolean isAce) {
        //Given
        Card actualCard = new Card(number, Kind.PICAS);
        //When
        boolean isAceCard = actualCard.isAce();
        //Then
        Assert.assertEquals(isAceCard, isAce);
    }
}