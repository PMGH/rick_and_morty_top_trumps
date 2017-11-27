package com.example.peter.toptrumps;

import com.example.peter.toptrumps.Objects.Card;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Peter on 10/11/2017.
 */

public class GameTest {

    Game game;

    @Before
    public void before(){
        game = Game.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        try {
            // load class
            Class c = Class.forName("com.example.peter.toptrumps.Game");
            // get all constructors (whether public or private)
            Constructor[] constructors = c.getDeclaredConstructors();
            // suppress access check errors
            constructors[0].setAccessible(true);
            // instance
            Field field = Game.class.getDeclaredField("INSTANCE");
            field.setAccessible(true);
            field.set(game, constructors[0].newInstance());
            field.setAccessible(false);
        } catch (ClassNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
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
