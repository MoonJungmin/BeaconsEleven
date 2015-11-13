package com.beaconseleven.moon.beaconseleven.global;

import android.app.Application;

import com.beaconseleven.moon.beaconseleven.datatype.TypeMember;
import com.beaconseleven.moon.beaconseleven.datatype.TypeTask;

/**
 * Created by Moon on 2015-11-13.
 */

public class mGlobal extends Application {

    public int mWidth;
    public int mHeight;

    public TypeMember mMember;
    public TypeTask mTask;



    public void initMember(){
        mMember = new TypeMember();
    }
    public void initTask(){
        mTask = new TypeTask();
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }
}
