package com.ogungor.retrofitjava.Service;

import com.ogungor.retrofitjava.Model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface CryptoAPI {


    @GET("prices?key=2f755ad27d39fc3abeac67b109afc8a2")
    Call<List<CryptoModel>> getData();
}
