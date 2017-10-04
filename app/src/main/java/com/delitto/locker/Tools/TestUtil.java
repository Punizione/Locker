package com.delitto.locker.Tools;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Delitto on 2017/10/4.
 */

public class TestUtil {
    public static void makeTest(Context context){
        Toast.makeText(context, "Test",Toast.LENGTH_SHORT).show();
    }

    public static void makeTest(Context context, String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
