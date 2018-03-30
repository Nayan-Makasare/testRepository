package com.example.nayan_makasare.myimagedemo.Sever;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nayan_makasare on 26/3/18.
 */

public class ApiClient
{
    private static final String BASE_URL = "http://192.168.1.143/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
