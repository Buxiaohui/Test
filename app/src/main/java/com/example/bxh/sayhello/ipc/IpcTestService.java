package com.example.bxh.sayhello.ipc;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by buxiaohui on 5/18/17.
 */

public class IpcTestService extends IntentService {

    public IpcTestService() {
      super(null);
    }
    public IpcTestService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int num = intent.getIntExtra("args", -1);
        Log.d("DataProvider", "onHandleIntent num=" + num);
        if (num == 1) {
            CallArgs result = IpcUtils.callRemoteMethod(getApplicationContext(), "prepareDataForIpcTest", "from IpcTestService");
        }

    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
    }
}
