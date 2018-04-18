package com.example.mrphonglinh.apptruyencuoi;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrphonglinh.apptruyencuoi.adapter.StoryAdapter;
import com.example.mrphonglinh.apptruyencuoi.adapter.TopicAdapter;
import com.example.mrphonglinh.apptruyencuoi.model.ItemStory;
import com.example.mrphonglinh.apptruyencuoi.model.ItemTopic;

import java.util.ArrayList;
import java.util.Arrays;

public class TopicActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton btnGotoTop;
    ImageView btnBack;
    TextView txtTopicName;
    RecyclerView recyclerViewStory;
    ArrayList<ItemStory> stories;
    StoryAdapter storyAdapter;
    LinearLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        final Intent intent = getIntent();
        ItemTopic itemTopic = (ItemTopic) intent.getSerializableExtra("topic");
        btnGotoTop = (FloatingActionButton) findViewById(R.id.btnGotoTop);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        txtTopicName = (TextView) findViewById(R.id.txtTopicName);
        txtTopicName.setText(itemTopic.getTopic());

        recyclerViewStory = (RecyclerView) findViewById(R.id.recyclerViewStory);
        stories = itemTopic.getStories();
        storyAdapter = new StoryAdapter(this,stories);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewStory.setLayoutManager(manager);
        recyclerViewStory.setAdapter(storyAdapter);
        recyclerViewStory.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemCount = manager.findLastVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (totalItemCount - lastVisibleItemCount == 1){
                    if (btnGotoTop.getVisibility() == View.INVISIBLE)
                        btnGotoTop.setVisibility(View.VISIBLE);
                }else {
                    if (btnGotoTop.getVisibility() == View.VISIBLE)
                        btnGotoTop.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnBack.setOnClickListener(this);
        btnGotoTop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnGotoTop:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewStory.smoothScrollToPosition(0);
                    }
                }).start();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
}
