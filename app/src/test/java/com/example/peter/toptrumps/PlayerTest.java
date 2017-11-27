package com.example.peter.toptrumps;

import com.example.peter.toptrumps.Objects.Card;
import com.example.peter.toptrumps.Objects.Deck;
import com.example.peter.toptrumps.Objects.Player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Peter on 10/11/2017.
 */

public class PlayerTest {

    Player player;
    Deck deck;

    @Before
    public void before(){
        player = new Player("Peter");
        deck = new Deck();
    }

    @Test
    public void handStartsEmpty(){
        assertEquals(0, player.getNumCards());
    }

    @Test
    public void canAddToHand(){
        player.addToHand(deck.removeTopCard());

        assertEquals(1, player.getNumCards());
    }

    @Test
    public void canRemoveFromHand(){
        Card card = deck.removeTopCard();
        player.addToHand(card);
        assertEquals(1, player.getNumCards());

        player.removeFromHand(card);
        assertEquals(0, player.getNumCards());
    }

    @Test
    public void returnsNullIfPlayedCardNotInHand(){
        Card card = deck.removeTopCard();
        Card card2 = deck.removeTopCard();
        player.addToHand(card);
        assertEquals(1, player.getNumCards());

        assertEquals(null, player.removeFromHand(card2));
    }
}
