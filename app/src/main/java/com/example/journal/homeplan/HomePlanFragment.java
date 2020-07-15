package com.example.journal.homeplan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.journal.R;
import com.example.journal.model.DailyLifeItem;
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
    DatabaseReference mdatabase;
    RecyclerView recyclerView;
    RecyclerAdapterDailyLife recyclerAdapterDailyLife;
    RecyclerView educationalRecyclerView;
    RecyclerAdapterDailyLife educationalAdapter;
    RecyclerView socialRecyclerView;
    RecyclerAdapterDailyLife socialAdapter;
    RecyclerView otherRecyclerView;
    RecyclerAdapterDailyLife otherAdapter;
    ArrayList<DailyLifeItem> otherList=new ArrayList<>();
    ArrayList<DailyLifeItem> dailyLifeItems=new ArrayList<>();
    ArrayList<DailyLifeItem> educationalList=new ArrayList<>();
    ArrayList<DailyLifeItem> socialList=new ArrayList<>();





    public interface onSomeActivitySelected {
        public void someActivitySelected(int num,String type);

    }

    onSomeActivitySelected someEventListener;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        try {
            someEventListener = (onSomeActivitySelected) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_home_plan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView=getView().findViewById(R.id.dailyLifeActivities);
        recyclerAdapterDailyLife=new RecyclerAdapterDailyLife(dailyLifeItems, getActivity(), new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                someEventListener.someActivitySelected(position+1,"DailyLife");
            }
        });
        recyclerView.setAdapter(recyclerAdapterDailyLife);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("DailyLife").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                dailyLifeItems.clear();
                recyclerAdapterDailyLife.notifyDataSetChanged();
                for(final DataSnapshot snapshot:dataSnapshot.getChildren()){
                    final int finalI = i;
                    final int totalSteps=snapshot.child("stepsTotal").getValue(Integer.class);
                    final int[] checkedSteps = {0};
                    mdatabase.child("Vibhu").child("HomePlan").child("DailyLife").child(i+"").child("steps").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot1:dataSnapshot.getChildren()){
                                if(snapshot1.child("done").getValue(Boolean.class)){
                                    checkedSteps[0]++;
                                }
                            }
                            final int result=Math.round((checkedSteps[0]*100/totalSteps));
                            mdatabase.child("Vibhu").child("HomePlan").child("DailyLife").child(finalI+"").child("media").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.getKey()!=null){
                                        String imgActivity=dataSnapshot.child("1").getValue(String.class);
                                        dailyLifeItems.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),result, finalI,imgActivity));
                                        recyclerAdapterDailyLife.notifyDataSetChanged();
                                    }else{
                                        dailyLifeItems.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),result, finalI,null));
                                        recyclerAdapterDailyLife.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    i++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        educationalRecyclerView=getView().findViewById(R.id.educationalActivities);
        educationalAdapter=new RecyclerAdapterDailyLife(educationalList,getActivity(),new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                intent.putExtra("type","Educational");
                startActivity(intent);
            }
        });
        educationalRecyclerView.setAdapter(educationalAdapter);
        educationalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("Educational").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                educationalList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("checker6","1");
//                    int pro=snapshot.child("progress").getValue(Integer.class);
                    educationalList.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),0,i,"tI7fhRpTXXg"));
                    i++;

                }
                educationalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        socialRecyclerView=getView().findViewById(R.id.socialActivities);
        socialAdapter=new RecyclerAdapterDailyLife(socialList,getActivity(),new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                intent.putExtra("type","Social");
                startActivity(intent);
            }
        });
        socialRecyclerView.setAdapter(socialAdapter);
        socialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("Social").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                socialList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("checker6","1");
                    socialList.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),0,i,"ecty4bj6d6o"));
                    i++;
                }
                socialAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        otherRecyclerView=getView().findViewById(R.id.otherActivities);
        otherAdapter=new RecyclerAdapterDailyLife(otherList,getActivity(),new RecyclerAdapterDailyLife.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),HomePlanActivityDetailedInfo.class);
                intent.putExtra("pos",position+1);
                intent.putExtra("type","Others");
                startActivity(intent);
            }
        });
        otherRecyclerView.setAdapter(otherAdapter);
        otherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        mdatabase.child("Vibhu").child("HomePlan").child("Others").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                otherList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("checker6","1");
                    otherList.add(new DailyLifeItem(snapshot.child("name").getValue(String.class),snapshot.child("timeleft").getValue(Integer.class)+" days left","Time spent: "+snapshot.child("timespent").getValue(Integer.class) +" mins","Difficulty: "+snapshot.child("difficulty").getValue(String.class),0,i,"PrHM0WQBVwE"));
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
