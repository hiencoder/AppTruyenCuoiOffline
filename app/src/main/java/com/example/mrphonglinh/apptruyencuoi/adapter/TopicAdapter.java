package com.example.mrphonglinh.apptruyencuoi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrphonglinh.apptruyencuoi.R;
import com.example.mrphonglinh.apptruyencuoi.TopicActivity;
import com.example.mrphonglinh.apptruyencuoi.model.ItemTopic;

import java.util.ArrayList;

/**
 * Created by MyPC on 14/01/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder>
                            implements View.OnClickListener{
    Context context;
    ArrayList<ItemTopic> topics;

    public TopicAdapter(Context context, ArrayList<ItemTopic> topics) {
        this.context = context;
        this.topics = topics;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic,null);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        ItemTopic itemTopic = topics.get(position);
        holder.imgTopic.setImageResource(itemTopic.getImage());
        holder.txtTopicName.setText(itemTopic.getTopic());
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        ItemTopic itemTopic = topics.get(position);
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra("topic",itemTopic);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_in,R.anim.left_out);
    }

    class TopicViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTopic;
        TextView txtTopicName;
        ImageView imgOpen;
        public TopicViewHolder(View itemView) {
            super(itemView);
            imgTopic = (ImageView) itemView.findViewById(R.id.imgTopic);
            txtTopicName = (TextView) itemView.findViewById(R.id.txtTopicName);
            imgOpen = (ImageView) itemView.findViewById(R.id.imgOpen);
        }
    }

}
