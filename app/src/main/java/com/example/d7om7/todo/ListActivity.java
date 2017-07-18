package com.example.d7om7.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        imageButton = (ImageButton) findViewById(R.id.imageButton3);
        AddListEditText=(EditText)findViewById(R.id.AddListEditText);




        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ListAdaptor(itemData,this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

 }

    public  void AddListButton (View view){

        if (!AddListEditText.getText().toString().equals("")) {
            itemData.add(new ItemData(AddListEditText.getText().toString()));
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
