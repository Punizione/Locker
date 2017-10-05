package com.delitto.locker.Struct;

import java.sql.Time;

/**
 * Created by Delitto on 2017/9/14.
 */

public enum TimeUnit {
    Minute,
    Hour,
    Day,
    Week,
    Month;

    public static long fromTimeUnitToLong(TimeUnit tu){
        long res = 0L;
        switch (tu){
            case Minute:
                res = 1L;
                break;
            case Hour:
                res = 60L;
                break;
            case Day:
                res = 60*24L;
                break;
            case Week:
                res = 60*24*7L;
                break;
            case  Month:
                res = 60*24*7*30L;
                break;
        }
        return res;
    }

    public static TimeUnit fromLongToTimeUnit(long time){
        if(time >= fromTimeUnitToLong(TimeUnit.Month)){
            return TimeUnit.Month;
        }else if(time >= fromTimeUnitToLong(TimeUnit.Week)){
            return TimeUnit.Week;
        }else if(time >= fromTimeUnitToLong(TimeUnit.Day)){
            return TimeUnit.Day;
        }else  if(time >= fromTimeUnitToLong(TimeUnit.Hour)){
            return TimeUnit.Hour;
        }else{
            return TimeUnit.Minute;
        }
    }
}
