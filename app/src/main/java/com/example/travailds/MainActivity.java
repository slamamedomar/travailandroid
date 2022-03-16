package com.example.travailds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button loginButton ;
    EditText login ,editTextTextPassword ;
    TextView welcomeMsg,msgError ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loggin();
    }

    private void loggin() {
        loginButton = findViewById(R.id.loginButton);
        login = findViewById(R.id.login);
        editTextTextPassword =  findViewById(R.id.editTextTextPassword);
        welcomeMsg = findViewById(R.id.welcomeMsg) ;
        msgError  = findViewById(R.id.msgerreur);
        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               String _login = login.getText().toString();
                                               String _password = editTextTextPassword.getText().toString();
                                               if (_login.equals("admin") && _password.equals("123456")){

                                                   Intent intent = new Intent(MainActivity.this, TelephoneAct.class);
                                                   intent.putExtra("value1", _login);
                                                   startActivity(intent);

                                           }else
                                              {
                                                   msgError.setText("vérifier login et mot de passe !!");
                                              }
                                            }}

                                             );
    }

}