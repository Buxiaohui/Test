package com.example.bxh.sayhello.sometest;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.bxh.sayhello.MyApp;

/**
 * Created by bxh on 6/6/17.
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewTest {
    final static String TAG = "WebViewTest";
    WebView webView;

    public WebViewTest(WebView webview) {
        this.webView = webview;
    }

    public void testWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        // 添加js交互接口类，并起别名 injectedObject
        webView.addJavascriptInterface(new JsObject(), "injectedObject");

        //webView.loadUrl("https://www.baidu.com");
        //webView.loadUrl("http://blog.csdn.net/smile_raccoon/article/details/52786124?locationNum=5");
        webView.loadUrl("http://app.qq.com/#id=index");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                super.onConsoleMessage(message, lineNumber, sourceID);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i(TAG,"injectedObject consoleMessage="+consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                                         if (url.startsWith("http")) {
//                                             return false;
//                                         }
                                         return super.shouldOverrideUrlLoading(view, url);
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         //view.loadUrl("javascript:alert(injectedObject.toString())");
                                         addImageClickListner();
                                     }

                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                     }

                                     @Override
                                     public void onLoadResource(WebView view, String url) {
                                         super.onLoadResource(view, url);
                                     }

                                     @Override
                                     public void onPageCommitVisible(WebView view, String url) {
                                         super.onPageCommitVisible(view, url);

                                     }
                                 }


        );

    }

    /**
     * 这段js函数的功能就是，遍历所有的button，并添加onclick函数，
     * 函数的功能是在button点击的时候调用本地java接口并传递参数过去
     */
    private void addImageClickListner() {

        this.webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByClassName(\"xz\");" +
                "console.info('objs is '+objs+'--objs.length is '+objs.length);"+
                "for(var i=0;i<objs.length;i++)" +
                "{"+
                     "console.info('objs[i]'+objs[i]);"+
                     "objs[i].onclick=function()" +
                    "{" +
                        "window.injectedObject.toString();" +
                    "}" +
                "}"+
                "}())");
    }

    class JsObject {
        @JavascriptInterface
        public String toString() {
            Toast.makeText(MyApp.context, "ahhahah", Toast.LENGTH_SHORT).show();
            Log.i(TAG,"injectedObject toString()");
            return "injectedObject";
        }
    }
}
