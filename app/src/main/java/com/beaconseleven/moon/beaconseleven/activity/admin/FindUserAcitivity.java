package com.beaconseleven.moon.beaconseleven.activity.admin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Vibrator;
import android.util.Log;
import android.widget.ImageView;

import com.beaconseleven.moon.beaconseleven.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class FindUserAcitivity extends Activity implements BeaconConsumer {

    private BeaconManager beaconManager;
    private double minDistance = 9999;

    public ImageView mSpinner;
    public boolean roateFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user_acitivity);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);

        mSpinner = (ImageView)findViewById(R.id.spinner);


        RotateThread spin = new RotateThread(this);
        spin.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        roateFlag = false;
        beaconManager.unbind(this);
    }


    public void checkBeaconVive()
    {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(minDistance < 0.5)
        {
            Log.i("JM", "beaconFinde 0.5");
            vibe.vibrate(1000);
            return ;
        }

        if(minDistance < 1) {
            Log.i("JM", "beaconFinde 1");
            vibe.vibrate(500);
            return ;
        }

        if(minDistance < 5) {
            Log.i("JM", "beaconFinde 5");
            vibe.vibrate(50);
            return ;
        }

        if(minDistance < 10) {
            Log.i("JM", "beaconFinde 10");
            vibe.vibrate(5);
            return ;
        }

    }


    @Override
    public void onBeaconServiceConnect() {

        Log.i("JM", "onBeaconServiceConnect");

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {

                minDistance = 9999;
                for (Beacon beacon : beacons) {
                    double distance = beacon.getDistance();
                    if(minDistance > beacon.getDistance()) {
                        minDistance = distance;
                    }
                }

                Log.i("JM", "min distance : " + minDistance);
                checkBeaconVive();
            }
        });

        try {
            String uuid = "myBeacon";
            beaconManager.startRangingBeaconsInRegion(new Region(uuid, null, null, null));
        }
        catch (RemoteException e) {    }

    }
}


class RotateThread extends Thread{

    int Angle = 0;
    FindUserAcitivity mActivity;

    public RotateThread(FindUserAcitivity aActivity) {
        this.mActivity = aActivity;
    }

    public void run() {
        while (mActivity.roateFlag) {
            try {


                mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        mActivity.mSpinner.setRotation(Angle);
                    }
                });

                Angle ++;
                Thread.sleep(10);

            } catch (Exception e) {

            }
        }
    }
}