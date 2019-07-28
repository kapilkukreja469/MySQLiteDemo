package com.example.mysqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText rollno, name, marks;
    Button send;
    TextView textView;
    SQLiteDatabase sqLiteDatabase;
    DataModel dataModel = new DataModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyHelper myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getReadableDatabase();
        rollno = findViewById(R.id.rollNo);
        name = findViewById(R.id.name);
        marks = findViewById(R.id.marks);
        send = findViewById(R.id.send);
        textView = findViewById(R.id.text);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyHelper helper = new MyHelper(MainActivity.this);
                dataModel.setRoll(Integer.parseInt(rollno.getText().toString()));
                dataModel.setName(name.getText().toString());
                dataModel.setMarks(Integer.parseInt(marks.getText().toString()));
                helper.insertData(dataModel);
                Toast.makeText(MainActivity.this, "Data Add Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchData(View view) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT rollNo,name,marks FROM STUDENT", new String[]{});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        StringBuilder builder = new StringBuilder("nbn");
        do {
            int roll = cursor.getInt(0);
            String name = cursor.getString(1);
            int marks = cursor.getInt(2);
            builder.append("rollnumber=" + roll + " name=" + name + " marks=" + marks + "\n");
        } while (cursor.moveToNext());
        textView.setText(builder.toString());
    }
}