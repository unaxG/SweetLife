package com.example.sweetlife.Pantallas;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;

public class CondicionesPersonales extends AppCompatActivity {

    Informacion infoUsuario = new Informacion();
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);

        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("informacion");
        usuario = bundle.getString("usuario");

        TextView nombreUsuario = (TextView) findViewById(R.id.nutricion_textViewNombre);
        CheckBox fumador = (CheckBox) findViewById(R.id.condiciones_checkboxFuma);
        CheckBox problemasCorazon = (CheckBox) findViewById(R.id.condiciones_checkboxProblemasCorazon);
        CheckBox asma = (CheckBox) findViewById(R.id.condiciones_checkboxAsma);
        CheckBox presionBaja = (CheckBox) findViewById(R.id.condiciones_checkboxPresionArterialBaja);
        CheckBox presionAlta = (CheckBox) findViewById(R.id.condiciones_checkboxPresionArterialAlta);
        CheckBox artritis = (CheckBox) findViewById(R.id.condiciones_checkboxArtritis);
        CheckBox acidoUrico = (CheckBox) findViewById(R.id.condiciones_checkboxAcidoUrico);
        CheckBox diabetes = (CheckBox) findViewById(R.id.condiciones_checkboxDiabetes);


        nombreUsuario.setText(infoUsuario.getNombre()+" "+infoUsuario.getApellidos());


        fumador.setChecked(infoUsuario.isFumar());
        problemasCorazon.setChecked(infoUsuario.isProblemasCorazon());
        asma.setChecked(infoUsuario.isAsma());
        presionBaja.setChecked(infoUsuario.isPresionBaja());
        presionAlta.setChecked(infoUsuario.isPresionAlta());
        artritis.setChecked(infoUsuario.isArtritis());
        acidoUrico.setChecked(infoUsuario.isAcidoUrico());
        diabetes.setChecked(infoUsuario.isDiabetes());



    }

}
