package com.beaconseleven.moon.beaconseleven.utils;

import android.util.Log;

import com.beaconseleven.moon.beaconseleven.datatype.TypeBeacon;
import com.beaconseleven.moon.beaconseleven.datatype.TypeMember;
import com.beaconseleven.moon.beaconseleven.datatype.TypeTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Moon on 2015-08-28.
 */
public class JSONParser {

    public static TypeMember getTypeMember(String src) {
        TypeMember dst = new TypeMember();
        try {
            JSONArray jArr = new JSONArray(src);

            Log.i("JM", "json : " + jArr.getJSONObject(0).toString());
            dst.setMb_no(jArr.getJSONObject(0).getString("mb_no"));
            dst.setMb_id(jArr.getJSONObject(0).getString("mb_id"));
            dst.setMb_name(jArr.getJSONObject(0).getString("mb_name"));
            dst.setMb_level(jArr.getJSONObject(0).getString("mb_level"));
            dst.setMb_mac(jArr.getJSONObject(0).getString("mb_mac"));
            dst.setMb_now_task(jArr.getJSONObject(0).getString("mb_now_task"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dst;
    }

    public static TypeTask getTypeTask(String src){
        TypeTask dst = new TypeTask();
        try {
            JSONArray jArr = new JSONArray(src);

            Log.i("JM", "json : " + jArr.getJSONObject(0).toString());
            dst.setTask_no(jArr.getJSONObject(0).getString("tk_no"));
            dst.setTaskName(jArr.getJSONObject(0).getString("tk_name"));

            JSONArray jArr2 = new JSONArray(jArr.getJSONObject(0).getString("beacons"));
            ArrayList<TypeBeacon> beacons = new ArrayList<TypeBeacon>();

            for(int i=0;i<jArr2.length();i++)
            {
                TypeBeacon beacon = new TypeBeacon();
                beacon.setMac_addr(jArr2.getJSONObject(i).getString("bc_mac_addr"));
                beacon.setX_Pos(jArr2.getJSONObject(i).getString("bc_pos_x"));
                beacon.setY_Pos(jArr2.getJSONObject(i).getString("bc_pos_y"));
                beacon.setZ_Pos(jArr2.getJSONObject(i).getString("bc_pos_z"));
                beacon.setAlertFlag(jArr2.getJSONObject(i).getString("bc_alert"));
                beacons.add(beacon);

            }

            dst.setBeaconsList(beacons);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dst;
    }

}
