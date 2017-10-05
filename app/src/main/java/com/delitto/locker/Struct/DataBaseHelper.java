package com.delitto.locker.Struct;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.delitto.locker.Tools.Constants;

import java.util.ArrayList;
import java.util.List;

import com.delitto.locker.Struct.LockerDBSchema.RuleTable;
import com.delitto.locker.Struct.LockerDBSchema.RuleTable.Cols;
/**
 * Created by Delitto on 2017/10/4.
 */


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DataBaseHelper";
    private static final String DatabaseName = "Locker.db";
    private static final int version = 1;

    private SQLiteDatabase db = null;
    public DataBaseHelper(Context context){
        super(context, DatabaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table " + RuleTable.NAME + "(" +
                    Cols.PACKAGENAME+" not null, " +
                    Cols.LESSTIME+" not null, " +
                    Cols.CYCLETIME+" not null, " +
                    Cols.RULELESSTIME+" not null, " +
                    Cols.RULECYCLETIME+" not null" +
                    Cols.ISWORKING+" not null" +
                "); ";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //TODO
    }

    public void insert(String packageName, long lestTime, long cycleTime, long ruleLessTime, long ruleCycleTime, boolean isWorking){
        if(db==null){
            db = this.getWritableDatabase();
        }
        String sql = "insert into "+RuleTable.NAME+"(" +
                Cols.PACKAGENAME + ", " +
                Cols.LESSTIME +", " +
                Cols.CYCLETIME +", " +
                Cols.RULELESSTIME +", " +
                Cols.RULECYCLETIME +", " +
                Cols.ISWORKING +") values(?,?,?,?,?,?)";
        try{
            db.execSQL(sql, new Object[]{packageName, lestTime, cycleTime, ruleLessTime, ruleCycleTime, isWorking});
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

    public void update(int filter, String packageName, Object data)throws Exception{
        if(db == null){
            db = this.getWritableDatabase();
        }
        String dataFrom;
        switch (filter){
            case Constants.UPDATE_LESSTIME:
                dataFrom = Cols.LESSTIME;
                break;
            case Constants.UPDATE_CYCLETIME:
                dataFrom = Cols.CYCLETIME;
                break;
            case Constants.UPDATE_RULE_LESSTIME:
                dataFrom = Cols.RULELESSTIME;
                break;
            case Constants.UPDATE_RULE_CYCLETIME:
                dataFrom = Cols.RULECYCLETIME;
                break;
            case Constants.UPDATE_RULE_ISWORKING:
                dataFrom = Cols.ISWORKING;
                break;
            default:
                throw new Exception("Illegal Parameter");
        }
        // update rule set dateFrom = 'data' where packagename = 'packagename'
        String sql = "update "+RuleTable.NAME+
                " set "+dataFrom+" = '"+data+
                "' where "+Cols.PACKAGENAME+" = '"+packageName+"'";
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
            c = db.query(RuleTable.NAME,null,null,null,null,null,null,null);
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
                c.move(i);
                list.add(
                        new Rule(
                            c.getString(c.getColumnIndex(Cols.PACKAGENAME)),
                            c.getLong(c.getColumnIndex(Cols.LESSTIME)),
                            c.getLong(c.getColumnIndex(Cols.CYCLETIME)),
                            c.getLong(c.getColumnIndex(Cols.RULELESSTIME)),
                            c.getLong(c.getColumnIndex(Cols.RULECYCLETIME)),
                            Boolean.valueOf(c.getString(c.getColumnIndex(Cols.ISWORKING)))
                        )
                );
            }
        }
        return list;
    }
}
