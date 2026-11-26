package com.rayzgar.oyun;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** Rayzgâr: oyunu tam ekran bir WebView içinde, internetsiz açar. */
public class MainActivity extends Activity implements Runnable {
    private WebView web;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
        web = new WebView(this);
        web.setBackgroundColor(Color.parseColor("#151036"));
        web.setOverScrollMode(View.OVER_SCROLL_NEVER);
        web.setVerticalScrollBarEnabled(false);
        web.setHorizontalScrollBarEnabled(false);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setAllowFileAccess(true);
        s.setSupportZoom(false);
        s.setBuiltInZoomControls(false);
        s.setTextZoom(100);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.addJavascriptInterface(this, "RayzgarApp");
        setContentView(web);
        hideSystemBars();
        if (state != null) web.restoreState(state);
        else web.loadUrl("file:///android_asset/www/index.html");
    }

    private void hideSystemBars() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemBars();
    }

    @Override
    protected void onPause() { super.onPause(); web.onPause(); }

    @Override
    protected void onResume() { super.onResume(); web.onResume(); hideSystemBars(); }

    @Override
    protected void onSaveInstanceState(Bundle out) { super.onSaveInstanceState(out); web.saveState(out); }

    @Override
    protected void onDestroy() { if (web != null) web.destroy(); super.onDestroy(); }

    /** Geri tuşu: açık paneli kapatır, oyun sırasında mola verir, ana ekranda uygulamadan çıkar. */
    @Override
    public void onBackPressed() {
        String js = "(function(){var q=function(i){return document.getElementById(i)};"
            + "var p=[['marketOv','mkClose'],['dailyOv','dailyClose'],['questOv','questClose']];"
            + "for(var i=0;i<p.length;i++){var o=q(p[i][0]);if(o&&!o.hidden){q(p[i][1]).click();return 'h';}}"
            + "var st=q('stage');if(st&&!st.classList.contains('demo')&&q('pauseOv').hidden&&q('overOv').hidden){q('pauseBtn').click();return 'h';}"
            + "RayzgarApp.exitApp();return 'x';})()";
        web.evaluateJavascript(js, null);
    }

    /** Oyun sayfası, kapatılacak bir şey kalmadığında bunu çağırır. */
    @JavascriptInterface
    public void exitApp() { runOnUiThread(this); }

    @Override
    public void run() { finish(); }
}
