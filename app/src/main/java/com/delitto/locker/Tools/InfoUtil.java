package com.delitto.locker.Tools;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.delitto.locker.Activity.MainActivity;
import com.delitto.locker.Activity.SelectActivity;
import com.delitto.locker.AppContext;
import com.delitto.locker.Struct.AppInfo;

import android.app.Activity;
import android.content.pm.PackageManager;

import com.delitto.locker.Tools.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Delitto on 2017/9/13.
 */

public class InfoUtil {
    public static List<AppInfo> getAllAppInfo(Context context, int systemfilter){
        List<AppInfo> list = new ArrayList<>();
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for(PackageInfo packageInfo : packages){
            AppInfo tempInfo = new AppInfo();
            tempInfo.setAppNmae(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            tempInfo.setPackageName(packageInfo.packageName);
            tempInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
            if(systemfilter == Constants.USER_INSTALLED_APPLICATIONS){
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                    list.add(tempInfo);


                }
            }else if(systemfilter == Constants.SYSTEM_INSTALLED_APPLICATIONS){
                if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0){
                    list.add(tempInfo);

                }
            }else if(systemfilter == Constants.ALL_APPLICATIONS){
                list.add(tempInfo);

            }
        }

        return list;
    }


    /**
     *  Using AppContext instead of param Context
     * @param systemfilter
     * @return
     */
    public static List<AppInfo> getAllAppInfo(int systemfilter){
        List<AppInfo> list = new ArrayList<>();
        List<PackageInfo> packages = AppContext.getInstance().getPackageManager().getInstalledPackages(0);
        for(PackageInfo packageInfo : packages){
            AppInfo tempInfo = new AppInfo();
            tempInfo.setAppNmae(packageInfo.applicationInfo.loadLabel(AppContext.getInstance().getPackageManager()).toString());
            tempInfo.setPackageName(packageInfo.packageName);
            tempInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(AppContext.getInstance().getPackageManager()));
            if(systemfilter == Constants.USER_INSTALLED_APPLICATIONS){
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                    list.add(tempInfo);


                }
            }else if(systemfilter == Constants.SYSTEM_INSTALLED_APPLICATIONS){
                if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0){
                    list.add(tempInfo);

                }
            }else if(systemfilter == Constants.ALL_APPLICATIONS){
                list.add(tempInfo);

            }
        }

        return list;
    }





    public static AppInfo getAppInfoByPackageName(String packageName){
        AppInfo retInfo = null;
        List<PackageInfo> infos = AppContext.getInstance().getPackageManager().getInstalledPackages(0);
        for(PackageInfo info:infos){
            if(info.packageName.equals(packageName)){
                retInfo = new AppInfo();
                retInfo.setAppNmae(info.applicationInfo.loadLabel(AppContext.getInstance().getPackageManager()).toString());
                retInfo.setPackageName(packageName);
                retInfo.setAppIcon(info.applicationInfo.loadIcon(AppContext.getInstance().getPackageManager()));
                break;
            }
        }
        return retInfo;
    }
}
