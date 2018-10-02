package com.example.wishyp.myapplication.dag;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.wishyp.myapplication.AppPresenter;
import com.example.wishyp.myapplication.dag.network.NetworkCall;
import com.example.wishyp.myapplication.dag.network.NetworkConstant;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




/**
 * Created by wishy.gupta on 02-03-2018.
 */
@Module
public class AppClient {

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(NetworkConstant.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    NetworkCall provideApiCall(Retrofit retrofit) {
        return retrofit.create(NetworkCall.class);
    }





    @Provides
    @Singleton
    AppPresenter provideAppPresenter(
                                     NetworkCall apiCall) {
        return new AppPresenter(apiCall);
    }

}
