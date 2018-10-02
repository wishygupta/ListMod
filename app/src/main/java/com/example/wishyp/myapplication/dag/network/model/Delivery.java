package com.example.wishyp.myapplication.dag.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Delivery implements Serializable{

    @Expose
    @SerializedName("id")
    int id;
    @Expose
    @SerializedName("description")
    String description;
    @Expose
    @SerializedName("imageUrl")
    String imageUrl;
    @Expose
    @SerializedName("location")
    DeliveryLocation location;


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DeliveryLocation getLocation() {
        return location;
    }
}
