package com.example.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DescriptionBox extends Fragment {
    public interface onMedicationStarted{
        public void someMedicStarted(String share,String type);
    }

    onMedicationStarted someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onMedicationStarted) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.descriptionbox,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        final EditText editText= Objects.requireNonNull(getView()).findViewById(R.id.editwhatwentchanged);
        final Button button=getView().findViewById(R.id.submitChanged);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc=editText.getText().toString();
                someEventListener.someMedicStarted(desc,getArguments().getString("type"));
                button.setEnabled(false);
                editText.setEnabled(false);
            }
        });
    }
}
