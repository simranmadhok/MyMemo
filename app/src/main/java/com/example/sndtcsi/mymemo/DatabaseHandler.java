package com.example.sndtcsi.mymemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public static final String DATABASE = "memos.db";
    public static final String TABLE = "memo";
    public static final int VERSION = 1;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    public DatabaseHandler(Context context)
    {
        super(context,  DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_TEXT+" TEXT,"+COLUMN_DESCRIPTION+" TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "memo");
        onCreate(sqLiteDatabase);
    }
}
