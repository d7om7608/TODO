package com.example.d7om7.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


import com.example.d7om7.todo.Data.TodoCantract;
import com.example.d7om7.todo.Data.TodoDBHelper;
import com.example.d7om7.todo.Data.TodoHandler;


import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;
import static android.R.attr.settingsActivity;
import static com.example.d7om7.todo.TodoManager.todoLists;

public class ListActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    TextView ListNumbersTextView ;
    ItemActivity itemActivity = new ItemActivity() ;
    ListAdaptor myAdapter;
    EditText AddListEditText;
    LinearLayout Listbackground ;
    SQLiteDatabase mdb;
    TodoDBHelper helper=new TodoDBHelper(this);
    static int idOfTodoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        ListNumbersTextView = (TextView) findViewById(R.id.List_number_TextView);
        AddListEditText=(EditText)findViewById(R.id.AddListEditText);
        Listbackground = (LinearLayout) findViewById(R.id.background);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAllTODO();
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

                myAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                todoLists.remove(viewHolder.getLayoutPosition());
                mdb=helper.getWritableDatabase();
                TodoHandler.removeTODO(mdb,(Integer)viewHolder.itemView.getTag());

                myAdapter.notifyDataSetChanged();

            }

        }).attachToRecyclerView(recyclerView);
 }

 private void getAllTODO(){
     mdb=helper.getReadableDatabase();
     Cursor cursor=TodoHandler.cursor(mdb);
     for(int i=0;i<cursor.getCount();i++){
         cursor.moveToPosition(i);
      String title=cursor.getString(cursor.getColumnIndex(TodoCantract.TodoEntry.TODO_NAME));
         int id =cursor.getInt(cursor.getColumnIndex(TodoCantract.TodoEntry.TODO_ID));
         todoLists.add(new TodoList(title,new ArrayList<TodoItem>(),id));

     }
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
            mdb = helper.getWritableDatabase();
            int i= TodoHandler.addNewTodo(mdb,AddListEditText.getText().toString());


         String mItemNumbers = "45";
            //ListNumbersTextView.setText("45");
            AddListEditText.setText("");
        }

   }


    @Override
    public void Clicked(int position) {
        idOfTodoList=todoLists.indexOf(position);

        Intent startChildActivityIntent = new Intent(this, ItemActivity.class);
        startChildActivityIntent.putExtra("position", position);
        startActivity(startChildActivityIntent);


    }

}
