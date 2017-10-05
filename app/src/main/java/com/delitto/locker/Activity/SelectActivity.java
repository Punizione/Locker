package com.delitto.locker.Activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.delitto.locker.Adapter.AppInfoAdapter;
import com.delitto.locker.R;
import com.delitto.locker.Struct.AppInfo;

import java.util.ArrayList;
import java.util.List;

import com.delitto.locker.Tools.Constants;
import com.delitto.locker.Tools.InfoUtil;
import com.delitto.locker.Tools.TestUtil;

/**
 * Created by Delitto on 2017/10/3.
 */

public class SelectActivity extends AppCompatActivity {
    final private String tag = "SelectActivity";
    private SwipeRefreshLayout mRefreshLayout;
    private int filterForRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<AppInfo> list;



    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.content_select);
        filterForRefreshLayout = Constants.ALL_APPLICATIONS;

        Toolbar toolbar = (Toolbar) findViewById(R.id.appinfoToolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.select_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        list = new ArrayList<>();
        initRefresh();
        refreshAppInfo();



        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            //当上拉刷新时
            @Override
            public void onRefresh() {
                refreshAppInfo();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.showAll:
                filterForRefreshLayout = Constants.ALL_APPLICATIONS;
                refreshAppInfo();

                if(!item.isChecked()){
                    item.setChecked(true);
                }
                return true;
            case R.id.showSystem:
                filterForRefreshLayout = Constants.SYSTEM_INSTALLED_APPLICATIONS;
                refreshAppInfo();
                if(!item.isChecked()){
                    item.setChecked(true);
                }
                return true;
            case R.id.showUser:
                filterForRefreshLayout = Constants.USER_INSTALLED_APPLICATIONS;
                refreshAppInfo();
                if(!item.isChecked()){
                    item.setChecked(true);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void refreshAppInfo(){
     //   TestUtil.makeTest(this);
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected void onPreExecute(){
                mRefreshLayout.setRefreshing(true);
                list.clear();
            }

            @Override
            protected Integer doInBackground(Void ... params){

                try{
                    list.addAll(InfoUtil.getAllAppInfo( filterForRefreshLayout));
                    System.out.println(list.size());
                }catch (Exception e){
                    e.printStackTrace();
                    return Constants.GET_APPINFO_ERROR;
                }
                return Constants.GET_APPINFO_SUCCESS;
            }

            @Override
            protected void onProgressUpdate(Void... values){
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Integer code){
//                TestUtil.makeTest(SelectActivity.this, "Execute");
                if(code == Constants.GET_APPINFO_ERROR){
                    Toast.makeText(getBaseContext(),"刷新失败",Toast.LENGTH_SHORT).show();
                }else if(code == Constants.GET_APPINFO_SUCCESS){
                    //真让人摸不着头脑.jpg
                    if(mAdapter == null){
                        mAdapter =  new AppInfoAdapter(list);
                        mRecyclerView.setAdapter(mAdapter);
                    }else{
                        mAdapter.notifyDataSetChanged();
                    }


                }
                mRefreshLayout.setRefreshing(false);
            }

        }.execute();
    }

    public void initRefresh(){
        mRefreshLayout.setColorSchemeResources(R.color.primary_dark, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置手指在屏幕上下拉多少距离开始刷新
        mRefreshLayout.setDistanceToTriggerSync(300);
        //设置下拉刷新按钮的背景颜色
        mRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        //设置下拉刷新按钮的大小
        mRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
    }



}
