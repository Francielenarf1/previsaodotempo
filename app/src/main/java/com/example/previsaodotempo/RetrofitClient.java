package com.example.previsaodotempo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    // 2. Sua chave da API (appid)
    public static final String API_KEY = "f478788d855e93f427e21ada03d7dd9c";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}