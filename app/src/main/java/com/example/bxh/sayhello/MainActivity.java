package com.example.bxh.sayhello;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bxh.sayhello.Optimization.ClipRectActivity;
import com.example.bxh.sayhello.data.DataTest;
import com.example.bxh.sayhello.dynamic.DynamicTest;
import com.example.bxh.sayhello.enumtest.EnumTest;
import com.example.bxh.sayhello.ipc.IpcTestService;
import com.example.bxh.sayhello.inject.InjectUtils;
import com.example.bxh.sayhello.inject.ViewInject;
import com.example.bxh.sayhello.concurrency.LockTestClient;
import com.example.bxh.sayhello.sometest.ChildClass;
import com.example.bxh.sayhello.sometest.EnclosingClass;
import com.example.bxh.sayhello.sometest.OtherTest;
import com.example.bxh.sayhello.sometest.Person;
import com.example.bxh.sayhello.sometest.ThreadTest;
import com.example.bxh.sayhello.sometest.ThreadlocalTest;
import com.example.bxh.sayhello.sometest.WebViewTest;

import java.io.File;
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
        //ObjectCloneTestClient.test();
        //StringTest.testString();
        //testCapture();
        //testRxJava();
//        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
//            @Override
//            public void doFrame(long frameTimeNanos) {
//                Log.i(TAG,"frameTimeNanos="+frameTimeNanos);
//            }
//        });

        //LockTestClient.test();
        //this.startActivity(new Intent(this,ClipRectActivity.class));
        //EnumTest.test();
        DataTest.test();
    }
    private void testRxJava(){
        RxJavaTest.test();
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
        //mTextView2.setSpannableFactory();
        //mTextView2.setText(Html.fromHtml(sText, imageGetter, null));

    }
    private void testCapture(){
        mTextView2.setText("testCapture");
        mTextView2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String path = Utils.getDefaultCachePath(v.getContext());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path, "haha")));
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("AAAAA","requestCode="+requestCode);
        Log.i("AAAAA","resultCode="+resultCode);
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

}
