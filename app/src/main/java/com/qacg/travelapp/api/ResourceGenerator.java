package com.qacg.travelapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResourceGenerator {

    /**
     * Url base de los servicios de la aplicacion.
     */
    private static final String BASE_URL = "https://travelapp-api.herokuapp.com/api/";
    
    private static ITravelResource travelResource;

    private ResourceGenerator() {}

    static {
        setupRetrofit();
    }

    private static void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        travelResource = retrofit.create(ITravelResource.class);
    }

    public static ITravelResource getTravelResource() {
        return travelResource;
    }

}
