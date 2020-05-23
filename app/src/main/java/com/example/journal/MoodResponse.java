package com.example.journal;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eyalbira.loadingdots.LoadingDots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoodResponse extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.moodresponse,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        String strtext = getArguments().getString("mood");
        TextView textView=getView().findViewById(R.id.txt_moodresponse);
        textView.setText(strtext);
    }
}
