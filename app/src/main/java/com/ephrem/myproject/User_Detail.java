package com.ephrem.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class User_Detail extends AppCompatActivity {
    TextView fname,uname,email,phno,gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__detail);

        fname= findViewById(R.id.fullname_id);
        uname= findViewById(R.id.usename_id);
        email= findViewById(R.id.email_id);
        phno= findViewById(R.id.phnumber_id);
        gen= findViewById(R.id.gender_id);

        Intent intent = getIntent();
        String fnam= intent.getStringExtra("fullname");
        String unam= intent.getStringExtra("username");
        String email1= intent.getStringExtra("email");
        String phnumber= intent.getStringExtra("phnumber");
        String gndr= intent.getStringExtra("gender");


        fname.setText(fnam);
        uname.setText(unam);
        email.setText(email1);
        phno.setText(phnumber);
        gen.setText(gndr);



    }
}
