package com.fkinh.bugreporter.lib;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.Arrays;

/**
 * Author: jinghao
 * Email: fkinh26@gmail.com
 * Date: 2016-04-25
 */
public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static GlobalExceptionHandler instance;

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private Context mContext;

    private OnCrashListener mListener;

    public static GlobalExceptionHandler getInstance(Context context) {
        if (instance == null) {
            synchronized (GlobalExceptionHandler.class) {
                if (instance == null) {
                    instance = new GlobalExceptionHandler(context);
                }
            }
        }
        return instance;
    }

    public GlobalExceptionHandler(Context context){
        mContext = context;
        mListener = new OnCrashListener() {
            @Override
            public void onCrash() {
                Log.e("bug-reporter", "default on crash");
            }
        };
    }

    public void init() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void setOnCrashListener(OnCrashListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        insertThrowable2Db(ex);
        if (mDefaultHandler != null) {
            mListener.onCrash();
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private void insertThrowable2Db(Throwable ex){
        ContentValues cv = new ContentValues();
        cv.put("package", mContext.getPackageName());
        cv.put("message", ex.getMessage());
        cv.put("localized_message", ex.getLocalizedMessage());
        cv.put("cause", ex.getCause().toString());
        cv.put("stack_trace", Arrays.toString(ex.getStackTrace()));
        cv.put("date", System.currentTimeMillis());
        mContext.getContentResolver().insert(ErrorProvider.ERROR_URI, cv);
    }
}