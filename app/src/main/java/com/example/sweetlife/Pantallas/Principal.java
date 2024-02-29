package com.example.sweetlife.Pantallas;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class Principal extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Informacion infoUsuario = new Informacion();

    TextView bienvenida;


    ImageButton buttonActividad;
    ImageButton buttonNutricion;
    ImageView buttonEstado;
    ImageView buttonMedidas;
    ImageView buttonInformacionPersonal;
    ImageView buttonCuenta;

    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        bienvenida = (TextView) findViewById(R.id.PrincipalTextView_Datos);

        buttonCuenta = findViewById(R.id.principal_imageViewCuenta);
        buttonActividad = (ImageButton) findViewById(R.id.Principal_btn_Actividad);
        buttonNutricion = (ImageButton) findViewById(R.id.Principal_btn_Nutricion);
        buttonEstado = (ImageView) findViewById(R.id.Principal_imgvw_Flecha1);
        buttonMedidas = (ImageView) findViewById(R.id.Principal_imgvw_Flecha2);
        buttonInformacionPersonal = (ImageView) findViewById(R.id.Principal_imgvw_Flecha3);

        buttonActividad.setClickable(false);
        buttonNutricion.setClickable(false);
        buttonEstado.setClickable(false);
        buttonMedidas.setClickable(false);
        buttonInformacionPersonal.setClickable(false);



        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");



        recogerInfoFirebase(usuario);




    }

    public void recogerInfoFirebase(String usuario){
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef  = db.collection("Usuarios").document(usuario);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Informacion informacion = documentSnapshot.toObject(Informacion.class);
                Log.d(TAG, informacion.getNombre());


                if(informacion.getNombre().length()>0){
                    bienvenida.setText("Bienvenido "+informacion.getNombre()+"!");
                }else{
                    bienvenida.setText("Bienvenido usuario!");
                }

                infoUsuario=informacion;

                setOnClicks();

            }
        });
    }

    public void setOnClicks(){

        buttonCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Cuenta.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) infoUsuario);
                startActivity(cambio);

            }
        });



        buttonActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Seleccion_Actividad.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) infoUsuario);
                startActivity(cambio);

            }
        });

        buttonNutricion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Seleccion_Nutricion.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) infoUsuario);
                startActivity(cambio);
            }
        });

        buttonEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Estado.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) infoUsuario);
                startActivity(cambio);
            }
        });

        buttonMedidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, Medidas.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) infoUsuario);
                startActivity(cambio);
            }
        });

        buttonInformacionPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cambio = new Intent(Principal.this, CondicionesPersonales.class);
                cambio.putExtra("usuario", usuario);
                cambio.putExtra("informacion", (Serializable) infoUsuario);
                startActivity(cambio);
            }
        });


    }
}