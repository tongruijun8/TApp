package com.trj.tlib.uils;

import android.util.Log;

/**
 * @author tong
 * @date 2018/3/16 16:11
 * 日志打印的工具类
 */

public class LogUtil {
    //设为false关闭日志
    public static boolean print = false;

    public static void t(String msg){
        if(print){
            Log.i("tong", "t: "+msg);
        }
    }

    public static void i(String tag, String msg){
        if (print){
            Log.i(tag, msg);
        }
    }
    public static void v(String tag, String msg){
        if (print){
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if (print){
            Log.d(tag, msg);
        }
    }
    public static void w(String tag, String msg){
        if (print){
            Log.w(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if (print){
            Log.e(tag, msg);
        }
    }
}
