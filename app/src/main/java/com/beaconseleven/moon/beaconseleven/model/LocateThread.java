package com.beaconseleven.moon.beaconseleven.model;

import com.beaconseleven.moon.beaconseleven.activity.UserActivity;
import com.beaconseleven.moon.beaconseleven.global.mGlobal;

/**
 * Created by Moon on 2015-12-04.
 */
public class LocateThread extends Thread{
    mGlobal mGlobalVar;
    UserActivity mActivity;


    public LocateThread(mGlobal mGlobalVar, UserActivity aActivity) {
        this.mGlobalVar = mGlobalVar;
        this.mActivity = aActivity;
    }

    public void run() {
        while (mActivity.StartFlag) {

        }

    }
}
