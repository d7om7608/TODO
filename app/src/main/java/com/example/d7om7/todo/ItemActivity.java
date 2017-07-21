package com.example.d7om7.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.d7om7.todo.TodoManager.todoLists;

/**
 * Created by Azura on 7/18/2017.
 */

public class ItemActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    EditText AddItemEditText ;
//    SettingsActivity settingsActivity = new SettingsActivity() ;
    int position;
    LinearLayout Itembackground ;
    ItemAdaptor myAdapter;
    public int ItemNumbers = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        position= getIntent().getIntExtra("position",0);
        AddItemEditText = (EditText) findViewById(R.id.AddItemEditText);
        Itembackground = (LinearLayout) findViewById(R.id.background);

//        settingsActivity.colorSpinner(Itembackground);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ItemAdaptor(todoLists.get(position).items);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,ItemTouchHelper.RIGHT) {



            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                myAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                todoLists.remove(viewHolder.getLayoutPosition());
                myAdapter.notifyDataSetChanged();

            }

        }).attachToRecyclerView(recyclerView);

    }

    public  void AddItemButton (View view){

        if (!AddItemEditText.getText().toString().equals("")) {
            todoLists.get(position).items.add(new TodoItem(AddItemEditText.getText().toString(),false));
            myAdapter.notifyDataSetChanged();
            AddItemEditText.setText("");
            ItemNumbers = ItemNumbers + 1 ;
        }
    }

    @Override
    public void Clicked(int position) {
        Intent startChildActivityIntent = new Intent(this, ListActivity.class);
        startChildActivityIntent.putExtra("position", position);
        startActivity(startChildActivityIntent);

    }
}
