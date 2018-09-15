package com.example.sndtcsi.mymemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class EditNoteActivity extends AppCompatActivity
{
    protected Button saveNote;
    protected Button deleteNote;
    int status=0;
    List<Note> noteList;
    protected EditText settitle;
    protected EditText setdescription;
    DatabaseValues dbvalues;
    int intentId;
    String intentTitle, intentDescription;
    int idValue;
    String titleValue, descriptionValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        saveNote = (Button) findViewById(R.id.saveButton);
        deleteNote = (Button) findViewById(R.id.deleteButton);
        noteList = new ArrayList<>();
        settitle = (EditText) findViewById(R.id.settitle);
        setdescription = (EditText) findViewById(R.id.setdescription);
        dbvalues = new DatabaseValues(this);

        Bundle b = getIntent().getExtras();
        int intentNote = b.getInt("notetype");

        switch (intentNote)
        {
            case 1:
                deleteNote.setVisibility(View.INVISIBLE);
                status =1;

                break;


            case 2:
                deleteNote.setVisibility(View.VISIBLE);
                status =2;

                intentId = b.getInt("id");
                intentTitle = b.getString("title");
                intentDescription = b.getString("description");

                settitle.setText(intentTitle);
                setdescription.setText(intentDescription);

                break;
        }


        saveNote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(status==1) {
                    addNote();
                }
                else if(status==2) {
                    updateNote();
                }
            }
        });

        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
            }
        });

    }

    private void getValues()
    {
        idValue = intentId;
        titleValue = settitle.getText().toString();
        descriptionValue = setdescription.getText().toString();
    }

    private void addNote()
    {
        getValues();

        dbvalues.save(titleValue, descriptionValue);

        Intent gotoMain = new Intent(EditNoteActivity.this, MainActivity.class);
        startActivity(gotoMain);
    }

    private void deleteNote()
    {
        getValues();

        dbvalues.delete(idValue);

        Intent gotoMain = new Intent(EditNoteActivity.this, MainActivity.class);
        startActivity(gotoMain);
    }

    private void updateNote()
    {
        getValues();

        dbvalues.update(titleValue, descriptionValue, idValue);

        Intent gotoMain = new Intent(EditNoteActivity.this, MainActivity.class);
        startActivity(gotoMain);
    }
}
