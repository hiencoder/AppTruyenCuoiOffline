package com.example.mrphonglinh.apptruyencuoi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrphonglinh.apptruyencuoi.database.DatabaseHandler;
import com.example.mrphonglinh.apptruyencuoi.model.Story;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtCatName;
    ImageView btnBack, btnZoomIn, btnZoomOut, btnShare, btnFavourite;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    //Biến đối tượng truyện đang đọc
    private Story storyReading;
    //Biến vị trí của truyện đang đọc
    private int storyPosition = 0;
    private Intent intent;
    //Biến danh sách truyện
    ArrayList<Story> stories;
    int catId = 0;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        databaseHandler = new DatabaseHandler(this);
        initView();
        intent = getIntent();
        storyReading = (Story) intent.getSerializableExtra("story");
        catId = intent.getIntExtra("catid",0);
        Log.d("Cat id", "onCreate: " + catId);
        stories = databaseHandler.getStoryByCat(catId);
        txtCatName.setText(databaseHandler.getCatName(catId));

        //Set current page cho viewpager là chuyện đang được select
        storyPosition = intent.getIntExtra("position",0);
        Log.d("StoryActivity", "onCreate: " + stories.get(storyPosition).getContent());
        viewPagerAdapter = new ViewPagerAdapter(this,stories);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(storyPosition);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        txtCatName = (TextView) findViewById(R.id.txtTopicName);
        btnZoomIn = (ImageView) findViewById(R.id.btnZoomIn);
        btnZoomOut = (ImageView) findViewById(R.id.btnZoomOut);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnShare = (ImageView) findViewById(R.id.btnShare);
        btnFavourite = (ImageView) findViewById(R.id.btnFavourite);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                break;
            case R.id.btnZoomIn:
                break;
            case R.id.btnZoomOut:
                break;
            case R.id.btnFavourite:
                break;
            case R.id.btnShare:
                break;
        }
    }

    public class ViewPagerAdapter extends PagerAdapter{
        Context mContext;
        ArrayList<Story> listStory;

        public ViewPagerAdapter(Context mContext, ArrayList<Story> listStory) {
            this.mContext = mContext;
            this.listStory = listStory;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.lt_story_reading,null);
            Story story = listStory.get(position);
            TextView txtContent = (TextView) view.findViewById(R.id.txtContent);
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"Roboto-Light.ttf");
            txtContent.setTypeface(typeface);
            txtContent.setText(story.getContent());
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return listStory.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        //Hủy 1 item
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }
}
