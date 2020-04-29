package com.example.wendigo;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

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

    protected Task(Parcel in) {
        _id = in.readString();
        mTask = in.readString();
        mStatus = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(mTask);
        dest.writeString(mStatus);
    }
}