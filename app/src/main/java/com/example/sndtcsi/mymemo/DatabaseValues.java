package com.example.sndtcsi.mymemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseValues
{
    private SQLiteDatabase database;
    private DatabaseHandler dbhandler;
    List<Note> notesList;

    public DatabaseValues(Context context)
    {
        this.dbhandler = new DatabaseHandler(context);
    }

    public void open() {
        this.database = dbhandler.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void save(String title, String description)
    {
        open();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        database.insert(dbhandler.TABLE, null, values);
        close();
    }

    public void update(String title, String description, int id)
    {
        open();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        database.update(dbhandler.TABLE, values,   "id = ?", new String[]{String.valueOf(id)});
        close();
    }

    public void delete(int id)
    {
        open();
        database.delete(dbhandler.TABLE, "id = ?", new String[]{String.valueOf(id)});
        close();
    }

    public List<Note> getAllNotes()
    {
        notesList = new ArrayList<>();
        open();

        Cursor cursor = database.rawQuery("SELECT * FROM "+dbhandler.TABLE, null);
        if (cursor.moveToFirst())
            {
                do {
                    Note n = new Note();
                    n.setId(cursor.getInt(0));
                    n.setTitle(cursor.getString(1));
                    n.setDescription(cursor.getString(2));
                    notesList.add(n);
                } while (cursor.moveToNext());
            }
        close();
        return notesList;
    }

    public int getNotesCount()
    {
        String countQuery = "SELECT  * FROM "+dbhandler.TABLE;
        SQLiteDatabase db = dbhandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}
