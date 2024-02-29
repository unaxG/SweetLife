package com.example.sweetlife.Pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Cuenta extends AppCompatActivity {


    private FirebaseAuth mAuth;

    Informacion info;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RadioButton presionBaja;
    RadioButton presionAlta;

    Informacion infoUsuario;

    String usuario;

    EditText nombre;
    EditText apellidos;
    EditText fecha;
    EditText peso;
    EditText altura;
    CheckBox fuma;
    CheckBox problemasCorazon;
    CheckBox asma;
    CheckBox artritis;
    CheckBox acidoUrico;
    CheckBox diabetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        Bundle bundle = getIntent().getExtras();
        infoUsuario = (Informacion) bundle.getSerializable("informacion");

        usuario = bundle.getString("usuario");

        mAuth = FirebaseAuth.getInstance();


        presionBaja = (RadioButton) findViewById(R.id.cuenta_radioButtonPresionBaja);
        presionAlta = (RadioButton) findViewById(R.id.cuenta_radioButtonPresionAlta);
        nombre = (EditText) findViewById(R.id.Registro_editTextNombre);
        apellidos = (EditText) findViewById(R.id.Registro_editTextApellidos);
        fecha = (EditText) findViewById(R.id.Registro_editTextFechaNacimiento);
        peso = (EditText) findViewById(R.id.Registro_editTextPeso);
        altura = (EditText) findViewById(R.id.Registro_editTextAltura);
        fuma = (CheckBox) findViewById(R.id.condiciones_checkboxFuma);
         problemasCorazon = (CheckBox) findViewById(R.id.condiciones_checkboxProblemasCorazon);
         asma = (CheckBox) findViewById(R.id.condiciones_checkboxAsma);
         artritis = (CheckBox) findViewById(R.id.condiciones_checkboxArtritis);
         acidoUrico = (CheckBox) findViewById(R.id.condiciones_checkboxAcidoUrico);
         diabetes = (CheckBox) findViewById(R.id.condiciones_checkboxDiabetes);


        presionBaja.setChecked(infoUsuario.isPresionBaja());
        presionAlta.setChecked(infoUsuario.isPresionAlta());
        nombre.setText(infoUsuario.getNombre());
        apellidos.setText(infoUsuario.getApellidos());
        fecha.setText(infoUsuario.getFechaNacimiento());
        peso.setText(Float.toString(infoUsuario.getPeso()));
        altura.setText(Float.toString(infoUsuario.getAltura()));
        fuma.setChecked(infoUsuario.isFumar());
        problemasCorazon.setChecked(infoUsuario.isProblemasCorazon());
        asma.setChecked(infoUsuario.isAsma());
        artritis.setChecked(infoUsuario.isArtritis());
        acidoUrico.setChecked(infoUsuario.isAcidoUrico());
        diabetes.setChecked(infoUsuario.isDiabetes());






        Button guardar = (Button) findViewById(R.id.Registro_btnRegistrarse);

        presionBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presionAlta.setChecked(false);
            }
        });

        presionAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presionBaja.setChecked(false);
            }
        });



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearInfo();
                enviarInfo();

            }
        });


    }


    public void enviarInfo(){

        db.collection("Usuarios").document(usuario).set(info).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Intent i = new Intent(Cuenta.this, Principal.class);
                    i.putExtra("usuario",usuario);
                    startActivity(i);
                }
            }



        });



    }

    public boolean crearInfo(){

        info = new Informacion("","","",0, 0,false,false,false,false,false,false,false,false);
        boolean correcto=true;


        String insertarDato = nombre.getText().toString();
        info.setNombre(insertarDato);


        insertarDato = apellidos.getText().toString();
        info.setApellidos(insertarDato);


        insertarDato = fecha.getText().toString();
        info.setFechaNacimiento(insertarDato);


        float insertarFloat = Float.parseFloat(peso.getText().toString());
        info.setPeso(insertarFloat);


        insertarFloat = Float.parseFloat(altura.getText().toString());
        info.setAltura(insertarFloat);


        boolean insertarBoolean = fuma.isChecked();
        info.setFumar(insertarBoolean);


        insertarBoolean = problemasCorazon.isChecked();
        info.setProblemasCorazon(insertarBoolean);


        insertarBoolean = asma.isChecked();
        info.setAsma(insertarBoolean);


        insertarBoolean = presionBaja.isChecked();
        info.setPresionBaja(insertarBoolean);


        insertarBoolean = presionAlta.isChecked();
        info.setPresionAlta(insertarBoolean);


        insertarBoolean = artritis.isChecked();
        info.setArtritis(insertarBoolean);


        insertarBoolean = acidoUrico.isChecked();
        info.setAcidoUrico(insertarBoolean);


        insertarBoolean = diabetes.isChecked();
        info.setDiabetes(insertarBoolean);







        return correcto;
    }

}