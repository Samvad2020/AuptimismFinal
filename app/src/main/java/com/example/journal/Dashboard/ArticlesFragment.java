package com.example.journal.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.journal.R;
import com.example.journal.model.TagModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArticlesFragment extends Fragment {
    RecyclerView tagsRecyclerView;
    ArrayList<TagModel> tagsList;
    TagsAdapter tagsAdapter;
    RecyclerView articlesRecyclerView;
    ArrayList<String> articleTitle;
    TrendingArticlesAdapter trendingArticlesAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        tagsRecyclerView=getView().findViewById(R.id.recyclerViewTags);
        tagsList=new ArrayList<>();
        tagsList.add(new TagModel("All",true));
        tagsList.add(new TagModel("Nutrition",false));
        tagsList.add(new TagModel("Sensory",false));
        tagsList.add(new TagModel("Games",false));
        tagsAdapter=new TagsAdapter(getActivity(),tagsList);
        tagsRecyclerView.setAdapter(tagsAdapter);
        tagsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        articlesRecyclerView=getView().findViewById(R.id.recyclerViewTrendingArticles);
        articleTitle=new ArrayList<>();
        articleTitle.add("Top 10 sensory...");
        articleTitle.add("Things to try with kids at home");
        articleTitle.add("Facts about ASD");
        trendingArticlesAdapter=new TrendingArticlesAdapter(articleTitle,getActivity());
        articlesRecyclerView.setAdapter(trendingArticlesAdapter);
        articlesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
    }
}
