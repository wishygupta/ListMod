package com.example.wishyp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.wishyp.myapplication.dag.MyApplication;
import com.example.wishyp.myapplication.dag.network.NetworkCall;
import com.example.wishyp.myapplication.dag.network.model.Delivery;
import com.example.wishyp.myapplication.dag.network.model.DeliveryResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDeliveryList extends AppCompatActivity {
    @BindView(R.id.deliveire)
    RecyclerView deliveryView;
    @Inject
    AppPresenter presenter;
    @Inject
    NetworkCall apiCall;
    DeliveryAdapter deliveryAdapter;
    List<Delivery> deliveries=new ArrayList<>();
    int offsetRec = 0;
    int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_list);
        ButterKnife.bind(this);
        ((MyApplication) this.getApplication()).getAppComponent().inject(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        deliveryView.setLayoutManager(layoutManager);
        presenter.createLoadingDialog(this);
        deliveryAdapter = new DeliveryAdapter(null, presenter);
        deliveryView.setAdapter(deliveryAdapter);
        if (!presenter.isNetworkAvailable(this))
            return;
        getDeliviers(offsetRec, limit);
        deliveryView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    getDeliviers(offsetRec, limit);

                }

            }
        });
    }

    void getDeliviers(final int offset, int limit) {
        presenter.showLoading();
        apiCall.getDeliveries(offset, limit).enqueue(new Callback<List<Delivery>>() {
            @Override
            public void onResponse(Call<List<Delivery>> call, Response<List<Delivery>> response) {
                List<Delivery> deliveriesResponse = response.body();
                if (deliveriesResponse != null) {
                    offsetRec = offset + deliveriesResponse.size();
                    deliveries.addAll(deliveriesResponse);
                    deliveryAdapter.setDeliveries(deliveries);
                    deliveryAdapter.notifyDataSetChanged();
                }
                presenter.stopLoading();
            }

            @Override
            public void onFailure(Call<List<Delivery>> call, Throwable t) {
                presenter.stopLoading();
                Toast.makeText(ActivityDeliveryList.this,"Uh-oh, Something went wrong try again",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
