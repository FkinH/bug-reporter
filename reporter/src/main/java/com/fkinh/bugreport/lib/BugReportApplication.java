package com.fkinh.bugreport.lib;

import android.app.Application;
import android.provider.Settings;

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
