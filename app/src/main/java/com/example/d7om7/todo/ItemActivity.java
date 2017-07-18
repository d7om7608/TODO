package com.example.d7om7.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Azura on 7/18/2017.
 */

public class ItemActivity extends AppCompatActivity {
    Module module = new Module();
    EditText AddItemEditText ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        AddItemEditText = (EditText) findViewById(R.id.AddItemEditText);
        module.initilaiz(this);
    }

    public  void AddItemButton (View view){

        module.addListTODO(AddItemEditText,this);
    }
}
