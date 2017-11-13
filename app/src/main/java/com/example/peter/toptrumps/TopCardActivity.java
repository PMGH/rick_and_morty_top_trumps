package com.example.peter.toptrumps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TopCardActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_card);

        Log.d("TopCard Activity", "On TopCard Activity");
//        game = Game.getInstance();

//        Card userCard = game.getPlayer1().getTopCard();

    }
}
