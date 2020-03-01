package com.ephrem.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText inserted_email,inserted_password;
    RecyclerView recyclerView;
    SharedPref sharedPref;
    //RecyclerAdapter recyclerAdapter;
    //ArrayList<String> data;
   // Data dat = new Data();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
        inserted_email= findViewById(R.id.uname);
        inserted_password= findViewById(R.id.pass);
        sharedPref = new SharedPref(this);

        if (sharedPref.readStatus()) {
            Intent intent = new Intent(MainActivity.this, User_List.class);
            startActivity(intent);
            finish();
        }


        //recyclerView = findViewById(R.id.recycler_id);
        //data.addAll(dat.getFullname_data(),dat.getUsername_data(),dat.getEmail_data(),dat.getPassword_data(),dat.getPh_number_data());
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerAdapter = new RecyclerAdapter(data);
        // recyclerView.setAdapter(recyclerAdapter);
    }

    public void Login(View view) {
        String emailname,password;
        emailname= inserted_email.getText().toString().trim();
        password= inserted_password.getText().toString().trim();

        if(TextUtils.isEmpty(emailname)){
            inserted_email.setError("Enter Email And Try Again");
            return;
        }
        if(TextUtils.isEmpty(password)){
            inserted_password.setError("Enter Password And Try Again");
            return;
        }


        if(password.length() < 6){
            inserted_password.setError("Your Password must be grater than 6 Characters Try Again");
            return;
        }

        if (db.authentication(emailname,password)) {
            sharedPref.writeStatus(true);
            startActivity(new Intent(this, User_List.class));

        }
            else {
                inserted_email.getText().clear();
                inserted_password.getText().clear();
                Toast.makeText(this, "Failed Try Again", Toast.LENGTH_LONG).show();
            }

        }

    public void Sign_up(View view) {
        startActivity(new Intent(this,Registere.class));
    }


    public void showmsg(String Title,String message){
        AlertDialog.Builder bldr = new AlertDialog.Builder(this);
        bldr.setCancelable(true);
        bldr.setTitle(Title);
        bldr.setMessage(message);
        bldr.show();
    }
}
