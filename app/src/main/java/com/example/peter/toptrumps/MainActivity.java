package com.example.peter.toptrumps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView roundNumberText;
    TextView cpuNameText;
    TextView cpuWinsText;
    TextView cpuNumCardsText;
    TextView userNameText;
    TextView userWinsText;
    TextView userNumCardsText;
    String userName;
    int id;
    DBHelper dbHelper;
    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();

        id = extras.getInt("id");

        game = Game.getInstance();

        dbHelper = new DBHelper(this);
        Playable player = dbHelper.getPlayerById(id);
        userName = player.getName();

        game.start(userName);

        // round number
        roundNumberText = (TextView) findViewById(R.id.round_number_text);
        String round = "Round: " + game.getRoundNumber().toString();
        roundNumberText.setText(round);

        // user
        userNameText = (TextView) findViewById(R.id.user_name_text);
        userNameText.setText(userName);

        userWinsText = (TextView) findViewById(R.id.user_wins_text);
        String userWins = "Game Wins: " + player.getNumWins().toString();
        userWinsText.setText(userWins);

        userNumCardsText = (TextView) findViewById(R.id.user_num_cards_text);
        // add column to DB table?
        Integer userNumCards = Integer.valueOf(game.getPlayer1().getNumCards());
        String userNumCardsStr = "Number of Cards: " + userNumCards.toString();
        userNumCardsText.setText(userNumCardsStr);


        // CPU
        cpuNameText = (TextView) findViewById(R.id.CPU_user_name_text);
        cpuNameText.setText(game.getDealer().getName());

        cpuWinsText = (TextView) findViewById(R.id.CPU_user_wins_text);
        String cpuWins = "Game Wins: " + game.getDealer().getNumWins().toString();
        cpuWinsText.setText(cpuWins);

        cpuNumCardsText = (TextView) findViewById(R.id.CPU_user_num_cards_text);
        Integer cpuNumCards = Integer.valueOf(game.getDealer().getNumCards());
        String cpuNumCardsStr = "Number of Cards: " + cpuNumCards.toString();
        cpuNumCardsText.setText(cpuNumCardsStr);
    }

    public void playRoundButtonOnClick(View button){
        if ((game.getPlayerTurn().getName()).equals(userName)){
            initializeIntent();
        }
    }

    public void initializeIntent(){
        Intent intent = new Intent(MainActivity.this, TopCardActivity.class);
        startActivity(intent);
    }
}
