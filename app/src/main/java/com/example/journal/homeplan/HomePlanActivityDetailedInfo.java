package com.example.journal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HomePlanActivityDetailedInfo extends AppCompatActivity {
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_plan_detailed_info);
        Intent intent=getIntent();
        pos=intent.getIntExtra("pos",-1);
    }
}
