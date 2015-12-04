package com.beaconseleven.moon.beaconseleven.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beaconseleven.moon.beaconseleven.R;
import com.beaconseleven.moon.beaconseleven.activity.admin.FindUserAcitivity;
import com.beaconseleven.moon.beaconseleven.activity.admin.MapViewActivity;

public class AdminActivity extends Activity {

    private Button Btn_FindUser;
    private Button Btn_MapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        Btn_FindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindUserAcitivity.class);
                startActivity(intent);
            }
        });
        Btn_MapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapViewActivity.class);
                startActivity(intent);
            }
        });

    }

    public void init(){
        Btn_FindUser = (Button)findViewById(R.id.btn_find_user);
        Btn_MapView = (Button)findViewById(R.id.btn_mapview);
    }
}

