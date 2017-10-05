package com.delitto.locker.Tools;

/**
 * Created by Delitto on 2017/9/13.
 */

public class Constants {
    //Applicaton Filter
    public final static int SYSTEM_INSTALLED_APPLICATIONS = 1;
    public final static int USER_INSTALLED_APPLICATIONS = 2;
    public final static int ALL_APPLICATIONS = 3;


    public final static String NOT_PERMISSION_ENOUGH = "权限不足\n请打开手机设置，为Locker授权查看使用情况";


    public final static int GET_APPINFO_ERROR = -1;
    public final static int GET_APPINFO_SUCCESS = 0;

    public final static int UPDATE_LESSTIME = 1;
    public final static int UPDATE_RULE_LESSTIME = 2;
    public final static int UPDATE_CYCLETIME = 3;
    public final static int UPDATE_RULE_CYCLETIME = 4;
    public final static int UPDATE_RULE_ISWORKING = 5;


}
