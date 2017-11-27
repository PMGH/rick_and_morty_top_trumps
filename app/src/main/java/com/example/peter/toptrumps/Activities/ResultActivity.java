package com.example.peter.toptrumps.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.toptrumps.Game;
import com.example.peter.toptrumps.Objects.Card;
import com.example.peter.toptrumps.R;

import static android.widget.Toast.makeText;

public class ResultActivity extends AppCompatActivity {

    TextView roundResultText;
    TextView roundCategoryText;
    TextView roundWinDeclarationText;
    Toast toast;

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

    Game game;
    Card roundWinningCard;
    String category;
    String roundWinnerName;
    Integer roundWinnerScore;
    boolean isRoundDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // setup game
        game = Game.getInstance();

        //get extras
        handleExtras();

        // setup views
        getLayoutViews();
        setLayoutViews();

        // play round and get winner details
        game.playRound(category);
        isRoundDraw = game.isRoundDraw();
        roundWinnerName = game.getRoundWinner().getName();
        roundWinningCard = game.getRoundWinningCard();
        roundWinnerScore = game.getRoundWinningScore();

        // declare win
        winDeclaration();

        // check if draw then set colours accordingly
        if (isRoundDraw){
            setDrawColours();
            // draw declaration
            String roundDrawDeclarationTextStr = "DRAW! Cards added to the pile.";
            roundWinDeclarationText.setText(roundDrawDeclarationTextStr);
        } else {
            setWinColours();
            toastCatchphrase();
        }

    }

    public void handleExtras(){
        Intent resultActivityIntent = getIntent();

        if (resultActivityIntent.hasExtra("category")){
            Bundle extras = resultActivityIntent.getExtras();
            String roundCategory = extras.getString("category");
            category = game.playerSetCategory(roundCategory);
        } else {
            category = game.getCPUBestCategory();
        }
    }

    public void getLayoutViews(){
        // get page title text
        roundResultText = (TextView) findViewById(R.id.round_result_title_text);

        // get round category
        roundCategoryText = (TextView) findViewById(R.id.round_category_text);

        // get cpu views
        cpuNameText = (TextView) findViewById(R.id.cpu_name_text);
        cpuBackplate = (ImageView) findViewById(R.id.cpu_top_card_backplate);
        cpuTopCardName = (TextView) findViewById(R.id.cpu_top_card_name_text);
        cpuTopCardImage = (ImageView) findViewById(R.id.cpu_top_card_character_image);
        cpuIntellectText = (TextView) findViewById(R.id.cpu_top_card_intellect_value_text);
        cpuLethalityText = (TextView) findViewById(R.id.cpu_top_card_lethality_value_text);
        cpuMoralityText = (TextView) findViewById(R.id.cpu_top_card_morality_value_text);
        cpuHowSchwiftyText = (TextView) findViewById(R.id.cpu_top_card_howSchwifty_value_text);

        // get user views
        userNameText = (TextView) findViewById(R.id.user_name_text);
        userBackplate = (ImageView) findViewById(R.id.user_top_card_backplate);
        userTopCardName = (TextView) findViewById(R.id.user_top_card_name_text);
        userTopCardImage = (ImageView) findViewById(R.id.user_top_card_character_image);
        userIntellectText = (TextView) findViewById(R.id.user_top_card_intellect_value_text);
        userLethalityText = (TextView) findViewById(R.id.user_top_card_lethality_value_text);
        userMoralityText = (TextView) findViewById(R.id.user_top_card_morality_value_text);
        userHowSchwiftyText = (TextView) findViewById(R.id.user_top_card_howSchwifty_value_text);
    }

    public void setLayoutViews(){
        // set page title text
        String roundResultTextStr = "Round " + game.getRoundNumber() + " Result";
        roundResultText.setText(roundResultTextStr);

        // set round category
        String roundCategoryTextStr = "Category:  " + category;
        roundCategoryText.setText(roundCategoryTextStr);

        // set cpu views
        cpuNameText.setText(game.getDealer().getName());
        cpuTopCardName.setText(game.getDealer().getTopCard().getName());
        cpuTopCardImage.setImageResource(game.getDealer().getTopCard().getImageSource());
        cpuIntellectText.setText(game.getDealer().getTopCard().getIntellect().toString());
        cpuLethalityText.setText(game.getDealer().getTopCard().getLethality().toString());
        cpuMoralityText.setText(game.getDealer().getTopCard().getMorality().toString());
        cpuHowSchwiftyText.setText(game.getDealer().getTopCard().getHowSchwifty().toString());

        // set user views
        userNameText.setText(game.getPlayer1().getName());
        userTopCardName.setText(game.getPlayer1().getTopCard().getName());
        userTopCardImage.setImageResource(game.getPlayer1().getTopCard().getImageSource());
        userIntellectText.setText(game.getPlayer1().getTopCard().getIntellect().toString());
        userLethalityText.setText(game.getPlayer1().getTopCard().getLethality().toString());
        userMoralityText.setText(game.getPlayer1().getTopCard().getMorality().toString());
        userHowSchwiftyText.setText(game.getPlayer1().getTopCard().getHowSchwifty().toString());
    }

    public void setWinColours(){
        // set cpu colours
        if (roundWinnerName.equals(game.getDealer().getName())){
            // on win
            cpuNameText.setTextColor(Color.parseColor("#ff669900"));
            cpuBackplate.setBackgroundColor(Color.parseColor("#ff669900"));
            cpuTopCardName.setTextColor(Color.parseColor("#ff669900"));
        } else {
            // on lose
            cpuNameText.setTextColor(Color.parseColor("#ffcc0000"));
            cpuBackplate.setBackgroundColor(Color.parseColor("#ffcc0000"));
            cpuBackplate.setPadding(2, 2, 2, 2);
            cpuTopCardName.setTextColor(Color.parseColor("#ffcc0000"));
        }

        // set user colours
        if (roundWinnerName.equals(game.getPlayer1().getName())){
            // on win
            userNameText.setTextColor(Color.parseColor("#ff669900"));
            userBackplate.setBackgroundColor(Color.parseColor("#ff669900"));
            userTopCardName.setTextColor(Color.parseColor("#ff669900"));
        } else {
            // on lose
            userNameText.setTextColor(Color.parseColor("#ffcc0000"));
            userBackplate.setBackgroundColor(Color.parseColor("#ffcc0000"));
            userBackplate.setPadding(2, 2, 2, 2);
            userTopCardName.setTextColor(Color.parseColor("#ffcc0000"));
        }
    }

    public void setDrawColours(){
        // set draw declaration
        roundWinDeclarationText.setTextColor(Color.parseColor("#FFAAAAAA"));

        // set cpu colours
        cpuTopCardName.setTextColor(Color.parseColor("#FFAAAAAA"));
        cpuNameText.setTextColor(Color.parseColor("#FFAAAAAA"));
        cpuBackplate.setBackgroundColor(Color.parseColor("#FFAAAAAA"));

        // set user colours
        userTopCardName.setTextColor(Color.parseColor("#FFAAAAAA"));
        userNameText.setTextColor(Color.parseColor("#FFAAAAAA"));
        userBackplate.setBackgroundColor(Color.parseColor("#FFAAAAAA"));
    }

    public void winDeclaration(){
        // get/set win declaration
        roundWinDeclarationText = (TextView) findViewById(R.id.round_win_declaration_text);
        String roundWinDeclarationTextStr = roundWinnerName + " won with a score of " + roundWinnerScore.toString() + ".";
        roundWinDeclarationText.setText(roundWinDeclarationTextStr);
    }

    public void toastCatchphrase(){
        // toast catchphrase
        toast = Toast.makeText(this, roundWinningCard.getCatchphrase(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, -150);
        toast.show();
    }

    public void nextRoundButtonOnClick(View button){
        if (!isRoundDraw){
            toast.cancel();
        }

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

}
