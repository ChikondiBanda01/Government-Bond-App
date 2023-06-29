package com.example.govbondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button investorBtn= findViewById(R.id.investorBtn);
        final Button administratorBtn= findViewById(R.id.administratorBtn);


        investorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                String userType = "investor";
                intent.putExtra("USERTYPE", userType);
                startActivity(intent);
            }
        });

        administratorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                String userType = "admin";
                intent.putExtra("USERTYPE", userType);
                startActivity(intent);
            }
        });
    }


}