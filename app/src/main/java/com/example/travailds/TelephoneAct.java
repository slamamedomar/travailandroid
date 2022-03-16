package com.example.travailds;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelephoneAct extends AppCompatActivity {
    Button  btnAppeler , btnAppeler2;
    EditText  numPhone,codeRecharge,codeAAppeler,codeSolde ;
    TextView login_name,demandeCode,typeLigne;
    private String codeTypeRecharge , phoneNumberG;
    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;
    private static final String LOG_TAG = "AndroidExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        btnAppeler= findViewById(R.id.btnAppeler);
        btnAppeler2= findViewById(R.id.btnAppeler2);
        numPhone = findViewById(R.id.numPhone);
        codeRecharge = findViewById(R.id.codeRecharge);
        codeAAppeler = findViewById(R.id.codeAAppeler);
        login_name  = findViewById(R.id. login_name);
        demandeCode= findViewById(R.id.demandeCode );
        codeSolde = findViewById(R.id.codeSolde );
        typeLigne = findViewById(R.id.typeLigne );

        Bundle extra =getIntent().getExtras();
        login_name.setText(extra.getString("value1"));

        getTypeLigne();
        getCodedeRecharge();

        btnAppeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberG = codeAAppeler.getText().toString() ;
                demandeDePermission(phoneNumberG);
            }
        });
        btnAppeler2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberG = codeSolde.getText().toString();
                demandeDePermission(phoneNumberG);
            }
        });

    }



    private void getTypeLigne() {
        numPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    String phoneNumber =numPhone.getText().toString()  ;
                    String color ="FFFFFF";
                    char first = phoneNumber.charAt(0);
                    if (first =='9')
                    {
                        typeLigne.setText(" Votre ligne est Telecom");
                        typeLigne.setTextColor(Color.parseColor("#"+color));
                        color = "0000FF";
                        demandeCode.setText(" Entrer votre code de recharge  (13 chiffres)");

                        codeAAppeler.setText("*123*");
                        codeTypeRecharge = "*123*" ;
                        codeAAppeler.setBackgroundColor(Color.parseColor("#"+color));
                        codeSolde.setText("*122#");
                        codeSolde.setBackgroundColor(Color.parseColor("#"+color));


                    }else if (first =='2')
                    {
                        typeLigne.setText(" Votre ligne est Ooredoo");
                        color = "9b1c31";
                        demandeCode.setText(" Entrer votre code de recharge  (14 chiffres)");
                        codeAAppeler.setText("*101*");
                        codeTypeRecharge = "*101*" ;
                        codeAAppeler.setBackgroundColor(Color.parseColor("#"+color));
                        codeSolde.setText("*100#");
                        codeSolde.setBackgroundColor(Color.parseColor("#"+color));
                        typeLigne.setTextColor(Color.parseColor("#"+color));
                    }else if (first =='5')
                    {
                        typeLigne.setText(" Votre ligne est Orange");
                        color = "ff7f00";
                        demandeCode.setText(" Entrer votre code de recharge  (14 chiffres)");
                        codeAAppeler.setText("*100*");
                        codeTypeRecharge = "*100*" ;
                        typeLigne.setTextColor(Color.parseColor("#"+color));
                        codeSolde.setText("*101#");
                        codeAAppeler.setBackgroundColor(Color.parseColor("#"+color));
                        codeSolde.setBackgroundColor(Color.parseColor("#"+color));


                    }

                }
                catch (Exception ex)
                {
                    numPhone.setError(ex.toString());
                }

            }


            @Override
            public void afterTextChanged(Editable s) {
               if (numPhone.getText().toString().length()!=8)
               {
                   numPhone.setError(" 8 chiffres !");
               }
            }
        });

    }
    private void getCodedeRecharge() {
        codeRecharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if ((codeRecharge.getText().toString().length()<=12)||(codeRecharge.getText().toString().length()>=15))
                    {
                        codeRecharge.setError(" au moin 13 chiffres et au plus 15!!");

                        codeAAppeler.setText(codeTypeRecharge+codeRecharge.getText().toString()+"#");
                    }
                }catch (Exception ex)
                {
                    codeRecharge.setError(ex.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        } );
    }
    private void demandeDePermission(String phoneNumber) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);

            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSION_REQUEST_CODE_CALL_PHONE
                );
                return;
            }
        }
        this.appeler(phoneNumber);
    }

    @SuppressLint("MissingPermission")
    private void appeler(String _phoneNumber) {
        try {
            String USSD =  _phoneNumber ;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + USSD)));

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Your call failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }




}