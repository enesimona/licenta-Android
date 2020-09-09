package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInregistrare;
    private Button btnAutentificare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    protected void initComponents(){
        btnAutentificare=(Button) findViewById(R.id.btn_main_autentificare);
        btnInregistrare=(Button) findViewById(R.id.btn_main_inregistrare);

        btnAutentificare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NewUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
