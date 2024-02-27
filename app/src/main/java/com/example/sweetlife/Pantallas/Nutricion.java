package com.example.sweetlife.Pantallas;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
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

public class Nutricion extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Informacion infoUsuario = new Informacion();
    String usuario;

    float IMC=0;

    TextView nombre;

    ArrayList<Comida> comidas = new ArrayList<Comida>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricion);



        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("informacion");

        usuario = bundle.getString("usuario");

        TextView recomendacion = (TextView) findViewById(R.id.nutricion_textViewRecomendacion);
        nombre = (TextView) findViewById(R.id.nutricion_textViewNombre);
        TextView descripcion = (TextView) findViewById(R.id.nutricion_textViewDescripcion);
        TextView ingredientes = (TextView) findViewById(R.id.nutricion_textViewIngredientes);

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

        nombre.setText(comidas.get(0).getNombre());



    }


}
