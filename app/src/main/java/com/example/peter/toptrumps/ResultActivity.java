package com.example.peter.toptrumps;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView cpuBackplate;

    TextView userNameText;
    TextView userTopCardName;
    ImageView userTopCardImage;
    TextView userIntellectText;
    TextView userLethalityText;
    TextView userMoralityText;
    TextView userHowSchwiftyText;
    ImageView userBackplate;

    Card userCard;
    Card cpuCard;
    Game game;
    String category;
    boolean isRoundDraw;
    String roundWinnerName;
    Card roundWinningCard;
    Integer roundWinnerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent resultActivityIntent = getIntent();

        if (resultActivityIntent.hasExtra("category")){
            Bundle extras = resultActivityIntent.getExtras();
            String roundCategory = extras.getString("category");
            game = Game.getInstance();
            category = game.playerSetCategory(roundCategory);
        } else {
            game = Game.getInstance();
            category = game.getCPUBestCategory();
        }

        game = Game.getInstance();

        userCard = game.getPlayer1().getTopCard();
        cpuCard = game.getDealer().getTopCard();

        // page title text
        roundResultText = (TextView) findViewById(R.id.round_result_title_text);
        String roundResultTextStr = "Round " + game.getRoundNumber() + " Result";
        roundResultText.setText(roundResultTextStr);

        // cpu
        cpuNameText = (TextView) findViewById(R.id.cpu_name_text);
        cpuNameText.setText(game.getDealer().getName());
        cpuBackplate = (ImageView) findViewById(R.id.cpu_top_card_backplate);
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
        userBackplate = (ImageView) findViewById(R.id.user_top_card_backplate);
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

        // play round and get winner details
        game.playRound(category);
        isRoundDraw = game.isRoundDraw();
        roundWinnerName = game.getRoundWinner().getName();
        roundWinningCard = game.getRoundWinningCard();
        roundWinnerScore = game.getRoundWinningScore();

        // round category
        roundCategoryText = (TextView) findViewById(R.id.round_category_text);
        String roundCategoryTextStr = "Category:  " + category;
        roundCategoryText.setText(roundCategoryTextStr);

        // win declaration
        roundWinDeclarationText = (TextView) findViewById(R.id.round_win_declaration_text);
        String roundWinDeclarationTextStr = roundWinnerName + " won with a score of " + roundWinnerScore.toString() + ".";
        roundWinDeclarationText.setText(roundWinDeclarationTextStr);

        // check if draw then set colours accordingly
        if (isRoundDraw){
            setDrawColours();
            // draw declaration
            String roundDrawDeclarationTextStr = "DRAW! Cards added to the pile.";
            roundWinDeclarationText.setText(roundDrawDeclarationTextStr);
        } else {
            setWinColours();
        }

        // toast catchphrase
        Toast.makeText(this, roundWinningCard.getCatchphrase(), Toast.LENGTH_LONG).show();
    }

    public void nextRoundButtonOnClick(View button){
        if (!game.isGameWon()){
            initializeMainActivityIntent();
        } else {
            initializeGameWonIntent();
        }
    }

    public void initializeMainActivityIntent(){
        Intent mainActivityIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void initializeGameWonIntent(){
        Intent gameWonIntent = new Intent(ResultActivity.this, GameWonActivity.class);
        startActivity(gameWonIntent);
    }

    public void setWinColours(){
        // cpu colours
        if (roundWinnerName.equals(game.getDealer().getName())){
            cpuTopCardName.setTextColor(Color.parseColor("#ff669900"));
            cpuNameText.setTextColor(Color.parseColor("#ff669900"));
            cpuBackplate.setBackgroundColor(Color.parseColor("#ff669900"));
        } else {
            cpuTopCardName.setTextColor(Color.parseColor("#ffcc0000"));
            cpuNameText.setTextColor(Color.parseColor("#ffcc0000"));
            cpuBackplate.setBackgroundColor(Color.parseColor("#ffcc0000"));
        }

        // user colours
        if (roundWinnerName.equals(game.getPlayer1().getName())){
            userTopCardName.setTextColor(Color.parseColor("#ff669900"));
            userNameText.setTextColor(Color.parseColor("#ff669900"));
            userBackplate.setBackgroundColor(Color.parseColor("#ff669900"));
        } else {
            userTopCardName.setTextColor(Color.parseColor("#ffcc0000"));
            userNameText.setTextColor(Color.parseColor("#ffcc0000"));
            userBackplate.setBackgroundColor(Color.parseColor("#ffcc0000"));

        }
    }

    public void setDrawColours(){
        // draw declaration
        roundWinDeclarationText.setTextColor(Color.parseColor("#FFAAAAAA"));

        // cpu colours
        cpuTopCardName.setTextColor(Color.parseColor("#FFAAAAAA"));
        cpuNameText.setTextColor(Color.parseColor("#FFAAAAAA"));
        cpuBackplate.setBackgroundColor(Color.parseColor("#FFAAAAAA"));

        // user colours
        userTopCardName.setTextColor(Color.parseColor("#FFAAAAAA"));
        userNameText.setTextColor(Color.parseColor("#FFAAAAAA"));
        userBackplate.setBackgroundColor(Color.parseColor("#FFAAAAAA"));
    }

}
