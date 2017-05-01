package com.example.bxh.sayhello;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.bxh.sayhello.inject.InjectUtils;
import com.example.bxh.sayhello.inject.ViewInject;
import com.example.bxh.sayhello.sometest.ExceptionTest;
import com.example.bxh.sayhello.widgets.Line;
import com.example.bxh.sayhello.widgets.LineBuilder;
import com.example.bxh.sayhello.widgets.TableView;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

public class TableActivity extends Activity {
    private static final String TAG = "TableActivity";
    @ViewInject(value = R.id.table, value3 = 0)
    private TableView mTableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        InjectUtils.autoInjectAllField(this);
        float[] vals = {1f, 1.5f, 3f, 3.5f, 2f, 2.5f,3.0f,4.5f,3.5f,4f,5f,6f};
        float[] vals1 = {5f, 6.5f, 8f, 11.5f, 7f, 7.5f,8.0f,9.5f,8.5f,9f,10f,11f};
        Line line = new LineBuilder().setValues(vals).build();
        LineBuilder builder1 = new LineBuilder();
        builder1.getDefaultConnectionPaint().setColor(Color.RED);
        builder1.getDefaultNodePaint().setColor(Color.RED);
        builder1.getDefaultNodeTextPaint().setColor(Color.RED);
        Line line1 = builder1.setValues(vals1).build();
        mTableView.setmGuardLineVal(1.5f);
        mTableView.addLine(line);
        mTableView.addLine(line1);


        //Tree.test();
        //test();
        //test1();
        test3();
    }

    public static void test1() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println("------test1-------");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }
    public static void test() {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println("-------test------");
        //常量池
        //f1、f2、f3、f4四个变量都是Integer对象引用，==运算比较的不是值而是引用
        System.out.println(f1 == f2);//true
        System.out.println(f3 == f4);//false
        f1 = 20;
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1 == f2);
    }

    public  void test4(){
        HttpManager l = new HttpManager() {
            @Override
            public <T> Callback.Cancelable get(RequestParams entity, Callback.CommonCallback<T> callback) {
                return null;
            }

            @Override
            public <T> Callback.Cancelable post(RequestParams entity, Callback.CommonCallback<T> callback) {
                return null;
            }

            @Override
            public <T> Callback.Cancelable request(HttpMethod method, RequestParams entity, Callback.CommonCallback<T> callback) {
                return null;
            }

            @Override
            public <T> T getSync(RequestParams entity, Class<T> resultType) throws Throwable {
                return null;
            }

            @Override
            public <T> T postSync(RequestParams entity, Class<T> resultType) throws Throwable {
                return null;
            }

            @Override
            public <T> T requestSync(HttpMethod method, RequestParams entity, Class<T> resultType) throws Throwable {
                return null;
            }

            @Override
            public <T> T requestSync(HttpMethod method, RequestParams entity, Callback.TypedCallback<T> callback) throws Throwable {
                return null;
            }
        };
        l.get(new RequestParams(), new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public  void test3(){
        try {
            new ExceptionTest().test4();
        }catch (Exception e){
            System.out.println("BBBBBBB--"+e.toString());
        }

    }

}
