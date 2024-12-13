package com.example.myproject;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name and Version
    public static final String DATABASE_NAME = "reminders.db";
    private static final int DATABASE_VERSION = 1;

//    public static String getDatabaseName() {
//        return DATABASE_NAME;
//    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the reminders table
        db.execSQL("CREATE TABLE reminders " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "priority TEXT," +
                "title TEXT," +
                "timeDate TEXT," +
                "isChecked INTEGER)"); // isChecked: 1 for true, 0 for false
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if the schema changes
        db.execSQL("DROP TABLE IF EXISTS reminders");
        onCreate(db);
    }
}

