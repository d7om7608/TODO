package com.example.d7om7.todo;

import java.util.List;

/**
 * Created by d7om7 on 7/17/2017.
 */

public class TodoList {
    public String title;
    public List<TodoItem>items;
    public int id;
    public TodoList(String title, List<TodoItem>items){
        this.title=title;
        this.items=items;

    }
    public TodoList(String title, List<TodoItem>items,int id){
        this.title=title;
        this.items=items;
        this.id=id;

    }

}
