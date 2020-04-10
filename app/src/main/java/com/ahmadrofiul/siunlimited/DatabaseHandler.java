package com.ahmadrofiul.siunlimited;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tugas.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table data(matkul text null, deadline text null, tugas text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        //sql = "INSERT INTO data (matkul, deadline, tugas) VALUES ('1', 'Darsiwan', '1996-07-12');";
        //db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {


    }


}
