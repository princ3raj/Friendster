package com.example.friendster.rest;

import android.app.Activity;

import com.google.gson.Gson;

import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient extends Activity {

    private static final String BASE_URL="http://192.168.64.2/friendster/public/app/";
    private static Retrofit sRetrofit=null;

    public static Retrofit getApiClient()
    {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        if(sRetrofit==null)
        {
            sRetrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                      .client(httpClient)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();

        }
        return  sRetrofit;


    }

}
