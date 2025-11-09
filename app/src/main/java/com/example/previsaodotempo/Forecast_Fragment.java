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

import java.util.ArrayList;
import java.util.List;

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

        // 1. Encontra o RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_forecast);

        // 2. Define a lista como HORIZONTAL
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // 3. Inicializa o Adaptador (vazio)
        forecastAdapter = new ForecastAdapter();
        recyclerView.setAdapter(forecastAdapter);

        // 4. Prepara o serviço da API (Retrofit)
        apiService = RetrofitClient.getClient().create(ApiService.class);

        return view;
    }

    // 5. Método que a MainActivity chama
    public void searchCity(String cityName) {

        Toast.makeText(getContext(), "Buscando " + cityName + "...", Toast.LENGTH_SHORT).show();

        // 6. Chama a nova API "getForecast"
        Call<ForecastResponse> call = apiService.getForecast(
                cityName,
                RetrofitClient.API_KEY,
                "metric",
                "pt_br"
        );

        // 7. Processa a resposta
        call.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    // Passa a lista inteira para o Adaptador (que vai filtrar)
                    forecastAdapter.setForecastList(response.body().list);

                } else {
                    Toast.makeText(getContext(), "Cidade não encontrada.", Toast.LENGTH_LONG).show();
                    Log.e("API_ERROR", "Erro: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Falha na conexão com a API.", Toast.LENGTH_LONG).show();
                Log.e("API_FAILURE", "Erro: " + t.getMessage());
            }
        });
    }

}