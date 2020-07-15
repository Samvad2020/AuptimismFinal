package com.example.journal.IEP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.journal.IEP.EducationIEP;
import com.example.journal.IEP.TherapyIEP;
import com.example.journal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IEPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_e_p);
        final FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1=fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.fragment_container1,new EducationIEP());
        fragmentTransaction1.commit();
        final BottomNavigationView bottomNavigationView=findViewById(R.id.navigation2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.navigation_education){
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container1,new EducationIEP());
                    fragmentTransaction.commit();

                }else if(item.getItemId()==R.id.navigation_therapy){
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container1,new TherapyIEP());
                    fragmentTransaction.commit();
                }
                return false;
            }
        });
    }
}
