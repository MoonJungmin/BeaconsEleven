package com.beaconseleven.moon.beaconseleven.activity;

import android.app.Activity;
import android.os.Bundle;

import com.beaconseleven.moon.beaconseleven.R;


public class MainActivity extends Activity {

    public int testInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testInt = 0;
    }
}
