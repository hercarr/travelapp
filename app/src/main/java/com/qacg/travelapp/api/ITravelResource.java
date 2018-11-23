package com.qacg.travelapp.api;

import com.qacg.travelapp.models.Place;
import com.qacg.travelapp.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ITravelResource {

    @POST("user/autenticate")
    Call<User> authenticateUser(@Body Object body);

    @GET("place/all")
    Call<List<Place>> getAllPlaces();
    
}
