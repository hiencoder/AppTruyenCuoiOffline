package com.example.mrphonglinh.apptruyencuoi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrphonglinh.apptruyencuoi.R;
import com.example.mrphonglinh.apptruyencuoi.CategoryActivity;
import com.example.mrphonglinh.apptruyencuoi.model.Category;

import java.util.ArrayList;

/**
 * Created by MyPC on 14/01/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.TopicViewHolder>
                            implements View.OnClickListener{
    Context context;
    ArrayList<Category> topics;

    //Mảng image
    int[] images = {
            R.drawable.im_28,R.drawable.im_1,R.drawable.im_2,R.drawable.im_3,
            R.drawable.im_4,R.drawable.im_5,R.drawable.im_6,R.drawable.im_7,
            R.drawable.im_10,R.drawable.im_12,R.drawable.im_13,R.drawable.im_14,
            R.drawable.im_15,R.drawable.im_22,R.drawable.im_27
    };

    public CategoryAdapter(Context context, ArrayList<Category> topics) {
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
        Category category = topics.get(position);
        holder.imgTopic.setImageResource(images[position]);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"Roboto-Light.ttf");
        holder.txtTopicName.setTypeface(typeface);
        holder.txtTopicName.setText(category.getName());
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
        Category category = topics.get(position);
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra("topic", category);
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
