package com.fkinh.bugreporter.lib;

import android.app.Application;
import android.content.ContentValues;
import android.test.ApplicationTestCase;

import com.fkinh.bugreporter.lib.ErrorProvider;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testInsertThrowable2Db(){
        ContentValues cv = new ContentValues();
        cv.put("package", "");
        cv.put("message", "");
        cv.put("localized_message", "");
        cv.put("cause", "");
        cv.put("stack_trace", "");
        cv.put("date", System.currentTimeMillis());
        getContext().getContentResolver().insert(ErrorProvider.ERROR_URI, cv);
    }
}