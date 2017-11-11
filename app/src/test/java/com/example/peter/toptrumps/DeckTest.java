package com.example.peter.toptrumps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Peter on 10/11/2017.
 */

public class DeckTest {

    Deck deck;

    @Before
    public void before(){
        deck = new Deck();
    }

    @Test
    public void hasCards(){
        assertNotNull(deck.getNumCards());
        assertEquals(10, deck.getNumCards());
    }

    @Test
    public void canRemoveTopCard(){
        deck.removeTopCard();
        assertEquals(9, deck.getNumCards());
    }

    @Test
    public void returnsNullWhenAllCardsRemoved(){
        // remove all 10 cards
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();
        deck.removeTopCard();

        // remove another
        assertEquals(null, deck.removeTopCard());
    }
}
