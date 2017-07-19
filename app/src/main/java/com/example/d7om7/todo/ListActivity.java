package com.example.d7om7.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ListActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    List<ItemData> itemData=new ArrayList<>();
    ListAdaptor myAdapter;
    EditText AddListEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        AddListEditText=(EditText)findViewById(R.id.AddListEditText);

        Cursor cursor ;


        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ListAdaptor(itemData,this);
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
                 itemData.remove(viewHolder.getLayoutPosition());
            }

        }).attachToRecyclerView(recyclerView);
 }



    public  void AddListButton (View view){

        if (!AddListEditText.getText().toString().equals("")) {
            itemData.add(new ItemData(AddListEditText.getText().toString(),null));
            myAdapter.notifyDataSetChanged();
            AddListEditText.setText("");
        }

   }


    @Override
    public void Clicked(int position) {


        Intent startChildActivityIntent = new Intent(this, ItemActivity.class);
        startChildActivityIntent.putExtra("ghj", "who");
        startActivity(startChildActivityIntent);

    }

}
