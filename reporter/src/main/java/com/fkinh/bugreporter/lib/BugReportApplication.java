package com.fkinh.bugreporter.lib;

import android.app.Application;

/**
 * Author: jinghao
 * Email: fkinh26@gmail.com
 * Date: 2016-04-11
 */
public class BugReportApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        GlobalExceptionHandler handler = GlobalExceptionHandler.getInstance(getApplicationContext());
        handler.init();
    }

}
