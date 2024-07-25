package com.example.apicallapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController {

    private static Retrofit retrofitObject;
    private static APIController apiControllerObject;
    private static final String baseURL = "enter your base url";

    public APIController() {
        retrofitObject = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized APIController getInstance() {
        if (apiControllerObject == null) {
            apiControllerObject = new APIController();
        }
        return apiControllerObject;
    }

    APIInterface getPostAPI() {
        return retrofitObject.create(APIInterface.class);
    }
}
