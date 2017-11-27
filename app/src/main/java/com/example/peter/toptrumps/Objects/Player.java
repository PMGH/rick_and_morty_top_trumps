package com.example.peter.toptrumps.Objects;

import com.example.peter.toptrumps.Interfaces.Playable;
import com.example.peter.toptrumps.Objects.Card;

import java.util.ArrayList;

/**
 * Created by Peter on 10/11/2017.
 */

public class Player implements Playable {

    private int id;

    private String name;
    private ArrayList<Card> hand;
    private Integer numWins;

    public Player(String name) {
        this.name = name;
        this.numWins = 0;
        this.hand = new ArrayList<>();
        // Extension:  this.human = true // human or bot - true or false
    }

    // DB constructor overload
    public Player(int id, String name, Integer numWins) {
        this.id = id;
        this.name = name;
        this.numWins = numWins;
        this.hand = new ArrayList<>();
    }


    // getters

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getNumCards(){
        return hand.size();
    }

    public Card getTopCard(){
        if (hand.size() > 0){
            return hand.get(0);
        }
        return null;
    }

    public Integer getNumWins() {
        return numWins;
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
