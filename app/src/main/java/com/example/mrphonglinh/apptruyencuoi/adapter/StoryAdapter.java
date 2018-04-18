package com.example.mrphonglinh.apptruyencuoi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrphonglinh.apptruyencuoi.R;
import com.example.mrphonglinh.apptruyencuoi.StoryActivity;
import com.example.mrphonglinh.apptruyencuoi.model.Category;
import com.example.mrphonglinh.apptruyencuoi.model.Story;

import java.util.ArrayList;

/**
 * Created by MyPC on 14/01/2017.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>
                        implements View.OnClickListener{
    Activity activity;
    ArrayList<Story> stories;

    public StoryAdapter(Activity activity, ArrayList<Story> stories) {
        this.activity = activity;
        this.stories = stories;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story,null);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Story story = stories.get(position);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(),"Roboto-Light.ttf");
        holder.txtStoryName.setTypeface(typeface);
        holder.txtStoryName.setText(story.getName());
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        Story story = stories.get(position);
        Intent intent = new Intent(activity, StoryActivity.class);
        Category category = (Category) activity.getIntent().getSerializableExtra("topic");
        int catId = category.getId();
        intent.putExtra("catid",catId);
        intent.putExtra("story",story);
        intent.putExtra("position",position);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.right_in,R.anim.left_out);
    }

    class StoryViewHolder extends RecyclerView.ViewHolder{
        TextView txtStoryName;
        public StoryViewHolder(View itemView) {
            super(itemView);
            txtStoryName = (TextView) itemView.findViewById(R.id.txtStoryName);
        }
    }
}
