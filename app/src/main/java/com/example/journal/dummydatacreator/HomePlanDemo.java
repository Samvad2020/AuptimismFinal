package com.example.journal.dummydatacreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomePlanDemo extends AppCompatActivity {
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    TextView description;
    TextView suggestion;
    TextView title;
    TextView visibil;
    TextView category;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_plan_demo);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String n1=prefs.getString("uuid","btXbuRdzedTUW5H05hjN7vPo1m03");
        description=findViewById(R.id.description);
        suggestion=findViewById(R.id.suggestion);
        title=findViewById(R.id.title);
        visibil=findViewById(R.id.Visibility);
        category=findViewById(R.id.category);
        imageView=findViewById(R.id.videoThumb);
        mdatabase.child("users").child(n1).child("homeplan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()!=null){
                    description.setText(dataSnapshot.child("Description").getValue(String.class));
                    suggestion.setText(dataSnapshot.child("Suggestion").getValue(String.class));
                    title.setText(dataSnapshot.child("Title").getValue(String.class));
                    visibil.setText(dataSnapshot.child("Visibility").getValue(String.class));
                    category.setText(dataSnapshot.child("category").getValue(String.class));
                    Picasso.get().load("https://img.youtube.com/vi/"+dataSnapshot.child("video").getValue(String.class)+"/0.jpg").into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
