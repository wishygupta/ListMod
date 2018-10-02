package com.example.wishyp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wishyp.myapplication.dag.network.model.Delivery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityDeliveryDetails extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @BindView(R.id.deliveryLoc)
    TextView deliveryLoc;
    @BindView(R.id.deliveryDesc)
    TextView deliveryDesc;
    @BindView(R.id.deliveryImage)
    ImageView deliveryImage;
    Delivery deliveryDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            deliveryDetails = (Delivery) (getIntent().getSerializableExtra("delivery_details"));
            deliveryDesc.setText(deliveryDetails.getId() + ". " + deliveryDetails.getDescription());
            deliveryLoc.setText(deliveryDetails.getLocation().getAddress());
            Glide.with(this).load(deliveryDetails.getImageUrl()).into(deliveryImage);
            onMapReady(mMap);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (deliveryDetails != null && mMap != null) {
            LatLng deliverylocation = new LatLng(deliveryDetails.getLocation().getLatitude(), deliveryDetails.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(deliverylocation).title(deliveryDetails.getLocation().getAddress()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(deliverylocation));
        }
    }
}
