package com.example.peter.toptrumps;

import java.util.ArrayList;

/**
 * Created by Peter on 10/11/2017.
 */

public class Player implements Playable {

    private String name;
    private ArrayList<Card> hand;
    // numWins for leader board
    private Integer numWins;

    public Player(String name) {
        this.name = name;
        // numWins for leader board
        this.numWins = 0;
        this.hand = new ArrayList<>();
    }


    // getters

    public int getNumCards(){
        return hand.size();
    }

    public Card getTopCard(){
        if (hand.size() > 0){
            return hand.get(0);
        }
        return null;
    }


    // setter

    public void addWin() {
        this.numWins++;
    }


    // implement interface

    @Override
    public Card addToHand(Card card) {
        hand.add(card);
        return card;
    }

    @Override
    public Card removeFromHand(Card card) {
        if (hand.contains(card)){
            hand.remove(card);
            return card;
        }
        return null;
    }
}
