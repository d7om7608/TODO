package com.example.d7om7.todo;

/**
 * Created by d7om7 on 7/19/2017.
 */

public class TodoItem {
    String title;
    boolean check;
    int id;
    int Itemid;
//    public TodoItem(String s, boolean b){
//        title=s;
//        check=b;
//    }
   public TodoItem(String s, boolean b,int Itemid){
        title=s;
        check=b;
        this.id=id;
        this.Itemid=Itemid;

    }
}
