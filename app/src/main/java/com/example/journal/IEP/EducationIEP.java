package com.example.journal.IEP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.journal.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EducationIEP extends Fragment {
    RecyclerView therapistRecyclerView;
    ArrayList<String> therapists;
    TherapistRecyclerAdapter therapistRecyclerAdapter;
    RecyclerView objectivesRecyclerView;
    ArrayList<String> objectives;
    ObjectivesRecyclerAdapter objectivesRecyclerAdapter;
    RecyclerView topicsRecyclerView;
    ArrayList<String> topics;
    ObjectivesRecyclerAdapter topicsRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenteducationiep,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        therapists=new ArrayList<>();
        therapists.add("Uma Bachao");
        therapists.add("Deepa");
        therapists.add("Sheetal");
        therapists.add("Poornima");
        therapistRecyclerView=getView().findViewById(R.id.recycler_therapists);
        therapistRecyclerAdapter=new TherapistRecyclerAdapter(therapists,getActivity());
        therapistRecyclerView.setAdapter(therapistRecyclerAdapter);
        therapistRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        objectivesRecyclerView=getView().findViewById(R.id.recycler_objectives);
        objectives=new ArrayList<>();
        objectives.add("1. Make him learn to speak A-Z.");
        objectives.add("2. Make him learn Multiplications.");
        objectives.add("3. Make him do additions.");
        objectivesRecyclerAdapter=new ObjectivesRecyclerAdapter(objectives,getActivity());
        objectivesRecyclerView.setAdapter(objectivesRecyclerAdapter);
        objectivesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        topicsRecyclerView=getView().findViewById(R.id.recycler_topics);
        topics=new ArrayList<>();
        topics.add("1. Maths");
        topics.add("2. English");
        topics.add("3. Something");
        topicsRecyclerAdapter=new ObjectivesRecyclerAdapter(topics,getActivity());
        topicsRecyclerView.setAdapter(topicsRecyclerAdapter);
        topicsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));


    }
}
