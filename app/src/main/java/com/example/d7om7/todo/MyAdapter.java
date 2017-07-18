package com.example.d7om7.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by d7om7 on 7/17/2017.
 */

public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ItemData> itemDatas;
    public  MyAdapter(List<ItemData>itemDatas){this.itemDatas=itemDatas;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder_view,null);
       ViewHolder viewHolder=new ViewHolder(itemLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(itemDatas.get(position).title);
    }

    @Override
    public int getItemCount() {
        return itemDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
public ViewHolder(View itemLayout){
    super(itemLayout);
    textView=(TextView)itemLayout.findViewById(R.id.textWork);

}


}}
