package com.example.bxh.sayhello;

import android.animation.FloatEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bxh.sayhello.abstractfactory.AbstractFactoryTestClient;
import com.example.bxh.sayhello.color.ColorActivity;
import com.example.bxh.sayhello.command.CommandActivity;
import com.example.bxh.sayhello.factory.FactoryClient;
import com.example.bxh.sayhello.genericity.GenericityTestClient;
import com.example.bxh.sayhello.ipc.IpcTestService;
import com.example.bxh.sayhello.otheralgorithms.TestAlgorithms;
import com.example.bxh.sayhello.inject.InjectUtils;
import com.example.bxh.sayhello.inject.ViewInject;
import com.example.bxh.sayhello.otheralgorithms.TestAlgorithms;
import com.example.bxh.sayhello.sometest.ChildClass;
import com.example.bxh.sayhello.sometest.IntegerTest;
import com.example.bxh.sayhello.sometest.OtherTest;
import com.example.bxh.sayhello.sometest.ThreadTest;
import com.example.bxh.sayhello.sometest.ThreadlocalTest;
import com.example.bxh.sayhello.sort.Sort;
import com.example.bxh.sayhello.widgets.DefineView;

import java.net.URL;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    float maxVal = 100.0f;
    float defineViewVal;
    DefineView defineView;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG, "msg callback");
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //floatEvaluator.evaluate(0.01f,0.0f,1.0f);
            defineViewVal++;
            if (defineViewVal > maxVal) {
                defineViewVal = 0;
            }
            sendEmptyMessageDelayed(0, 10);
            defineView.setVal(defineViewVal / maxVal);
            Log.i(TAG, "handleMessage");
        }
    };
    FloatEvaluator floatEvaluator;
    @ViewInject(value = R.id.text1, value3 = 0)
    private TextView mTextView1;
    @ViewInject(value = R.id.text2, value3 = 0)
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.autoInjectAllField(this);
//        mTextView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ColorActivity.class));
//            }
//        });
//        IntegerTest.testVal();
//        defineView = (DefineView) findViewById(R.id.defineView);
//        handler.sendEmptyMessage(0);
//        floatEvaluator = new FloatEvaluator() {
//            @Override
//            public Float evaluate(float fraction, Number startValue, Number endValue) {
//
//                float val = super.evaluate(fraction, startValue, endValue);
//                Log.i(TAG, "BBBBB evaluate val=" + val);
//                return val;
//            }
//        };


        //testThread();
        //testHtmlTextView1();
        //testHtmlTextView2();
        //testClone();
        //handler.sendEmptyMessageDelayed(1, 2000);
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG,"run in post");
//            }
//        });
        //testClassInit();
        //testNestedInit();
        //testAlgorithms();
        //test();
        //TestAlgorithms.testPrintNodeListFromTail2Head();
        //Tree.test();
        //StackToQueue.test();
        //TestAlgorithms.string2String();
        //testFibonacci();
        //PlaneLinePointsNum.testPoints();
        //TestAlgorithms.getNumOfOneInBinery();
        //TestAlgorithms.findSpecialNum();
        //TestAlgorithms.testGetExponentOfNum01();
        //TestAlgorithms.testGetAdd();
        //TestAlgorithms.printMatrix();
        //MergeNodelist.testMergeList();
        //StringTest.get01();
        //new B();
        //OtherTest.test();

        //TestAlgorithms.testFindValueInMatrix();

        //startActivity(new Intent(this,TestActivityPrefrence.class));
        //Sort.test();
        //HeapSort.sortTest();
        //new StringTest().test();
        //MergeNodelist.testMergeList();
        //OtherTest.testInteger();
        //testWebView();
        //System.out.println("classTest----" + ChildClass.staticField);
        //TestAlgorithms.testMergeSequentialArray();
        //defineView = (DefineView) findViewById(R.id.defineView);
        //testThreadLocalStatic();
        //testMap();
        //FactoryClient.test();
        //GenericityTestClient.getGenericityTest();
        //AbstractFactoryTestClient.test();
        CommandActivity.toCommandActivity(this);
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

        /**
         * 对象的克隆---对象的复制，完整的一个复制对象
         */
        Person p1 = new Person("张三");
        Person p2 = null;
        try {
            p2 = (Person) p1.clone();//向下转型----P2没有被实例化
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p2.setName("李四");
        System.out.println("testClone 原始对象：" + p1);
        System.out.println("testClone 克隆之后的对象：" + p2);

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

    class A {
        private int i = 1;

        public A() {
            print();
        }

        public void print() {
            System.out.println("base i=" + i);
        }
    }

    class B extends A {
        private int i = 1;

        public B() {
            i = 2;
        }

        public void print() {
            System.out.println("child i=" + i);
        }

    }

}
