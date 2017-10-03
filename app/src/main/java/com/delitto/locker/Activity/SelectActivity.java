package com.delitto.locker.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.delitto.locker.Adapter.AppInfoAdapter;
import com.delitto.locker.R;
import com.delitto.locker.Struct.AppInfo;

import java.util.List;

import com.delitto.locker.Tools.Constants;
import com.delitto.locker.Tools.InfoUtil;
/**
 * Created by Delitto on 2017/10/3.
 */

public class SelectActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mRecyclerView = (RecyclerView)findViewById(R.id.select_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<AppInfo> list = InfoUtil.getAllAppInfo(this, Constants.ALL_APPLICATIONS);
        mAdapter = new AppInfoAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }



}
