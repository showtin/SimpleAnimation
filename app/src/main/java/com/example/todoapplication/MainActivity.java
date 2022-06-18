package com.example.todoapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBaseHelper db;
    ArrayList<String> note_id, note_title, note_description, note_date;
    NoteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Показываем стрелку в ActionBar
        recyclerView = findViewById(R.id.list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Define LayoutManager for RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton addButton = findViewById(R.id.add_note_button);
        addButton.setOnClickListener(this::onClickAddButton);

        db = new DataBaseHelper(MainActivity.this);
        note_id = new ArrayList<>();
        note_title = new ArrayList<>();
        note_description = new ArrayList<>();
        note_date = new ArrayList<>();

        storeDataInArrays();
        adapter = new NoteAdapter(MainActivity.this, note_id, note_title, note_description, note_date);


        recyclerView.setAdapter(adapter);

    }

    // store data which we are get from db
    void storeDataInArrays() {
        Cursor cursor = db.getAllNotes();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                note_id.add(cursor.getString(0));
                note_title.add(cursor.getString(1));
                note_description.add(cursor.getString(2));
                note_date.add(cursor.getString(3));

            }
        }
    }

    private void onClickAddButton(View view) {
        Intent intent = new Intent(this, NoteEditorActivity.class);
        startActivity(intent);
    }




    // show add_icon in menu bar
 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/
}