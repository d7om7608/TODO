package com.example.d7om7.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.d7om7.todo.MyAdapter.*;

public class MainActivity extends AppCompatActivity {
    Module module=new Module();
    EditText texy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texy=(EditText)findViewById(R.id.Edit);
        module.initilaiz(this);
 }

    public  void onClick(View view){

     module.addListTODO(texy,this);
   }

  }
