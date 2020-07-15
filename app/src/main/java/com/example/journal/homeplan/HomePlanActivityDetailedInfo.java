package com.example.journal.homeplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journal.R;
import com.example.journal.model.DailyLifeMediaItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HomePlanActivityDetailedInfo extends AppCompatActivity {
    int pos;
    String type;
    ArrayList<DailyLifeMediaItem> arrayList;
    RecyclerAdapterMedia recyclerAdapterMedia;
    private DatabaseReference mdatabase;
    RecyclerView recyclerView;
    TextView txt_about_activity;
    TextView txt_tips;
    LinearLayout layoutSteps;
//    ListView listView;
//    ArrayList<String> arrayListComments;
//    ArrayAdapter<String> arrayAdapter;
    LinearLayout layoutComments;
    int hey=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_plan_detailed_info);
//        listView=findViewById(R.id.listview_comments);
//        arrayListComments=new ArrayList<>();
//        arrayAdapter=new ArrayAdapter<>(HomePlanActivityDetailedInfo.this,android.R.layout.simple_list_item_1,arrayListComments);
//        listView.setAdapter(arrayAdapter);
        layoutComments=findViewById(R.id.layout_comments);
        layoutSteps=findViewById(R.id.layout_steps);
        txt_about_activity=findViewById(R.id.txt_about_activity);
        txt_tips=findViewById(R.id.txt_tips_suggestions);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        Intent intent=getIntent();
        pos=intent.getIntExtra("pos",-1);
        type=intent.getStringExtra("type");
        arrayList=new ArrayList<>();
        recyclerAdapterMedia=new RecyclerAdapterMedia(arrayList, HomePlanActivityDetailedInfo.this, new RecyclerAdapterMedia.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent1=new Intent(HomePlanActivityDetailedInfo.this,MediaPlayingActivity.class);
                intent1.putExtra("videoid",arrayList.get(position).getUrl());
                startActivity(intent1);
            }
        });
        recyclerView=findViewById(R.id.dailyLifeMediaRecyclerview);
        recyclerView.setAdapter(recyclerAdapterMedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePlanActivityDetailedInfo.this, LinearLayoutManager.HORIZONTAL,false));

        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("media").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String lastFourDigits = Objects.requireNonNull(snapshot.getValue(String.class)).substring(Objects.requireNonNull(snapshot.getValue(String.class)).length() - 4);
                    if(lastFourDigits.substring(0,1).equals(".")){
                        arrayList.add(new DailyLifeMediaItem(snapshot.getValue(String.class),false,true));
                    }else{
                        arrayList.add(new DailyLifeMediaItem(snapshot.getValue(String.class),true,false));
                    }

                }
                recyclerAdapterMedia.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("introduction").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String check="";
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    check=check+snapshot.getValue(String.class)+"\n\n";
                }
                txt_about_activity.setText(check);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("tips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String check="";
                int temp=1;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    check=check+temp+". ";
                    check=check+snapshot.getValue(String.class)+"\n\n";
                    temp++;
                }
                txt_tips.setText(check);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("steps").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layoutSteps.removeAllViews();
                for(final DataSnapshot snapshot:dataSnapshot.getChildren()){
                    final CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText(snapshot.child("name").getValue(String.class));
                    if(snapshot.child("done").getValue(Boolean.class)!=null && snapshot.child("done").getValue(Boolean.class)){
                        cb.setChecked(true);
                    }else{
                        cb.setChecked(false);
                    }
                    final String num=snapshot.getKey();
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
                            final boolean[] check1 = {false};
                            final boolean[] check2 = {false};
                            final boolean[] check3 = {false};
                            if(b){
                                View howDone=View.inflate(HomePlanActivityDetailedInfo.this,R.layout.choosehowvideodone,null);
                                TextView txt_howdone=howDone.findViewById(R.id.txt_how_done);
                                CheckBox checkBoxAssis=howDone.findViewById(R.id.checkboxAssistance);
                                checkBoxAssis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("steps").child(num).child("doneAssistance").setValue(b);
                                        cb.setChecked(false);
                                        check1[0] =true;
                                    }
                                });
                                CheckBox checkBoxSelf=howDone.findViewById(R.id.checkboxIndio);
                                checkBoxSelf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("steps").child(num).child("doneOwn").setValue(b);
                                        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("steps").child(num).child("done").setValue(b);
                                        cb.setChecked(true);
                                        check2[0] =true;
                                    }
                                });
                                CheckBox checkBoxPrompt=howDone.findViewById(R.id.checkboxPrompt);
                                checkBoxPrompt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("steps").child(num).child("donePrompt").setValue(b);
                                        cb.setChecked(false);
                                        check3[0] =true;
                                    }
                                });
                                txt_howdone.setText(snapshot.child("name").getValue(String.class));
                                AlertDialog.Builder builder = new AlertDialog.Builder(HomePlanActivityDetailedInfo.this);
                                builder.setView(howDone)
                                        .setCancelable(false)
                                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                if(!check1[0] && !check2[0] && !check3[0]){
                                                    cb.setChecked(!b);
                                                }
                                            }
                                        })
                                        .show();

                            }

                        }
                    });
                    layoutSteps.addView(cb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                layoutComments.removeAllViews();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    TextView textView=new TextView(getApplicationContext());
                    textView.setText(snapshot.child("name").getValue(String.class)+" : "+snapshot.child("content").getValue(String.class));
                    layoutComments.addView(textView);
//                    arrayListComments.add(snapshot.child("name").getValue(String.class)+" : "+snapshot.child("content").getValue(String.class));
                    hey++;
//                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final EditText editText=findViewById(R.id.edit_comments);
        findViewById(R.id.img_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("comments").child(hey+"").child("name").setValue("Tim");
                mdatabase.child("Vibhu").child("HomePlan").child(type).child(pos+"").child("comments").child(hey+"").child("content").setValue(editText.getText().toString());
                editText.setText("");
                hey++;
            }
        });

    }
}
