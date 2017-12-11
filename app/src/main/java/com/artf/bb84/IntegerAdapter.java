package com.artf.bb84;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegerAdapter extends RecyclerView.Adapter<IntegerAdapter.MyViewHolder> {

    private static final String DOT = "\u2022";

    private List<Integer> data;
    private int id;

    public IntegerAdapter(List<Integer> myDataset, int id) {
        data = myDataset;
        this.id = id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);


    }

    public Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clearMovies() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public void setMovies(List<Integer> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<Integer> getData() {
        return data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.description) TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
                Integer integer = (Integer) getDataAtPosition(position);
                if(integer != null) {
                    if(id == 0) {
                        String string = String.valueOf(integer);
                        description.setText(string);
                    }else if(id == 1){
                        String string = integer == 0 ? "-" : "+";
                        description.setText(string);
                    }
                }
        }

    }
}
