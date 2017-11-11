package com.example.peter.toptrumps;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
        game = new Game();
        game.start();
    }


    // game start tests

    @Test
    public void startsWithDealer(){
        assertEquals("Player", game.getPlayerTurn().getClass().getSimpleName());
    }

    @Test
    public void startsWithThreePlayers(){
        assertEquals(3, game.getNumPlayers());
    }

    @Test
    public void dealerDealsAllCards(){
        assertEquals(0, game.getDeckSize());
    }

    @Test
    public void playersHaveCards(){
        // dealer
        assertNotNull(game.getPlayers().get(0).getNumCards());
        // player1
        assertNotNull(game.getPlayers().get(1).getNumCards());
        // player3
        assertNotNull(game.getPlayers().get(2).getNumCards());
    }

    @Test
    public void turnStartsLeftOfDealer(){
        assertEquals("Player", game.getPlayerTurn().getClass().getSimpleName());
    }


    // ancillary functions

//    @Test
//    public void canGenerateRandom(){
//        // mockito?
//    }

//    @Test
//    public void canSelectCategory(){
//
//    }

//    @Test
//    public void canGetCardCategoryValue(){
//        // mockito?
//    }

    @Test
    public void pileStartsEmpty(){
        assertEquals(0, game.getPileSize());
    }

    @Test
    public void canAddToPile() {
        // adds top card of each player to pile
        game.addToPile();
        assertEquals(game.getNumPlayers(), game.getPileSize());
    }

    @Test
    public void canSelectCategory(){
        game.setCategory("Lethality");
        assertEquals("Lethality", game.getCategory());
    }

    @Test
    public void gameCanBeWon(){
        game.play();
        assertEquals(true, game.isGameWon());
    }

}
