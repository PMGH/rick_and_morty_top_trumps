package com.example.peter.toptrumps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Peter on 10/11/2017.
 */

public class GameTest {

    Game game;
    Game spyGame;

    @Before
    public void before(){
        game = Game.getInstance();
        spyGame = Mockito.spy(game);
    }

    @After
    public void tearDown() throws Exception {
        // reflection - accessing a private method and setting it to accessible then resetting game instance to a new game
        Field field = Game.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(game, new Game());
    }

    @Test
    public void startsWithTwoPlayers(){
        game.start("Peter");
        assertEquals(2, game.getNumPlayers());
    }

    @Test
    public void dealerDealsAllCards(){
        game.start("Peter");
        int remainder = game.getDeckSize() % game.getNumPlayers();
        assertEquals(remainder, game.getDeckSize());
    }

    @Test
    public void playersHaveCards(){
        game.start("Peter");
        // dealer
        assertNotNull(game.getPlayers().get(0).getNumCards());
        // player1
        assertNotNull(game.getPlayers().get(1).getNumCards());
    }

    @Test
    public void turnStartsLeftOfDealer(){
        game.start("Peter");
        assertEquals("Player", game.getPlayerTurn().getClass().getSimpleName());
    }

    @Test
    public void canGetCategoryValue(){
        Card card = new Card ("Rick Sanchez", 95, 80, 40, 9, "WUBBA LUBBA DUB DUB", R.drawable.rick_sanchez);

        assertEquals(95, game.getCardCategoryValue(card, "Intellect"));

        assertEquals(80, game.getCardCategoryValue(card, "Lethality"));

        assertEquals(40, game.getCardCategoryValue(card, "Morality"));

        assertEquals(9, game.getCardCategoryValue(card, "How Schwifty"));
    }

    @Test
    public void canAddToPile() {
        game.start("Peter");
        game.addToPile();
        // adds top card of each player to pile
        assertEquals(game.getNumPlayers(), game.getPileSize());
    }

    @Test
    public void gameCanBeWon(){
        game.start("Peter");
        game.play();
        assertEquals(true, game.isGameWon());
    }

}
