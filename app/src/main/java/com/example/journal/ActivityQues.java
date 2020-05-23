package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ActivityQues extends Fragment {

    public interface onCaptureDescription {
        public void capturedDescription(String s,String type);
    }

    onCaptureDescription someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onCaptureDescription) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activityques,container,false);
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
        final String type=getArguments().getString("type");
        if(type.equals("1")){
            TextView textView= getView().findViewById(R.id.txtstartques);
            textView.setText("Wonderful! Please describe the activity");
        }else if(type.equals("4")){
            TextView textView= getView().findViewById(R.id.txtstartques);
            textView.setText("Wonderful! Please describe the activity");
        }else if(type.equals("5")){
            TextView textView= getView().findViewById(R.id.txtstartques);
            textView.setText("Can you please describe the activity");
        }else if(type.equals("6")) {
            TextView textView = getView().findViewById(R.id.txtstartques);
            textView.setText("Can you please describe the activity");
        }else if(type.equals("7")){
            TextView textView = getView().findViewById(R.id.txtstartques);
            textView.setText("Great! Please describe the activity");
        }else if(type.equals("9")){
            TextView textView = getView().findViewById(R.id.txtstartques);
            textView.setText("Can you please describe the activity");
        }else if(type.equals("11")){
            TextView textView = getView().findViewById(R.id.txtstartques);
            textView.setText("Oh no! please share the details");
        }else if(type.equals("12")){
            TextView textView = getView().findViewById(R.id.txtstartques);
            textView.setText("Oh no! please share the details");
        }else if(type.equals("14")){
            TextView textView = getView().findViewById(R.id.txtstartques);
            textView.setText("Then what happened ?");
        }
        final Button submit=getView().findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=getView().findViewById(R.id.editDescription);
                String desc=editText.getText().toString();
                submit.setEnabled(false);
                editText.setEnabled(false);
                someEventListener.capturedDescription(desc,type);
            }
        });
    }
}
