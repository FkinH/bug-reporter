package com.fkinh.bugreporter.lib;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Author: jinghao
 * Email: fkinh26@gmail.com
 * Date: 2016-05-23
 */
public class ErrorProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "br.provider.ERROR";

    public static final String TABLE_ERROR = "error";

    public static final Uri ERROR_URI = Uri.parse("content://" + PROVIDER_NAME + "/" + TABLE_ERROR);

    private static final int MATCHER_ERROR = 0;

    private Context mContext;

    private ErrorDatabase mErrorDatabase;

    private static String _ID = "_id";

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "error", MATCHER_ERROR);
    }

    @Override
    public boolean onCreate(){
        mErrorDatabase = new ErrorDatabase(getContext());
        mContext = getContext();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mErrorDatabase.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case MATCHER_ERROR:
                qb.setTables(TABLE_ERROR);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || "".equals(sortOrder)) {
            sortOrder = _ID;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(mContext.getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = mErrorDatabase.getWritableDatabase();
        long id;
        switch (uriMatcher.match(uri)){
            case MATCHER_ERROR:
                id = db.insert(TABLE_ERROR, null, values);
                if (id > 0) {
                    Uri _uri = ContentUris.withAppendedId(ERROR_URI, id);
                    mContext.getContentResolver().notifyChange(uri, null);
                    return _uri;
                }
                break;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mErrorDatabase.getWritableDatabase();
        int count;
        switch (uriMatcher.match(uri)) {
            case MATCHER_ERROR:
                count = db.delete(TABLE_ERROR, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        mContext.getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mErrorDatabase.getWritableDatabase();
        int count;
        switch (uriMatcher.match(uri)) {
            case MATCHER_ERROR:
                count = db.update(TABLE_ERROR, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        mContext.getContentResolver().notifyChange(uri, null);
        return count;
    }
}
