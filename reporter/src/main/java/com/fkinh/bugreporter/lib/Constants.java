package com.fkinh.bugreporter.lib;

import android.os.Environment;

import java.io.File;

/**
 * Author: jinghao
 * Email: fkinh26@gmail.com
 * Date: 2016-05-23
 */
public class Constants {

    public static final String TAG = "error-report";

    public static final String PACKAGE_NAME = "com.fkinh.bugreporter";

    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static final String LOCAL_PATH = SD_PATH + "Android" + File.separator + "data" + File.separator + PACKAGE_NAME + File.separator;

}
