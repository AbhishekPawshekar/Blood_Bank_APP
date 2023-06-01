package com.example.blood_bank_app;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

  import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


    public class Contact extends AppCompatActivity {
        Button buttoncall1, buttoncall2, buttoncall3, buttonem;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact);


            //button
            buttoncall1 = (Button) findViewById(R.id.buttonshradha);

            buttoncall3=findViewById(R.id.buttonyogesh);
            buttoncall2 = (Button) findViewById(R.id.buttonkanchan);

            buttonem = (Button) findViewById(R.id.emergency);
            //action on number
            buttoncall1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_DIAL);

                    i.setData(Uri.parse("tel:9307208371"));
                    startActivity(i);
                }
            });
            buttoncall2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_DIAL);

                    i.setData(Uri.parse("tel:9881311742"));
                    startActivity(i);
                }
            });
            buttoncall3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_DIAL);

                    i.setData(Uri.parse("tel:7020344748"));
                    startActivity(i);
                }
            });

            buttonem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), data_ret.class);
                    startActivity(i);
                }
            });
        }
    }


