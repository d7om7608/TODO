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

import static android.R.id.list;
import static com.example.d7om7.todo.R.id.Back_Ground_Color_Spinner;
import static com.example.d7om7.todo.R.id.Font_Spinner;


public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    LinearLayout Settingsbackground;
    Spinner BackGroundColorSpinner ;
    Spinner FontSpinner;
    ChangeColor changeColor = new ChangeColor() ;
     int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Settingsbackground = (LinearLayout) findViewById(R.id.background);


        color = getResources().getColor(R.color.White);
        BackGroundColorSpinner = (Spinner) findViewById(Back_Ground_Color_Spinner);
        ArrayAdapter<String> ColorAdapter;
        List<String> ColorList;
        ColorList = new ArrayList<String>();
        ColorList.add("White");
        ColorList.add("Blue");
        ColorList.add("Gray");
        ColorList.add("Yellow");
        ColorList.add("Red");
        ColorList.add("Green");
        ColorAdapter = new ArrayAdapter<String>(getApplicationContext(),
        android.R.layout.simple_spinner_item, list);
        ColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BackGroundColorSpinner.setAdapter(ColorAdapter);

        FontSpinner = (Spinner) findViewById(Font_Spinner);
        ArrayAdapter<String> FontAdapter;
        List<String> FontList;
        FontList = new ArrayList<String>();
        FontList.add("Normal");//none
        FontList.add("serif");
        FontList.add("casual");
        FontAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        FontAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BackGroundColorSpinner.setAdapter(FontAdapter);

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
        changeColor.colorSpinner(Settingsbackground);

        Toast.makeText(getApplicationContext(), "Settings Saved", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
