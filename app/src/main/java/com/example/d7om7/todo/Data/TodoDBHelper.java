package com.example.d7om7.todo.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.name;

/**
 * Created by d7om7 on 7/21/2017.
 */

public class TodoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="myTodo.db";
    private static final int DATABASE_VERSION=5;

    public TodoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREAT_TODO_TABLE=" CREATE TABLE "+TodoCantract.TodoEntry.TABLE_NAME+" ("+
                TodoCantract.TodoEntry.TODO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TodoCantract.TodoEntry.TODO_NAME+ " TEXT NOT NULL );";
        db.execSQL(CREAT_TODO_TABLE);

        final String CREAT_ITME_TABLE="CREATE TABLE "+TodoCantract.ItemEntry.TABLE_NAME+" ("+
                TodoCantract.ItemEntry.ITEM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TodoCantract.ItemEntry.TODO_CONECT_ID+" INTEGER NOT NULL, "+
                TodoCantract.ItemEntry.TIME_CHECK+" BOOLEAN NOT NULL, "+
                TodoCantract.ItemEntry.ITEM_NAME+" TEXT NOT NULL,"+
                TodoCantract.ItemEntry.TIME_STAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
                "FOREIGN KEY ("+ TodoCantract.ItemEntry.TODO_CONECT_ID+" ) REFERENCES "+
                TodoCantract.TodoEntry.TABLE_NAME+"("+ TodoCantract.TodoEntry.TODO_ID+"));";
        db.execSQL(CREAT_ITME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TodoCantract.ItemEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TodoCantract.TodoEntry.TABLE_NAME);
        onCreate(db);
    }
}
