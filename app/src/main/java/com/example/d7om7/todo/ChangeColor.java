package com.example.d7om7.todo;

import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by Azura on 7/20/2017.
 */

public class ChangeColor {
    SettingsActivity settingsActivity = new SettingsActivity() ;
    ListActivity listActivity = new ListActivity();
    ItemActivity itemActivity = new ItemActivity();
    int color = settingsActivity.getResources().getColor(R.color.White) ;


    public void colorSpinner (LinearLayout background){

        if (settingsActivity.BackGroundColorSpinner.getSelectedItem()== "White"){
            color = settingsActivity.getResources().getColor(R.color.White);
            background.setBackgroundColor(color);

        }

        if (settingsActivity.BackGroundColorSpinner.getSelectedItem()== "Gray"){
            color = settingsActivity.getResources().getColor(R.color.Gray);
            background.setBackgroundColor(color);
        }

        if (settingsActivity.BackGroundColorSpinner.getSelectedItem()== "Yellow"){
            color = settingsActivity.getResources().getColor(R.color.Yellow);
            background.setBackgroundColor(color);
        }

        if (settingsActivity.BackGroundColorSpinner.getSelectedItem()== "Blue"){
            color = settingsActivity.getResources().getColor(R.color.Blue);
            background.setBackgroundColor(color);
        }

        if (settingsActivity.BackGroundColorSpinner.getSelectedItem()== "Green"){
            color = settingsActivity.getResources().getColor(R.color.Green);
            background.setBackgroundColor(color);
        }

        if (settingsActivity.BackGroundColorSpinner.getSelectedItem()== "Red"){
            color = settingsActivity.getResources().getColor(R.color.Red);
            background.setBackgroundColor(color);
        }




    }
}
