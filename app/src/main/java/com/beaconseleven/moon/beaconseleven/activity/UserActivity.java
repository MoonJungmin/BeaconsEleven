package com.beaconseleven.moon.beaconseleven.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.beaconseleven.moon.beaconseleven.R;
import com.beaconseleven.moon.beaconseleven.datatype.TypeBeacon;
import com.beaconseleven.moon.beaconseleven.global.mGlobal;
import com.beaconseleven.moon.beaconseleven.model.LocateThread;
import com.beaconseleven.moon.beaconseleven.model.TCP_Socket_Model;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

public class UserActivity extends Activity implements BeaconConsumer {

    mGlobal mGlobalVar = null;
    private BeaconManager beaconManager;
    public ImageView mStopImage;

    public boolean StartFlag = false;

    private LocateThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mStopImage = (ImageView)findViewById(R.id.stop);
        mGlobalVar = (mGlobal)getApplicationContext();
        getTaskInfo();
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);

        mThread = new LocateThread(mGlobalVar, this);
     //   mThread.start();


        mStopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        mStopImage.setVisibility(View.GONE);
                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.cancel();
                    }
                });
            }
        });
    }

    private void getTaskInfo()
    {
        String task_no = mGlobalVar.mMember.getMb_now_task();
        JSONObject jsonMember = new JSONObject();
        try {
            jsonMember.put("FLAG", "2");
            jsonMember.put("task_no", task_no);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TCP_Socket_Model tp = new TCP_Socket_Model(jsonMember.toString(), mGlobalVar, 2);
        tp.run();
        while (true) {
            if (tp.endFlag == true)
                break;
        }
    }

    private void checkAlertBeacon(String mac, double distance)
    {
        if(distance < 1)
        {
            for(TypeBeacon beacon : mGlobalVar.mTask.getBeaconsList()) {
                if(beacon.getMac_addr().equals(mac) && beacon.getAlertFlag().equals("1")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            mStopImage.setVisibility(View.VISIBLE);
                        }
                    });
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(1000);

                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }


    @Override
    public void onBeaconServiceConnect() {

        Log.i("JM", "onBeaconServiceConnect");

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {

                for (Beacon beacon : beacons) {
                    checkAlertBeacon(beacon.getBluetoothAddress(), beacon.getDistance());
                }
            }
        });

        try {
            String uuid = "myBeacon";
            beaconManager.startRangingBeaconsInRegion(new Region(uuid, null, null, null));
        }
        catch (RemoteException e) {    }

    }
}
