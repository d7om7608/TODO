package com.example.d7om7.todo;

import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.settingsActivity;
import static android.R.id.list;
import static com.example.d7om7.todo.R.id.Back_Ground_Color_Spinner;
import static com.example.d7om7.todo.R.id.Font_Spinner;


public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    LinearLayout Settingsbackground;
    Spinner BackGroundColorSpinner ;
    Spinner FontSpinner;
     int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Settingsbackground = (LinearLayout) findViewById(R.id.background);


        BackGroundColorSpinner = (Spinner) findViewById(Back_Ground_Color_Spinner);
        
        ArrayAdapter<String> adapter;
        List<String> list;
        list = new ArrayList<String>();
        list.add("White");
        list.add("Blue");
        list.add("Gray");
        list.add("Yellow");
        list.add("Red");
        list.add("Green");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BackGroundColorSpinner.setAdapter(adapter);


        ActionBar actionBar=this.getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void save_settings (View view){
        colorSpinner(Settingsbackground);

        Toast.makeText(getApplicationContext(), "Settings Saved", Toast.LENGTH_LONG).show();
    }




    public void colorSpinner (LinearLayout background){

        if (BackGroundColorSpinner.getSelectedItem()== "White"){
            color = getResources().getColor(R.color.White);
            background.setBackgroundColor(color);

        }

        if (BackGroundColorSpinner.getSelectedItem()== "Gray"){
            color = getResources().getColor(R.color.Gray);
            background.setBackgroundColor(color);
        }

        if (BackGroundColorSpinner.getSelectedItem()== "Yellow"){
            color = getResources().getColor(R.color.Yellow);
            background.setBackgroundColor(color);
        }

        if (BackGroundColorSpinner.getSelectedItem()== "Blue"){
            color = getResources().getColor(R.color.Blue);
            background.setBackgroundColor(color);
        }

        if (BackGroundColorSpinner.getSelectedItem()== "Green"){
            color = getResources().getColor(R.color.Green);
            background.setBackgroundColor(color);
        }

        if (BackGroundColorSpinner.getSelectedItem()== "Red"){
            color = getResources().getColor(R.color.Red);
            background.setBackgroundColor(color);
        }




    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
