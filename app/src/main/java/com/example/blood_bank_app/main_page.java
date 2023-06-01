package com.example.blood_bank_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class main_page extends AppCompatActivity {
    ImageView bloodfact,blooddonate,bloodrequest,notificcation,contact,location;
    ImageSlider is;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bloodfact = findViewById(R.id.blood_facts);
        blooddonate = findViewById(R.id.blood_donate);
        bloodrequest = findViewById(R.id.blood_request);
        notificcation =findViewById(R.id.blood_notification);
        contact =findViewById(R.id.contact_now);
        is=findViewById(R.id.imageslider);
        location =findViewById(R.id.locate_us);

        List slider=new ArrayList();
        slider.add(new SlideModel(R.drawable.blood_bank_image1));
        slider.add(new SlideModel(R.drawable.blood_bank_image2));
        slider.add(new SlideModel(R.drawable.blood_bank_image3));
        slider.add(new SlideModel(R.drawable.blood_bank_image4));
is.setImageList(slider,false);
        //action fact
        bloodfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Discription.class);
                startActivity(i);
            }
        });
        //blooddonate
        blooddonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Blood_Donate.class);
                startActivity(i);
            }
        });
        bloodrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Blood_Gain.class);
                startActivity(i);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Contact.class);
                startActivity(i);
            }
        });
        notificcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/www.arpanbloodbank.org/"));
                startActivity(i);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:20.005037, 73.787244"));
                Intent chooser = Intent.createChooser(i,"Launch Maps");
                startActivity(i);
            }

        });


    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure You Want To Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}