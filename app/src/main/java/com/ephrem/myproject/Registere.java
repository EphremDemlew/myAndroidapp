package com.ephrem.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Registere extends AppCompatActivity {
    EditText fullname_reg,uname_reg,email_reg,password_reg,phnumber_reg;
    RadioGroup Gender_btn;
    RadioButton selected;
    Data data;
    String sex=null;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registere);
        fullname_reg= findViewById(R.id.full_name);
        uname_reg= findViewById(R.id.user_name);
        email_reg= findViewById(R.id.email);
       password_reg= findViewById(R.id.pass);
        phnumber_reg= findViewById(R.id.phno);
        Gender_btn= findViewById(R.id.radio_group);
        data = new Data();
        Gender_btn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                sex=radioButton.getText().toString();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.r_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.exit:
                moveTaskToBack(true);
                finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }



    public void Sign_In(View view) {


        data.setFullname_data(fullname_reg.getText().toString());
        data.setUsername_data(uname_reg.getText().toString());
        data.setEmail_data(email_reg.getText().toString());
        data.setPassword_data(password_reg.getText().toString());
        data.setPh_number_data(phnumber_reg.getText().toString());
        data.setGender_data(sex);

        if(TextUtils.isEmpty(data.fullname_data)){
            fullname_reg.setError("Invalid FullName");
            return;
        }
        if(TextUtils.isEmpty(data.username_data)){
            uname_reg.setError("Invalid UserName");
            return;
        }
        if(TextUtils.isEmpty(data.email_data)){
            email_reg.setError("Invalid Email");
            return;
        }
        if(TextUtils.isEmpty(data.password_data)){
            password_reg.setError("Invalid Password");
            return;
        }
        if(password_reg.length() < 6){
            password_reg.setError("Invalid Password");
            return;
        }
        if(TextUtils.isEmpty(data.ph_number_data)){
            phnumber_reg.setError("Invalid Phone Number");
            return;
        }
        if(TextUtils.isEmpty(sex)){
            Toast.makeText(this, "Invalid Gender", Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseHelper db = new DatabaseHelper(this);
        boolean added = db.insertData(data.getFullname_data(),data.getUsername_data(),data.getEmail_data(),data.getPassword_data(),data.getPh_number_data(),data.getGender_data());
        if(added == true)
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Failed to Save",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,User_List.class));
    }

    public void selectRadioButton(View view) {
        int selecte_gender = Gender_btn.getCheckedRadioButtonId();
        selected= findViewById(selecte_gender);
    }
}
