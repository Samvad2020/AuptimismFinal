package com.example.journal.journaling;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.journal.R;
import com.eyalbira.loadingdots.LoadingDots;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OnBoard extends Fragment {
    public interface onNameProvided {
        public void someNameAdded(String name,String type,ArrayList<String> list);
    }

    onNameProvided someEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onNameProvided) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.onboard,container,false);
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
        final ArrayList<String> arrayList=getArguments().getStringArrayList("list");
        TextView textView=getView().findViewById(R.id.txt_name);
        if(getArguments().getString("type").equals("15")){
    //        arrayList.add("Hello ! Welcome Onboard.\n\nI'm you journal assistant\n\nMy name is Diary\n\nWhat's your little one's name?");
            textView.setText("Hello ! Welcome Onboard.\n\nI'm you journal assistant\n\nMy name is Diary\n\nWhat's your little one's name?");
        }else if(getArguments().getString("type").equals("16")){
//            arrayList.add("What does \" +getArguments().getString(\"name\")+\" eat daily?");
            textView.setText("What does " +getArguments().getString("name")+" eat daily?");
        }else if(getArguments().getString("type").equals("17")){
  //          arrayList.add("What is regular sleep time of "+getArguments().getString("name")+"?");
            textView.setText("What is regular sleep time of "+getArguments().getString("name")+"?");
        }
        final Button button=getView().findViewById(R.id.submitIntroduction);
        final EditText editText=getView().findViewById(R.id.editName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                someEventListener.someNameAdded(name,getArguments().getString("type"),arrayList);
                editText.setEnabled(false);
                button.setEnabled(false);
            }
        });
    }
}
