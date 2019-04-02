package com.example.testPoker.model;

import com.example.testPoker.model.game.Card;
import com.example.testPoker.service.game.ScoreCalculator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class PlayerTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private Player player;

    @Mock
    private ScoreCalculator scoreCalculator;


    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCard() {
        //Given
        Card card = Mockito.mock(Card.class);
        List<Card> hand = Arrays.asList(card);
        when(scoreCalculator.checkHandScore(hand)).thenReturn(10);

        //When
        player.addCardToHand(card);

        //Then
        Assert.assertEquals(player.getScore(), 10);
    }
}
