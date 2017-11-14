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
    int counter;

    @Before
    public void before(){
        deck = new Deck();
        counter = 0;
        for (Character character : Character.values()){
            counter++;
        }
    }

    @Test
    public void hasCards(){
        assertNotNull(deck.getNumCards());
        assertEquals(counter, deck.getNumCards());
    }

    @Test
    public void canAddToDeck(){
        // e.g. returning cards to dealer
        Card card = new Card("Scary Terry", 30, 80, 28, 5, "You can run, but you can't hide, BITCH!", R.drawable.scary_terry);
        deck.addToDeck(card);
        assertEquals(counter + 1, deck.getNumCards());
    }

    @Test
    public void canRemoveTopCard(){
        deck.removeTopCard();
        assertEquals(counter - 1, deck.getNumCards());
    }

    @Test
    public void returnsNullWhenAllCardsRemoved(){
        // remove all cards
        for (int i = 0; i < counter + 1; i++){
            deck.removeTopCard();
        }

        // remove another
        assertEquals(null, deck.removeTopCard());
    }
}
