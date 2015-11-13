package com.beaconseleven.moon.beaconseleven.datatype;

import java.util.ArrayList;

/**
 * Created by Moon on 2015-11-13.
 */
public class TypeTask {

    private ArrayList<TypeBeacon> beaconsList;
    private String taskName;
    private String task_no;

    public TypeTask() {

    }

    public TypeTask(ArrayList<TypeBeacon> beaconsList, String taskName, String task_no) {
        this.beaconsList = beaconsList;
        this.taskName = taskName;
        this.task_no = task_no;
    }

    public ArrayList<TypeBeacon> getBeaconsList() {
        return beaconsList;
    }

    public void setBeaconsList(ArrayList<TypeBeacon> beaconsList) {
        this.beaconsList = beaconsList;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTask_no() {
        return task_no;
    }

    public void setTask_no(String task_no) {
        this.task_no = task_no;
    }
}
