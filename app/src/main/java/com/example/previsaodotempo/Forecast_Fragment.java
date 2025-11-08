package com.example.previsaodotempo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forecast_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ForecastAdapter forecastAdapter;
    private ApiService apiService;

    public Forecast_Fragment() {
        // Construtor vazio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_forecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        forecastAdapter = new ForecastAdapter();
        recyclerView.setAdapter(forecastAdapter);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        return view;
    }

    public void searchCity(String cityName) {

        Toast.makeText(getContext(), "Buscando " + cityName + "...", Toast.LENGTH_SHORT).show();

        Call<WeatherResponse> call = apiService.getWeather(
                cityName,                           // "q" (cidade)
                RetrofitClient.API_KEY,             // "appid" (sua chave)
                "metric",                           // "units" (celsius)
                "pt_br"                             // "lang" (português)
        );

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    forecastAdapter.setWeatherResponse(response.body());
                } else {
                    Toast.makeText(getContext(), "Cidade não encontrada.", Toast.LENGTH_LONG).show();
                    Log.e("API_ERROR", "Erro: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Falha na conexão com a API.", Toast.LENGTH_LONG).show();
                Log.e("API_FAILURE", "Erro: " + t.getMessage());
            }
        });
    }
}