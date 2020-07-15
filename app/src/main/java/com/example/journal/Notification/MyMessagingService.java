package com.example.journal.Notification;

import android.app.NotificationManager;
import android.util.Log;

import com.example.journal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyMessagingService extends FirebaseMessagingService {

    private DatabaseReference mdatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mdatabase= FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotification(final String title, final String message){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.profile)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat manager=NotificationManagerCompat.from(getApplicationContext());
        manager.notify(999,builder.build());
        final int[] i = {0};
        Log.d("vibhu123456",title);
        mdatabase.child("Vibhu").child("Notifications").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getKey()!=null){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        i[0]++;
                    }
                }
                mdatabase.child("Vibhu").child("Notifications").child(i[0]+1+"").child("Title").setValue(title);
                mdatabase.child("Vibhu").child("Notifications").child(i[0]+1+"").child("Description").setValue(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
