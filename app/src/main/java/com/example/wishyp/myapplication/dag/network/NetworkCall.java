package com.example.wishyp.myapplication.dag.network;

import com.example.wishyp.myapplication.dag.network.model.Delivery;
import com.example.wishyp.myapplication.dag.network.model.DeliveryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkCall {
    @GET(NetworkConstant.GET_DELIVERIES)
    Call<List<Delivery>> getDeliveries(@Query("offset") int offset,@Query("limit") int limit);

}
