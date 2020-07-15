package com.example.journal.journaling;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.journal.R;
import com.eyalbira.loadingdots.LoadingDots;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MediaShare extends Fragment {
    public interface onBooleanMediaShare {
        public void someMediaShare(boolean share,String type);
    }

    onBooleanMediaShare someEventListener;
    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onBooleanMediaShare) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mediashare,container,false);
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
        textView=getView().findViewById(R.id.txtquestiondesc);
        textView.setText(getArguments().getString("content"));
        final Button yes= Objects.requireNonNull(getView()).findViewById(R.id.yesMediaShare);
        final Button no=getView().findViewById(R.id.noMediaShare);
        final String mode=getArguments().getString("type");
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.someMediaShare(true,mode);
                yes.setEnabled(false);
                no.setEnabled(false);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.someMediaShare(false,mode);
                yes.setEnabled(false);
                no.setEnabled(false);
            }
        });
    }
}
