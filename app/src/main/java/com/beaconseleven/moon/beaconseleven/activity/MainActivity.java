package com.beaconseleven.moon.beaconseleven.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beaconseleven.moon.beaconseleven.R;
import com.beaconseleven.moon.beaconseleven.global.mGlobal;
import com.beaconseleven.moon.beaconseleven.model.TCP_Socket_Model;
import com.beaconseleven.moon.beaconseleven.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {


    private LinearLayout mLoginPage;
    private TextView mTxt_LoginPageID;
    private TextView mTxt_LoginPagePW;
    private EditText mEdit_LoginPageID;
    private EditText mEdit_LoginPagePW;
    private Button mBtn_LoginPageLogin;

    private LinearLayout mMypage;
    private Button mBtn_MyPageAdmin;
    private Button mBtn_MyPageUser;

    mGlobal mGlobalVar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SplashActivity.class));
        mGlobalVar = (mGlobal)getApplicationContext();
        mGlobalVar.mHeight = Util.getScreenHeight(this);
        mGlobalVar.mWidth = Util.getScreenWidth(this);
        mGlobalVar.initMember();
        mGlobalVar.initTask();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        init();
        mBtn_LoginPageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mID = mEdit_LoginPageID.getText().toString();
                String mPW = mEdit_LoginPagePW.getText().toString();
                JSONObject jsonMember = new JSONObject();
                try {
                    jsonMember.put("FLAG", "1");
                    jsonMember.put("ID", mID);
                    jsonMember.put("PW", mPW);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TCP_Socket_Model tp = new TCP_Socket_Model(jsonMember.toString(), mGlobalVar, 1);
                tp.run();
                while (true) {
                    if (tp.endFlag == true)
                        break;
                }
                checkLogin();
                Util.hideKeyboard(getApplicationContext(), mEdit_LoginPagePW);

            }
        });

        mBtn_MyPageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        mBtn_MyPageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });

    }


    public void checkLogin(){
        if(mGlobalVar.mMember.isLogin()){
            mMypage.setVisibility(View.VISIBLE);
            mLoginPage.setVisibility(View.GONE);
        }

    }

    public void init()
    {
        mLoginPage = (LinearLayout)findViewById(R.id.loginpage);
        mTxt_LoginPageID = (TextView)findViewById(R.id.loginpage_txt_id);
        mTxt_LoginPagePW = (TextView)findViewById(R.id.loginpage_txt_pw);
        mEdit_LoginPageID = (EditText)findViewById(R.id.loginpage_edit_id);
        mEdit_LoginPagePW = (EditText)findViewById(R.id.loginpage_edit_pw);
        mBtn_LoginPageLogin = (Button)findViewById(R.id.loginpage_btn_login);

        mMypage = (LinearLayout)findViewById(R.id.mypage);
        mBtn_MyPageAdmin = (Button)findViewById(R.id.mypage_btn_admin);
        mBtn_MyPageUser = (Button)findViewById(R.id.mypage_btn_user);
    }
}
