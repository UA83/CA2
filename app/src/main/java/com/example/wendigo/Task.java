package com.example.wendigo;

public class Task implements java.io.Serializable{

    private String mName;
    private String mStatus = "0";

    public Task(String mName, String mStatus) {
        this.mName = mName;
        this.mStatus = mStatus;
    }

    public Task(String mName) {
        this.mName = mName;
    }


    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}