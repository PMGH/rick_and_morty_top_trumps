package com.example.peter.toptrumps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ResultActivity extends AppCompatActivity {

    Card userCard;
    Card cpuCard;
    Game game;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        category = extras.getString("category");

        game = Game.getInstance();

        userCard = game.getPlayer1().getTopCard();
        cpuCard = game.getDealer().getTopCard();
    }
}
