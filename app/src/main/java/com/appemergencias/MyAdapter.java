package com.appemergencias;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public MyViewHolder(TextView tv){
            super(tv);
            mTextView = tv;
        }

    }

    public MyAdapter(String[] myDataSet){
        this.mDataSet = myDataSet;

    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_configuration, parent, false);
        //...
        MyViewHolder vh = new MyViewHolder(tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.mTextView.setText(mDataSet[position]);
    }

    @Override
    public int getItemCount(){
        return mDataSet.length;
    }

}
