package com.delitto.locker.Struct;

/**
 * Created by Delitto on 2017/9/14.
 */


public class TimeCycle {
    private int numerator;
    private TimeUnit numeratorUnit;

    private int denominator;
    private TimeUnit denominatorUnit;

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

    public TimeCycle(){}

    public TimeCycle(int numerator,TimeUnit numeratorUnit, int denominator,TimeUnit denominatorUnit){
        setNumerator(numerator);
        setNumeratorUnit(numeratorUnit);
        setDenominator(denominator);
        setDenominatorUnit(denominatorUnit);
    }
    public long timeFormat(){
        long remainingTime = 0L;
        remainingTime = numerator*TimeUnit.format(numeratorUnit);
        return remainingTime;
    }

    public long timeFormatWithCycle(){
        long cycleTime = 0L;
        cycleTime = denominator*TimeUnit.format(denominatorUnit);
        return cycleTime;
    }

}
