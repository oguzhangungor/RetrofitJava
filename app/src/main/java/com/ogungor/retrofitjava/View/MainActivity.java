package com.ogungor.retrofitjava.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ogungor.retrofitjava.Adapter.RecylerViewAdapter;
import com.ogungor.retrofitjava.Model.CryptoModel;
import com.ogungor.retrofitjava.R;
import com.ogungor.retrofitjava.Service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ArrayList<CryptoModel> cryptoModelArrayList;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecylerViewAdapter recylerViewAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recylerview);
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        loadData();
    }

    private void loadData() {
        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()) {

                    List<CryptoModel> responseList = response.body();
                    cryptoModelArrayList = new ArrayList<>(responseList);

                    //recylerviet

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recylerViewAdapter = new RecylerViewAdapter(cryptoModelArrayList);
                    recyclerView.setAdapter(recylerViewAdapter);


                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {

                t.printStackTrace();

            }
        });


    }


}