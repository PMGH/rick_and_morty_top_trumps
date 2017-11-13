package com.example.peter.toptrumps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TopCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_card);

        Game game = Game.getInstance();

        Card userCard = game.getPlayer1().getTopCard();


    }
}
