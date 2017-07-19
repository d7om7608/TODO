package com.example.d7om7.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static com.example.d7om7.todo.TodoManager.todoLists;

/**
 * Created by Azura on 7/18/2017.
 */

public class ItemActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    EditText AddItemEditText ;
    int position;

    ItemAdaptor myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        position= getIntent().getIntExtra("position",0);
        AddItemEditText = (EditText) findViewById(R.id.AddItemEditText);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ItemAdaptor(todoLists.get(position).items);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public  void AddItemButton (View view){

        if (!AddItemEditText.getText().toString().equals("")) {
            todoLists.get(position).items.add(new TodoItem(AddItemEditText.getText().toString(),false));
            myAdapter.notifyDataSetChanged();
            AddItemEditText.setText("");
        }
    }

    @Override
    public void Clicked(int position) {

    }
}
