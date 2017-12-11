package com.artf.bb84;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.MyViewHolder> {

    private static final String DOT = "\u2022";

    private List<String> data;
    private int id;


    public StringAdapter(List<String> myDataset) {
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

    public void setMovies(List<String> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.description) TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            String string = (String) getDataAtPosition(position);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("00", "\u2191");
            hashMap.put("01", "\u2192");
            hashMap.put("10", "\u2197");
            hashMap.put("11", "\u2198");
            description.setText(hashMap.get(string));
        }

    }
}
