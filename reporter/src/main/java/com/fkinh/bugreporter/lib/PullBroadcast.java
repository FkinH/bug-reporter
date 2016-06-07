package com.fkinh.bugreporter.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;

/**
 * Author: jinghao
 * Email: jinghao@meizu.com
 * Date: 2016-06-07
 */
public class PullBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("bug-report", "pulling db");
        File result = new File(Constants.LOCAL_PATH);
        if (!result.exists()) {
            result.mkdir();
        }
        FileUtil.copyFileByStream(context.getDatabasePath("error.db").getAbsoluteFile(), new File(Constants.LOCAL_PATH + "error.db"));
    }

}
