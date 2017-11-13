package com.example.peter.toptrumps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RulesActivity extends AppCompatActivity {

    EditText name_text;
    String name;
    DBHelper dbHelper;
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
        // create user entry in DB
        dbHelper = new DBHelper(this);
        dbHelper.deleteAll();
        int id = dbHelper.save(name, 0);
        Log.d("Rules Activity", "Saved to DB");
        Integer id_integer = Integer.valueOf(id);
        Log.d("Rules Activity", "Integer returned from DB");

        Intent mainActivityIntent = new Intent(RulesActivity.this, MainActivity.class);
        mainActivityIntent.putExtra("id", id_integer);
        startActivity(mainActivityIntent);
    }

    public void tearDown() throws Exception {
        // reflection - accessing a private method and setting it to accessible then resetting game instance to a new game
        Field field = Game.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        field.set(game, new Game());
    }
}