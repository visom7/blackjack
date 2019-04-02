package com.example.testPoker.service.game;

import com.example.testPoker.model.Player;
import com.example.testPoker.model.game.Card;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class GameServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private GameService gameService;

    @Mock
    private Player player;
    @Mock
    private Player crupier;
    @Mock
    private DeckGenerator deckGenerator;
    @Mock
    private CardPicker cardPicker;
    @Mock
    private ScoreCalculator scoreCalculator;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDeck() {
        //Given
        List<Card> expectedDeck = new ArrayList<>();
        when(deckGenerator.createDeck()).thenReturn(expectedDeck);

        //When
        List<Card> actualDeck = deckGenerator.createDeck();

        //Then
        Assert.assertEquals(actualDeck, expectedDeck);
    }

    @Test
    public void testInitGame() {
        //Given
        Card card = Mockito.mock(Card.class);
        List<Card> expectedDeck = new ArrayList<>();
        expectedDeck.add(card);

        when(deckGenerator.createDeck()).thenReturn(expectedDeck);

        //When
        int size = gameService.initGame();

        //Then
        Assert.assertEquals(size, 4);
    }
}