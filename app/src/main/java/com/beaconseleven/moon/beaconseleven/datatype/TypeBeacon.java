package com.beaconseleven.moon.beaconseleven.datatype;

/**
 * Created by Moon on 2015-11-13.
 */
public class TypeBeacon {

    private String Mac_addr;
    private String x_Pos;
    private String y_Pos;
    private String z_Pos;
    private String alertFlag;

    public TypeBeacon() {
    }

    public TypeBeacon(String alertFlag, String mac_addr, String x_Pos, String y_Pos, String z_Pos) {
        this.alertFlag = alertFlag;
        Mac_addr = mac_addr;
        this.x_Pos = x_Pos;
        this.y_Pos = y_Pos;
        this.z_Pos = z_Pos;
    }

    public String getAlertFlag() {
        return alertFlag;
    }

    public void setAlertFlag(String alertFlag) {
        this.alertFlag = alertFlag;
    }

    public String getMac_addr() {
        return Mac_addr;
    }

    public void setMac_addr(String mac_addr) {
        Mac_addr = mac_addr;
    }

    public String getX_Pos() {
        return x_Pos;
    }

    public void setX_Pos(String x_Pos) {
        this.x_Pos = x_Pos;
    }

    public String getY_Pos() {
        return y_Pos;
    }

    public void setY_Pos(String y_Pos) {
        this.y_Pos = y_Pos;
    }

    public String getZ_Pos() {
        return z_Pos;
    }

    public void setZ_Pos(String z_Pos) {
        this.z_Pos = z_Pos;
    }

}
