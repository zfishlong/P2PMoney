package com.ilmare.p2pmoney.Common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;
import java.sql.SQLOutput;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/9/2016 10:06 AM
 * 版本号： 1.0
 * 版权所有(C) 6/9/2016
 * 描述：全局异常捕捉  单例的
 * ===============================
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler crashHandler=new CrashHandler();
    private UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private Context mContext;
    private CrashHandler(){

    }

    public static CrashHandler getInstance(){
        return crashHandler;
    }

    public void init(Context context){
        this.mContext=context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //捕捉异常
        new Thread() {
            @Override
            public void run() {
                //Android系统当中，默认情况下，线程是没有开启looper消息处理的，但是主线程除外
                Looper.prepare();
                Toast.makeText(mContext, "抱歉，系统出现未知异常，即将退出....", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //收集错误信息
        CollectAndUploadErrorMessage(thread, ex);

        //关闭程序 三种方式共同使用
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AppManager.getInstance().removeAll();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            }
        }.start();



    }

    private void CollectAndUploadErrorMessage(Thread thread, final Throwable ex) {

        String phoneInfo= Build.DEVICE+Build.HARDWARE+Build.BOARD+"";

        new Thread(){
            @Override
            public void run() {
                //TODO 上传错误信息到服务器
                System.out.println(ex.getMessage());
            }
        }.start();


    }
}
