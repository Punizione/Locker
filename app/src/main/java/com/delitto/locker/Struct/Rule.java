package com.delitto.locker.Struct;

/**
 * Created by Delitto on 2017/9/14.
 */

public class Rule {
    private AppInfo targetApp;
    private boolean isWorking;
    private TimeCycle cycle;

    private long remainingTime;

    public Rule(){ }

    public Rule(AppInfo targetApp, TimeCycle cycle){
        this.targetApp = targetApp;
        this.cycle = cycle;
        this.isWorking = true;
        this.remainingTime = cycle.timeFormat();
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
