package com.example.peter.toptrumps.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peter.toptrumps.Game;
import com.example.peter.toptrumps.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class RulesActivity extends AppCompatActivity {

    EditText name_text;
    String name;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        name_text = (EditText) findViewById(R.id.user_name_text);
    }

    public void playGameButtonOnClick(View button) {
        game = Game.getInstance();

        // empty game
        try {
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        name = name_text.getText().toString();

        if (!name.equals("")){
            initializeIntent();
        } else {
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
        }
    }

    public void initializeIntent(){
        game = Game.getInstance();
        game.start(name);
        Intent mainActivityIntent = new Intent(RulesActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void tearDown() throws Exception {
        // reflection - accessing a private method and setting it to accessible then resetting game instance to a new game
        try {
            //load class
            Class c = Class.forName("com.example.peter.toptrumps.Game");
            //get all constructors (whether public or private)
            Constructor[] constructors = c.getDeclaredConstructors();
            //suppress access check errors
            constructors[0].setAccessible(true);
            //instance no 1
            Field field = Game.class.getDeclaredField("INSTANCE");
            field.setAccessible(true);
            field.set(game, constructors[0].newInstance());
            field.setAccessible(false);
        } catch (ClassNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
