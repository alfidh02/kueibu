package com.alfidh02.kueibu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addData;

    CustomAdapter customAdapter;

    TextView notifText;

    DatabaseHelper databaseHelper;
    ArrayList<String> cake_id,cake_deadline,cake_type,cake_quantity,cake_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.recyclerView);
        addData = findViewById(R.id.createData);
        notifText = findViewById(R.id.notifText);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });

        databaseHelper = new DatabaseHelper(MainActivity.this);
        cake_id = new ArrayList<>();
        cake_deadline = new ArrayList<>();
        cake_type = new ArrayList<>();
        cake_quantity = new ArrayList<>();
        cake_note = new ArrayList<>();

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this,cake_id,cake_deadline,cake_type,cake_quantity,cake_note);
        if (customAdapter.getItemCount() != 0){
            notifText.setVisibility(View.GONE);
        } else {
            notifText.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeData(){
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                cake_id.add(cursor.getString(0));
                cake_deadline.add(cursor.getString(1));
                cake_type.add(cursor.getString(2));
                cake_quantity.add(cursor.getString(3));
                cake_note.add(cursor.getString(4));
            }
        }
    }
}
