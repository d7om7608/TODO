package com.example.d7om7.todo;

import android.app.Activity;
import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.nfc.Tag;
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

import static android.R.attr.description;
import static android.R.attr.name;
import static com.example.d7om7.todo.Data.ItemHandler.updateItem;
import static com.example.d7om7.todo.ListAdaptor.myposition;
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
    int position;
    SQLiteDatabase mdb;
    TodoDBHelper helper=new TodoDBHelper(this);
    LinearLayout Itembackground ;

    ItemAdaptor ItmemyAdapter;
    RecyclerView.ViewHolder helpme;
    static int  ItemNumbers =0;

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
        ItmemyAdapter=new ItemAdaptor(todoLists.get(position).items);
        recyclerView.setAdapter(ItmemyAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());





        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {




            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                ItmemyAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                todoLists.get(position).items.remove(viewHolder.getLayoutPosition());
                mdb=helper.getWritableDatabase();
                checkbox=(CheckBox) viewHolder.itemView.findViewById(R.id.checkBox);
                ItemHandler.removeItem(mdb,(Integer)viewHolder.itemView.getTag());

                // Log.d("Number of Rows that have been removed",((Integer)viewHolder.itemView.getTag())+"");
                //Log.d("Number of Rows that have been removed",ItemHandler.removeItem(mdb,(Integer)viewHolder.itemView.getTag())+"");




            }

        }).attachToRecyclerView(recyclerView);

    }


        public  void AddItemButton (View view){

            if (!AddItemEditText.getText().toString().equals("")) {
                mdb = helper.getWritableDatabase();
                String name = AddItemEditText.getText().toString();
                ItemHandler.addNewItem(mdb, name, todoLists.get(position).id, false);
                todoLists.get(position).items.add(new TodoItem(name, false, todoLists.get(position).id));
                ItmemyAdapter.notifyDataSetChanged();
                AddItemEditText.setText("");

                setResult(Activity.RESULT_OK, getIntent());
            }

    }

    @Override
    public void Clicked(int position,int id) {

//        ItemHandler.updateItem(mdb,todoLists.get(position)
//                .items.get(todoLists.get(this.position).id).title, )

        Intent startChildActivityIntent = new Intent(this, ListActivity.class);
        startChildActivityIntent.putExtra("position", position);
        startActivity(startChildActivityIntent);

    }
    public void check(View view){
         mdb = helper.getReadableDatabase();
        checkbox=(CheckBox) view.findViewById(R.id.checkBox);
        checkbox=(CheckBox)findViewById(R.id.checkBox);
        Cursor cursor = TodoHandler.cursor(mdb);
        cursor.moveToPosition(position);
        int id = cursor.getInt(cursor.getColumnIndex(TodoCantract.TodoEntry.TODO_ID));
        Cursor ItemsCursor = ItemHandler.cursorItem(mdb,id);
        ItemsCursor.moveToPosition((int)view.getTag());
        int ItemsId = ItemsCursor.getInt(ItemsCursor.getColumnIndex(TodoCantract.ItemEntry.ITEM_ID));
        int  todoid=ItemsCursor.getInt(ItemsCursor.getColumnIndex(TodoCantract.ItemEntry.TODO_CONECT_ID));


        if (checkbox.isChecked()){
            mdb = helper.getWritableDatabase();

            ItemHandler.updateItem(mdb,ItemsId,true,todoid);

      }else{
            mdb = helper.getWritableDatabase();

            ItemHandler.updateItem(mdb,ItemsId,false,todoid);

       }










    }
}
