package com.delitto.locker.Struct;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by Delitto on 2017/9/13.
 */

public class AppInfo {
    private String appName;
    private String packageName;
    private Drawable appIcon;

    public String getAppName(){
        return appName;
    }

    public String getPackageName(){
        return packageName;
    }

    public Drawable getAppIcon(){
        return appIcon;
    }

    public void setAppNmae(String appName){
        this.appName = appName;
    }

    public  void setPackageName(String packageName){
        this.packageName = packageName;
    }

    public  void setAppIcon(Drawable appIcon){
        this.appIcon = appIcon;
    }

    public void print(){
        Log.v("app","Name:"+appName);
        Log.v("app","Package:"+packageName);
    }
}
