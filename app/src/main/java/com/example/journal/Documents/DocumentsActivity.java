package com.example.journal.Documents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.journal.R;

import java.util.ArrayList;

public class DocumentsActivity extends AppCompatActivity {
    RecyclerView recyclerViewAdmission;
    RecyclerView recyclerViewHomework;
    DocumentListAdapter admissionAdapter;
    DocumentListAdapter homeworkAdapter;
    ArrayList<String> arrayListadmission;
    ArrayList<String> arrayListhomework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        arrayListadmission=new ArrayList<>();
        arrayListadmission.add("Photograph");
        arrayListadmission.add("Other doc");
        arrayListhomework=new ArrayList<>();
        arrayListhomework.add("Heading");
        arrayListhomework.add("Other doc");
        recyclerViewAdmission=findViewById(R.id.admissiondocumentlist);
        recyclerViewHomework=findViewById(R.id.homeworkdocumentlist);
        admissionAdapter=new DocumentListAdapter(arrayListadmission, DocumentsActivity.this, new DocumentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerViewAdmission.setAdapter(admissionAdapter);
        recyclerViewAdmission.setLayoutManager(new LinearLayoutManager(DocumentsActivity.this, LinearLayoutManager.VERTICAL,false));
        homeworkAdapter=new DocumentListAdapter(arrayListhomework, DocumentsActivity.this, new DocumentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerViewHomework.setAdapter(homeworkAdapter);
        recyclerViewHomework.setLayoutManager(new LinearLayoutManager(DocumentsActivity.this, LinearLayoutManager.VERTICAL,false));

    }
}
