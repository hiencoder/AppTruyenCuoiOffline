package com.example.mrphonglinh.apptruyencuoi.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mrphonglinh.apptruyencuoi.model.Category;
import com.example.mrphonglinh.apptruyencuoi.model.Story;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by MyPC on 22/02/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public String DATABASE_PATH = "//data//data//%s//databases//";
    public static final String DATABASE_NAME = "truyencuoi2016.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_STORIES = "stories";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COUNT = "count";
    public static final String KEY_ORDERING = "ordering";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_CATID = "cat_id";
    public static final String KEY_CREATED = "created";
    public SQLiteDatabase sqLiteDatabase;
    private Context mContext;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DATABASE_PATH = String.format(DATABASE_PATH,context.getPackageName());
        this.mContext = context;
    }

    /*- Copy database từ assets folder nếu nó k tồn tại
    * - Trả về true nếu nó không tồn tại và tạo database thành công*/
    public boolean isCreateDatabase() throws Exception {
        //Mặc định
        boolean result = true;
        //Nếu chưa tồn tại data thì cop từ assets vào data
        if (!checkExistDatabase()){
            this.getReadableDatabase();
            try {
                copyDatabase();
                result = false;
            }catch (Exception e){
                throw new Exception("Lỗi copy database");
            }
        }
        return result;
    }

    /*- Kiểm tra xem database đã tồn tại hay chưa
    * - Nếu đã tồn tại thì return true*/
    private boolean checkExistDatabase() {
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File fileDB = new File(myPath);
            if (fileDB.exists()){
                //Nếu đã tồn tại file
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    //Hàm copy database
    private void copyDatabase() throws IOException {
        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);
        OutputStream myOutput = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer, 0, length);
        }
        //Đóng luồng ghi dữ liệu
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /*Xóa file database*/
    public boolean deleteDatabase(){
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        return file.delete();
    }

    /*Hàm mở database*/
    public void openDatabase(){
        sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME,
                null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    /*Phương thức đóng database*/
    public synchronized void close(){
        if (sqLiteDatabase != null){
            sqLiteDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Phương thức đọc toàn bộ danh sách categories
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> categories = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CATEGORIES + " ORDER BY " + KEY_ORDERING + " DESC";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int count = cursor.getInt(cursor.getColumnIndex(KEY_COUNT));
                int ordering = cursor.getInt(cursor.getColumnIndex(KEY_ORDERING));

                Category category = new Category(id,name,count,ordering);
                categories.add(category);

                cursor.moveToNext();
            }
        }
        return categories;
    }

    //Phương thức lấy danh sách truyện theo cat_id
    public ArrayList<Story> getStoryByCat(int catId){
        ArrayList<Story> stories = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_STORIES + " WHERE " + KEY_CATID + " = " + catId;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
                String created = cursor.getString(cursor.getColumnIndex(KEY_CREATED));

                Story story = new Story(id,name,content,catId,created);

                stories.add(story);
                cursor.moveToNext();
            }
        }
        return stories;
    }

    //Phương thức lấy ra cat name theo catid
    public String getCatName(int catId){
        String catName = "";
        sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT " + KEY_NAME + " FROM " + TABLE_CATEGORIES + " WHERE " + KEY_ID + " = " + catId;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                catName = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        return catName;
    }
}
