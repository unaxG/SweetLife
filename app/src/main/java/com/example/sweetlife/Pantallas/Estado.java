package com.example.sweetlife.Pantallas;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;

public class Estado extends AppCompatActivity {

    Informacion infoUsuario = new Informacion();
    String usuario;

    float IMC=0;

    float pesoIdeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);

        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("informacion");

        usuario = bundle.getString("usuario");

        TextView altura = (TextView) findViewById(R.id.textViewAltura);
        TextView peso = (TextView) findViewById(R.id.textViewPeso);
        TextView imc = (TextView) findViewById(R.id.textViewIMC);
        TextView categoriaPeso = (TextView) findViewById(R.id.textViewCategoriaPeso);

        RelativeLayout layout = findViewById(R.id.layoutEstado);

        String categoria="";


        calcularIMC();


        if(IMC<18.5){
            categoria="Usted tiene el peso bajo";
            layout.setBackgroundResource(R.drawable.estado_background_azul); // Cambiar a fondo azul
        } else if(IMC>=18.5 && IMC<=24.9){
            categoria="Usted tiene un peso adecuado";
            layout.setBackgroundResource(R.drawable.estado_background_verde); // Cambiar a fondo verde
        } else if(IMC>=25 && IMC<=29.9){
            categoria="Usted tiene sobrepeso";
            layout.setBackgroundResource(R.drawable.estado_background_amarillo); // Cambiar a fondo amarillo
        } else if(IMC>=30){
            categoria="Usted tiene obesidad";
            layout.setBackgroundResource(R.drawable.estado_background_rojo); // Cambiar a fondo rojo
        }


        altura.setText("Altura: "+infoUsuario.getAltura()+" cm");
        peso.setText("Peso: "+infoUsuario.getPeso()+" kg");
        imc.setText("Índice de Masa Corporal (IMC): "+IMC);
        categoriaPeso.setText(categoria);



    }

    public void calcularIMC(){

        IMC=infoUsuario.getPeso() / ((infoUsuario.getAltura()/100)*(infoUsuario.getAltura()/100));

    }

}
