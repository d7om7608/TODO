package com.example.d7om7.todo.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static android.R.attr.description;
import static android.os.Build.VERSION_CODES.N;

/**
 * Created by d7om7 on 7/21/2017.
 */

public class ItemHandler {

    public static int addNewItem(SQLiteDatabase mDb, String categoryName, int id, boolean bol) {
        ContentValues cv = new ContentValues();
        cv.put(TodoCantract.ItemEntry.ITEM_NAME, categoryName);
        cv.put(TodoCantract.ItemEntry.TODO_CONECT_ID, id);
        cv.put(TodoCantract.ItemEntry.TIME_CHECK,bol);
        return (int) mDb.insert(TodoCantract.ItemEntry.TABLE_NAME, null, cv);
    }

    public static int updateItem(SQLiteDatabase mDb,int ItemId,boolean bol,int TODOid) {

        ContentValues cv = new ContentValues();

        cv.put(TodoCantract.ItemEntry.TIME_CHECK,bol);
        cv.put(TodoCantract.ItemEntry.ITEM_ID,ItemId);
        cv.put(TodoCantract.ItemEntry.TODO_CONECT_ID,TODOid);
        return mDb.update(TodoCantract.ItemEntry.TABLE_NAME, cv,
                TodoCantract.ItemEntry.ITEM_ID + " = " + ItemId, null);
    }

    public static int removeItem(SQLiteDatabase mDb, int id) {

        return mDb.delete(TodoCantract.ItemEntry.TABLE_NAME,
                TodoCantract.ItemEntry.ITEM_ID + " = " + id, null);
    }
    public static Cursor cursorItem(SQLiteDatabase mDb, int TODOId) {
        return mDb.query(
                TodoCantract.ItemEntry.TABLE_NAME, null,
                TodoCantract.ItemEntry.TODO_CONECT_ID + " = " + TODOId,
                null, null, null, null);
    }
}




