package com.example.peter.toptrumps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TopCardActivity extends AppCompatActivity {

    TextView characterNameText;
    ImageView characterImageView;
    TextView intellectValueText;
    TextView lethalityValueText;
    TextView moralityValueText;
    TextView howSchiwftyValueText;
    RadioGroup radioGroup;
    RadioButton intellectRadioBtn;
    RadioButton lethalityRadioBtn;
    RadioButton moralityRadioBtn;
    RadioButton howSchwiftyRadioBtn;
    Game game;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_card);

        game = Game.getInstance();

        Card userCard = game.getPlayer1().getTopCard();

        characterNameText = (TextView) findViewById(R.id.character_name_text);
        characterNameText.setText(userCard.getName());

        characterImageView = (ImageView) findViewById(R.id.character_image);
        characterImageView.setImageResource(userCard.getImageSource());

        intellectValueText = (TextView) findViewById(R.id.intellect_value_text);
        String intellectValueTextStr = userCard.getIntellect().toString() + " / 100";
        intellectValueText.setText(intellectValueTextStr);

        lethalityValueText = (TextView) findViewById(R.id.lethality_value_text);
        String lethalityValueTextStr = userCard.getLethality().toString() + " / 100";
        lethalityValueText.setText(lethalityValueTextStr);

        moralityValueText = (TextView) findViewById(R.id.morality_value_text);
        String moralityValueTextStr = userCard.getMorality().toString() + " / 100";
        moralityValueText.setText(moralityValueTextStr);

        howSchiwftyValueText = (TextView) findViewById(R.id.howSchwifty_value_text);
        String howSchiwftyValueTextStr = userCard.getHowSchwifty().toString() + " / 10";
        howSchiwftyValueText.setText(howSchiwftyValueTextStr);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        intellectRadioBtn = (RadioButton) findViewById(R.id.intellect_radio_btn);
        lethalityRadioBtn = (RadioButton) findViewById(R.id.lethality_radio_btn);
        moralityRadioBtn = (RadioButton) findViewById(R.id.morality_radio_btn);
        howSchwiftyRadioBtn = (RadioButton) findViewById(R.id.howSchwifty_radio_btn);

        intellectRadioBtn.setChecked(true);
    }

    public void confirmSelectionOnClick(View button){
        // set the category for the round
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.intellect_radio_btn:
                category = "Intellect";
                break;
            case R.id.lethality_radio_btn:
                category = "Lethality";
                break;
            case R.id.morality_radio_btn:
                category = "Morality";
                break;
            case R.id.howSchwifty_radio_btn:
                category = "How Schwifty";
                break;
            default:
                break;
        }
        initializeIntent();
    }

    public void initializeIntent(){
        Intent resultActivityIntent = new Intent(TopCardActivity.this, ResultActivity.class);

        resultActivityIntent.putExtra("category", category);

        startActivity(resultActivityIntent);
    }


}
