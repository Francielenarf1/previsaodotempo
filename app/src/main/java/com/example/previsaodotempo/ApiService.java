package com.example.previsaodotempo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("forecast")
    Call<ForecastResponse> getForecast(

                                        @Query("q") String cityName,
                                        @Query("appid") String apiKey,
                                        @Query("units") String units,
                                        @Query("lang") String lang
    );

}