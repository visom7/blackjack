package com.example.testPoker.service.game;

import com.example.testPoker.model.game.Card;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class CardPickerTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private CardPicker cardPicker;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCardPick() {
        //Given
        Card expectedCard = Mockito.mock(Card.class);
        List<Card> deck = new ArrayList<>();
        deck.add(expectedCard);

        //When
        Card actualCard = cardPicker.pickCardFromDeck(deck);

        //Then
        Assert.assertEquals(actualCard, expectedCard);
        Assert.assertEquals(deck.size(), 0);
    }
}
