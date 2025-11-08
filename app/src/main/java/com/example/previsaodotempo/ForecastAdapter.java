package com.example.previsaodotempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private WeatherResponse weatherResponse;

    public void setWeatherResponse(WeatherResponse response) {
        this.weatherResponse = response;
        notifyDataSetChanged(); // Avisa a lista para recarregar
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Carrega o layout do CardView que criamos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        if (weatherResponse == null) return; // Se não tem dados, não faz nada

        // Pega os dados da API e coloca nos TextViews
        Weather weather = weatherResponse.weather.get(0);
        Main main = weatherResponse.main;

        holder.tvDescription.setText("Descrição: " + weather.description);
        holder.tvTemp.setText("Temperatura: " + main.temp + " °C");
        holder.tvTempMinMax.setText("Mín: " + main.temp_min + " °C / Máx: " + main.temp_max + " °C");
    }

    @Override
    public int getItemCount() {
        // Se temos uma resposta, mostramos 1 item. Senão, 0.
        return (weatherResponse == null) ? 0 : 1;
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription, tvTemp, tvTempMinMax;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            tvTempMinMax = itemView.findViewById(R.id.tv_temp_min_max);
        }
    }
}