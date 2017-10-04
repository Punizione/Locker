package com.delitto.locker.Struct;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Delitto on 2017/10/4.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DatabaseName = "locker.db";
    private static final int version = 1;
    public DataBaseHelper(Context context){
        super(context, DatabaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table rule(" +
                    "packagename varchar(100) not null, " +
                    "lesstime int not null, " +
                    "cycletime int not null, " +
                    "rulelesstime int not null, " +
                    "rulecycletime int not null" +
                "); ";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int lodVersion, int newVersion){
        //TODO
    }

}
