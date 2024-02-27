package com.example.sweetlife.Pantallas;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;

public class Medidas extends AppCompatActivity {


    Informacion infoUsuario = new Informacion();
    String usuario;

    float IMC=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidas);

        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("informacion");

        usuario = bundle.getString("usuario");

        TextView altura = (TextView) findViewById(R.id.medidas_textViewAltura);
        TextView peso = (TextView) findViewById(R.id.medidas_textViewPeso);

        ImageView aprox = (ImageView) findViewById(R.id.medidas_imageViewAproximacionCuerpo);

        calcularIMC();

        if(IMC<18.5){
            aprox.setImageDrawable(getDrawable(R.drawable.delgado));
        }else  if(IMC>=18.5 && IMC<=24.9){
            aprox.setImageDrawable(getDrawable(R.drawable.peso_normal));
        }else if(IMC>=25 && IMC<=29.9){
            aprox.setImageDrawable(getDrawable(R.drawable.sobrepeso));
        }else if(IMC>=30){
            aprox.setImageDrawable(getDrawable(R.drawable.obesidad));
        }

        altura.setText("Altura: "+infoUsuario.getAltura()+" cm");
        peso.setText("Peso: "+infoUsuario.getPeso()+" kg");


    }

    public void calcularIMC(){

        IMC=infoUsuario.getPeso() / ((infoUsuario.getAltura()/100)*(infoUsuario.getAltura()/100));

    }

}
