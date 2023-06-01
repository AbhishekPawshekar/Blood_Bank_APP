package com.example.blood_bank_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Blood_Donate extends AppCompatActivity {
    EditText editname, editmobile_no, editage, editaddress;
    TextView textname, textphoneNo, textage, textaddress,textgender,bloodtype;
    Spinner sp, gender;
    Button b1;
    DatabaseReference reff;
    Donate donate;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood__donate);
        editname = findViewById(R.id.editText7);
        editmobile_no =findViewById(R.id.editText8);
        editage = findViewById(R.id.editText9);
        editaddress = findViewById(R.id.editText10);


        //firebase
        donate=new Donate();
        reff= FirebaseDatabase.getInstance().getReference().child("Donate");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //TextField
        textname = (TextView) findViewById(R.id.textView10);
        textphoneNo = (TextView) findViewById(R.id.textView11);
        textage = (TextView) findViewById(R.id.textView12);
        textaddress = (TextView) findViewById(R.id.textView14);
        textgender =(TextView) findViewById(R.id.textView15);
        bloodtype =(TextView) findViewById(R.id.textView16);


        // spinner
        gender = (Spinner) findViewById(R.id.spinner4);
        String[] Gender1 = {"Male", "Female"};
        ArrayAdapter genderadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Gender1);
        gender.setAdapter(genderadapter);

        sp = (Spinner) findViewById(R.id.spinner2);
        final String[] Blood_Type = {"O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"};
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Blood_Type);
        sp.setAdapter(adapter);

        //Button
        b1 = (Button) findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editname.getText().toString()))
                {
                    Toast.makeText(Blood_Donate.this, "Name shoud not be Empty!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(editmobile_no.getText().toString())){
                    Toast.makeText(Blood_Donate.this, "Mobile Number shoud not be Empty!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(editage.getText().toString())){
                    Toast.makeText(Blood_Donate.this, "Age shoud not be Empty!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(editaddress.getText().toString())){
                    Toast.makeText(Blood_Donate.this, "Adress shoud not be Empty!", Toast.LENGTH_LONG).show();
                }

                else {
                    int age=Integer.parseInt(editage.getText().toString().trim());
                    //long int mob=Integer.parseInt(editmobile_no.getText().toString().trim());
                    Long mob=Long.parseLong(editmobile_no.getText().toString().trim());
                    donate.setName(editname.getText().toString().trim());
                    donate.setAddress(editaddress.getText().toString().trim());
                    donate.setGender(gender.getSelectedItem().toString().trim());
                    donate.setBlood_type(sp.getSelectedItem().toString().trim());
                    donate.setAge(age);
                    donate.setMob(mob);
                    reff.child(String.valueOf(maxid +1)).setValue(donate);
                    //reff.push().setValue(donate);
                    // Toast.makeText(Blood_Donate.this, "Data sent Successfully", Toast.LENGTH_LONG).show();


                    PopupMenu popupMenu = new PopupMenu(Blood_Donate.this, b1);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.Gmail:


                                    Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
                                    i.setData(Uri.parse("\n" + "email"));
                                    String[] s = {"abc@gmail.com", "xyz@gmail.com"};
                                    String edit = "Name =  " + editname.getText().toString() + "\nMobile No: " + editmobile_no.getText().toString() + "\nAge: " + editage.getText().toString()
                                            + "\nAddress: " + editaddress.getText().toString() + "\nBlood_Type =  " + gender.getSelectedItem() + "\nBlood_Type =  " + sp.getSelectedItem();
                                    i.putExtra(Intent.EXTRA_EMAIL, s);
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Request to Blood Gain");
                                    i.putExtra(Intent.EXTRA_TEXT, "Blood Gain message\n " + edit);


                                    i.setType("message/rfc822");
                                    Intent chooser = Intent.createChooser(i, "Launch Email");
                                    startActivity(chooser);
                                    //set value null
                                    editname.setText(null);
                                    editmobile_no.setText(null);
                                    editage.setText(null);
                                    editaddress.setText(null);

                                    break;

                                case R.id.text_msg:

                                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                    String edit1 = "Name =  " + editname.getText().toString() + "\nMobile No: " + editmobile_no.getText().toString() + "\nAge: " + editage.getText().toString()
                                            + "\nAddress: " + editaddress.getText().toString() + "\nGender = " + gender.getSelectedItem() + "\nBlood_Type =  " + sp.getSelectedItem();

                                    Log.e("TAG", "EDIT !==" + edit1);

                                    sendIntent.setType("vnd.android-dir/mms-sms");
                                    sendIntent.putExtra("address", "9307208371");
                                    sendIntent.putExtra("sms_body", edit1);

                                    startActivity(sendIntent);
                                    //set value null
                                    editname.setText(null);
                                    editmobile_no.setText(null);
                                    editage.setText(null);
                                    editaddress.setText(null);

                                    break;

                            }

                            return true;

                        }

                    });
                    popupMenu.show();
                }



            }


        });

    }
}