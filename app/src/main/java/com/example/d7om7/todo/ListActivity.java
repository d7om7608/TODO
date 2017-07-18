package com.example.d7om7.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ListActivity extends AppCompatActivity implements ListAdaptor.changeActivity {
    Module module=new Module();
    EditText AddListEditText;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        imageButton = (ImageButton) findViewById(R.id.imageButton3);
        AddListEditText=(EditText)findViewById(R.id.AddListEditText);
        module.initilaiz(this,this);
 }

    public  void AddListButton (View view){

     module.addListTODO(AddListEditText,this);

   }


    @Override
    public void Clicked(int position) {
//    setContentView(R.layout.activity_item);
                        /* We'll first get the text entered by the user in the EditText */
//        String textEntered = mNameEntry.getText().toString();


        Intent startChildActivityIntent = new Intent(this, ItemActivity.class);
        startChildActivityIntent.putExtra("ghj", "who");
        startActivity(startChildActivityIntent);

    }
}
