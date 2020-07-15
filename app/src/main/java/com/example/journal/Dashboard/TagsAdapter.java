package com.example.journal.Dashboard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journal.R;
import com.example.journal.model.TagModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.UserViewHolder>{
    Context context;
    ArrayList<TagModel> tagsList;

    public TagsAdapter(Context context, ArrayList<TagModel> tagsList) {
        this.context = context;
        this.tagsList = tagsList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.tags_single_item,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TagModel tagModel=tagsList.get(position);
        holder.txt_tag.setText(tagModel.getTag());
        if(tagModel.isSelected()){
            holder.card_tag.setCardBackgroundColor(Color.parseColor("#204dea"));
            holder.txt_tag.setTextColor(Color.parseColor("#ffffff"));
        }else{
            holder.card_tag.setCardBackgroundColor(Color.parseColor("#ffffff"));
            holder.txt_tag.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return tagsList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        CardView card_tag;
        TextView txt_tag;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            card_tag=itemView.findViewById(R.id.tagCardView);
            txt_tag=itemView.findViewById(R.id.txt_tag);
            this.itemView=itemView;
        }
    }
}
