package com.example.wishyp.myapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wishyp.myapplication.dag.network.model.Delivery;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wishy.gupta on 24-02-2018.
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    List<Delivery> deliveries;
    AppPresenter appPresenter;

    public DeliveryAdapter(List<Delivery> deliveries, AppPresenter appPresenter) {
        if (deliveries == null)
            deliveries = new ArrayList<>();
        this.deliveries = deliveries;
        this.appPresenter = appPresenter;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.deliveryDesc.setText(deliveries.get(position).getId()+". "+deliveries.get(position).getDescription());
        holder.deliveryLoc.setText(deliveries.get(position).getLocation().getAddress());
        Glide.with(holder.itemView.getContext()).load(deliveries.get(position).getImageUrl()).into(holder.deliveryImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(),ActivityDeliveryDetails.class);
                intent.putExtra("delivery_details",deliveries.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.deliveryLoc)
        TextView deliveryLoc;
        @BindView(R.id.deliveryDesc)
        TextView deliveryDesc;
        @BindView(R.id.deliveryImage)
        ImageView deliveryImage;

        ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }


}
