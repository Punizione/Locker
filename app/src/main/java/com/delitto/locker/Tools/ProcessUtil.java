package com.delitto.locker.Tools;


import android.annotation.TargetApi;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Delitto on 2017/9/22.
 */



public class ProcessUtil {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean isCurrentTop(Context context, String packageName){
        class RecentUseComparator implements Comparator<UsageStats> {
            @Override
            public int compare(UsageStats lhs, UsageStats rhs){
                return (lhs.getLastTimeUsed() > rhs.getLastTimeUsed()) ?  -1 : (lhs.getLastTimeUsed() == rhs.getLastTimeUsed()) ? 0 : 1 ;
            }
        }

        RecentUseComparator mRecentComparator = new RecentUseComparator();
        long timeStamp = System.currentTimeMillis();
        UsageStatsManager mUasgeStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> usageStats = mUasgeStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, timeStamp-1000*10, timeStamp);
        if(usageStats == null || usageStats.size() == 0){
            if(havePermission(context) == false){
                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(context, Constants.NOT_PERMISSION_ENOUGH, Toast.LENGTH_SHORT);
            }
            return false;
        }
        Collections.sort(usageStats, mRecentComparator);
        String currentTopPackage = usageStats.get(0).getPackageName();
        if(currentTopPackage.equals(packageName)){
            return true;
        }else{
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static boolean havePermission(Context context){
        try{
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName);
            return (mode==AppOpsManager.MODE_ALLOWED);
        }catch (PackageManager.NameNotFoundException e){
            return true;
        }
    }


}
