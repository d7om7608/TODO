package com.example.d7om7.todo;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d7om7 on 7/18/2017.
 */

public class Module {
    List<ItemData> itemData=new ArrayList<>();
    ListAdaptor myAdapter;


    public void addListTODO(TextView textView,Activity activity){



        if (!textView.getText().toString().equals("")) {
            itemData.add(new ItemData(textView.getText().toString()));
            myAdapter.notifyDataSetChanged();
            textView.setText("");
        }
}

    public void initilaiz (Activity activity){

        RecyclerView recyclerView=(RecyclerView)activity.findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myAdapter=new ListAdaptor(itemData);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



}
