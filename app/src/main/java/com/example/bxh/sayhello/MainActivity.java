package com.example.bxh.sayhello;

import android.animation.FloatEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bxh.sayhello.dynamic.DynamicTest;
import com.example.bxh.sayhello.ipc.IpcTestService;
import com.example.bxh.sayhello.otheralgorithms.TestAlgorithms;
import com.example.bxh.sayhello.inject.InjectUtils;
import com.example.bxh.sayhello.inject.ViewInject;
import com.example.bxh.sayhello.sometest.ChildClass;
import com.example.bxh.sayhello.sometest.EnclosingClass;
import com.example.bxh.sayhello.sometest.OtherTest;
import com.example.bxh.sayhello.sometest.Person;
import com.example.bxh.sayhello.sometest.ThreadTest;
import com.example.bxh.sayhello.sometest.ThreadlocalTest;
import com.example.bxh.sayhello.sometest.WebViewTest;
import com.example.bxh.sayhello.tree.Tree;
import com.example.bxh.sayhello.widgets.DefineView;

import java.net.URL;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @ViewInject(value = R.id.text1, value3 = 0)
    private TextView mTextView1;
    @ViewInject(value = R.id.text2, value3 = 0)
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.autoInjectAllField(this);
        Tree.test();
    }

    private void testIpc() {
        Intent intent = new Intent(this, IpcTestService.class);
        intent.putExtra("args", 1);
        startService(intent);
    }

    private void testThreadLocal() {
        new ThreadlocalTest().test();
    }

    public void testThreadLocalStatic() {
        OtherTest.testHash();
        OtherTest[] a = new OtherTest[4];
        for (int i = 0; i < 4; i++) {
            a[i] = new OtherTest();
            Log.i(TAG, "OtherTest.nextHashCode is " + a[i].threadLocalHashCode);
        }
        Log.i(TAG, "----OtherTest.nextHashCode---");
        for (int i = 0; i < 4; i++) {
            Log.i(TAG, "OtherTest.nextHashCode is " + a[i].threadLocalHashCode);
        }
    }

    private void testFibonacci() {
        for (int i = 0; i < 10; i++) {
            System.out.println("fibonacci i=" + i + "--value=" + TestAlgorithms.fibonacci(i));
            System.out.println("fibonacci02 i=" + i + "--value=" + TestAlgorithms.fibonacci02(i));
        }

    }

    private void testClassInit() {
        System.out.println("classTest -----我是start分割线----");
        //new BaseClass();
        //System.out.println("classTest -----我是mid分割线----");
        new ChildClass();
        System.out.println("classTest -----我是end分割线----");
    }

    private void testNestedInit() {
        System.out.println("classTest -----我是start分割线----");
        new EnclosingClass();
        System.out.println("classTest -----我是分割线----");
        new EnclosingClass.NestedClass04();
        System.out.println("classTest -----我是分割线----");
        //new EnclosingClass.NestedClass03();


    }

    private void testDynamic() {
        new DynamicTest().test();
    }
    private void testAlgorithms() {
        double result = TestAlgorithms.test02(3);
        System.out.println("TestAlgorithms-----result=" + result);
    }

    private void test() {
        int[][] array = new int[30][40];
        array[0][0] = 0;
        for (int i = 0; i < array.length; i++) {
            int start = array[0][0] + i;
            for (int z = 0; z < array[0].length; z++) {
                array[i][z] = start + z;
                String out = array[i][z] < 10 ? array[i][z] + " ," : array[i][z] + ",";
                System.out.print(out);
                if (z == array[0].length - 1) {
                    System.out.println();
                }
            }
        }
        boolean x0 = TestAlgorithms.isInside01(37, array);
        boolean x1 = TestAlgorithms.isInside02(37, array);
    }

    private void testClone() {
        Person.testClone();
    }

    private void testHtmlTextView1() {
        final Html.ImageGetter imageGetter = new Html.ImageGetter() {

            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                URL url;
                try {
                    url = new URL(source);
                    drawable = Drawable.createFromStream(url.openStream(), "");
                    System.out.println("imageGetter thread=" + Thread.currentThread().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        final String sText = "测试图片信息：<br><img src=\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1487817104&di=33cd1b4a0e562a879160df72fcb58b84&src=http://img4q.duitang.com/uploads/item/201407/05/20140705144822_akWZc.thumb.700_0.jpeg\" /><img src=\"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1487817104&di=33cd1b4a0e562a879160df72fcb58b84&src=http://img4q.duitang.com/uploads/item/201407/05/20140705144822_akWZc.thumb.700_0.jpeg\" />";
        mTextView1.setText(Html.fromHtml(sText, imageGetter, null));
    }

    private void testHtmlTextView2() {
        final String sText = "测试图片信息：<img src=\"" + R.drawable.wanjian_girl + "\" />";
        final Html.ImageGetter imageGetter = new Html.ImageGetter() {

            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                int rId = Integer.parseInt(source);
                drawable = getResources().getDrawable(rId);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        mTextView2.setText(Html.fromHtml(sText, imageGetter, null));
    }

    private void testThread() {
        new ThreadTest().test();
    }

    void testWebView() {
        WebView webView = (WebView) findViewById(R.id.webview);
        new WebViewTest(webView).testWebView();
        LinearLayout container = (LinearLayout) findViewById(R.id.activity_main);
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = container.getChildAt(i);
            if (view instanceof WebView) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    void testMap() {
        new TestAlgorithms.TestMap().test();
    }

}
