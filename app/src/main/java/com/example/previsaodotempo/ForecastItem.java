package com.example.previsaodotempo;

import java.util.List;

public class ForecastItem {

    // A data e hora (ex: "2025-11-09 12:00:00")
    public String dt_txt;

    // O objeto "Main" com as temperaturas (o mesmo que já tínhamos)
    public Main main;

    // A lista "Weather" com a descrição (a mesma que já tínhamos)
    public List<Weather> weather;
}