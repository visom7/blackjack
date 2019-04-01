package com.example.testPoker.model;

import com.example.testPoker.model.game.Card;
import com.example.testPoker.service.game.ScoreCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PlayerTest {

    @MockBean(name = "scoreCalculator")
    private ScoreCalculator scoreCalculator;

    @Test
    public void testPlayerConstruction() {
        //When
        Player player = new Player();
        //Then
        Assert.assertEquals(player.getHand(), Collections.emptyList());
        Assert.assertEquals(player.getScore(), 0);
        Assert.assertNull(player.getPlayerStatus());
    }

    @Test
    public void testAddCard() {
        //Given
        Card card = Mockito.mock(Card.class);
        List<Card> hand = Arrays.asList(card);
        when(scoreCalculator.checkHandScore(hand)).thenReturn(10);

        Player player = new Player();

        //When
        player.addCardToHand(card);

        //Then
        Assert.assertEquals(player.getScore(), 10);
    }
}
