package com.alfidh02.kueibu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AddActivity extends AppCompatActivity {

    EditText deadlineInput,typeInput,quantityInput,noteInput;
    RelativeLayout addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        deadlineInput = findViewById(R.id.deadlineInput);
        typeInput = findViewById(R.id.typeInput);
        quantityInput = findViewById(R.id.quantityInput);
        noteInput = findViewById(R.id.noteInput);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                databaseHelper.addCake(deadlineInput.getText().toString().trim(),
                        Integer.valueOf(typeInput.getText().toString().trim()),
                        Integer.valueOf(quantityInput.getText().toString().trim()),
                        noteInput.getText().toString().trim());
                startActivity(new Intent(AddActivity.this,MainActivity.class));
            }
        });
    }

    public void backButton(View view) {
        finish();
    }
}
