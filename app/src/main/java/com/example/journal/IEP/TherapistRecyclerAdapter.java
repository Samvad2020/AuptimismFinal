package com.example.journal.IEP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journal.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class TherapistRecyclerAdapter extends RecyclerView.Adapter<TherapistRecyclerAdapter.UserViewHolder> {

    ArrayList<String> therapistProfiles;
    Context context;

    public TherapistRecyclerAdapter(ArrayList<String> therapistProfiles, Context context) {
        this.therapistProfiles = therapistProfiles;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.layout_therapist_single_item,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String therapistName=therapistProfiles.get(position);
        holder.nameOfTherapist.setText(therapistName);
        if(therapistName.equals("Uma Bachao")){
            holder.therapistImage.setBackgroundResource(R.drawable.demotherapist1);
        }else if(therapistName.equals("Deepa")){
            holder.therapistImage.setBackgroundResource(R.drawable.demotherapist2);
        }else if(therapistName.equals("Sheetal")){
            holder.therapistImage.setBackgroundResource(R.drawable.demotherapist3);
        }else{
            holder.therapistImage.setBackgroundResource(R.drawable.demotherapist4);
        }
    }

    @Override
    public int getItemCount() {
        return therapistProfiles.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfTherapist;
        CircleImageView therapistImage;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            nameOfTherapist=itemView.findViewById(R.id.txt_nameoftherapist);
            therapistImage=itemView.findViewById(R.id.therapistImage);
            this.itemView=itemView;
        }
    }
}
