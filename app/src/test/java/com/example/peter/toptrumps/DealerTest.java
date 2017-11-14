package com.example.peter.toptrumps;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Peter on 10/11/2017.
 */

public class DealerTest {

    Dealer dealer;
    Player player;
    int counter;

    @Before
    public void before(){
        dealer = new Dealer("CPU David");
        player = new Player("Peter");

        counter = 0;
        for (Character character : Character.values()){
            counter++;
        }
    }

    @Test
    public void hasDeck(){
        assertNotNull(dealer.getDeck());
        assertEquals(counter, dealer.getDeckSize());
    }

    @Test
    public void canDealTopCard(){
        dealer.dealTopCard();

        assertEquals(counter - 1, dealer.getDeckSize());
    }

    @Test
    public void canDealTopCardToPlayer(){
        player.addToHand(dealer.dealTopCard());

        assertEquals(counter - 1, dealer.getDeckSize());
        assertEquals(1, player.getNumCards());
    }
}
