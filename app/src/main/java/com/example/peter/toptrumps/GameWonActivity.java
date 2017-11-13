package com.example.peter.toptrumps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.peter.toptrumps.R.id.game_result_text;

public class GameWonActivity extends AppCompatActivity {

    TextView gameResultText;
    Game game;
    Playable gameWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        game = Game.getInstance();

        gameResultText = (TextView) findViewById(game_result_text);
        gameWinner = game.getWinner();
        String gameWinnerStr = gameWinner.getName() + " Won!";
        gameResultText.setText(gameWinnerStr);
    }

    public void playAgainOnClick(View button){
        Intent rulesIntent = new Intent(GameWonActivity.this, RulesActivity.class);
        startActivity(rulesIntent);
    }
}