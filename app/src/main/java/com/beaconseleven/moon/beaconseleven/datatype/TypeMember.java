package com.beaconseleven.moon.beaconseleven.datatype;

/**
 * Created by Moon on 2015-11-13.
 */
public class TypeMember {

    private String mb_no;
    private String mb_name;
    private String mb_level;
    private String mb_id;
    private String mb_mac;
    private String mb_now_task;
    private String mb_now_pos_x;
    private String mb_now_pos_y;



    private boolean isLogin = false;

    public TypeMember(){

    }

    public TypeMember(boolean isLogin, String mb_no, String mb_name, String mb_level, String mb_id, String mb_mac, String mb_now_task) {
        this.isLogin = isLogin;
        this.mb_no = mb_no;
        this.mb_name = mb_name;
        this.mb_level = mb_level;
        this.mb_id = mb_id;
        this.mb_mac = mb_mac;
        this.mb_now_task = mb_now_task;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getMb_mac() {
        return mb_mac;
    }

    public void setMb_mac(String mb_mac) {
        this.mb_mac = mb_mac;
    }

    public String getMb_no() {
        return mb_no;
    }

    public void setMb_no(String mb_no) {
        this.mb_no = mb_no;
    }

    public String getMb_name() {
        return mb_name;
    }

    public void setMb_name(String mb_name) {
        this.mb_name = mb_name;
    }

    public String getMb_level() {
        return mb_level;
    }

    public void setMb_level(String mb_level) {
        this.mb_level = mb_level;
    }

    public String getMb_id() {
        return mb_id;
    }

    public void setMb_id(String mb_id) {
        this.mb_id = mb_id;
    }

    public String getMb_now_task() {
        return mb_now_task;
    }

    public void setMb_now_task(String mb_now_task) {
        this.mb_now_task = mb_now_task;
    }

    public String getMb_now_pos_y() {
        return mb_now_pos_y;
    }

    public void setMb_now_pos_y(String mb_now_pos_y) {
        this.mb_now_pos_y = mb_now_pos_y;
    }

    public String getMb_now_pos_x() {
        return mb_now_pos_x;
    }

    public void setMb_now_pos_x(String mb_now_pos_x) {
        this.mb_now_pos_x = mb_now_pos_x;
    }

}
