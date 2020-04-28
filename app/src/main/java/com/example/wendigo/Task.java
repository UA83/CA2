package com.example.wendigo;

public class Task {

    String _id;
    String mTask;
    String mStatus = "0";

    public Task(String mTask, String mStatus) {

        this.mTask = mTask;
        this.mStatus = mStatus;
    }

    public Task(String _id, String mTask, String mStatus) {
        this._id = _id;
        this.mTask = mTask;
        this.mStatus = mStatus;
    }

    public String getID(){
        return this._id;
    }

    public void setID(String id){
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