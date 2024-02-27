package com.example.sweetlife.Pantallas;

import android.os.Bundle;
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

        String categoria="";


        calcularIMC();


        if(IMC<18.5){
            categoria="Usted tiene peso bajo";
        }else  if(IMC>=18.5 && IMC<=24.9){
            categoria="Usted tiene peso adecuado";
        }else if(IMC>=25 && IMC<=29.9){
            categoria="Usted tiene sobrepeso";
        }else if(IMC>=30){
            categoria="Usted tiene obesidad";
        }


        altura.setText("Altura: "+infoUsuario.getAltura()+" cm");
        peso.setText("Peso: "+infoUsuario.getPeso()+" kg");
        imc.setText("IMC: "+IMC);
        categoriaPeso.setText(categoria);



    }

    public void calcularIMC(){

       IMC=infoUsuario.getPeso() / ((infoUsuario.getAltura()/100)*(infoUsuario.getAltura()/100));

    }

}
