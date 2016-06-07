package com.fkinh.bugreporter.lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Author: jinghao
 * Email: fkinh26@gmail.com
 * Date: 2016-05-23
 */
public class ErrorDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "error";

    public static final int DB_VERSION = 1;

    public ErrorDatabase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public ErrorDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(Constants.TAG, "create error db.");

        String createError = "CREATE TABLE \"main\".\"error\" (\n" +
                "\"_id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\"package\"  TEXT NOT NULL,\n" +
                "\"message\"  TEXT,\n" +
                "\"localized_message\"  TEXT,\n" +
                "\"cause\"  TEXT,\n" +
                "\"stack_trace\"  TEXT,\n" +
                "\"date\"  TEXT NOT NULL\n" +
                ")\n" +
                ";\n";

        db.execSQL(createError);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
