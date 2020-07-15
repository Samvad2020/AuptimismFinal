package com.example.journal.homeplan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journal.model.DailyLifeItem;
import com.example.journal.R;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        DailyLifeItem dailyLifeItem=videos.get(position);
        holder.txt_Name.setText(dailyLifeItem.getName());
        holder.txt_TimeSpent.setText(dailyLifeItem.getTimeSpent());
        holder.txt_TimeLeft.setText(dailyLifeItem.getTimeLeft());
        holder.txt_difficultyLevel.setText(dailyLifeItem.getDifficulty());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        holder.txt_progress.setText(dailyLifeItem.getProgress()+"% Complete");
        String lastFourDigits=videos.get(position).getImgActivityUrl().substring(videos.get(position).getImgActivityUrl().length()-4);
        if(lastFourDigits.substring(0,1).equals(".")){
            Picasso.get().load(videos.get(position).getImgActivityUrl()).into(holder.imageActivity);
            Log.d("checker","yes");
        }else{
            Picasso.get().load("https://img.youtube.com/vi/"+videos.get(position).getImgActivityUrl()+"/0.jpg").into(holder.imageActivity);
        }

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
        TextView txt_progress;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            imageActivity=itemView.findViewById(R.id.imgActivity);
            txt_Name=itemView.findViewById(R.id.txt_nameOfActivity);
            txt_TimeLeft=itemView.findViewById(R.id.txt_timeleft);
            txt_difficultyLevel=itemView.findViewById(R.id.txt_difficulty);
            txt_TimeSpent=itemView.findViewById(R.id.txt_timeSpent);
            txt_progress=itemView.findViewById(R.id.txt_progress);
            this.itemView=itemView;
        }
    }
}
