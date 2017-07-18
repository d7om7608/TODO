package com.example.d7om7.todo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Module {
    List<ItemData> itemData=new ArrayList<>();
    ListAdaptor myAdapter;
    Context context ;


    public void addListTODO(TextView textView,Activity activity){



        if (!textView.getText().toString().equals("")) {
            itemData.add(new ItemData(textView.getText().toString()));
            myAdapter.notifyDataSetChanged();
            textView.setText("");
        }
}

    public void initilaiz (Activity activity,ListAdaptor.changeActivity lestiner){

        RecyclerView recyclerView=(RecyclerView)activity.findViewById(R.id.rv_numbers);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myAdapter=new ListAdaptor(itemData,lestiner);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



}
