package com.example.journal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MoodMeter.onSomeEventListener,ActivityInfoLanding.onRecordingSelectedListener,ActivityQues.onCaptureDescription
,MediaShare.onBooleanMediaShare,MediaUpload.onMediaShareDone,AnythingElse.onAnythingElseSelected,EatingHabbits.onBooleanEatingHabitChanged
,DescriptionBox.onMedicationStarted,WhenHappened.onWhenHappened,WhereHappened.onWhereDidItHappen,MoodOfChild.onMoodOfChild,TimeOfChild.onTimeOfChild
,WhatDidYouDo.onWhatDidYouDo,PossibleTriggers.onPossibleTrigger,OnBoard.onNameProvided{
    FragmentManager fragmentManager;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    ScrollView scrollview;
    private DatabaseReference mdatabase;
    String person;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        Date currentTime = Calendar.getInstance().getTime();
        time=currentTime+"";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("time",time);
        editor.apply();
        scrollview = ((ScrollView) findViewById(R.id.rootScrollView));
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      scrollview.postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                                          }
                                      }, 0);
                                  }

                              },
                0,
                500);
        fragmentManager=getSupportFragmentManager();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean firstTime=prefs.getBoolean("FirstTime",true);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(firstTime){
            Bundle bundle=new Bundle();
            bundle.putString("type","15");
            OnBoard onBoard=new OnBoard();
            onBoard.setArguments(bundle);
            fragmentTransaction.add(R.id.container,onBoard);
            Toast.makeText(this, "Welcome on-board", Toast.LENGTH_SHORT).show();
        }else{
            person=prefs.getString("Name","child");
            MoodMeter moodMeter=new MoodMeter();
            Bundle bundle1=new Bundle();
            bundle1.putString("moodMeterText","Nice to see you back again!\nHow's "+person+" day going?");
            moodMeter.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,moodMeter);
            Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
        }
        fragmentTransaction.commit();
    }


    @Override
    public void someEvent(String s) {

        Bundle bundle = new Bundle();
        bundle.putString("mood", s);
        MoodResponse moodResponse=new MoodResponse();
        moodResponse.setArguments(bundle);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,moodResponse);
        if(s.equals("Wonderful")){
            mdatabase.child(person).child(time).child("How was Day").setValue("Wonderful");
            Bundle bundle1=new Bundle();
            bundle1.putString("greeting","Yay ! I am really keen to know");
            bundle1.putString("ques1","let's save all that happened.");
            bundle1.putString("ques2","Which activity made your day?");
            bundle1.putString("type","1");
            ActivityInfoLanding activityInfoLanding=new ActivityInfoLanding();
            activityInfoLanding.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,activityInfoLanding);
        }else if(s.equals("Happy")){
            mdatabase.child(person).child(time).child("How was Day").setValue("Happy");
            Bundle bundle1=new Bundle();
            bundle1.putString("content","That's great to know ! Do you remember any positive event that happened today?");
            bundle1.putString("type","7");
            MediaShare mediaShare=new MediaShare();
            mediaShare.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,mediaShare);
        }else if(s.equals("Difficult")){
            mdatabase.child(person).child(time).child("How was Day").setValue("Difficult");
            Bundle bundle1=new Bundle();
            bundle1.putString("greeting","You seem upset");
            bundle1.putString("ques1","let's save all that happened.");
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String n1=prefs.getString("Name","child");
            bundle1.putString("ques2","Did "+n1+" face any issue in:");
            bundle1.putString("type","9");
            ActivityInfoLanding activityInfoLanding=new ActivityInfoLanding();
            activityInfoLanding.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,activityInfoLanding);
        }else{
            mdatabase.child(person).child(time).child("How was Day").setValue("Very Difficult");
            Bundle bundle1=new Bundle();
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String n1=prefs.getString("Name","child");
            bundle1.putString("content","Oh ! Sorry to know that ! Is "+n1+" fine?");
            bundle1.putString("type","10");
            MediaShare mediaShare=new MediaShare();
            mediaShare.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,mediaShare);
        }

        fragmentTransaction.commit();


    }


    @Override
    public void onRecordingSelected(String s,String type) {
        mdatabase.child(person).child(time).child("Activity").child("name").setValue(s);
        Bundle bundle = new Bundle();
        bundle.putString("mood", s);
        MoodResponse moodResponse=new MoodResponse();
        moodResponse.setArguments(bundle);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,moodResponse);
        ActivityQues activityQues=new ActivityQues();
        Bundle bundle1=new Bundle();
        bundle1.putString("type",type);
        activityQues.setArguments(bundle1);
        fragmentTransaction.add(R.id.container,activityQues);
        fragmentTransaction.commit();
    }

    @Override
    public void capturedDescription(String s,String type) {
        mdatabase.child(person).child(time).child("Activity").child("information").setValue(s);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();

        if(type.equals("1")){
            bundle.putString("content","Is there a picture or video you want to share for sweet memories ?");
            bundle.putString("type", type);
        }else if(type.equals("4")){
            bundle.putString("content","Is there a picture or video you want to share for sweet memories ?");
            bundle.putString("type", type);
        }else if(type.equals("5")){
            bundle.putString("content","Is there a picture or video you want to share for the same ?");
            bundle.putString("type", type);
        }else if(type.equals("7")){
            bundle.putString("content","Is there a picture or video you want to share for sweet memories ?");
            bundle.putString("type", "8");
        }else if(type.equals("9")){
            bundle.putString("content","Is there a picture or video you want to share for the same ?");
            bundle.putString("type", type);
        }else if(type.equals("6")){
            bundle.putString("content","Is there a picture or video you want to share for the same ?");
            bundle.putString("type", type);
        }else if(type.equals("11")){
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String n1=prefs.getString("Name","child");
            bundle.putString("content","Does "+n1+" had meltdown ?");
            bundle.putString("type", "12");
        }else if(type.equals("12")){
            bundle.putString("content","Is there a picture or video you want to share for the same ?");
            bundle.putString("type", "13");
        }else if(type.equals("14")){
            bundle.putString("content","Is there a picture or video you want to share for the same ?");
            bundle.putString("type", "14");
        }
        MediaShare mediaShare=new MediaShare();
        mediaShare.setArguments(bundle);
        fragmentTransaction.add(R.id.container,mediaShare);
        fragmentTransaction.commit();

    }

    @Override
    public void someMediaShare(boolean share,String type) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(type.equals("1")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                AnythingElse anythingElse=new AnythingElse();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","1");
                anythingElse.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,anythingElse);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1=new Bundle();
                bundle1.putString("type","1");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("2")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "3");
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String n1=prefs.getString("Name","child");
                bundle1.putString("content", "Well great ! \nWhat about the sleep ? \nDid "+n1+" sleep well?");
                MediaShare mediaShare = new MediaShare();
                mediaShare.setArguments(bundle1);
                fragmentTransaction.add(R.id.container, mediaShare);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("mood", "YES");
                bundle1.putString("type","2");
                DescriptionBox descriptionBox=new DescriptionBox();
                descriptionBox.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,descriptionBox);
                fragmentTransaction.commit();
            }
        }else if(type.equals("3")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("mood", "NO");
                bundle1.putString("type","3");
                DescriptionBox descriptionBox=new DescriptionBox();
                descriptionBox.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,descriptionBox);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                fragmentTransaction.add(R.id.container,new EndChat());
                fragmentTransaction.commit();
            }
        }else if(type.equals("4")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                fragmentTransaction.add(R.id.container,new EndChat());
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "4");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("5")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1=new Bundle();
                bundle1.putString("type","5");
                WhenHappened whenHappened=new WhenHappened();
                whenHappened.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,whenHappened);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "5");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("6")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1=new Bundle();
                bundle1.putString("type","6");
                WhenHappened whenHappened=new WhenHappened();
                whenHappened.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,whenHappened);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "6");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("7")){
            if(!share){
                Bundle bundle=new Bundle();
                bundle.putString("mood","NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                AnythingElse anythingElse=new AnythingElse();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","8");
                anythingElse.setArguments(bundle1);
                fragmentTransaction.add(R.id.container, anythingElse);
                fragmentTransaction.commit();
            }else{
                Bundle bundle=new Bundle();
                bundle.putString("mood","YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1=new Bundle();
                bundle1.putString("type","7");
                bundle1.putString("greeting","That's great");
                bundle1.putString("ques1","Let save all that happened");
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String n1=prefs.getString("Name","child");
                bundle1.putString("ques2","Did "+n1+" show any progress in");
                ActivityInfoLanding infoLanding=new ActivityInfoLanding();
                infoLanding.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,infoLanding);
                fragmentTransaction.commit();
            }
        }else if(type.equals("8")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                AnythingElse anythingElse=new AnythingElse();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","8");
                anythingElse.setArguments(bundle1);
                fragmentTransaction.add(R.id.container, anythingElse);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "8");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("9")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                WhenHappened whenHappened=new WhenHappened();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","9");
                whenHappened.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,whenHappened);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "9");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("10")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1=new Bundle();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String n1=prefs.getString("Name","child");
                bundle1.putString("content","Is "+n1+" suffering fron any medical issue?");
                bundle1.putString("type","11");
                MediaShare mediaShare=new MediaShare();
                mediaShare.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaShare);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                MediaShare mediaShare=new MediaShare();
                Bundle bundle1=new Bundle();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String n1=prefs.getString("Name","child");
                bundle1.putString("content","Does "+n1+" had meltdown ?");
                bundle1.putString("type", "12");
                mediaShare.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaShare);
                fragmentTransaction.commit();
            }
        }else if(type.equals("11")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                MediaShare mediaShare=new MediaShare();
                Bundle bundle1=new Bundle();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String n1=prefs.getString("Name","child");
                bundle1.putString("content","Does "+n1+" had meltdown ?");
                bundle1.putString("type", "12");
                mediaShare.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaShare);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                ActivityQues activityQues=new ActivityQues();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","11");
                activityQues.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,activityQues);
                fragmentTransaction.commit();
            }
        }else if(type.equals("12")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                //MediaShare mediaShare=new MediaShare();
                Bundle bundle1=new Bundle();
                ActivityQues activityQues=new ActivityQues();
                bundle1.putString("type","14");
                activityQues.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,activityQues);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                ActivityInfoLanding infoLanding=new ActivityInfoLanding();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","12");
                bundle1.putString("greeting","That was difficult!");
                bundle1.putString("ques1","let's save all that happened.");
                bundle1.putString("ques2","During which activity that happened");
                infoLanding.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,infoLanding);
                fragmentTransaction.commit();
            }
        }else if(type.equals("13")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                WhenHappened whenHappened=new WhenHappened();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","13");
                whenHappened.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,whenHappened);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "13");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }else if(type.equals("14")){
            if(!share){
                Bundle bundle = new Bundle();
                bundle.putString("mood", "NO");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                WhenHappened whenHappened=new WhenHappened();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","14");
                whenHappened.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,whenHappened);
                fragmentTransaction.commit();
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("mood", "YES");
                MoodResponse moodResponse=new MoodResponse();
                moodResponse.setArguments(bundle);
                fragmentTransaction.add(R.id.container,moodResponse);
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "14");
                MediaUpload mediaUpload=new MediaUpload();
                mediaUpload.setArguments(bundle1);
                fragmentTransaction.add(R.id.container,mediaUpload);
                fragmentTransaction.commit();
            }
        }




    }

    @Override
    public void MediaShared(boolean share,String type) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(type.equals("1")) {
            AnythingElse anythingElse=new AnythingElse();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","1");
            anythingElse.setArguments(bundle1);
            fragmentTransaction.add(R.id.container, anythingElse);
            fragmentTransaction.commit();
        }
        if(type.equals("4")){
            fragmentTransaction.add(R.id.container,new EndChat());
            fragmentTransaction.commit();
        }
        if(type.equals("5")){
            Bundle bundle1=new Bundle();
            bundle1.putString("type","5");
            WhenHappened whenHappened=new WhenHappened();
            whenHappened.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,whenHappened);
            fragmentTransaction.commit();
        }
        if(type.equals("6")){
            Bundle bundle1=new Bundle();
            bundle1.putString("type","6");
            WhenHappened whenHappened=new WhenHappened();
            whenHappened.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,whenHappened);
            fragmentTransaction.commit();
        }
        if(type.equals("8")){
            AnythingElse anythingElse=new AnythingElse();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","8");
            anythingElse.setArguments(bundle1);
            fragmentTransaction.add(R.id.container, anythingElse);
            fragmentTransaction.commit();
        }
        if(type.equals("9")){
            WhenHappened whenHappened=new WhenHappened();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","9");
            whenHappened.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,whenHappened);
            fragmentTransaction.commit();
        }
        if(type.equals("13")){
            WhenHappened whenHappened=new WhenHappened();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","13");
            whenHappened.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,whenHappened);
            fragmentTransaction.commit();
        }
        if(type.equals("14")){
            WhenHappened whenHappened=new WhenHappened();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","14");
            whenHappened.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,whenHappened);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onElseSelected(String share,String type) {
        mdatabase.child(person).child(time).child("AnythingElse").setValue(share);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(share.equals("None")){
            Bundle bundle = new Bundle();
            bundle.putString("mood", "None");
            MoodResponse moodResponse=new MoodResponse();
            moodResponse.setArguments(bundle);
            fragmentTransaction.add(R.id.container,moodResponse);
            fragmentTransaction.add(R.id.container,new EatingHabbits());
            fragmentTransaction.commit();
        }else if(share.equals("Progress")){
            Bundle bundle = new Bundle();
            bundle.putString("mood", "Progress");
            MoodResponse moodResponse=new MoodResponse();
            moodResponse.setArguments(bundle);
            fragmentTransaction.add(R.id.container,moodResponse);
            ActivityInfoLanding infoLanding=new ActivityInfoLanding();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","4");
            bundle1.putString("greeting","Yay ! I am really keen to know");
            bundle1.putString("ques1","let's save all that happened.");
            bundle1.putString("ques2","Which activity made your day?");
            infoLanding.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,infoLanding);
            fragmentTransaction.commit();
        }else if(share.equals("Meltdown")){
            Bundle bundle = new Bundle();
            bundle.putString("mood", "Meltdown");
            MoodResponse moodResponse=new MoodResponse();
            moodResponse.setArguments(bundle);
            fragmentTransaction.add(R.id.container,moodResponse);
            ActivityInfoLanding infoLanding=new ActivityInfoLanding();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","5");
            bundle1.putString("greeting","That was difficult!");
            bundle1.putString("ques1","let's save all that happened.");
            bundle1.putString("ques2","During which activity that happened");
            infoLanding.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,infoLanding);
            fragmentTransaction.commit();
        }else if(share.equals("Issues")){
            Bundle bundle = new Bundle();
            bundle.putString("mood", "Issues");
            MoodResponse moodResponse=new MoodResponse();
            moodResponse.setArguments(bundle);
            fragmentTransaction.add(R.id.container,moodResponse);
            ActivityInfoLanding infoLanding=new ActivityInfoLanding();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","6");
            bundle1.putString("greeting","That was difficult!");
            bundle1.putString("ques1","let's save all that happened.");
            bundle1.putString("ques2","The child faced Issues in?");
            infoLanding.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,infoLanding);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void someEatingHabbitChanged(boolean share) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(share){
            Bundle bundle = new Bundle();
            bundle.putString("mood", "Yes");
            MoodResponse moodResponse=new MoodResponse();
            moodResponse.setArguments(bundle);
            fragmentTransaction.add(R.id.container,moodResponse);
            Bundle bundle1=new Bundle();
            bundle1.putString("type","1");
            DescriptionBox descriptionBox=new DescriptionBox();
            descriptionBox.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,descriptionBox);
            fragmentTransaction.commit();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("mood", "No");
            MoodResponse moodResponse=new MoodResponse();
            moodResponse.setArguments(bundle);
            fragmentTransaction.add(R.id.container,moodResponse);
            Bundle bundle1 = new Bundle();
            bundle1.putString("type", "2");
            bundle1.putString("content","Did you start any medication?");
            MediaShare mediaShare=new MediaShare();
            mediaShare.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,mediaShare);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void someMedicStarted(String share,String type) {

        if(type.equals("1")) {
            mdatabase.child(person).child(time).child("SomeFoodChanges").setValue(share);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("type", "2");
            bundle.putString("content", "Did you start any medication?");
            MediaShare mediaShare = new MediaShare();
            mediaShare.setArguments(bundle);
            fragmentTransaction.add(R.id.container, mediaShare);
            fragmentTransaction.commit();
        }else if(type.equals("2")){
            mdatabase.child(person).child(time).child("SomeMedicationStarted").setValue(share);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("type", "3");
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String n1=prefs.getString("Name","child");
            bundle.putString("content", "Well great ! \nWhat about the sleep ? \nDid "+n1+" sleep well?");
            MediaShare mediaShare = new MediaShare();
            mediaShare.setArguments(bundle);
            fragmentTransaction.add(R.id.container, mediaShare);
            fragmentTransaction.commit();
        }else if(type.equals("3")){
            mdatabase.child(person).child(time).child("SomeSleepingChanges").setValue(share);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,new EndChat());
            fragmentTransaction.commit();
        }else if(type.equals("4")){
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,new EndChat());
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onwhen(String when, String type) {
        mdatabase.child(person).child(time).child("When").setValue(when);
        Toast.makeText(this, when, Toast.LENGTH_SHORT).show();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("mood", when);
        MoodResponse moodResponse=new MoodResponse();
        moodResponse.setArguments(bundle);
        fragmentTransaction.add(R.id.container,moodResponse);
        Bundle bundle1=new Bundle();
        bundle1.putString("type",type);
        WhereHappened whereHappened=new WhereHappened();
        whereHappened.setArguments(bundle1);
        fragmentTransaction.add(R.id.container,whereHappened);
        fragmentTransaction.commit();

    }

    @Override
    public void whereHappened(String when, String type) {
        mdatabase.child(person).child(time).child("Where").setValue(when);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        MoodOfChild moodOfChild=new MoodOfChild();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        moodOfChild.setArguments(bundle);
        fragmentTransaction.add(R.id.container,moodOfChild);
        fragmentTransaction.commit();

    }

    @Override
    public void childmoodListSelected(ArrayList<String> arrayList, String type) {
        mdatabase.child(person).child(time).child("childMood").setValue(arrayList);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        TimeOfChild timeOfChild=new TimeOfChild();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        timeOfChild.setArguments(bundle);
        fragmentTransaction.add(R.id.container,timeOfChild);
        fragmentTransaction.commit();

    }

    @Override
    public void someTime(String desc, String type) {
        mdatabase.child(person).child(time).child("HowLong").setValue(desc);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        WhatDidYouDo whatDidYouDo=new WhatDidYouDo();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        whatDidYouDo.setArguments(bundle);
        fragmentTransaction.add(R.id.container,whatDidYouDo);
        fragmentTransaction.commit();
    }

    @Override
    public void whatYouDo(String when, String type) {
        mdatabase.child(person).child(time).child("whatYouDo").setValue(when);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(type.equals("9")){
            AnythingElse anythingElse=new AnythingElse();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","9");
            anythingElse.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,anythingElse);
        }else if(type.equals("6")){
            fragmentTransaction.add(R.id.container,new EndChat());
        }else {
            PossibleTriggers possibleTriggers = new PossibleTriggers();
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            possibleTriggers.setArguments(bundle);
            fragmentTransaction.add(R.id.container, possibleTriggers);
        }
        fragmentTransaction.commit();

    }

    @Override
    public void possibleTrigger(String when, String type) {
        mdatabase.child(person).child(time).child("Trigger").setValue(when);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(type.equals("13") || type.equals("14")){
            AnythingElse anythingElse=new AnythingElse();
            Bundle bundle1=new Bundle();
            bundle1.putString("type","13");
            anythingElse.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,anythingElse);
        }else {
            fragmentTransaction.add(R.id.container, new EndChat());
        }
        fragmentTransaction.commit();

    }

    @Override
    public void someNameAdded(String name,String type) {
        if(type.equals("15")){
            person=name;
            Bundle bundle=new Bundle();
            bundle.putString("type","16");
            bundle.putString("name",name);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Name",name);
            editor.apply();
            OnBoard onBoard=new OnBoard();
            onBoard.setArguments(bundle);
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,onBoard);
            fragmentTransaction.commit();
        }else if(type.equals("16")){
            Bundle bundle=new Bundle();
            mdatabase.child(person).child(time).child("FoodHabitsGeneral").setValue(name);
            bundle.putString("type","17");
            bundle.putString("name",person);
            OnBoard onBoard=new OnBoard();
            onBoard.setArguments(bundle);
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,onBoard);
            fragmentTransaction.commit();
        }else if(type.equals("17")){
            mdatabase.child(person).child(time).child("SleepHabitsGeneral").setValue(name);
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,new StartJournaling());
            MoodMeter moodMeter=new MoodMeter();
            Bundle bundle1=new Bundle();
            bundle1.putString("moodMeterText","How's "+person+" day going?");
            moodMeter.setArguments(bundle1);
            fragmentTransaction.add(R.id.container,moodMeter);
            fragmentTransaction.commit();
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("FirstTime",false);
            editor.apply();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

    }
}
