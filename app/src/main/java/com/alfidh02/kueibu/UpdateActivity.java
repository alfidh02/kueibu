package com.alfidh02.kueibu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText deadlineInput2,typeInput2,quantityInput2,noteInput2;
    RelativeLayout editButton;

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

        editButton = findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSetIntentData();
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
}
