package com.example.sweetlife.Pantallas;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetlife.BaseDeDatos.Comida;
import com.example.sweetlife.BaseDeDatos.Ejercicio;
import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Actividad_Lista extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Informacion infoUsuario = new Informacion();
    String usuario;

    float IMC=0;

    View linearLayout;

    String estado="";

    String tipoEjercicio="";

    Ejercicio ejercicioSeleccionado;

    ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista);
        linearLayout =  findViewById(R.id.actividades_lista_linearlayout);


        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("nueva_informacion");

        usuario = bundle.getString("usuario");
        tipoEjercicio = bundle.getString("tipoActividad");

        TextView recomendacion = (TextView) findViewById(R.id.actividades_lista_textViewRecomendacion);

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
        db.collection("Ejercicios")
                .document(estado)
                .collection(tipoEjercicio)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Ejercicio ejercicio = document.toObject(Ejercicio.class);
                                ejercicios.add(ejercicio);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        colocarEjercicios();

                    }



                });
    }

    public void recogerTodaInfoFirebase(){
        db = FirebaseFirestore.getInstance();
        db.collection("Ejercicios")
                .document("delgado")
                .collection(tipoEjercicio)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Ejercicio ejercicio = document.toObject(Ejercicio.class);
                                ejercicios.add(ejercicio);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        db.collection("Ejercicios")
                                .document("sobrepeso")
                                .collection(tipoEjercicio)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Ejercicio ejercicio = document.toObject(Ejercicio.class);
                                                ejercicios.add(ejercicio);
                                            }
                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }

                                        db.collection("Ejercicios")
                                                .document("obesidad")
                                                .collection(tipoEjercicio)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                                Ejercicio ejercicio = document.toObject(Ejercicio.class);
                                                                ejercicios.add(ejercicio);
                                                            }
                                                        } else {
                                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                                        }

                                                        colocarEjercicios();

                                                    }



                                                });

                                    }



                                });

                    }



                });
    }



    public void colocarEjercicios(){

        for(int i = 0; i < ejercicios.size(); i++) {

            if (esApto(ejercicios.get(i))) {

                TextView ejercicio = new TextView(Actividad_Lista.this);

                //atributos del textview y layout
                ejercicio.setText(ejercicios.get(i).getNombre());
                ejercicio.setId(i);
                ejercicio.setTextSize(20);
                ejercicio.setGravity(Gravity.CENTER);

            /*
            // Definir el fondo del TextView
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(8); // radio giro
            int colorfondo = Color.parseColor("#FCE4EC");
            drawable.setColor(colorfondo); // color
            comida.setBackground(drawable);
            */
                ejercicio.setTypeface(null, Typeface.ITALIC);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT

                );
                params.setMargins(0, 10, 0, 30);
                ejercicio.setLayoutParams(params); //aplicar cambios de estylo

                //añadir textview
                ((LinearLayout) linearLayout).addView(ejercicio);

                // Crear un view como línea decorativa
                View linea = new View(Actividad_Lista.this);
                linea.setLayoutParams(new LinearLayout.LayoutParams(
                        800, // ancho de la línea en pixeles
                        5    // altura de la línea en pixeles
                ));
                int colorfondo = Color.parseColor("#d1727c");
                linea.setBackgroundColor(colorfondo);

                // Agregar la linea al LinearLayout
                ((LinearLayout) linearLayout).addView(linea);

                //textview onclicklistener
                ejercicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //pasar a la pagina de ejercicio seleccionado
                        for (int i = 0; i < ejercicios.size(); i++) {
                            if (ejercicios.get(i).getNombre().equals(ejercicio.getText())) {
                                ejercicioSeleccionado = ejercicios.get(i);
                            }
                        }

                        Intent cambio = new Intent(Actividad_Lista.this, Actividad.class);
                        cambio.putExtra("ejercicio", (Serializable) ejercicioSeleccionado);
                        startActivity(cambio);

                    }
                });


            }//fin bucle
        }//fin if esApto
    } //fin metodo colocarcomida


    public boolean esApto(Ejercicio ejercicio){

        boolean esApto=true;

        try {
            String[] lista = ejercicio.getFiltros().split(", ");

            if (infoUsuario.isFumar() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("Fumar")) {
                        esApto = false;
                    }
                }
            }


            if (infoUsuario.isArtritis() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("Artritis")) {
                        esApto = false;
                    }
                }
            }

            if (infoUsuario.isProblemasCorazon() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("pCorazon")) {
                        esApto = false;
                    }
                }
            }

            if (infoUsuario.isDiabetes() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("Diabetes")) {
                        esApto = false;
                    }
                }
            }

            if (infoUsuario.isAsma() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("Asma")) {
                        esApto = false;
                    }
                }
            }

            if (infoUsuario.isAcidoUrico() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("Acido")) {
                        esApto = false;
                    }
                }
            }

            if (infoUsuario.isPresionBaja() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("pBaja")) {
                        esApto = false;
                    }
                }
            }

            if (infoUsuario.isPresionAlta() && esApto) {

                for (int i = 0; i < lista.length; i++) {
                    if (lista[i].equals("pAlta")) {
                        esApto = false;
                    }
                }
            }

        }catch(NullPointerException e){
            System.out.print("NullPointerException caught");
            Log.d(TAG, "NullPointerException caught");
        }



        return esApto;

    }


}
