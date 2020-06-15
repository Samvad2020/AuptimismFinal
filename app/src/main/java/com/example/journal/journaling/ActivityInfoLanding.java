package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eyalbira.loadingdots.LoadingDots;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ActivityInfoLanding extends Fragment {

    public interface onRecordingSelectedListener {
        public void onRecordingSelected(String s,String type);
    }

    onRecordingSelectedListener someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onRecordingSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onRecordingSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activityinfolanding,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        final LoadingDots loadingDots=getView().findViewById(R.id.dots);
        final LinearLayout linearLayout=getView().findViewById(R.id.infoloadingroot);
        Runnable mRunnable;
        Handler mHandler=new Handler();

        mRunnable=new Runnable() {

            @Override
            public void run() {
                linearLayout.setVisibility(View.VISIBLE);
                loadingDots.setVisibility(View.GONE);
            }
        };
        mHandler.postDelayed(mRunnable,5*1000);
        TextView greeting= Objects.requireNonNull(getView()).findViewById(R.id.txt_greeting);
        TextView ques1=getView().findViewById(R.id.ques1);
        TextView ques2=getView().findViewById(R.id.ques2);
        assert getArguments() != null;
        String str_greeting=getArguments().getString("greeting");
        String str_ques1=getArguments().getString("ques1");
        String str_ques2=getArguments().getString("ques2");
        final String type=getArguments().getString("type");
        greeting.setText(str_greeting);
        ques1.setText(str_ques1);
        ques2.setText(str_ques2);
        final Button daily=getView().findViewById(R.id.dailyLiving);
        final Button learning=getView().findViewById(R.id.learningActivity);
        final Button social=getView().findViewById(R.id.SocialInteraction);
        final Button other=getView().findViewById(R.id.other);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onRecordingSelected("Daily Living",type);
                daily.setEnabled(false);
                learning.setEnabled(false);
                social.setEnabled(false);
                other.setEnabled(false);

            }
        });
        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onRecordingSelected("Learning Activity",type);
                daily.setEnabled(false);
                learning.setEnabled(false);
                social.setEnabled(false);
                other.setEnabled(false);
            }
        });
        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onRecordingSelected("Social Interaction",type);
                daily.setEnabled(false);
                learning.setEnabled(false);
                social.setEnabled(false);
                other.setEnabled(false);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onRecordingSelected("Other",type);
                daily.setEnabled(false);
                learning.setEnabled(false);
                social.setEnabled(false);
                other.setEnabled(false);
            }
        });

    }
}
