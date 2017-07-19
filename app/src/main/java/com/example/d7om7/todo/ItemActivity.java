package com.example.d7om7.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static com.example.d7om7.todo.R.id.AddListEditText;

/**
 * Created by Azura on 7/18/2017.
 */

public class ItemActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    EditText AddItemEditText ;
    List<ItemData> itemData=new ArrayList<>();
    ItemAdaptor myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        AddItemEditText = (EditText) findViewById(R.id.AddItemEditText);


        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new ItemAdaptor(itemData);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public  void AddItemButton (View view){

        if (!AddItemEditText.getText().toString().equals("")) {
            itemData.add(new ItemData(AddItemEditText.getText().toString(),null));
            myAdapter.notifyDataSetChanged();
            AddItemEditText.setText("");
        }
    }

    @Override
    public void Clicked(int position) {

    }
}
