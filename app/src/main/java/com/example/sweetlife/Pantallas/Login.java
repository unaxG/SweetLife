package com.example.sweetlife.Pantallas;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sweetlife.MainActivity;
import com.example.sweetlife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //sharedPreferences crear
        Context context = Login.this;
        sharedpreferences = context.getSharedPreferences(
                getString(R.string.preference_email_user), Context.MODE_PRIVATE);

        //sharedPreferences leer
        String defaultUser = getResources().getString(R.string.preference_email_user);
        String rememberUser = sharedpreferences.getString(getString(R.string.preference_email_user), defaultUser);


        //firebase
        mAuth = FirebaseAuth.getInstance();

        EditText txtUsuario = findViewById(R.id.Login_editTextUsername);
        EditText contrasenna = findViewById(R.id.Login_editTextContraseña);

        Button buttonIniciarSesion = (Button) findViewById(R.id.Login_btnIniciarSesion);
        TextView textViewCrearCuenta = findViewById(R.id.Login_textViewCrearCuenta);

        txtUsuario.setText(rememberUser);

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtUsuario.getText().toString();
                String password = contrasenna.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);


                    builder.setTitle("Login");

                    builder.setPositiveButton("Aceptar", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // boton de aceptar y cerrar pop-up

                        dialog.cancel();
                    });

                    builder.setMessage("Usuario o contraseña vacios");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    //intent
                                    Intent cambio = new Intent(Login.this, Principal.class);

                                    cambio.putExtra("usuario", email);
                                    startActivity(cambio);

                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);


                                    builder.setTitle("Login");

                                    builder.setPositiveButton("Aceptar", (DialogInterface.OnClickListener) (dialog, which) -> {
                                        // boton de aceptar y cerrar pop-up

                                        dialog.cancel();
                                    });

                                    builder.setMessage("Usuario o contraseña incorrecta");
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                }
                            }
                        });
                //sharedPreferences escribir
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(getString(R.string.preference_email_user), email);
                editor.apply();
            }

        });


        textViewCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de Registro
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });
    }
}
