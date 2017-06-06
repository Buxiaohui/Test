package com.example.bxh.sayhello.ipc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URISyntaxException;

/**
 * Created by buxiaohui on 5/18/17.
 */

public class DataProvider extends ContentProvider {
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    public String prepareDataForIpcTest() {
        try {
            Intent intent = Intent.getIntent(IpcUtils.CALL_URI.toString());
            if (intent != null && intent.hasExtra(IpcUtils.METHOD_CALL_ARGS)) {
                String args = intent.getStringExtra(IpcUtils.METHOD_CALL_ARGS);
                Log.d("DataProvider", "data from client is =" + args);
            } else {
                Log.d("DataProvider", "data from client is null");
            }
        } catch (URISyntaxException e) {
            Log.d("DataProvider", "data from client is URISyntaxException");
        } catch (Exception e) {
            Log.d("DataProvider", "data from client is Exception");
        }

        Log.d("DataProvider", "return data to client");
        return "hahahshdhahsdhashdhasdhah";
    }

    @Nullable
    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        Log.d("DataProvider", "method="+method);
        Log.d("DataProvider", "arg="+arg);
        return super.call(method, arg, extras);
    }
}
