package com.example.d7om7.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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


import com.example.d7om7.todo.Data.ItemHandler;
import com.example.d7om7.todo.Data.TodoCantract;
import com.example.d7om7.todo.Data.TodoDBHelper;
import com.example.d7om7.todo.Data.TodoHandler;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.d7om7.todo.TodoManager.todoLists;

public class ListActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    TextView List_number_TextView;
    TextView ListNumbersTextView;
    ItemActivity itemActivity = new ItemActivity();
    ListAdaptor myAdapter;
    EditText AddListEditText;
    LinearLayout Listbackground;
    SQLiteDatabase mDb;
    TodoDBHelper helper;
    static int idOfTodoList;
    public static final int RC_SIGN_IN = 1;

    //fire base
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mFirebaseAuth = FirebaseAuth.getInstance();


        ListNumbersTextView = (TextView) findViewById(R.id.List_number_TttextView);
        AddListEditText = (EditText) findViewById(R.id.AddListEditText);
        Listbackground = (LinearLayout) findViewById(R.id.background);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new TodoDBHelper(this);
        getAllTODO();
        myAdapter = new ListAdaptor(this, todoLists, this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                myAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                todoLists.remove(viewHolder.getLayoutPosition());
                mDb = helper.getWritableDatabase();
                TodoHandler.removeTODO(mDb, (Integer) viewHolder.itemView.getTag());

                myAdapter.notifyDataSetChanged();

            }

        }).attachToRecyclerView(recyclerView);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(ListActivity.this, "Signed in!", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);

    }

    private List<TodoList> getAllTODO() {
        List<TodoList> TODOList = new ArrayList<>();


        mDb = helper.getReadableDatabase();
        Cursor cursor = TodoHandler.cursor(mDb);


        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            List<TodoItem> ItemsList = new ArrayList<>();
            String name = cursor.getString(cursor.getColumnIndex(TodoCantract.TodoEntry.TODO_NAME));
            int id = cursor.getInt(cursor.getColumnIndex(TodoCantract.TodoEntry.TODO_ID));
            Cursor ItemsCursor = ItemHandler.cursorItem(mDb, id);

            for (int i2 = 0; i2 < ItemsCursor.getCount(); i2++) {
                ItemsCursor.moveToPosition(i2);
                int ItemsId = ItemsCursor.getInt(ItemsCursor.getColumnIndex(TodoCantract.ItemEntry.ITEM_ID));
                String ItemsTitle = ItemsCursor.getString(ItemsCursor.getColumnIndex(TodoCantract.ItemEntry.ITEM_NAME));


                ItemsList.add(new TodoItem(ItemsTitle, true));
            }

            TODOList.add(new TodoList(name, ItemsList, id));
        }
        return TODOList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_avtivity_mnue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
//            startActivity(startSettingsActivity);
//            return true;
//
//        }
//        return super.onOptionsItemSelected(item);

//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.log_out:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void AddListButton(View view) {

        if (!AddListEditText.getText().toString().equals("")) {
            String name = AddListEditText.getText().toString();
            mDb = helper.getWritableDatabase();
            int id = TodoHandler.addNewTodo(mDb, name);
            todoLists.add(new TodoList(name, new ArrayList<TodoItem>(), id));
            myAdapter.notifyDataSetChanged();

            int i = TodoHandler.addNewTodo(mDb, AddListEditText.getText().toString());
            Log.d("hello", i + "");


            String mItemNumbers = "45";
            AddListEditText.setText("");
        }

    }


    @Override
    public void Clicked(int position, int id) {
        idOfTodoList = todoLists.indexOf(position);
        Intent startChildActivityIntent = new Intent(this, ItemActivity.class);
        startChildActivityIntent.putExtra("position", position);
        startActivity(startChildActivityIntent);


    }

}
