package com.example.peter.toptrumps;

/**
 * Created by Peter on 13/11/2017.
 */

public class Runner {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.start("Ally");
        game.play();
    }
}
