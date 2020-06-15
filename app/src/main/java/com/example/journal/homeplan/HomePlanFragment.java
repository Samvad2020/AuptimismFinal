package com.example.journal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomePlanFragment extends Fragment {

    ArrayList<DailyLifeItem> dailyLifeItems=new ArrayList<>();
    ArrayList<DailyLifeItem> educationalList=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_plan,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();

        RecyclerView recyclerView=getView().findViewById(R.id.dailyLifeActivities);
        final RecyclerAdapterDailyLife recyclerAdapterDailyLife=new RecyclerAdapterDailyLife(dailyLifeItems, getActivity(), new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(recyclerAdapterDailyLife);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("DailyLife").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    dailyLifeItems.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),i));
                    i++;
                }
                recyclerAdapterDailyLife.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RecyclerView educationalRecyclerView=getView().findViewById(R.id.educationalActivities);
        final RecyclerAdapterDailyLife educationalAdapter=new RecyclerAdapterDailyLife(educationalList,getActivity(),new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                startActivity(intent);
            }
        });
        educationalRecyclerView.setAdapter(educationalAdapter);
        educationalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("Educational").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("checker6","1");
                    educationalList.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),i));
                    i++;

                }
                educationalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        RecyclerView socialRecyclerView=getView().findViewById(R.id.socialActivities);
        final ArrayList<DailyLifeItem> socialList=new ArrayList<>();
        final RecyclerAdapterDailyLife socialAdapter=new RecyclerAdapterDailyLife(socialList,getActivity(),new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                startActivity(intent);
            }
        });
        socialRecyclerView.setAdapter(socialAdapter);
        socialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("Social").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("checker6","1");
                    socialList.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),i));
                    i++;
                }
                socialAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RecyclerView otherRecyclerView=getView().findViewById(R.id.otherActivities);
        final ArrayList<DailyLifeItem> otherList=new ArrayList<>();
        final RecyclerAdapterDailyLife otherAdapter=new RecyclerAdapterDailyLife(otherList,getActivity(),new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                startActivity(intent);
            }
        });
        otherRecyclerView.setAdapter(otherAdapter);
        otherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("Others").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("checker6","1");
                    otherList.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),i));
                    i++;
                }
                otherAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
