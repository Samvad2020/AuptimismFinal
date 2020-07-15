package com.example.journal.Notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journal.R;
import com.example.journal.model.NotificationModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.UserViewHolder> {
    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<NotificationModel> videos;
    Context context;
    OnItemClickListener listener;

    public NotificationRecyclerAdapter(ArrayList<NotificationModel> videos, Context context, OnItemClickListener listener) {
        this.videos = videos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.notification_recycler_item,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        NotificationModel notificationModel=videos.get(position);
        holder.txt_Title.setText(notificationModel.getTitle());
        holder.txt_message.setText(notificationModel.getMessage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txt_Title;
        TextView txt_message;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            txt_Title=itemView.findViewById(R.id.txt_notification_title);
            txt_message=itemView.findViewById(R.id.txt_notification_message);
            this.itemView=itemView;
        }
    }
}
