package com.example.loginsabado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autorizacion;

    EditText cajaCorreo,cajaPassword;

    Button boton,botonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autorizacion = FirebaseAuth.getInstance();

        cajaCorreo=findViewById(R.id.correo);
        cajaPassword=findViewById(R.id.password);

       boton=findViewById(R.id.registrar);
       boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo=cajaCorreo.getText().toString();
                String password=cajaPassword.getText().toString();

                registrarUsuario(correo,password);

            }
        });

       botonLogin=findViewById(R.id.ingresar);
       botonLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String correo=cajaCorreo.getText().toString();
               String password=cajaPassword.getText().toString();

               ingresarUsuario(correo,password);

           }
       });

    }

    public void registrarUsuario(String correo, String password){

        autorizacion.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "exito registro", Toast.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }

    public void ingresarUsuario(String correo, String password){

        autorizacion.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(MainActivity.this,Home.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Fallo en el login",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

}