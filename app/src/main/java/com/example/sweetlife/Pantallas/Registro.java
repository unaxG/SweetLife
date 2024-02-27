package com.example.sweetlife.Pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sweetlife.BaseDeDatos.Informacion;
import com.example.sweetlife.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Informacion info;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CheckBox presionBaja;
    CheckBox presionAlta;

// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        presionBaja = (CheckBox) findViewById(R.id.condiciones_checkboxPresionArterialBaja);
        presionAlta = (CheckBox) findViewById(R.id.condiciones_checkboxPresionArterialAlta);


        Button confirmar = (Button) findViewById(R.id.Registro_btnRegistrarse);
        TextView atras = (TextView) findViewById(R.id.Registro_textViewTengoCuenta);

        EditText email = (EditText) findViewById(R.id.Registro_editTextEmail);

        EditText contrasenna = (EditText) findViewById(R.id.Registro_editTextContraseña);
        EditText contrasennaRepetir = (EditText) findViewById(R.id.Registro_editTextRepetirContraseña);


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


        //listener textview volver a login
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });


        //listener para el boton de crear usuario
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sacar email y contraseña
                String emailToString = email.getText().toString();
                String password = contrasenna.getText().toString();
                String passwordRep = contrasennaRepetir.getText().toString();

                // si contraseñas son iguales crear usuario en AUTH

                if(password.equals(passwordRep)) {

                    if(crearInfo()) {




                        mAuth.createUserWithEmailAndPassword(emailToString, password).addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    //si funciona avisar al usuario
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);


                                    builder.setTitle("Usuario creado");

                                    builder.setPositiveButton("Aceptar", (DialogInterface.OnClickListener) (dialog, which) -> {
                                        // boton de aceptar y cerrar pop-up

                                        dialog.cancel();
                                    });

                                    builder.setMessage("Enhorabuena, ya es usted parte de (nombre de app)");
                                    AlertDialog dialog = builder.create();
                                    dialog.show();




                                    enviarInfo(emailToString, info);



                                } else {
                                    // si NO funciona avisar al usuario
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);


                                    builder.setTitle("Error Registrar");

                                    builder.setPositiveButton("Aceptar", (DialogInterface.OnClickListener) (dialog, which) -> {
                                        // boton de aceptar y cerrar pop-up

                                        dialog.cancel();
                                    });

                                    builder.setMessage("Error al intentar crear un usuario, porfavor inserte un email y una contraseña de al menos 6 caracteres");
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                }
                            }
                        });

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);


                        builder.setTitle("Error Info");

                        builder.setPositiveButton("Aceptar", (DialogInterface.OnClickListener) (dialog, which) -> {
                            // boton de aceptar y cerrar pop-up

                            dialog.cancel();
                        });

                        builder.setMessage("Error al introducir datos");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);


                    builder.setTitle("La contraseña repetida es diferente");

                    builder.setPositiveButton("Aceptar", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // boton de aceptar y cerrar pop-up

                        dialog.cancel();
                    });

                    builder.setMessage("Parece que la contraseña que ha insertado y la repticion son diferentes, por favor insrtelos otra vez");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        });

    };

    public void enviarInfo(String usuario, Informacion e){

            db.collection("Usuarios").document(usuario).set(e).addOnCompleteListener(new OnCompleteListener<Void>() {



                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Intent i = new Intent(Registro.this, Principal.class);
                        i.putExtra("usuario",usuario);
                        startActivity(i);
                    }
                }



            });



    }

    public boolean crearInfo(){

        info = new Informacion("","","",0, 0,false,false,false,false,false,false,false,false);
        boolean correcto=true;

        EditText nombre = (EditText) findViewById(R.id.Registro_editTextNombre);
        String insertarDato = nombre.getText().toString();
        info.setNombre(insertarDato);

        EditText apellidos = (EditText) findViewById(R.id.Registro_editTextApellidos);
        insertarDato = apellidos.getText().toString();
        info.setApellidos(insertarDato);

        EditText fecha = (EditText) findViewById(R.id.Registro_editTextFechaNacimiento);
        insertarDato = fecha.getText().toString();
        info.setFechaNacimiento(insertarDato);

        EditText peso = (EditText) findViewById(R.id.Registro_editTextPeso);
        float insertarFloat = Float.parseFloat(peso.getText().toString());
        info.setPeso(insertarFloat);

        EditText altura = (EditText) findViewById(R.id.Registro_editTextAltura);
        insertarFloat = Float.parseFloat(altura.getText().toString());
        info.setAltura(insertarFloat);

        CheckBox fuma = (CheckBox) findViewById(R.id.condiciones_checkboxFuma);
        boolean insertarBoolean = fuma.isChecked();
        info.setFumar(insertarBoolean);

        CheckBox problemasCorazon = (CheckBox) findViewById(R.id.condiciones_checkboxProblemasCorazon);
        insertarBoolean = problemasCorazon.isChecked();
        info.setProblemasCorazon(insertarBoolean);

        CheckBox asma = (CheckBox) findViewById(R.id.condiciones_checkboxAsma);
        insertarBoolean = asma.isChecked();
        info.setAsma(insertarBoolean);


        insertarBoolean = presionBaja.isChecked();
        info.setPresionBaja(insertarBoolean);


        insertarBoolean = presionAlta.isChecked();
        info.setPresionAlta(insertarBoolean);

        CheckBox artritis = (CheckBox) findViewById(R.id.condiciones_checkboxArtritis);
        insertarBoolean = artritis.isChecked();
        info.setArtritis(insertarBoolean);

        CheckBox acidoUrico = (CheckBox) findViewById(R.id.condiciones_checkboxAcidoUrico);
        insertarBoolean = acidoUrico.isChecked();
        info.setAcidoUrico(insertarBoolean);

        CheckBox diabetes = (CheckBox) findViewById(R.id.condiciones_checkboxDiabetes);
        insertarBoolean = diabetes.isChecked();
        info.setDiabetes(insertarBoolean);







        return correcto;
    }

}