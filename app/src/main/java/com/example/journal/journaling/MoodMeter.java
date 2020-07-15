package com.example.journal.journaling;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journal.R;
import com.eyalbira.loadingdots.LoadingDots;
import com.ramotion.circlemenu.CircleMenuView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoodMeter extends Fragment {
    private SeekBar moodseekbar;
    String mood="Wonderful";
    public interface onSomeEventListener {
        public void someEvent(String s);
    }

    onSomeEventListener someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onSomeEventListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.moodmeter,container,false);
    }


    @Override
    public void onResume() {
        super.onResume();
        TextView textView=getView().findViewById(R.id.txtmoodmeter);
        textView.setText(getArguments().getString("moodMeterText"));
        final LinearLayout rootLayout=getView().findViewById(R.id.rootLayout);
        final LoadingDots loadingDots=getView().findViewById(R.id.dots);
        Runnable mRunnable;
        Handler mHandler=new Handler();

        mRunnable=new Runnable() {

            @Override
            public void run() {
                rootLayout.setVisibility(View.VISIBLE);
                loadingDots.setVisibility(View.GONE);
            }
        };
        mHandler.postDelayed(mRunnable,5*1000);
        final Drawable thumbHappy = getResources().getDrawable(R.drawable.happy);
        final Drawable thumbNeutral = getResources().getDrawable(R.drawable.neutral);
        final Drawable thumbSad = getResources().getDrawable(R.drawable.sad);
        final Drawable thumbVerySad=getResources().getDrawable(R.drawable.exterme);
        moodseekbar= Objects.requireNonNull(getView()).findViewById(R.id.moodseekbar);
        moodseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i==0){
                    moodseekbar.setThumb(thumbHappy);
                    mood="Wonderful";
                }else if(i==1){
                    moodseekbar.setThumb(thumbNeutral);
                    mood="Happy";
                }else if(i==2){
                    moodseekbar.setThumb(thumbSad);
                    mood="Difficult";
                }else{
                    moodseekbar.setThumb(thumbVerySad);
                    mood="Extremely Difficult";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                someEventListener.someEvent(mood);
                seekBar.setEnabled(false);

            }
        });

    }
}
