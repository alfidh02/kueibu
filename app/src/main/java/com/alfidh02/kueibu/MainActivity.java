package com.alfidh02.kueibu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addData,deleteAll;

    CustomAdapter customAdapter;

    TextView textEmpty;

    ImageView emptyData;

    DatabaseHelper databaseHelper;
    ArrayList<String> cake_id,cake_deadline,cake_type,cake_quantity,cake_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.recyclerView);
        textEmpty = findViewById(R.id.textEmpty);
        emptyData = findViewById(R.id.emptyData);
        addData = findViewById(R.id.createData);
        deleteAll = findViewById(R.id.deleteAll);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeData(){
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 0){
            deleteAll.setVisibility(View.INVISIBLE);
            emptyData.setVisibility(View.VISIBLE);
            textEmpty.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()){
                cake_id.add(cursor.getString(0));
                cake_deadline.add(cursor.getString(1));
                cake_type.add(cursor.getString(2));
                cake_quantity.add(cursor.getString(3));
                cake_note.add(cursor.getString(4));
            }
            emptyData.setVisibility(View.GONE);
            textEmpty.setVisibility(View.GONE);
            deleteAll.setVisibility(View.VISIBLE);
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Hapus semua data? ");
        builder.setMessage("Yakin ingin hapus semua data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                databaseHelper.deleteAllData();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
