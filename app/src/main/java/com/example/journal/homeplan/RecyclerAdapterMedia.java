package com.example.journal.homeplan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journal.R;
import com.example.journal.model.DailyLifeMediaItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterMedia extends RecyclerView.Adapter<RecyclerAdapterMedia.UserViewHolder>{
    interface OnItemClickListener {

        void onItemClick(int position);
    }
    ArrayList<DailyLifeMediaItem> videos;
    Context context;
    OnItemClickListener listener;

    public RecyclerAdapterMedia(ArrayList<DailyLifeMediaItem> videos, Context context, OnItemClickListener listener) {
        this.videos = videos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.dailylifeitemlayout,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        final String video=videos.get(position).getUrl();
        if(videos.get(position).isImage()){
            Picasso.get().load(video).into(holder.imageActivity);
            holder.imagePlay.setVisibility(View.GONE);
        }else{
            Picasso.get().load("https://img.youtube.com/vi/"+video+"/0.jpg").into(holder.imageActivity);
        }
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
        ImageView imageActivity;
        View itemView;
        ImageView imagePlay;


        public UserViewHolder(View itemView) {
            super(itemView);
            imageActivity=itemView.findViewById(R.id.img_media_daily);
            imagePlay=itemView.findViewById(R.id.img_play);
            this.itemView=itemView;
        }
    }
}
