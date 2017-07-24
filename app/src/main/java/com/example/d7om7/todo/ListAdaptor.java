package com.example.d7om7.todo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.media.CamcorderProfile.get;
import static com.example.d7om7.todo.R.id.List_number_TttextView;

public class ListAdaptor extends RecyclerView.Adapter<ListAdaptor.ViewHolder> {
   TextView List_number_TextView;
    private changeActivity mCategoryHandler;
    private List<TodoList> itemDatas;
    private Context change ;
    public ListAdaptor(Context context, List<TodoList> list, changeActivity handler) {
        this.itemDatas = list;
        change = context;
        mCategoryHandler =handler;
    }

    public interface changeActivity  {
        public void Clicked (int position,int id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayout);
        List_number_TextView= (TextView)itemLayout.findViewById(R.id.List_number_TttextView);
        List_number_TextView.setText("0"+" / "+"0" );
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.itemView.setTag(itemDatas.get(position).id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCategoryHandler.Clicked(position, id);
            }
        });

        holder.ListTextView.setText(itemDatas.get(position).title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryHandler.Clicked(position,id);
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
