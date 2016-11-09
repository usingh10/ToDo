package com.example.utkarshsingh.todo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Utkarsh Singh on 09-11-2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

    private static ArrayList<Row> list = new ArrayList<Row>();
    public Adapter(ArrayList<Row> list)
    {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acticity_row, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Row row = list.get(position);
        holder.title.setText(row.getTitle());
    }

    public void remove(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        public Holder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Intent i = new Intent(view.getContext(),ViewPagerActivity.class);
                    i.putExtra("pos", getAdapterPosition());
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}
