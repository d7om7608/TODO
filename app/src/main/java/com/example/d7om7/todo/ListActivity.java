package com.example.d7om7.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ListActivity extends AppCompatActivity {
    Module module=new Module();
    EditText AddListEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       AddListEditText=(EditText)findViewById(R.id.AddListEditText);
        module.initilaiz(this);
 }

    public  void AddListButton (View view){

     module.addListTODO(AddListEditText,this);
        setContentView(R.layout.activity_item);
   }

  }
