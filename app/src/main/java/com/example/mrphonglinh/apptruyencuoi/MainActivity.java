package com.example.mrphonglinh.apptruyencuoi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mrphonglinh.apptruyencuoi.adapter.CategoryAdapter;
import com.example.mrphonglinh.apptruyencuoi.database.DatabaseHandler;
import com.example.mrphonglinh.apptruyencuoi.model.Category;
import com.example.mrphonglinh.apptruyencuoi.model.Story;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView recyclerViewTopic;
    ArrayList<Category> categories;
    Category[] topics = {
    };
    CategoryAdapter categoryAdapter;
    LinearLayoutManager manager;
    FloatingActionButton btnGotoTop;

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Init dữ liệu
        databaseHandler = new DatabaseHandler(this);
        CopyAsync copyAsync = new CopyAsync();
        copyAsync.execute();
        recyclerViewTopic = (RecyclerView) findViewById(R.id.recyclerViewTopic);
        //categoryAdapter = new CategoryAdapter(this,new ArrayList<Category>(Arrays.asList(topics)));
        categories = databaseHandler.getAllCategory();
        categoryAdapter = new CategoryAdapter(this,categories);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewTopic.setLayoutManager(manager);
        recyclerViewTopic.setAdapter(categoryAdapter);

        btnGotoTop = (FloatingActionButton) findViewById(R.id.btnGotoTop);
        btnGotoTop.setOnClickListener(this);

        recyclerViewTopic.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Lấy ra tổng item
                int totalItemCount = manager.getItemCount();
                //Lấy ra vị trí của item cuối trên màn hình
                int lastVisibleItemCount = manager.findLastVisibleItemPosition();
                if (totalItemCount - lastVisibleItemCount == 1){
                    if (btnGotoTop.getVisibility() == View.INVISIBLE)
                        btnGotoTop.setVisibility(View.VISIBLE);
                }else {
                    if (btnGotoTop.getVisibility() == View.VISIBLE)
                        btnGotoTop.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGotoTop:
                Thread mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewTopic.smoothScrollToPosition(0);
                    }
                });
                mThread.start();
                break;
        }
    }

    //Viết class asynctask để load dữ liệu
    public class CopyAsync extends AsyncTask<Void,Void,Void>{
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Copying data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Gọi hàm iscreateDatabase
            try {
                if (databaseHandler.isCreateDatabase()){
                    Toast.makeText(MainActivity.this, "Copy data thành công", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
