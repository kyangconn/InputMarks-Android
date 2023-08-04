package com.kyangconn.inputmarks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Vector;

public class ParcelableVector implements Parcelable {
    public static final Creator<ParcelableVector> CREATOR = new Creator<>() {
        @Override
        public ParcelableVector createFromParcel(Parcel in) {
            return new ParcelableVector(in);
        }

        @Override
        public ParcelableVector[] newArray(int size) {
            return new ParcelableVector[size];
        }
    };
    private Vector<Float> vector;

    public ParcelableVector(Vector<Float> vector) {
        this.vector = vector;
    }

    protected ParcelableVector(Parcel in) {
        vector = new Vector<>();
        in.readList(vector, Float.class.getClassLoader());
    }

    public Vector<Float> getVector() {
        return vector;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(vector);
    }
}