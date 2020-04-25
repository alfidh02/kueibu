package com.alfidh02.kueibu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText deadlineInput2,typeInput2,quantityInput2,noteInput2;
    RelativeLayout editButton,deleteButton;

    String id, deadline, quantity, type, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        deadlineInput2 = findViewById(R.id.deadlineInput2);
        typeInput2 = findViewById(R.id.typeInput2);
        quantityInput2 = findViewById(R.id.quantityInput2);
        noteInput2 = findViewById(R.id.noteInput2);

        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

//        Call this first
        getSetIntentData();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                // Ini perlu, buat ngambil data dari inputan
                deadline = deadlineInput2.getText().toString().trim();
                type = typeInput2.getText().toString().trim();
                quantity = quantityInput2.getText().toString().trim();
                note = noteInput2.getText().toString().trim();
                //        So we can use this
                myDB.updateData(id, deadline, type, quantity, note);
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }


    void getSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("deadline") && getIntent().hasExtra("quantity") &&
            getIntent().hasExtra("type") && getIntent().hasExtra("note")) {

//          Getting data from intent
            id = getIntent().getStringExtra("id");
            deadline = getIntent().getStringExtra("deadline");
            quantity = getIntent().getStringExtra("quantity");
            type = getIntent().getStringExtra("type");
            note = getIntent().getStringExtra("note");

//          Setting intent data
            deadlineInput2.setText(deadline);
            quantityInput2.setText(quantity);
            typeInput2.setText(type);
            noteInput2.setText(note);

        } else {
            Toast.makeText(this, "Tidak ada data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void backButton(View view) {
        finish();
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" Hapus data? ");
        builder.setMessage("Yakin ingin hapus data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteOneRow(id);
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
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
