package com.fkinh.bugreporter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fkinh.bugreporter.lib.BugReportApplication;
import com.fkinh.bugreporter.lib.OnCrashListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        BugReportApplication application = (BugReportApplication) getApplication();
        application.setOnCrashListener(new OnCrashListener() {
            @Override
            public void onCrash() {
                Log.e("bug-report", "errrrrrrrrrrrrrrrrrrrrroooooooooooooorrrrrrrrrrrrrrr");
            }
        });
        Log.i("bug-report", getApplication().getClass().getName());
        generateNullPointer();
    }

    public void generateNullPointer(){
        throw new NullPointerException("hehe");
    }


}
