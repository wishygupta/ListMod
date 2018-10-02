package com.example.wishyp.myapplication.dag.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryLocation implements Serializable{

    @Expose
    @SerializedName("lat")
    double latitude;
    @Expose
    @SerializedName("lng")
    double longitude;
    @Expose
    @SerializedName("address")
    String address;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }
}
