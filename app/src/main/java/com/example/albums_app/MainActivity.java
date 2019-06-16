package com.example.albums_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;

    String JSONURL ="https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);


        fetchJSON();
    }
    private void fetchJSON(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(JSONURL)

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerInterface api=retrofit.create(RecyclerInterface.class);
        Call<ArrayList<Pozo>> call=api.getAlbums();
        call.enqueue(new Callback<ArrayList<Pozo>>() {
            @Override
            public void onResponse(Call<ArrayList<Pozo>> call, Response<ArrayList<Pozo>> response) {
                if (response.isSuccessful()){
                    if (response.body() !=null){
                        writeRecycler(response.body());


                    }else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();


                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pozo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }



        });
    }
    private void writeRecycler(ArrayList<Pozo> response) {

        try {
            ArrayList<ModelRecycler> model = new ArrayList<>();
            //getting the whole json object from the response
            for (int i = 0; i < response.size(); i++) {
                ModelRecycler modelRecycler = new ModelRecycler();
                modelRecycler.setId(response.get(i).getId());
                modelRecycler.setUserId(response.get(i).getUserId());
                modelRecycler.setTitle(response.get(i).getTitle());
                model.add(modelRecycler);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            retrofitAdapter = new RetrofitAdapter(getApplicationContext(), model);
            recyclerView.setAdapter(retrofitAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}


