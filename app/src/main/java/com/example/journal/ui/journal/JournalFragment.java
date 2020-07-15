package com.example.journal.ui.journal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journal.R;
import com.example.journal.journaling.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class JournalFragment extends Fragment {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public interface onStartJournaling {
        public void someJournalingSelected(boolean check);
    }

    onStartJournaling someEventListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onStartJournaling) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_journal_start,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().findViewById(R.id.btn_start_journal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.someJournalingSelected(true);

            }
        });

    }
}
