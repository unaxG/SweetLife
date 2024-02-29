package com.example.sweetlife.Pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;

import java.io.Serializable;

public class Seleccion_Nutricion extends AppCompatActivity {

    Informacion este_infoUsuario = new Informacion();

    LinearLayout linear_comida;
    LinearLayout linear_bebida;

    ImageView buttonCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_nutricion);


        linear_comida = findViewById(R.id.seleccion_comida_linearlayout_comida);
        linear_bebida = findViewById(R.id.seleccion_comida_linearlayout_bebida);
        buttonCuenta = findViewById(R.id.seleccion_nutricion_imageViewCuenta);


        buttonCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                String usuario = intent.getStringExtra("usuario");

                Bundle bundle = getIntent().getExtras();
                este_infoUsuario = (Informacion) bundle.getSerializable("informacion");

                Intent cambio = new Intent(Seleccion_Nutricion.this, Cuenta.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) este_infoUsuario);
                startActivity(cambio);

            }
        });

        linear_comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                String usuario = intent.getStringExtra("usuario");

                Bundle bundle = getIntent().getExtras();
                este_infoUsuario = (Informacion) bundle.getSerializable("informacion");

                Intent cambio = new Intent(Seleccion_Nutricion.this, Cuenta.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) este_infoUsuario);
                startActivity(cambio);
            }
        });


        linear_bebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent cambio = new Intent(Seleccion_Nutricion.this, Bebida_Lista.class);
                startActivity(cambio);
                 */
            }
        });


    }
}