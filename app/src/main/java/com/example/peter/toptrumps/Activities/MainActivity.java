package com.example.peter.toptrumps.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.peter.toptrumps.Game;
import com.example.peter.toptrumps.Interfaces.Playable;
import com.example.peter.toptrumps.Objects.Dealer;
import com.example.peter.toptrumps.Objects.Player;
import com.example.peter.toptrumps.R;

public class MainActivity extends AppCompatActivity {

    TextView roundNumberText;
    TextView cpuNameText;
    TextView cpuWinsText;
    TextView cpuNumCardsText;
    TextView cardPileTotalText;
    TextView userNameText;
    TextView userWinsText;
    TextView userNumCardsText;

    Game game;
    Player player;
    Dealer dealer;
    Playable turn;
    Integer cardPileTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main Activity", "Made it to the main activity intent");

        game = Game.getInstance();

        dealer = game.getDealer();
        player = game.getPlayer1();

        turn = game.getPlayerTurn();
        cardPileTotal = game.getDrawPileSize();

        // round number
        roundNumberText = (TextView) findViewById(R.id.round_number_text);
        String roundStr = "Round " + game.getRoundNumber().toString() + " (" + turn.getName() + "\'s Turn)";
        roundNumberText.setText(roundStr);

        // cards in pile
        cardPileTotalText = (TextView) findViewById(R.id.number_cards_in_pile_text);
        String cardPileTotalTextStr = cardPileTotal + " Cards in the Pile";
        cardPileTotalText.setText(cardPileTotalTextStr);

        // user
        userNameText = (TextView) findViewById(R.id.user_name_text);
        userNameText.setText(player.getName());

        userWinsText = (TextView) findViewById(R.id.user_wins_text);
        String userWinsStr = "Game Wins:  " + player.getNumWins().toString();
        userWinsText.setText(userWinsStr);

        userNumCardsText = (TextView) findViewById(R.id.user_num_cards_text);
        // add column to DB table?
        Integer userNumCards = Integer.valueOf(player.getNumCards());
        String userNumCardsStr = "Number of Cards:  " + userNumCards.toString();
        userNumCardsText.setText(userNumCardsStr);


        // CPU
        cpuNameText = (TextView) findViewById(R.id.CPU_user_name_text);
        cpuNameText.setText(dealer.getName());

        cpuWinsText = (TextView) findViewById(R.id.CPU_user_wins_text);
        String cpuWins = "Game Wins:  " + dealer.getNumWins().toString();
        cpuWinsText.setText(cpuWins);

        cpuNumCardsText = (TextView) findViewById(R.id.CPU_user_num_cards_text);
        Integer cpuNumCards = Integer.valueOf(dealer.getNumCards());
        String cpuNumCardsStr = "Number of Cards:  " + cpuNumCards.toString();
        cpuNumCardsText.setText(cpuNumCardsStr);
    }

    public void playRoundButtonOnClick(View button){
        if ((turn).equals(player)){
            initializeTopCardIntent();
        } else {
            initializeResultIntent();
        }
    }

    public void initializeTopCardIntent(){
        Intent topCardActivityintent = new Intent(MainActivity.this, TopCardActivity.class);
        startActivity(topCardActivityintent);
    }

    public void initializeResultIntent(){
        Intent resultActivityIntent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(resultActivityIntent);
    }
}
