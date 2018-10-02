package com.example.wishyp.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.wishyp.myapplication.dag.network.NetworkCall;

import javax.inject.Inject;

public class AppPresenter {
    Dialog progressDialog;
    NetworkCall apiCall;
    @Inject
    public AppPresenter(NetworkCall apiCall) {
        this.apiCall = apiCall;
    }

    public void createLoadingDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress);
        progressDialog = builder.create();
        progressDialog.setCancelable(false);
    }

    public void showLoading() {
        progressDialog.show();
    }

    public void stopLoading() {
        progressDialog.dismiss();
    }

    public boolean isNetworkAvailable(Context context) {
        boolean networkConnected = false;
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected())
            networkConnected = true;
        if (!networkConnected)
            Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        return networkConnected;
    }
}
