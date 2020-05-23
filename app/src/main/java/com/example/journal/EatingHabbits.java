package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EatingHabbits extends Fragment {
    public interface onBooleanEatingHabitChanged {
        public void someEatingHabbitChanged(boolean share);
    }

    onBooleanEatingHabitChanged someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onBooleanEatingHabitChanged) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.eatinghabbits,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        final Button yes=getView().findViewById(R.id.yesChangeInEatingHabits);
        final Button no=getView().findViewById(R.id.noChangeInEatingHabits);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.someEatingHabbitChanged(true);
                yes.setEnabled(false);
                no.setEnabled(false);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               someEventListener.someEatingHabbitChanged(false);
               yes.setEnabled(false);
               no.setEnabled(false);

            }
        });
    }
}
