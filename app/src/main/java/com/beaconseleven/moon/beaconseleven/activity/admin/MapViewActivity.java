package com.beaconseleven.moon.beaconseleven.activity.admin;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.beaconseleven.moon.beaconseleven.R;


public class MapViewActivity extends Activity {

    public WebView mWebView;
    public boolean reloadFlag = true;
    private reloadThread reload;
    private RelativeLayout mLayout;
    private Switch mSwitch;
    public MapViewActivity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        mActivity = this;

        mWebView = (WebView)findViewById(R.id.webView);
        mLayout = (RelativeLayout)findViewById(R.id.mlayoutwebview);
        mSwitch = (Switch)findViewById(R.id.switch1);

        mWebView.loadUrl("http://155.230.160.58/main/admin/2");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);



        mSwitch.setChecked(true);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reloadFlag = true;
                    reload = new reloadThread(mActivity);
                    reload.start();
                } else {
                    reloadFlag = false;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reloadFlag = false;
    }
}

class reloadThread extends Thread{

    int Angle = 0;
    MapViewActivity mActivity;

    public reloadThread(MapViewActivity aActivity) {
        this.mActivity = aActivity;
    }

    public void run() {
        while (mActivity.reloadFlag) {
            try {
                mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        mActivity.mWebView.reload();
                    }
                });
                Thread.sleep(1000);

            } catch (Exception e) {

            }
        }
    }
}