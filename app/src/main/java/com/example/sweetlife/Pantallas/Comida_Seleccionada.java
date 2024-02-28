package com.example.sweetlife.Pantallas;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sweetlife.BaseDeDatos.Comida;
import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Comida_Seleccionada extends AppCompatActivity {


    TextView comida_nombre;

    ImageView imagenReceta;

    FirebaseFirestore db;

    Comida recetaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_seleccionada);

        Bundle bundle = getIntent().getExtras();
        recetaSeleccionada = (Comida) bundle.getSerializable("receta");

        comida_nombre = findViewById(R.id.Comida_seleccionada_textView_comida_nombre);
        imagenReceta = findViewById(R.id.imageViewReceta);
        TextView comidaDescripcion = findViewById(R.id.comida_seleccionada_textViewDescripcion);
        TextView comidaIngredientes = findViewById(R.id.comida_seleccionada_textViewIngredientes);

        comida_nombre.setText(recetaSeleccionada.getNombre());
        comidaDescripcion.setText(recetaSeleccionada.getDescripcion());
        comidaIngredientes.setText(recetaSeleccionada.getIngredientes());



        setImage();

    }


    public void setImage (){

        Glide.with(this)
                .load(recetaSeleccionada.getUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.comida_logo) // Placeholder image
                        .error(R.drawable.logo) // Error image in case of loading failure
                )
                .into(imagenReceta);



    }

}
