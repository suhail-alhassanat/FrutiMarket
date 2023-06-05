package com.suhail.frutimarket.apis;

import android.widget.Toast;

import com.suhail.frutimarket.models.RegisterRequest;
import com.suhail.frutimarket.models.RegisterResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    public static ApiClient getClient(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://demo-api.mr-dev.tech/api/")
                .client(client)
                .build();
        ApiClient apiClient=retrofit.create(ApiClient.class);
        return apiClient;
    }

}
