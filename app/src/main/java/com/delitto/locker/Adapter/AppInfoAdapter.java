package com.delitto.locker.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delitto.locker.R;
import com.delitto.locker.Struct.AppInfo;

import java.util.List;

/**
 * Created by Delitto on 2017/10/3.
 */

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder>{
    private List<AppInfo> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mAppInfoName;
        public TextView mAppInfoPackageName;
        public ImageView mAppInfoIcon;

        public ViewHolder(View v){
            super(v);
            mAppInfoName = (TextView)v.findViewById(R.id.appinfoName);
            mAppInfoPackageName = (TextView)v.findViewById(R.id.appinfoPackageName);
            mAppInfoIcon = (ImageView)v.findViewById(R.id.appinfoIcon);
        }
    }

    public AppInfoAdapter(List<AppInfo> mDataSet){
        this.mDataSet = mDataSet;
    }

    @Override
    public AppInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.appinfo_item_card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        AppInfo appInfo = mDataSet.get(i);
        viewHolder.mAppInfoName.setText(appInfo.getAppName());
        viewHolder.mAppInfoPackageName.setText(appInfo.getPackageName());
        viewHolder.mAppInfoIcon.setImageDrawable(appInfo.getAppIcon());
    }

    @Override
    public int getItemCount(){
        return mDataSet == null?0:mDataSet.size();
    }


}
