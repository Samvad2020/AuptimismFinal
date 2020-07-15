package com.example.journal.IEP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.journal.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ObjectivesRecyclerAdapter extends RecyclerView.Adapter<ObjectivesRecyclerAdapter.UserViewHolder>  {

    ArrayList<String> objectivesList;
    Context context;

    public ObjectivesRecyclerAdapter(ArrayList<String> objectivesList, Context context) {
        this.objectivesList = objectivesList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.layout_objectives_single_item,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String objective=objectivesList.get(position);
        holder.txt_Objective.setText(objective);
    }

    @Override
    public int getItemCount() {
        return objectivesList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txt_Objective;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            txt_Objective=itemView.findViewById(R.id.txt_objective);
            this.itemView=itemView;
        }
    }
}
