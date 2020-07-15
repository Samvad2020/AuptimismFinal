package com.example.journal.ui.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.journal.Notification.NotificationRecyclerAdapter;
import com.example.journal.R;
import com.example.journal.model.NotificationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    RecyclerView notificationRecycler;
    ArrayList<NotificationModel> arrayListNotification;
    NotificationRecyclerAdapter notificationRecyclerAdapter;
    private DatabaseReference mdatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdatabase= FirebaseDatabase.getInstance().getReference();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        notificationRecycler=getView().findViewById(R.id.notificationRecyclerView);
        arrayListNotification=new ArrayList<>();
        notificationRecyclerAdapter=new NotificationRecyclerAdapter(arrayListNotification, getActivity(), new NotificationRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        notificationRecycler.setAdapter(notificationRecyclerAdapter);
        notificationRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }


        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                       // Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

        mdatabase.child("Vibhu").child("Notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()!=null){
                    arrayListNotification.clear();
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        arrayListNotification.add(new NotificationModel(dataSnapshot1.child("Title").getValue(String.class),dataSnapshot1.child("Description").getValue(String.class),"30 min"));
                    }
                    notificationRecyclerAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "No Notifications!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
