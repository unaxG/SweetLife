package com.example.sweetlife.Pantallas;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetlife.BaseDeDatos.Comida;
import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import android.graphics.drawable.GradientDrawable;

import java.io.Serializable;
import java.util.ArrayList;

public class Comida_Lista extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Informacion infoUsuario = new Informacion();
    String usuario;

    float IMC=0;

    View linearLayout;

    String estado="";

    Comida comidaSeleccionada;

    ArrayList<Comida> comidas = new ArrayList<Comida>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_lista);
        linearLayout =  findViewById(R.id.comida_lista_linearlayout);


        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("nueva_informacion");

        usuario = bundle.getString("usuario");

        TextView recomendacion = (TextView) findViewById(R.id.nutricion_textViewRecomendacion);

        calcularIMC();



        if(IMC<18.5){
            estado="delgado";
            recogerInfoFirebase(estado);
        }else  if(IMC>=18.5 && IMC<=24.9){
            estado="normal";
            recogerTodaInfoFirebase();
        }else if(IMC>=25 && IMC<=29.9){
            estado="sobrepeso";
            recogerInfoFirebase(estado);
        }else if(IMC>=30){
            estado="obesidad";
            recogerInfoFirebase(estado);
        }



    }

    public void calcularIMC(){

        IMC=infoUsuario.getPeso() / ((infoUsuario.getAltura()/100)*(infoUsuario.getAltura()/100));

    }

    public void recogerInfoFirebase(String estado){
        db = FirebaseFirestore.getInstance();
       db.collection("Comidas")
                .document(estado)
                .collection("1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               Comida comida = document.toObject(Comida.class);
                               comidas.add(comida);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        colocarComida();

                    }



                });
    }

    public void recogerTodaInfoFirebase(){
        db = FirebaseFirestore.getInstance();
        db.collection("Comidas")
                .document("delgado")
                .collection("1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Comida comida = document.toObject(Comida.class);
                                comidas.add(comida);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        db.collection("Comidas")
                                .document("sobrepeso")
                                .collection("1")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Comida comida = document.toObject(Comida.class);
                                                comidas.add(comida);
                                            }
                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }

                                        db.collection("Comidas")
                                                .document("obesidad")
                                                .collection("1")
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                                Comida comida = document.toObject(Comida.class);
                                                                comidas.add(comida);
                                                            }
                                                        } else {
                                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                                        }

                                                        colocarComida();

                                                    }



                                                });

                                    }



                                });

                    }



                });
    }



    public void colocarComida(){

        for(int i = 0; i < comidas.size(); i++){

            TextView comida = new TextView(Comida_Lista.this);

            //atributos del textview y layout
            comida.setText(comidas.get(i).getNombre());
            comida.setId(i);
            comida.setTextSize(20);
            comida.setGravity(Gravity.CENTER);

            /*
            // Definir el fondo del TextView
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(8); // radio giro
            int colorfondo = Color.parseColor("#FCE4EC");
            drawable.setColor(colorfondo); // color
            comida.setBackground(drawable);
            */
            comida.setTypeface(null, Typeface.ITALIC);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT

            );
            params.setMargins(0, 10, 0, 30);
            comida.setLayoutParams(params); //aplicar cambios de estylo

            //añadir textview
            ((LinearLayout) linearLayout).addView(comida);

            // Crear un view como línea decorativa
            View linea = new View(Comida_Lista.this);
            linea.setLayoutParams(new LinearLayout.LayoutParams(
                    800, // ancho de la línea en pixeles
                    5    // altura de la línea en pixeles
            ));
            int colorfondo = Color.parseColor("#d1727c");
            linea.setBackgroundColor(colorfondo);

            // Agregar la linea al LinearLayout
            ((LinearLayout) linearLayout).addView(linea);

            //textview onclicklistener
            comida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pasar a la pagina de comida seleccionada

                    for(int i=0;i<comidas.size();i++){
                        if(comidas.get(i).getNombre().equals(comida.getText())){
                            comidaSeleccionada=comidas.get(i);
                        }
                    }

                    Intent cambio = new Intent(Comida_Lista.this, Comida_Seleccionada.class);
                    cambio.putExtra("receta", (Serializable) comidaSeleccionada);
                    startActivity(cambio);

                }
            });


        }//fin bucle

    } //fin metodo colocarcomida


}
