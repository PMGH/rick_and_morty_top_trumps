package com.example.peter.toptrumps;

import java.util.ArrayList;

/**
 * Created by Peter on 10/11/2017.
 */

public interface Playable {

    String getName();
    ArrayList<Card> getHand();
    int getNumCards();
    Card getTopCard();
    Integer getNumWins();

    Card addToHand(Card card);
    Card removeFromHand(Card card);
    void addWin();

}
