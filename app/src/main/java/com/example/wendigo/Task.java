package com.example.wendigo;

public class Task {

    private int _id;
    private String mTask;
    private String mStatus = "0";

    public Task(String mTask, String mStatus) {

        this.mTask = mTask;
        this.mStatus = mStatus;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public Task(String mName) {
        this.mTask = mName;
    }

    public String getTask() {
        return mTask;
    }

    public void setTask(String mTask) {
        this.mTask = mTask;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}