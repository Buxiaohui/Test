package com.example.bxh.sayhello;

import android.content.Context;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.bxh.sayhello", appContext.getPackageName());
    }

    UiDevice mUIDevice;
    Context mContext;

    @Before
    public void setUp() throws RemoteException {
        mContext = InstrumentationRegistry.getContext();
        mUIDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());  //获得device对象


        if (!mUIDevice.isScreenOn()) {  //唤醒屏幕
            mUIDevice.wakeUp();
        }
        mUIDevice.pressHome();  //按home键
    }

//    @Test
    public void testDemo() {
//        try {
//            mUIDevice.findObject(new UiSelector().description("图库")).click();
//          UiObject Calculator = new UiObject(new UiSelector().description("壁纸"));
//
//        Calculator.clickAndWaitForNewWindow();
//        UiObject seven = new UiObject(new UiSelector().resourceId("com.android.calculator2:id/digit7"));
//        seven.click();
//        UiObject plus = new UiObject(new UiSelector().resourceId("com.android.calculator2:id/plus"));
//        plus.click();
//        UiObject one = new UiObject(new UiSelector().resourceId("com.android.calculator2:id/digit1"));
//        one.click();
//        UiObject result = new UiObject(new UiSelector().resourceId("com.android.calculator2:id/equal"));
//        result.click();
//        getUiDevice().pressBack();
//        } catch (UiObjectNotFoundException e) {
//
//        }


    }


}
