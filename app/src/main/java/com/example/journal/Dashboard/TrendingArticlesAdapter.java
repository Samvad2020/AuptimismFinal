package com.example.journal.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.journal.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingArticlesAdapter extends RecyclerView.Adapter<TrendingArticlesAdapter.UserViewHolder>{
    ArrayList<String> arrayList;
    Context context;

    public TrendingArticlesAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.singlearticleformat,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.txt_title.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            txt_title=itemView.findViewById(R.id.txt_title);
            this.itemView=itemView;
        }
    }
}
