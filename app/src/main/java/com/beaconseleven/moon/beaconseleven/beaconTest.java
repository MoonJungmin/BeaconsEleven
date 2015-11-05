package com.beaconseleven.moon.beaconseleven;

import android.app.Activity;
import android.os.Bundle;

import org.altbeacon.beacon.BeaconConsumer;


public class beaconTest extends Activity implements BeaconConsumer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_test);
    }

    @Override
    public void onBeaconServiceConnect(){

    }
}
