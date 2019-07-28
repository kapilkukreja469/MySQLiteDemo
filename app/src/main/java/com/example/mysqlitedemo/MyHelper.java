package com.example.mysqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.PrintStream;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "College", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creatQuery = "CREATE TABLE STUDENT(rollNo INTEGER PRIMARY KEY ,name TEXT,marks INTEGER)";
        sqLiteDatabase.execSQL(creatQuery);
    }
    public void insertData(DataModel dataModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rollNo", dataModel.getRoll());
        values.put("name", dataModel.getName());
        values.put("marks", dataModel.getMarks());
        database.insert("STUDENT", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
