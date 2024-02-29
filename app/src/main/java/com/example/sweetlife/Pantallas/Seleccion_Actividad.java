package com.example.sweetlife.Pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;

import java.io.Serializable;

public class Seleccion_Actividad extends AppCompatActivity {

    Informacion este_infoUsuario = new Informacion();

    LinearLayout linear_brazo;
    LinearLayout linear_torso;
    LinearLayout linear_pierna;

    ImageView buttonCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_actividad);


        linear_brazo = findViewById(R.id.seleccion_actividad_linearlayout_Brazo);
        linear_torso = findViewById(R.id.seleccion_actividad_linearlayout_Torso);
        linear_pierna = findViewById(R.id.seleccion_actividad_linearlayout_Pierna);
        buttonCuenta = findViewById(R.id.seleccion_actividad_imageViewCuenta);


        buttonCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                String usuario = intent.getStringExtra("usuario");

                Bundle bundle = getIntent().getExtras();
                este_infoUsuario = (Informacion) bundle.getSerializable("informacion");

                Intent cambio = new Intent(Seleccion_Actividad.this, Cuenta.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("nueva_informacion", (Serializable) este_infoUsuario);
                startActivity(cambio);

            }
        });

        linear_brazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                String usuario = intent.getStringExtra("usuario");

                Bundle bundle = getIntent().getExtras();
                este_infoUsuario = (Informacion) bundle.getSerializable("informacion");

                Intent cambio = new Intent(Seleccion_Actividad.this, Comida_Lista.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("nueva_informacion", (Serializable) este_infoUsuario);
                startActivity(cambio);
            }
        });





    }
}