package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.eyalbira.loadingdots.LoadingDots;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoodOfChild extends Fragment {

    public interface onMoodOfChild {
        public void childmoodListSelected(ArrayList<String> arrayList,String type);
    }

    onMoodOfChild someEventListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onMoodOfChild) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.moodofchild,container,false);
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
        final CheckBox aggressive=getView().findViewById(R.id.aggressive);
        final CheckBox annoyed=getView().findViewById(R.id.annoyed);
        final CheckBox grumpy=getView().findViewById(R.id.grumpy);
        final CheckBox crying=getView().findViewById(R.id.crying);
        final CheckBox sleepy=getView().findViewById(R.id.sleepy);
        final CheckBox irritated=getView().findViewById(R.id.irritated);
        final CheckBox selfHarm=getView().findViewById(R.id.selfharm);
        final CheckBox others=getView().findViewById(R.id.other);
        final Button button=getView().findViewById(R.id.submitMood);
        final ArrayList<String> arrayList=new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aggressive.isChecked()){
                    arrayList.add("Aggressive");
                }
                if(annoyed.isChecked()){
                    arrayList.add("Annoyed");
                }
                if(grumpy.isChecked()){
                    arrayList.add("Grumpy");
                }
                if(crying.isChecked()){
                    arrayList.add("Crying");
                }
                if(sleepy.isChecked()){
                    arrayList.add("Sleepy");
                }
                if(irritated.isChecked()){
                    arrayList.add("Irritated");
                }
                if(selfHarm.isChecked()){
                    arrayList.add("Self Harm");
                }
                if(others.isChecked()){
                    arrayList.add("Others");
                }
                button.setEnabled(false);
                aggressive.setEnabled(false);
                annoyed.setEnabled(false);
                grumpy.setEnabled(false);
                crying.setEnabled(false);
                sleepy.setEnabled(false);
                irritated.setEnabled(false);
                selfHarm.setEnabled(false);
                others.setEnabled(false);
                someEventListener.childmoodListSelected(arrayList,getArguments().getString("type"));
            }
        });
    }
}
