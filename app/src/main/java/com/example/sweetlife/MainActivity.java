package com.example.sweetlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.sweetlife.Pantallas.Login;

public class MainActivity extends AppCompatActivity {
    ImageView gift;
    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ImageView donde se mostrará el GIF
        gift = findViewById(R.id.hasieraLogoApp);

        // Iniciar la animación del GIF
        Glide.with(getBaseContext()).load(R.drawable.imagen_main).into(gift);

        //progresbar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // Iniciar la ProgressBar con una animación de 10 segundos
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1; // Aumenta el progreso más rápido para que se complete en 5 segundos
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(50); // Ajusta este valor para cambiar la velocidad de la animación
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Cuando se completa la animación de la ProgressBar, muestra la imagen y oculta la ProgressBar
                handler.post(new Runnable() {
                    public void run() {
                        gift.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();


        // Manejador para iniciar la actividad Login después de un retraso
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crear un intent para iniciar la actividad Login
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish(); // Finalizar la actividad MainActivity
            }
        }, 6000); // Retraso de 10 segundos (10000 milisegundos)
    }
}
