package com.example.journal.Documents;

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

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.UserViewHolder> {

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<String> arrayList;
    Context context;
    OnItemClickListener onItemClickListener;

    public DocumentListAdapter(ArrayList<String> arrayList, Context context, OnItemClickListener onItemClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.document_list_item,parent,false);
        UserViewHolder holder = new UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        holder.txt_document.setText(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txt_document;
        View itemView;


        public UserViewHolder(View itemView) {
            super(itemView);
            txt_document=itemView.findViewById(R.id.document_title);
            this.itemView=itemView;
        }
    }
}
