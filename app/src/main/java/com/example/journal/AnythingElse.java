package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnythingElse extends Fragment {
    public interface onAnythingElseSelected{
        public void onElseSelected(String share,String type);
    }

    onAnythingElseSelected someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onAnythingElseSelected) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anythingelse,container,false);
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
        final Button progress= Objects.requireNonNull(getView()).findViewById(R.id.progress);
        final Button issues=getView().findViewById(R.id.issues);
        final Button meltdown=getView().findViewById(R.id.meltdown);
        final Button none=getView().findViewById(R.id.noOther);
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onElseSelected("None",getArguments().getString("type"));
                progress.setEnabled(false);
                issues.setEnabled(false);
                meltdown.setEnabled(false);
                none.setEnabled(false);
            }
        });
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onElseSelected("Progress",getArguments().getString("type"));
                progress.setEnabled(false);
                issues.setEnabled(false);
                meltdown.setEnabled(false);
                none.setEnabled(false);
            }
        });
        issues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onElseSelected("Issues",getArguments().getString("type"));
                progress.setEnabled(false);
                issues.setEnabled(false);
                meltdown.setEnabled(false);
                none.setEnabled(false);
            }
        });
        meltdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.onElseSelected("Meltdown",getArguments().getString("type"));
                progress.setEnabled(false);
                issues.setEnabled(false);
                meltdown.setEnabled(false);
                none.setEnabled(false);
            }
        });
    }
}
