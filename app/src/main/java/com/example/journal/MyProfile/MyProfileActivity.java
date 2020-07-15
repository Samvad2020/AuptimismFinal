package com.example.journal.MyProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journal.Documents.DocumentsActivity;
import com.example.journal.IEP.IEPActivity;
import com.example.journal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MyProfileActivity extends AppCompatActivity {
    CircleImageView img_child_profile;
    TextView txt_child_name;
    TextView txt_birth_day;
    TextView txt_child_gender;
    TextView txt_father_name;
    TextView txt_father_email;
    TextView txt_father_number;
    TextView txt_mother_name;
    TextView txt_mother_email;
    TextView txt_mother_number;
    TextView txt_diagnosis;
    TextView txt_medical_condition;
    TextView txt_medication;
    TextView txt_address;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String name;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        img_child_profile=findViewById(R.id.img_child);
        txt_child_name=findViewById(R.id.txt_child_name);
        txt_birth_day=findViewById(R.id.txt_child_birthday);
        txt_child_gender=findViewById(R.id.txt_child_gender);
        txt_father_name=findViewById(R.id.txt_father_name);
        txt_father_email=findViewById(R.id.txt_father_email);
        txt_father_number=findViewById(R.id.txt_father_number);
        txt_mother_name=findViewById(R.id.txt_mother_name);
        txt_mother_email=findViewById(R.id.txt_mother_email);
        txt_mother_number=findViewById(R.id.txt_mother_number);
        txt_diagnosis=findViewById(R.id.txt_diagnosis);
        txt_medical_condition=findViewById(R.id.txt_medical_condition);
        txt_medication=findViewById(R.id.txt_medication);
        txt_address=findViewById(R.id.txt_address);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        name="aman";
        if(name!=null && name.equals("child")){
            Toast.makeText(this, "Please ask your specialist to verify your data.", Toast.LENGTH_SHORT).show();
        }else{
            txt_child_name.setText(name);
        }
        mdatabase= FirebaseDatabase.getInstance().getReference();
        mdatabase.child("users").child("Students").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()!=null){
                    if(dataSnapshot.child("profilepic").getKey()!=null){
                        Picasso.get().load(dataSnapshot.child("profilepic").getValue(String.class)).into(img_child_profile);
                    }
                    if(dataSnapshot.child("DOB").getKey()!=null){
                        txt_birth_day.setText(dataSnapshot.child("DOB").getValue(String.class));
                    }
                    if(dataSnapshot.child("Gender").getKey()!=null){
                        txt_child_gender.setText(dataSnapshot.child("Gender").getValue(String.class));
                    }
                    if(dataSnapshot.child("Fathername").getKey()!=null){
                        txt_father_name.setText(dataSnapshot.child("Fathername").getValue(String.class));
                    }
                    if(dataSnapshot.child("Emailfather").getKey()!=null){
                        txt_father_email.setText(dataSnapshot.child("Emailfather").getValue(String.class));
                    }
                    if(dataSnapshot.child("Phonefather").getKey()!=null){
                        txt_father_number.setText(dataSnapshot.child("Phonefather").getValue(String.class));
                    }
                    if(dataSnapshot.child("Mothername").getKey()!=null){
                        txt_mother_name.setText(dataSnapshot.child("Mothername").getValue(String.class));
                    }
                    if(dataSnapshot.child("Emailmother").getKey()!=null){
                        txt_mother_email.setText(dataSnapshot.child("Emailmother").getValue(String.class));
                    }
                    if(dataSnapshot.child("Phonemother").getKey()!=null){
                        txt_mother_number.setText(dataSnapshot.child("Phonemother").getValue(String.class));
                    }
                    if(dataSnapshot.child("diagnosis").getKey()!=null){
                        txt_diagnosis.setText(dataSnapshot.child("diagnosis").getValue(String.class));
                    }
                    if(dataSnapshot.child("medical_condition").getKey()!=null){
                        txt_medical_condition.setText(dataSnapshot.child("medical_condition").getValue(String.class));
                    }
                    if(dataSnapshot.child("medication").getKey()!=null){
                        txt_medication.setText(dataSnapshot.child("medication").getValue(String.class));
                    }
                    if(dataSnapshot.child("Address").getKey()!=null){
                        txt_address.setText(dataSnapshot.child("Address").getValue(String.class));
                    }
                }else{
                    Toast.makeText(MyProfileActivity.this, "Please logout and login again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void OpenIep(View view){
        Intent intent=new Intent(MyProfileActivity.this, IEPActivity.class);
        startActivity(intent);
    }

    public void OpenDocuments(View view){
        Intent intent=new Intent(MyProfileActivity.this, DocumentsActivity.class);
        startActivity(intent);
    }
}
