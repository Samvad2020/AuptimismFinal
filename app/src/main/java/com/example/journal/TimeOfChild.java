package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.eyalbira.loadingdots.LoadingDots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimeOfChild extends Fragment {
    public interface onTimeOfChild {
        public void someTime(String desc,String type);
    }

    onTimeOfChild someEventListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onTimeOfChild) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timeofchild,container,false);
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
        final EditText editText=getView().findViewById(R.id.editTime);
        final Button button=getView().findViewById(R.id.submitTime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time=editText.getText().toString();
                someEventListener.someTime(time,getArguments().getString("type"));
                button.setEnabled(false);
                editText.setEnabled(false);
            }
        });
    }
}
