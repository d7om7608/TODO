package com.example.d7om7.todo.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by d7om7 on 7/21/2017.
 */

public class TodoHandler {

   public static int addNewTodo(SQLiteDatabase mDb,String TODOName){
       ContentValues cv=new ContentValues();
       cv.put(TodoCantract.TodoEntry.TODO_NAME,TODOName);
       return (int)mDb.insert(TodoCantract.TodoEntry.TABLE_NAME,null,cv);}


    public static int removeTODO(SQLiteDatabase mDb,int id){

        return mDb.delete(TodoCantract.TodoEntry.TABLE_NAME, TodoCantract.TodoEntry.TODO_ID+" = "+id,null);
    }

    public static Cursor cursor(SQLiteDatabase mDb){


    return mDb.query(TodoCantract.TodoEntry.TABLE_NAME,null,null,null,null,null,null);}
}
