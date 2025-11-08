package com.example.previsaodotempo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String[] titles = {"Previsão", "Mapa"};


    public Forecast_Fragment forecastFragment;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                // Posição 0 (Aba 1)
                forecastFragment = new Forecast_Fragment();
                return forecastFragment;
            case 1:
                // Posição 1 (Aba 2)
                return new MapFragment();
            default:
                forecastFragment = new Forecast_Fragment();
                return forecastFragment;
        }
    }

    @Override
    public int getItemCount() {

        return titles.length;
    }

    public String getPageTitle(int position) {
        return titles[position];
    }
}