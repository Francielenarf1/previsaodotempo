package com.example.previsaodotempo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class LogoActivity extends AppCompatActivity {

    private static final int TEMPO_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        // Inicia o Handler para transição de tela
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Cria uma Intent para iniciar a MainActivity
                Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, TEMPO_SPLASH);
    }
}
