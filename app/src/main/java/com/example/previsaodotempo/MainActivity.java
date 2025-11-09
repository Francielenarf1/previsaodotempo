package com.example.previsaodotempo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    // O novo EditText
    private EditText etSearchCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Encontra os componentes do novo layout
        toolbar = findViewById(R.id.toolbar);
        etSearchCity = findViewById(R.id.et_search_city);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // 2. Define a Toolbar (agora ela tem o título)
        setSupportActionBar(toolbar);

        // 3. Configura as Abas (igual a antes)
        setupTabs();

        // 4. Configura o "Ouvinte" do EditText
        etSearchCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Verifica se o usuário apertou "Enter" ou "Buscar"
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    // Pega o texto
                    String cidade = etSearchCity.getText().toString();

                    // Chama o método de busca (que está no Fragment)
                    searchCity(cidade);

                    return true;
                }
                return false;
            }
        });
    }

    // Método para configurar as Abas
    private void setupTabs() {
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setText(viewPagerAdapter.getPageTitle(position));
                }
        ).attach();
    }

    // Método que envia a cidade para o Fragmento
    private void searchCity(String cityName) {
        if (cityName.isEmpty()) {
            Toast.makeText(this, "Por favor, digite uma cidade", Toast.LENGTH_SHORT).show();
            return;
        }

        // Pega a referência do fragmento que guardamos no adaptador
        Forecast_Fragment fragment = viewPagerAdapter.forecastFragment;

        if (fragment != null) {
            // Envia o texto da pesquisa
            fragment.searchCity(cityName);

            // Muda para a aba de previsão
            viewPager.setCurrentItem(0);
        }
    }


    // --- Métodos do Menu (SÓ TEM O "SOBRE" AGORA) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_sobre) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}