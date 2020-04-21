package com.alfidh02.kueibu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText deadlineInput,typeInput,quantityInput,noteInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Helper.statusBarTranslucent(this);

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
            }
        });
    }

    public void backButton(View view) {
        finish();
    }
}
