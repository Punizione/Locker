package com.delitto.locker;

import android.app.Application;

/**
 * Created by Delitto on 2017/10/5.
 */

public class AppContext extends Application {
    private static AppContext instance;
    public static AppContext getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

}
