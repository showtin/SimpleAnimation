package com.example.todoapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class NoteEditorActivity extends AppCompatActivity {
    EditText title_input, description_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(this::onClickBack);

        title_input = findViewById(R.id.title_input);
        description_input = findViewById(R.id.description_input);


    }

    /**
     * This method must save all changes about notes to database after called
     *
     * @see DataBaseHelper#addNote(java.lang.String, java.lang.String, java.lang.String)
     */
    //TODO Make input data processing
    private void onClickBack(View view) {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        DataBaseHelper db = new DataBaseHelper(this);
        db.addNote(title_input.getText().toString(), description_input.getText().toString(), formatter.format(date));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //LEGACY CODE (don't need for me but this code useful for menuBar processing)
/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //TODO add save data functionality
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/


}