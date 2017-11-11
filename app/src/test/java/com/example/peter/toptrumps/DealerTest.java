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

    @Before
    public void before(){
        dealer = new Dealer("CPU David");
        player = new Player("Peter");
    }

    @Test
    public void hasDeck(){
        assertNotNull(dealer.getDeck());
        assertEquals(10, dealer.getDeckSize());
    }

    @Test
    public void canDealTopCard(){
        dealer.dealTopCard();
        assertEquals(9, dealer.getDeckSize());
    }

    @Test
    public void canDealTopCardToPlayer(){
        player.addToHand(dealer.dealTopCard());
        assertEquals(9, dealer.getDeckSize());
        assertEquals(1, player.getNumCards());
    }
}
