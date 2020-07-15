package com.example.journal.Information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.journal.R;
import com.example.journal.authentication.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void EnterTheApp(View view){
        Intent intent=new Intent(this, InformationScreen.class);
        startActivity(intent);
    }
}
