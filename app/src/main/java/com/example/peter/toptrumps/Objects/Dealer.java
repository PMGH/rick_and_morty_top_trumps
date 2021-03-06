package com.example.peter.toptrumps.Objects;

import com.example.peter.toptrumps.Interfaces.Playable;

/**
 * Created by Peter on 10/11/2017.
 */

public class Dealer extends Player implements Playable {

    private Deck deck;

    public Dealer(String name) {
        super(name);
        this.deck = new Deck();
    }


    // getters

    public int getDeckSize(){
        return deck.getNumCards();
    }

    public Deck getDeck(){
        return deck;
    }


    // other behaviour

    public boolean shuffle(){
        deck.shuffle();
        return true;
    }

    public void addToDeck(Card card){
        deck.addToDeck(card);
    }

    public Card dealTopCard(){
        return deck.removeTopCard();
    }
}
