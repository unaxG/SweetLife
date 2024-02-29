package com.example.sweetlife.Pantallas;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sweetlife.BaseDeDatos.Comida;
import com.example.sweetlife.BaseDeDatos.Ejercicio;
import com.example.sweetlife.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class Actividad extends AppCompatActivity {


    TextView comida_nombre;

    WebView videoEjercicio;

    FirebaseFirestore db;

    Ejercicio ejercicioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        Bundle bundle = getIntent().getExtras();
        ejercicioSeleccionado = (Ejercicio) bundle.getSerializable("ejercicio");

        comida_nombre = findViewById(R.id.actividad_textView_nombre);
        videoEjercicio = findViewById(R.id.webViewEjercicio);
        TextView ejercicioDescripcion = findViewById(R.id.actividad_textViewDescripcion);
        TextView ejercicioMaterial = findViewById(R.id.actividad_textViewMateriales);

        comida_nombre.setText(ejercicioSeleccionado.getNombre());
        ejercicioDescripcion.setText(ejercicioSeleccionado.getDescripcion());

        if (!ejercicioSeleccionado.getMaterial().equals("nada")) {
            ejercicioMaterial.setText(ejercicioSeleccionado.getMaterial());
        }else{
            ejercicioMaterial.setText("No requiere materiales");
        }


        setVideo();

    }


    public void setVideo (){

        String path = ejercicioSeleccionado.getUrl();
        videoEjercicio.getSettings().setJavaScriptEnabled(true);
        videoEjercicio.setWebChromeClient(new WebChromeClient());
        videoEjercicio.loadUrl(path);


    }

}
