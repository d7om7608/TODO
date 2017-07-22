package com.example.d7om7.todo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.d7om7.todo.Data.ItemHandler;
import com.example.d7om7.todo.Data.TodoCantract;
import com.example.d7om7.todo.Data.TodoDBHelper;
import com.example.d7om7.todo.Data.TodoHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.d7om7.todo.ListActivity.idOfTodoList;
import static com.example.d7om7.todo.R.id.AddListEditText;
import static com.example.d7om7.todo.R.id.checkBox;
import static com.example.d7om7.todo.R.id.checkbox;
import static com.example.d7om7.todo.TodoManager.todoLists;

/**
 * Created by Azura on 7/18/2017.
 */

public class ItemActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    EditText AddItemEditText ;
    CheckBox checkbox;
//    SettingsActivity settingsActivity = new SettingsActivity() ;
    int position;
    SQLiteDatabase mdb;
    TodoDBHelper helper=new TodoDBHelper(this);
    LinearLayout Itembackground ;
    ItemAdaptor myAdapter;
    RecyclerView.ViewHolder helpme;
    static int  ItemNumbers =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        position= getIntent().getIntExtra("position",0);
        AddItemEditText = (EditText) findViewById(R.id.AddItemEditText);
        Itembackground = (LinearLayout) findViewById(R.id.background);

        getAllTODO();

//        settingsActivity.colorSpinner(Itembackground);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ItemAdaptor(todoLists.get(position).items);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());





        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {




            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                myAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                todoLists.get(position).items.remove(viewHolder.getLayoutPosition());
                mdb=helper.getWritableDatabase();
                checkbox=(CheckBox) viewHolder.itemView.findViewById(R.id.checkBox);
                ItemHandler.removeItem(mdb,(Integer)viewHolder.itemView.getTag());

                if (checkbox.isChecked()){
                    ItemNumbers--;
                    checkbox.setChecked(false);
                    Log.d("hello",ItemNumbers+"");

                }

            }

        }).attachToRecyclerView(recyclerView);





    }

    private void getAllTODO() {

    mdb=helper.getReadableDatabase();
        Cursor cursor = ItemHandler.cursorItem(mdb);

        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String title=cursor.getString(cursor.getColumnIndex(TodoCantract.ItemEntry.ITEM_NAME));
            int id =cursor.getInt(cursor.getColumnIndex(TodoCantract.ItemEntry.ITEM_ID));
            todoLists.get(position).items.add(new TodoItem(title,true,id));
            }

    }

        public  void AddItemButton (View view){

        if (!AddItemEditText.getText().toString().equals("")) {
            todoLists.get(position).items.add(new TodoItem(AddItemEditText.getText().toString(),true));
            myAdapter.notifyDataSetChanged();
            mdb = helper.getWritableDatabase();
            ItemHandler.addNewItem(mdb,AddItemEditText.getText().toString(),position);


            AddItemEditText.setText("");

        }
    }

    @Override
    public void Clicked(int position) {
        Intent startChildActivityIntent = new Intent(this, ListActivity.class);
        startChildActivityIntent.putExtra("position", position);
        startActivity(startChildActivityIntent);

    }
    public void check(View view){
        checkbox=(CheckBox) view.findViewById(R.id.checkBox);

        if (checkbox.isChecked()){
            checkbox=(CheckBox)findViewById(R.id.checkBox);

          ItemNumbers ++;
           Log.d("hello",ItemNumbers+"");


      }else{
            checkbox=(CheckBox)findViewById(R.id.checkBox);

        ItemNumbers -- ;
          Log.d("hello",ItemNumbers+"");

       }


    }
}
