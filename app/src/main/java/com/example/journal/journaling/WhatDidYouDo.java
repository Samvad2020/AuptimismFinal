package com.example.journal.journaling;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.journal.R;
import com.eyalbira.loadingdots.LoadingDots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WhatDidYouDo extends Fragment {
    public interface onWhatDidYouDo {
        public void whatYouDo(String when,String type);
    }

    onWhatDidYouDo someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onWhatDidYouDo) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.whatdidyoudo,container,false);
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
        final EditText editText=getView().findViewById(R.id.editwhatdidyoudo);
        final Button button=getView().findViewById(R.id.submitWhatdid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what=editText.getText().toString();
                someEventListener.whatYouDo(what,getArguments().getString("type"));
                editText.setEnabled(false);
                button.setEnabled(false);
            }
        });
    }
}
