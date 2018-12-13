package com.example.user.a10_12_2018_exrecise_4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;

public class MainActivity extends Activity implements View.OnClickListener {
    private int count;
    public static final int MAX = 5;
    public static final int MIN = -5;
    private TextView lblNumber;
    //private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblNumber = findViewById(R.id.lblNumber);
        count = 0;
        updateLbl();
        /*listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumber(view);
            }
        };*/

        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
    }

    public void changeNumber(View view) {
        if (view.getId() == R.id.btnPlus){
            if (count < MAX){
                count ++;
            }
            else
                Toast.makeText(this, "You reach MAX", Toast.LENGTH_SHORT).show();

        }else{
            if (count > MIN){
                count --;
            }else
                Toast.makeText(this, "You reach MIN", Toast.LENGTH_SHORT).show();
        }
        updateLbl();
    }

    private void updateLbl(){
        lblNumber.setText(String.valueOf(count));
    }
    @Override
    public void onClick(View view) {
        changeNumber(view);
    }
}
