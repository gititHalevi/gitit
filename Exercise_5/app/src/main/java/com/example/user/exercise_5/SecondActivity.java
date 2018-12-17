package com.example.user.exercise_5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String userName = getIntent().getStringExtra(MainActivity.USER_NAME);
        if (userName != null) {
            TextView lblMessage = findViewById(R.id.lblMessage);
            lblMessage.setText("welcome " + userName + "!");
        }

    }

    public void btmLogOutClicked(View view) {
        SharedPreferences pref = getSharedPreferences(MainActivity.PREFS, MODE_PRIVATE);
        pref.edit().remove(MainActivity.USER_NAME).remove(MainActivity.PASSWORD).commit();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
