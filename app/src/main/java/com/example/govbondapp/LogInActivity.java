package com.example.govbondapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class LogInActivity extends AppCompatActivity {

    DBHelper DB;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Button loginBtn = findViewById(R.id.loginBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //Initialise DB Helper
        DB = new DBHelper(LogInActivity.this);

        //Get current intent
        Intent intent = getIntent();

        //Get user type extras fom intent
        String userType = intent.getStringExtra("USERTYPE");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType.equals("admin")) {
                    if (email.getText().toString().equals("admin@bondapp.com") && password.getText().toString().equals("admin")) {
                        Toast.makeText(LogInActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LogInActivity.this, MainPage.class);
                        intent.putExtra("USERTYPE", userType);
                        startActivity(intent);

                        emptyTextFields();
                    } else {
                        Toast.makeText(LogInActivity.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                    }
                } else if (userType.equals("investor")) {
                    //Send user data to main page
                    email.getText().toString();

                    Cursor cursor = DB.getdata();

                    if (cursor.getCount() == 0) {
                        Toast.makeText(LogInActivity.this, "No users exist", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        while (cursor.moveToNext()) {
                            if (email.getText().toString().equals(cursor.getString(0)) &&
                                    password.getText().toString().equals(cursor.getString(2))) {
                                Toast.makeText(LogInActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogInActivity.this, MainPage.class);
                                Bundle extras = new Bundle();
                                extras.putString("USERTYPE", userType);
                                extras.putString("EMAIL", String.valueOf(email));
                                intent.putExtras(extras);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LogInActivity.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }


                }
            }
        });
    }

    private void emptyTextFields() {
        email.setText("");
        password.setText("");
    }
}