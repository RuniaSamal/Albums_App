package com.example.albums_app;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {
    @GET("albums")
    Call<ArrayList<Pozo>>getAlbums();


}
