package com.example.d7om7.todo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.d7om7.todo.R.id.List_number_TttextView;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ViewHolder> {
   TextView List_number_TextView;
    int count=0;
    private List<TodoList> itemDatas;
    changeActivity change ;
    public ListAdaptor(List<TodoList> itemDatas , changeActivity context) {
        this.itemDatas = itemDatas;
        change = context;
    }

    public interface changeActivity  {
        public void Clicked (int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayout);
        List_number_TextView= (TextView)itemLayout.findViewById(R.id.List_number_TttextView);
        count=getItemCount()+1;
        List_number_TextView.setText("0"+" / "+"0" );
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

     //   holder.textView.setText(itemDatas.get(position).title);
        holder.itemView.setTag(itemDatas.get(position).id);

        holder.ListTextView.setText(itemDatas.get(position).title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               change.Clicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ListTextView;

        public ViewHolder(View itemLayout) {
            super(itemLayout);
            ListTextView = (TextView) itemLayout.findViewById(R.id.list_textview);
        }
    }
}
