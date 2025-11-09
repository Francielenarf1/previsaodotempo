package com.example.previsaodotempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

// O Adaptador agora lida com uma LISTA de itens
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    // A lista de itens de previsão
    private List<ForecastItem> forecastList = new ArrayList<>();

    // Método para atualizar os dados da lista
    public void setForecastList(List<ForecastItem> list) {
        // Filtra a lista para pegar só 5 dias (a cada 8 posições)
        List<ForecastItem> dailyList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size() && i < 40; i += 8) {
                dailyList.add(list.get(i));
            }
        }

        this.forecastList = dailyList;
        notifyDataSetChanged(); // Avisa a lista para recarregar
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Carrega o layout do CardView horizontal que criamos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        // Pega o item da previsão para esta posição
        ForecastItem item = forecastList.get(position);

        // Pega os dados de dentro do item
        Weather weather = item.weather.get(0);
        Main main = item.main;

        // Coloca os dados nos TextViews
        // TODO: Converter data "dt_txt" para dia da semana
        holder.tvDay.setText(item.dt_txt.substring(5, 10)); // Mostra a data (ex: "11-09")
        holder.tvTemp.setText(String.format("%.0f°", main.temp)); // Arredonda a temp
        holder.tvDescription.setText(weather.description);
    }

    @Override
    public int getItemCount() {
        // Retorna o tamanho da nossa lista
        return forecastList.size();
    }

    // O "ViewHolder" encontra os TextViews dentro do CardView
    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvTemp, tvDescription;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}