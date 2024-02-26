package com.example.sweetlife.Pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sweetlife.R;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageButton buttonActividad = (ImageButton) findViewById(R.id.Principal_btn_Actividad);
        ImageButton buttonNutricion = (ImageButton) findViewById(R.id.Principal_btn_Nutricion);
        ImageView buttonEstado = (ImageView) findViewById(R.id.Principal_imgvw_Flecha1);
        ImageView buttonMedidas = (ImageView) findViewById(R.id.Principal_imgvw_Flecha2);
        ImageView buttonInformacionPersonal = (ImageView) findViewById(R.id.Principal_imgvw_Flecha3);



        buttonActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Actividad.class);
                startActivity(cambio);

            }
        });

        buttonNutricion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Nutricion.class);
                startActivity(cambio);
            }
        });

        buttonEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Estado.class);
                startActivity(cambio);
            }
        });

        buttonMedidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Medidas.class);
                startActivity(cambio);
            }
        });

        buttonInformacionPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, InformacionPersonal.class);
                startActivity(cambio);
            }
        });

    }
}