package com.delitto.locker.Tools;

import java.io.IOException;
import java.io.OutputStream;
/**
 * Created by Delitto on 2017/9/13.
 */

public class KillerUtil {
    private static Process process;

    public static void kill(String packageName){
        initProcess();
        killProcess(packageName);
        close();
    }

    private static void initProcess(){
        if(process == null){
            try{
                process = Runtime.getRuntime().exec("su");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void killProcess(String packageName){
        OutputStream output = process.getOutputStream();
        String cmd = "am force-stop "+packageName+" \n";
        try{
            output.write(cmd.getBytes());
            output.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private static void close(){
        if(process != null){
            try{
                process.getOutputStream().close();
                process = null;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
