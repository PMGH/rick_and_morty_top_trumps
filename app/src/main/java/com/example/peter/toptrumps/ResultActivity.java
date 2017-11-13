package com.example.peter.toptrumps;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView roundResultText;
    TextView roundCategoryText;
    TextView roundWinDeclarationText;

    TextView cpuNameText;
    TextView cpuTopCardName;
    ImageView cpuTopCardImage;
    TextView cpuIntellectText;
    TextView cpuLethalityText;
    TextView cpuMoralityText;
    TextView cpuHowSchwiftyText;

    TextView userNameText;
    TextView userTopCardName;
    ImageView userTopCardImage;
    TextView userIntellectText;
    TextView userLethalityText;
    TextView userMoralityText;
    TextView userHowSchwiftyText;

    Card userCard;
    Card cpuCard;
    Game game;
    String category;
    String roundWinnerName;
    Integer roundWinnerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent resultActivityIntent = getIntent();

        Log.d("Result Activity", "On Result Activity");
        if (resultActivityIntent.hasExtra("category")){
            Log.d("Result Activity", "Inside if");
            Bundle extras = resultActivityIntent.getExtras();
            String roundCategory = extras.getString("category");

            game = Game.getInstance();
            category = game.playerSetCategory(roundCategory);
            Log.d("Result Activity", "Player round played successfully");
        } else {
            Log.d("Result Activity", "Inside else");
            game = Game.getInstance();
            category = game.getBestCategory();
            Log.d("Result Activity", "CPU round played successfully");
        }

        game = Game.getInstance();

        userCard = game.getPlayer1().getTopCard();
        cpuCard = game.getDealer().getTopCard();

        roundResultText = (TextView) findViewById(R.id.round_result_title_text);
        String roundResultTextStr = "Round " + game.getRoundNumber() + " Result";
        roundResultText.setText(roundResultTextStr);

        // cpu
        cpuNameText = (TextView) findViewById(R.id.cpu_name_text);
        cpuNameText.setText(game.getDealer().getName());
        cpuTopCardName = (TextView) findViewById(R.id.cpu_top_card_name_text);
        cpuTopCardName.setText(game.getDealer().getTopCard().getName());
        cpuTopCardImage = (ImageView) findViewById(R.id.cpu_top_card_character_image);
        cpuTopCardImage.setImageResource(game.getDealer().getTopCard().getImageSource());
        cpuIntellectText = (TextView) findViewById(R.id.cpu_top_card_intellect_value_text);
        cpuIntellectText.setText(game.getDealer().getTopCard().getIntellect().toString());
        cpuLethalityText = (TextView) findViewById(R.id.cpu_top_card_lethality_value_text);
        cpuLethalityText.setText(game.getDealer().getTopCard().getLethality().toString());
        cpuMoralityText = (TextView) findViewById(R.id.cpu_top_card_morality_value_text);
        cpuMoralityText.setText(game.getDealer().getTopCard().getMorality().toString());
        cpuHowSchwiftyText = (TextView) findViewById(R.id.cpu_top_card_howSchwifty_value_text);
        cpuHowSchwiftyText.setText(game.getDealer().getTopCard().getHowSchwifty().toString());

        // user
        userNameText = (TextView) findViewById(R.id.user_name_text);
        userNameText.setText(game.getPlayer1().getName());
        userTopCardName = (TextView) findViewById(R.id.user_top_card_name_text);
        userTopCardName.setText(game.getPlayer1().getTopCard().getName());
        userTopCardImage = (ImageView) findViewById(R.id.user_top_card_character_image);
        userTopCardImage.setImageResource(game.getPlayer1().getTopCard().getImageSource());
        userIntellectText = (TextView) findViewById(R.id.user_top_card_intellect_value_text);
        userIntellectText.setText(game.getPlayer1().getTopCard().getIntellect().toString());
        userLethalityText = (TextView) findViewById(R.id.user_top_card_lethality_value_text);
        userLethalityText.setText(game.getPlayer1().getTopCard().getLethality().toString());
        userMoralityText = (TextView) findViewById(R.id.user_top_card_morality_value_text);
        userMoralityText.setText(game.getPlayer1().getTopCard().getMorality().toString());
        userHowSchwiftyText = (TextView) findViewById(R.id.user_top_card_howSchwifty_value_text);
        userHowSchwiftyText.setText(game.getPlayer1().getTopCard().getHowSchwifty().toString());

        game.playRound(category);
        roundWinnerName = game.getRoundWinner().getName();
        roundWinnerScore = game.getRoundWinnerScore();

        // win declaration
        roundCategoryText = (TextView) findViewById(R.id.round_category_text);
        String roundCategoryTextStr = "Category:  " + category;
        roundCategoryText.setText(roundCategoryTextStr);

        roundWinDeclarationText = (TextView) findViewById(R.id.round_win_declaration_text);
        if (roundWinnerName.equals(game.getDealer().getName())){
            roundWinDeclarationText.setTextColor(Color.parseColor("#ffcc0000"));
        } else {
            roundWinDeclarationText.setTextColor(Color.parseColor("#ff669900"));
        }
        String roundWinDeclarationTextStr = roundWinnerName + " won with a score of " + roundWinnerScore.toString() + ".";
        roundWinDeclarationText.setText(roundWinDeclarationTextStr);
    }

    public void nextRoundButtonOnClick(View button){
        Log.d("Result Activity", "Next Round button clicked");
        if (!game.isGameWon()){
            Log.d("Result Activity", "Inside if statement");
            Intent mainActivityIntent = new Intent(ResultActivity.this, MainActivity.class);
            Log.d("Result Activity", "Main Activity Intent created");
            startActivity(mainActivityIntent);
        } else {
            Log.d("Result Activity", "Inside else statement");
            Intent gameWonIntent = new Intent(ResultActivity.this, GameWonActivity.class);
            Log.d("Result Activity", "Game Won Activity Intent created");
            startActivity(gameWonIntent);
        }

    }
}
