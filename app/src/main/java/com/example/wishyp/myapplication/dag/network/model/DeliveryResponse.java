package com.example.wishyp.myapplication.dag.network.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class DeliveryResponse {
    @Expose
    Delivery deliveries;

    public Delivery getDeliveries() {
        return deliveries;
    }
}
