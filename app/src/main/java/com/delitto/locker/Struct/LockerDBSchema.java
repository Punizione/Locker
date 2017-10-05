package com.delitto.locker.Struct;

/**
 * Created by Delitto on 2017/10/5.
 */

public class LockerDBSchema {
    public static final class RuleTable{
        public static final String NAME = "rule";

        public static final class Cols{
            public static final String PACKAGENAME = "packagename";
            public static final String LESSTIME = "lesstime";
            public static final String CYCLETIME = "cycletime";
            public static final String RULELESSTIME = "rulelesstime";
            public static final String RULECYCLETIME = "rulecycletime";
            public static final String ISWORKING = "isworking";
        }
    }
}
