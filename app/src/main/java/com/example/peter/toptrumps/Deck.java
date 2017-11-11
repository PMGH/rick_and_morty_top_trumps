package com.example.peter.toptrumps;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Peter on 10/11/2017.
 */

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        generateCards();
    }

    public void generateCards(){
        for (Character character : Character.values()){
            String name = character.getName();
            Integer intellect = character.getIntellect();
            Integer lethality = character.getLethality();
            Integer morality = character.getMorality();
            Integer howSchwifty = character.getHowSchwifty();
            String catchphrase = character.getCatchphrase();
            Integer imageSource = character.getImageSource();

            cards.add(new Card(name, intellect, lethality, morality, howSchwifty, catchphrase, imageSource));
        }
    }


    // getters

    public int getNumCards() {
        return cards.size();
    }

    // other behaviour

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card removeTopCard(){
        // if statement to handle out of bounds exceptions
        if (getNumCards() > 0){
            return cards.remove(0);
        }
        return null;
    }

}
