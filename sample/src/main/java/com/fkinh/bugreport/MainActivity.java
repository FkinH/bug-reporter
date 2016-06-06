package com.fkinh.bugreport;

import android.content.ContentValues;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fkinh.bugreport.lib.ErrorProvider;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void insertThrowable2Db(){
        ContentValues cv = new ContentValues();
        cv.put("package", "");
        cv.put("message", "");
        cv.put("localized_message", "");
        cv.put("cause", "");
        cv.put("stack_trace", "");
        cv.put("date", System.currentTimeMillis());
        getContentResolver().insert(ErrorProvider.ERROR_URI, cv);
    }
}
