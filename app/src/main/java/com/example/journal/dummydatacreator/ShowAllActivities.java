package com.example.journal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ShowAllActivities extends AppCompatActivity {
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_activities);
        final TextView textView=findViewById(R.id.listOfActivities);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        Timer t = new Timer();
        final int[] i = {1};
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      Date currentTime = Calendar.getInstance().getTime();
                                      if(i[0]%2==0){
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("How was Day").setValue("Wonderful");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("information").setValue("Played well"+1);
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("name").setValue("Learning Activity");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("AnythingElse").setValue("Progress");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("FoodHabitsGeneral").setValue("Vegetables");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("ImageUrl").child(i[0]+"").setValue("https://www.google.com/search?q=image&rlz=1C1CHBD_enIN780IN780&sxsrf=ALeKk00JTvyFJzkR4KvG5ty4F1TpKd-YAg:1591255363225&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjW4K-V0OfpAhVXYysKHVHFCu8Q_AUoAXoECBIQAw&biw=1536&bih=706#imgrc=nH5liarSz56duM");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SleepHabitsGeneral").setValue("10 am");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SomeFoodChanges").setValue("Vegetables"+i[0]);
                                      }else if(i[0]%3==0){
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("How was Day").setValue("Happy");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("information").setValue("Played well"+1);
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("name").setValue("Learning Activity");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("AnythingElse").setValue("Progress");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("FoodHabitsGeneral").setValue("Vegetables");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("ImageUrl").child(i[0]+"").setValue("https://www.google.com/search?q=image&rlz=1C1CHBD_enIN780IN780&sxsrf=ALeKk00JTvyFJzkR4KvG5ty4F1TpKd-YAg:1591255363225&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjW4K-V0OfpAhVXYysKHVHFCu8Q_AUoAXoECBIQAw&biw=1536&bih=706#imgrc=nH5liarSz56duM");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SleepHabitsGeneral").setValue("10 am");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SomeFoodChanges").setValue("Vegetables"+i[0]);
                                      }else if(i[0]%5==0){
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("How was Day").setValue("Difficult");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("information").setValue("Something Happned"+1);
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("name").setValue("Learning Activity");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("AnythingElse").setValue("None");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("FoodHabitsGeneral").setValue("Vegetables");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("HowLong").setValue(10+i[0]+"");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("ImageUrl").child(i[0]+"").setValue("https://www.google.com/search?q=image&rlz=1C1CHBD_enIN780IN780&sxsrf=ALeKk00JTvyFJzkR4KvG5ty4F1TpKd-YAg:1591255363225&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjW4K-V0OfpAhVXYysKHVHFCu8Q_AUoAXoECBIQAw&biw=1536&bih=706#imgrc=nH5liarSz56duM");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SleepHabitsGeneral").setValue("10 am");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SomeFoodChanges").setValue("Vegetables"+i[0]);
                                          if(i[0]%2==0){
                                              mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("When").setValue("Morning");
                                          }else if(i[0]%3==0){
                                              mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("When").setValue("Evening");
                                          }else{
                                              mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("When").setValue("Night");
                                          }
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Where").setValue("Bed Room");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("childMood").child("0").setValue("Annoyed");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("childMood").child("1").setValue("Frustrated");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("whatYouDo").setValue("Did Something"+i[0]);
                                      }else{
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("How was Day").setValue("Very Difficult");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("information").setValue("Something Happened"+1);
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Activity").child("name").setValue("Daily Living");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("AnythingElse").setValue("None");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("FoodHabitsGeneral").setValue("Vegetables");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("HowLong").setValue(10+i[0]+"");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("ImageUrl").child(i[0]+"").setValue("https://www.google.com/search?q=image&rlz=1C1CHBD_enIN780IN780&sxsrf=ALeKk00JTvyFJzkR4KvG5ty4F1TpKd-YAg:1591255363225&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjW4K-V0OfpAhVXYysKHVHFCu8Q_AUoAXoECBIQAw&biw=1536&bih=706#imgrc=nH5liarSz56duM");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SleepHabitsGeneral").setValue("10 am");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("SomeFoodChanges").setValue("Vegetables"+i[0]);
                                          if(i[0]%2==0){
                                              mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("When").setValue("Morning");
                                          }else if(i[0]%3==0){
                                              mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("When").setValue("Evening");
                                          }else{
                                              mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("When").setValue("Night");
                                          }
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("Where").setValue("Living Room");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("childMood").child("0").setValue("Crying");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("childMood").child("1").setValue("Upset");
                                          mdatabase.child("Vibhu").child(31+i[0]+"").child("June "+i[0]+" 17:59:58").child("whatYouDo").setValue("Did Something"+i[0]);
                                      }

                                      i[0]++;




                                  }

                              },
                0,
                5000);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String n1=prefs.getString("uuid","btXbuRdzedTUW5H05hjN7vPo1m03");
        assert n1 != null;
        mdatabase.child("users").child(n1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()!=null) {
                    if (dataSnapshot.child("homeplan").child("Description").getValue(String.class) != null) {
                        textView.setText("Homeplan");
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ShowAllActivities.this, HomePlanDemo.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
