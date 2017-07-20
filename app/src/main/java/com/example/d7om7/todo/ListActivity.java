package com.example.d7om7.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.settingsActivity;
import static com.example.d7om7.todo.TodoManager.todoLists;

public class ListActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
//    SettingsActivity.
    ListAdaptor myAdapter;
    EditText AddListEditText;
    LinearLayout Listbackground ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        AddListEditText=(EditText)findViewById(R.id.AddListEditText);
        Listbackground = (LinearLayout) findViewById(R.id.background);

//        int color = getResources().getColor(R.color.Blue);
//        Listbackground.setBackgroundColor(color);
//        settingsActivity.colorSpinner(Listbackground,settingsActivity.BackGroundColorSpinner);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ListAdaptor(todoLists,this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {



            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                long id = (long) viewHolder.itemView.getTag();
//            mAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());

                myAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                todoLists.remove(viewHolder.getLayoutPosition());
                myAdapter.notifyDataSetChanged();

            }

        }).attachToRecyclerView(recyclerView);
 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_avtivity_mnue,menu);
        return true;}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        if(id==R.id.action_settings){
            Intent startSettingsActivity=new Intent(this,SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    public  void AddListButton (View view){

        if (!AddListEditText.getText().toString().equals("")) {
            todoLists.add(new TodoList(AddListEditText.getText().toString(),new ArrayList<TodoItem>()));
            myAdapter.notifyDataSetChanged();

            AddListEditText.setText("");
        }

   }


    @Override
    public void Clicked(int position) {
        Intent startChildActivityIntent = new Intent(this, ItemActivity.class);
        startChildActivityIntent.putExtra("position", position);
        startActivity(startChildActivityIntent);

    }

}
