package com.delitto.locker.Struct;

/**
 * Created by Delitto on 2017/9/14.
 */


public class TimeCycle {
    private int numerator;
    private TimeUnit numeratorUnit;

    private int denominator;
    private TimeUnit denominatorUnit;

    public TimeCycle(){}

    public TimeCycle(int numerator,TimeUnit numeratorUnit, int denominator,TimeUnit denominatorUnit){
        setNumerator(numerator);
        setNumeratorUnit(numeratorUnit);
        setDenominator(denominator);
        setDenominatorUnit(denominatorUnit);
    }

    public TimeCycle(long ruleLessTime, long ruleCycleTime){
        setNumeratorUnit(TimeUnit.fromLongToTimeUnit(ruleLessTime));
        setNumerator((int)(ruleLessTime/TimeUnit.fromTimeUnitToLong(this.getNumeratorUnit())));
        setDenominatorUnit(TimeUnit.fromLongToTimeUnit(ruleCycleTime));
        setDenominator((int)(ruleCycleTime/TimeUnit.fromTimeUnitToLong(this.getDenominatorUnit())));
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setNumeratorUnit(TimeUnit numeratorUnit){
        this.numeratorUnit = numeratorUnit;
    }

    public void setDenominator(int denominator){
        this.denominator = denominator;
    }

    public void setDenominatorUnit(TimeUnit denominatorUnit){
        this.denominatorUnit = denominatorUnit;
    }

    public int getNumerator(){
        return this.numerator;
    }
    public TimeUnit getNumeratorUnit(){
        return this.numeratorUnit;
    }
    public int getDenominator(){
        return this.denominator;
    }
    public TimeUnit getDenominatorUnit(){
        return  this.denominatorUnit;
    }


    public long timeFormat(){
        return  numerator*TimeUnit.fromTimeUnitToLong(numeratorUnit);
    }

    public long timeFormatWithCycle(){
        return denominator*TimeUnit.fromTimeUnitToLong(denominatorUnit);
    }



}
