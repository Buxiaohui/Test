package com.example.bxh.sayhello.ipc;

import android.content.Context;
import android.net.Uri;

/**
 * Created by buxiaohui on 5/18/17.
 */

public class IpcUtils {
    public static final String METHOD_CALL_ARGS = "method_call_args";
    public static final String METHOD_CALL_RESULT = "method_call_result";
    public static final Uri CALL_URI = Uri.parse("content://com.example.bxh.sayhello.ipc/ipc");

    public static CallArgs callRemoteMethod(Context hostContext, String method, Object... args) {
        CallArgs CallArgs = new CallArgs();
        CallArgs.method = method;
        CallArgs.methodArgs = args;
        android.os.Bundle bundleArgs = new android.os.Bundle();
        bundleArgs.putParcelable(METHOD_CALL_ARGS, CallArgs);
        android.os.Bundle bundleResult = hostContext.getContentResolver().call(
                CALL_URI, // 一个Uri,和使用ContentProvider的query方法一样的
                method, "client_args", bundleArgs);

        if (bundleResult == null) {
            return null;
        }
        bundleResult.setClassLoader(CallArgs.class.getClassLoader());
        CallArgs result = bundleResult.getParcelable(METHOD_CALL_RESULT);
        return result;
    }
}
