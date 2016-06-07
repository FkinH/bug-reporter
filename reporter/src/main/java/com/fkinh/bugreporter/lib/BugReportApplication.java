package com.fkinh.bugreporter.lib;

import android.app.Application;

/**
 * Author: jinghao
 * Email: fkinh26@gmail.com
 * Date: 2016-04-11
 */
public class BugReportApplication extends Application {

    private GlobalExceptionHandler mHandler;

    @Override
    public void onCreate(){
        super.onCreate();
        mHandler = GlobalExceptionHandler.getInstance(getApplicationContext());
        mHandler.init();
    }

    public void setOnCrashListener(OnCrashListener listener){
        mHandler.setOnCrashListener(listener);
    }

}
