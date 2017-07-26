package com.example.d7om7.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azura on 7/22/2017.
 */

public class ChangeColor extends AppCompatActivity{

    int color = getResources().getColor(R.color.White) ;


//    public void GlobalColor (LinearLayout layout , Spinner ColorSpinner) {
//
//        if( ColorSpinner.getSelectedItem().equals("White")){
//            color = getResources().getColor(R.color.White);
//            layout.setBackgroundColor(color);
//        }
//
//        if( ColorSpinner.getSelectedItem().equals("Red")){
//            color = getResources().getColor(R.color.Red);
//            layout.setBackgroundColor(color);
//        }
//
//        if( ColorSpinner.getSelectedItem().equals("Gray")){
//            color = getResources().getColor(R.color.Gray);
//            layout.setBackgroundColor(color);
//        }
//
//        if( ColorSpinner.getSelectedItem().equals("Green")){
//            color = getResources().getColor(R.color.Green);
//            layout.setBackgroundColor(color);
//        }
//
//        if( ColorSpinner.getSelectedItem().equals("Yellow")){
//            color = getResources().getColor(R.color.Yellow);
//            layout.setBackgroundColor(color);
//        }
//
//        if( ColorSpinner.getSelectedItem().equals("Blue")){
//            color = getResources().getColor(R.color.Blue);
//            layout.setBackgroundColor(color);
//        }
//
//    }
}
