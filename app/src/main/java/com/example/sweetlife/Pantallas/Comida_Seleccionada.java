package com.example.sweetlife.Pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sweetlife.R;

public class Comida_Seleccionada extends AppCompatActivity {


    TextView comida_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_seleccionada);

        comida_nombre = findViewById(R.id.Comida_seleccionada_textView_comida_nombre);

        Intent intent = getIntent();
        String comida_nombre_seleccionada = intent.getStringExtra("comida_nombre");

        comida_nombre.setText(comida_nombre_seleccionada);

    }
}