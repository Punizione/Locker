package com.delitto.locker.Struct;

import com.delitto.locker.Tools.InfoUtil;

/**
 * Created by Delitto on 2017/9/14.
 */

public class Rule {
    private AppInfo targetApp;
    private boolean isWorking;
    private TimeCycle cycle;

    private TimeCycle less;
    private long remainingTime;
    private long cycleTime;
    public Rule(){ }

    public Rule(AppInfo targetApp, TimeCycle cycle){
        this.targetApp = targetApp;
        this.isWorking = true;
        this.cycle = cycle;

        this.remainingTime = cycle.timeFormat();
        this.cycleTime = cycle.timeFormatWithCycle();
    }

    public Rule(String packageName, long lessTime, long RuleLessTime, long cycleTime, long RuleCycleTime,boolean isWorking){
        this.targetApp = InfoUtil.getAppInfoByPakcageName(packageName);
        this.isWorking = isWorking;

        this.cycle = new TimeCycle(RuleLessTime, RuleCycleTime);
        this.remainingTime = lessTime;
        this.cycleTime = cycleTime;
    }

    public AppInfo getTargetApp(){
        return targetApp;
    }

    public void setTargetApp(AppInfo targetApp){
        this.targetApp = targetApp;
    }

    public boolean getIsWorking(){
        return isWorking;
    }

    public void changeWorking(){
        isWorking = !isWorking;
    }

    public TimeCycle getCycle(){
        return this.cycle;
    }

    public void setCycle(TimeCycle timeCycle){
        this.cycle = timeCycle;
    }

    public long getRemainingTime(){
        return this.remainingTime;
    }


    public void refreshCycle(){
        this.remainingTime = cycle.timeFormat();
    }

    @Override
    public boolean equals(Object that){
        return (that instanceof Rule) && this.targetApp.equals(((Rule) that).getTargetApp());
    }



}
