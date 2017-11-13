package com.example.peter.toptrumps;

import java.util.ArrayList;

/**
 * Created by Peter on 10/11/2017.
 */

public interface Playable {

    String getName();
    Card removeFromHand(Card card);
    Card addToHand(Card card);
    int getNumCards();
    Card getTopCard();
    void addWin();
    Integer getNumWins();

}
