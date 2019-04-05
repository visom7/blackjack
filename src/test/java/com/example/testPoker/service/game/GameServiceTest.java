package com.example.testPoker.service.game;

import com.example.testPoker.model.Player;
import com.example.testPoker.model.game.Card;
import com.example.testPoker.model.game.PlayerStatus;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    public void testGetStartingHandGeneric() {
        //Given
        List<Card> hand = new ArrayList<>();
        hand.add(Mockito.mock(Card.class));

        when(cardPicker.pickCardFromDeck(anyListOf(Card.class))).thenReturn(Mockito.mock(Card.class));
        when(player.getHand()).thenReturn(hand);
        when(scoreCalculator.isBlackJack(hand)).thenReturn(false);

        //When
        gameService.getStartingHand();

        //Then
        verify(player, times(1)).cleanHand();
        verify(player, times(1)).setPlayerStatus(PlayerStatus.UNDER_21);
        verify(player, times(2)).addCardToHand(any(Card.class));
        verify(player, times(0)).setPlayerStatus(PlayerStatus.BLACKJACK);
    }

    @Test
    public void testGetStartingHandWithBlackjack() {
        //Given
        List<Card> hand = new ArrayList<>();
        hand.add(Mockito.mock(Card.class));

        when(cardPicker.pickCardFromDeck(anyListOf(Card.class))).thenReturn(Mockito.mock(Card.class));
        when(player.getHand()).thenReturn(hand);
        when(scoreCalculator.isBlackJack(hand)).thenReturn(true);

        //When
        gameService.getStartingHand();

        //Then
        verify(player, times(1)).cleanHand();
        verify(player, times(1)).setPlayerStatus(PlayerStatus.UNDER_21);
        verify(player, times(2)).addCardToHand(any(Card.class));
        verify(player, times(1)).setPlayerStatus(PlayerStatus.BLACKJACK);
    }

    @DataProvider(name = "testGetCardDataProvider")
    public Object[][] testGetCardDataProvider() {
        return new Object[][]{
                {21, PlayerStatus.JUST_21},
                {22, PlayerStatus.OVER_21}
        };
    }

    @Test(dataProvider = "testGetCardDataProvider")
    public void testGetCardWithoutAddingCard(int score, PlayerStatus playerStatus) {
        //Given
        when(cardPicker.pickCardFromDeck(anyListOf(Card.class))).thenReturn(Mockito.mock(Card.class));
        when(player.getScore()).thenReturn(score);

        //When
        gameService.getCard();

        //Then
        verify(player, times(0)).addCardToHand(any(Card.class));
        verify(player, times(1)).setPlayerStatus(playerStatus);
    }

    @Test
    public void testGetCardWhenAddingCard() {
        //Given
        when(cardPicker.pickCardFromDeck(anyListOf(Card.class))).thenReturn(Mockito.mock(Card.class));
        when(player.getScore()).thenReturn(20);

        //When
        gameService.getCard();

        //Then
        verify(player, times(1)).addCardToHand(any(Card.class));
        verify(player, times(1)).setPlayerStatus(PlayerStatus.UNDER_21);
    }
}