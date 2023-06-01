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



public class Blood_Gain extends AppCompatActivity {
    EditText editTextname,editTextmobile,editTextage,editTextaddress;
    TextView textVeiwname,textViewmobile,textViewage,textviewaddress,textViewgender,textViewbloodtype;
    Button submit;
    Spinner bloodtype,gender;
    DatabaseReference reff;
    bloodgain blood_requst;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood__gain);
        editTextname=(EditText)findViewById(R.id.editText);
        editTextmobile=(EditText)findViewById(R.id.editText2);
        editTextage=(EditText)findViewById(R.id.editText3);
        editTextaddress=(EditText)findViewById(R.id.editText5);

        blood_requst=new bloodgain();
        reff= FirebaseDatabase.getInstance().getReference().child("bloodgain");
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
        //Textview
        textVeiwname=(TextView)findViewById(R.id.textView);
        textViewmobile=(TextView)findViewById(R.id.textView2);
        textViewage=(TextView)findViewById(R.id.textView3);

        textviewaddress=(TextView)findViewById(R.id.textView5);
        textViewgender=(TextView)findViewById(R.id.textView6);
        textViewbloodtype=(TextView)findViewById(R.id.textView8);


        bloodtype=(Spinner)findViewById(R.id.spinner);
        String[]Blood_Type={"-O","O+","A-","A+","B-","B+","AB-","AB+"};
        ArrayAdapter adapter1 =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Blood_Type);
        bloodtype.setAdapter(adapter1);

        gender=(Spinner)findViewById(R.id.spinner3);
        String[]Gender={"Male","Female"};
        ArrayAdapter genderadapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Gender);
        gender.setAdapter(genderadapter);

        //Button
        submit=(Button)findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(editTextname.getText().toString())) {
                    Toast.makeText(Blood_Gain.this, "Name shoud not be Empty!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(editTextmobile.getText().toString())){
                    Toast.makeText(Blood_Gain.this, "Mobile Number shoud not be Empty!", Toast.LENGTH_LONG).show();

                }
                else if(TextUtils.isEmpty(editTextage.getText().toString())){
                    Toast.makeText(Blood_Gain.this, "Age shoud not be Empty!", Toast.LENGTH_LONG).show();

                }
                else if(TextUtils.isEmpty(editTextaddress.getText().toString())) {
                    Toast.makeText(Blood_Gain.this, "Address shoud not be Empty!", Toast.LENGTH_LONG).show();
                }



                else {

                    int age = Integer.parseInt(editTextage.getText().toString().trim());
                    Long mob = Long.parseLong(editTextmobile.getText().toString().trim());
                    blood_requst.setName(editTextname.getText().toString().trim());
                    blood_requst.setAddress(editTextaddress.getText().toString().trim());
                    blood_requst.setBlood_type(bloodtype.getSelectedItem().toString().trim());
                    blood_requst.setGender(gender.getSelectedItem().toString().trim());
                    blood_requst.setAge(age);
                    blood_requst.setMob(mob);
                    // reff.push().setValue(member);
                    reff.child(String.valueOf(maxid + 1)).setValue(blood_requst);
                    // Toast.makeText(Blood_Gain.this, "Data sent successfully", Toast.LENGTH_LONG).show();



                    PopupMenu popupMenu = new PopupMenu(Blood_Gain.this, submit);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.Gmail:


                                    Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
                                    i.setData(Uri.parse("\n" + "email"));
                                    String[] s = {"abc@gmail.com", "xyz@gmail.com"};
                                    String edit = "Name =  " + editTextname.getText().toString() + "\nMobile No: " + editTextmobile.getText().toString() + "\nAge: " + editTextage.getText().toString()
                                            + "\nAddress: " + editTextaddress.getText().toString() + "\nBlood_Type =  " + gender.getSelectedItem() + "\nBlood_Type =  " + bloodtype.getSelectedItem();
                                    i.putExtra(Intent.EXTRA_EMAIL, s);
                                    i.putExtra(Intent.EXTRA_SUBJECT, "Request to Blood Gain");
                                    i.putExtra(Intent.EXTRA_TEXT, "Blood Gain message\n " + edit);


                                    i.setType("message/rfc822");
                                    Intent chooser = Intent.createChooser(i, "Launch Email");
                                    startActivity(chooser);
                                    //set value null
                                    editTextname.setText(null);
                                    editTextmobile.setText(null);
                                    editTextage.setText(null);
                                    editTextaddress.setText(null);

                                    break;

                                case R.id.text_msg:

                                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                    String edit1 = "Name =  " + editTextname.getText().toString() + "\nMobile No: " + editTextmobile.getText().toString() + "\nAge: " + editTextage.getText().toString()
                                            + "\nAddress: " + editTextaddress.getText().toString() + "\nGender = " + gender.getSelectedItem() + "\nBlood_Type =  " + bloodtype.getSelectedItem();

                                    Log.e("TAG", "EDIT !==" + edit1);

                                    sendIntent.setType("vnd.android-dir/mms-sms");
                                    sendIntent.putExtra("address", "9307208371");
                                    sendIntent.putExtra("sms_body", edit1);

                                    startActivity(sendIntent);
                                    //set value null
                                    editTextname.setText(null);
                                    editTextmobile.setText(null);
                                    editTextage.setText(null);
                                    editTextaddress.setText(null);

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