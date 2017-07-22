package com.example.d7om7.todo.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by d7om7 on 7/21/2017.
 */

public class ItemHandler {

    public static int addNewItem(SQLiteDatabase mDb, String categoryName,int id){
        ContentValues cv=new ContentValues();
        cv.put(TodoCantract.ItemEntry.ITEM_NAME,categoryName);
        cv.put(TodoCantract.ItemEntry.TODO_CONECT_ID,id);
        return (int)mDb.insert(TodoCantract.ItemEntry.TABLE_NAME,null,cv);}


    public static int removeItem(SQLiteDatabase mDb,int id){

        return mDb.delete(TodoCantract.ItemEntry.TABLE_NAME, TodoCantract.ItemEntry.ITEM_ID+" = "+id,null);
    }

    public static Cursor cursorItem(SQLiteDatabase mDb){


        return mDb.query(TodoCantract.ItemEntry.TABLE_NAME,null,null,null,null,null,null);}
}





