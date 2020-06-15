package com.example.journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterDailyLife extends RecyclerView.Adapter<RecyclerAdapterDailyLife.UserViewHolder>{
    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<DailyLifeItem> videos;
    Context context;
    OnItemClickListener listener;

    public RecyclerAdapterDailyLife(ArrayList<DailyLifeItem> videos, Context context, OnItemClickListener listener) {
        this.videos = videos;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.home_plan_single_item_layout,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        DailyLifeItem dailyLifeItem=videos.get(position);
        holder.txt_Name.setText(dailyLifeItem.getName());
        holder.txt_TimeSpent.setText(dailyLifeItem.getTimeSpent());
        holder.txt_TimeLeft.setText(dailyLifeItem.getTimeLeft());
        holder.txt_difficultyLevel.setText(dailyLifeItem.getDifficulty());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView imageActivity;
        TextView txt_Name;
        TextView txt_TimeLeft;
        TextView txt_difficultyLevel;
        TextView txt_TimeSpent;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            imageActivity=itemView.findViewById(R.id.imgActivity);
            txt_Name=itemView.findViewById(R.id.txt_nameOfActivity);
            txt_TimeLeft=itemView.findViewById(R.id.txt_timeleft);
            txt_difficultyLevel=itemView.findViewById(R.id.txt_difficulty);
            txt_TimeSpent=itemView.findViewById(R.id.txt_timeSpent);
            this.itemView=itemView;
        }
    }
}
