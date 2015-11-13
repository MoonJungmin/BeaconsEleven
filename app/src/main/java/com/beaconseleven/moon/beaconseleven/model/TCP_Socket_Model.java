package com.beaconseleven.moon.beaconseleven.model;

import android.util.Log;

import com.beaconseleven.moon.beaconseleven.global.mGlobal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static com.beaconseleven.moon.beaconseleven.utils.JSONParser.getTypeMember;
import static com.beaconseleven.moon.beaconseleven.utils.JSONParser.getTypeTask;


/**
 * Created by Moon on 2015-11-13.
 */
public class TCP_Socket_Model implements Runnable{
    private static final String serverIP = "155.230.160.58";  // ex: 192.168.0.100
    private static final int serverPort = 10010; // ex: 5555
    private String msg;
    private String return_msg = null;
    mGlobal mGlobalVar;
    private int mProtocol;

    public boolean endFlag = false;

    public TCP_Socket_Model(String _msg, mGlobal mGlobalVar, int aProtocol) {
        this.msg = _msg;
        this.mGlobalVar = mGlobalVar;
        this.mProtocol = aProtocol;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {

            InetAddress server_adr = InetAddress.getByName(serverIP);
            Log.d("JM", "LoginSocket: Connecting...");
            Socket socket = new Socket(server_adr, serverPort);
            //socket.setSoTimeout(3000);
            try {
                Log.d("JM", "LoginSocket: Sending: '" + msg + "'");
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                out.println(msg);
                Log.d("JM", "LoginSocket: Sent.");
                Log.d("JM", "LoginSocket: Done.");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                return_msg = in.readLine();

                Log.d("JM", "LoginSocket: Server send to me this message -->" + return_msg);
            } catch (Exception e) {
                Log.e("JM", "LoginSocket: Error1", e);
            } finally {
                socket.close();
                endFlag = true;
                switch (mProtocol)
                {
                    case 1:
                        mGlobalVar.mMember = getTypeMember(return_msg);
                        mGlobalVar.mMember.setLogin(true);
                        break;
                    case 2:
                        mGlobalVar.mTask = getTypeTask(return_msg);
                        break;
                }


            }
        } catch (Exception e) {
            Log.e("JM", "LoginSocket: Error2", e);
        }
    }
}
