package com.delitto.locker.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delitto.locker.R;
import com.delitto.locker.Struct.Rule;

import java.util.List;

/**
 * Created by Delitto on 2017/9/16.
 */

public class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.ViewHolder>{
    private List<Rule> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mAppName;
        public TextView mLessTime;
        public ImageView mAppIcon;
        public ViewHolder(View v){
            super(v);
            mAppName = (TextView)v.findViewById(R.id.appName);
            mLessTime = (TextView)v.findViewById(R.id.lessTime);
            mAppIcon = (ImageView)v.findViewById(R.id.appIcon);

        }
    }

    public RuleAdapter(List<Rule> mDataSet){
        this.mDataSet = mDataSet;
    }
    @Override
    public RuleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.rule_item_card_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        Rule rule = mDataSet.get(i);
        viewHolder.mAppName.setText(rule.getTargetApp().getAppName());
        viewHolder.mLessTime.setText(rule.getRemainingTime()+"");
        viewHolder.mAppIcon.setImageDrawable(rule.getTargetApp().getAppIcon());
    }

    @Override
    public int getItemCount(){
        return mDataSet == null?0:mDataSet.size();
    }
}
