package com.example.d7om7.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Azura on 7/18/2017.
 */

public class ItemAdaptor  extends RecyclerView.Adapter<ItemAdaptor.ViewHolder> {
    private List<TodoItem> itemDatas;


    public ItemAdaptor(List<TodoItem> itemDatas ) {this.itemDatas = itemDatas;}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holder_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayout);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemLayout) {
            super(itemLayout);
            textView = (TextView) itemLayout.findViewById(R.id.textWork);
        }
    }
}
