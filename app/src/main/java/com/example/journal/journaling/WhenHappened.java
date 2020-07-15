package com.example.journal.journaling;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.journal.R;
import com.eyalbira.loadingdots.LoadingDots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WhenHappened extends Fragment {
    public interface onWhenHappened {
        public void onwhen(String when,String type);
    }

    onWhenHappened someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onWhenHappened) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.whenhappened,container,false);
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
        final Button morning=getView().findViewById(R.id.morning);
        final Button afternoon=getView().findViewById(R.id.afternoon);
        final Button evening=getView().findViewById(R.id.evening);
        final Button night=getView().findViewById(R.id.night);
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onwhen("Morning",getArguments().getString("type"));
                morning.setEnabled(false);
                afternoon.setEnabled(false);
                evening.setEnabled(false);
                night.setEnabled(false);
            }
        });
        afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onwhen("Afternoon",getArguments().getString("type"));
                morning.setEnabled(false);
                afternoon.setEnabled(false);
                evening.setEnabled(false);
                night.setEnabled(false);
            }
        });
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onwhen("Evening",getArguments().getString("type"));
                morning.setEnabled(false);
                afternoon.setEnabled(false);
                evening.setEnabled(false);
                night.setEnabled(false);
            }
        });
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onwhen("Night",getArguments().getString("type"));
                morning.setEnabled(false);
                afternoon.setEnabled(false);
                evening.setEnabled(false);
                night.setEnabled(false);
            }
        });
    }
}
