package com.example.nayan_makasare.myimagedemo.Sever;

import com.example.nayan_makasare.myimagedemo.Model.Example;
import com.example.nayan_makasare.myimagedemo.Model.Example2;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nayan_makasare on 26/3/18.
 */

public interface ApiInterface
{
    @GET("hello.json")
    Call<Example> getExample();


    @GET("Logo.json")
    Call<Example2> getExample2();

}
