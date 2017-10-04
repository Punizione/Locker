package com.delitto.locker.Struct;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.delitto.locker.Tools.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Delitto on 2017/10/4.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DataBaseHelper";
    private static final String DatabaseName = "locker.db";
    private static final int version = 1;

    private SQLiteDatabase db = null;
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

    public void insert(String packageName, long lestTime, long cycleTime, long ruleLessTime, long ruleCycleTime){
        if(db==null){
            db = this.getWritableDatabase();
        }
        String sql = "insert into rule(packagename, lesstime, cycletime, rulelesstime, rulecycletime) values(?,?,?,?,?)";
        try{
            db.execSQL(sql, new Object[]{packageName, lestTime, cycleTime, ruleLessTime, ruleCycleTime});
        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG, "insert error");
        }finally {
            db.close();
        }
        db = null;
    }

    public void delete(String packageName){
        if(db==null){
            db = this.getWritableDatabase();
        }
        String sql = "delete from rule where packagename = '"+packageName+"'";
        try{
            db.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG, "delete error");
        }finally {
            db.close();
        }
        db = null;
    }

    public void update(int filter, String packageName, long data)throws Exception{
        if(db == null){
            db = this.getWritableDatabase();
        }
        String dataFrom;
        switch (filter){
            case Constants.UPDATE_LESSTIME:
                dataFrom = "lesstime";
                break;
            case Constants.UPDATE_CYCLETIME:
                dataFrom = "cycletime";
                break;
            case Constants.UPDATE_RULE_LESSTIME:
                dataFrom = "rulelesstime";
                break;
            case Constants.UPDATE_RULE_CYCLETIME:
                dataFrom = "rulecycletime";
                break;
            default:
                throw new Exception("Illegal Parameter");
        }
        // update rule set dateFrom = 'data' where packagename = 'packagename'
        String sql = "update rule set "+dataFrom+" = '"+data+"' where packagename = '"+packageName+"'";
        try{
            db.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG, "update error");
        }finally {
            db.close();
        }
        db = null;
    }

    public Cursor select(){
        if(db==null){
            db = this.getReadableDatabase();
        }
        Cursor c = null;
        try{
            c = db.query("rule",null,null,null,null,null,null,null);
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "select error");
        }finally {
            db.close();
        }
        db = null;
        return c;
    }
    public List<Rule> getAllRules(){
        Cursor c = select();
        List<Rule> list = new ArrayList<>();
        if(c.moveToFirst()){
            for(int i=0;i<c.getCount();i++){
                //TODO
            }
        }
        return list;
    }
}
