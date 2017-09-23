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

    public static long format(TimeUnit tu){
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
}
