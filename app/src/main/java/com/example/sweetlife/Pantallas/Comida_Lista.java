package com.example.sweetlife.Pantallas;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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

import java.util.ArrayList;

public class Comida_Lista extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Informacion infoUsuario = new Informacion();
    String usuario;

    float IMC=0;

    View linearLayout;

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

        String estado="";

        if(IMC<18.5){
            estado="delgado";
        }else  if(IMC>=18.5 && IMC<=24.9){
            estado="normal";
        }else if(IMC>=25 && IMC<=29.9){
            estado="sobrepeso";
        }else if(IMC>=30){
            estado="obesidad";
        }

        recogerInfoFirebase(estado);

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



    public void colocarComida(){

        for(int i = 0; i < comidas.size(); i++){

            TextView comida = new TextView(Comida_Lista.this);

            //atributos del textview y layout
            comida.setText(comidas.get(i).getNombre());
            comida.setId(i);
            comida.setTextSize(25);
            comida.setGravity(Gravity.CENTER);
            comida.setTypeface(null, Typeface.BOLD);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT

            );
            params.setMargins(0, 10, 0, 30);
            comida.setLayoutParams(params); //aplicar cambios de estylo

            //añadir textview
            ((LinearLayout) linearLayout).addView(comida);

            //textview onclicklistener
            comida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pasar a la pagina de comida seleccionada
                    Intent cambio = new Intent(Comida_Lista.this, Comida_Seleccionada.class);
                    cambio.putExtra("comida_nombre", comida.getText());
                    startActivity(cambio);
                }
            });


        }//fin bucle

    } //fin metodo colocarcomida


}
