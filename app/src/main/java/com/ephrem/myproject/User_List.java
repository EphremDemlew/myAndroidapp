package com.ephrem.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class User_List extends AppCompatActivity {
    RecyclerView recyclerView;
    SQLiteDatabase sdb;
    DatabaseHelper databaseHelper ;
    RecyclerAdapter adapter;
    SharedPref sharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        databaseHelper=new DatabaseHelper(this);
        sdb= databaseHelper.getWritableDatabase();
        RecyclerView recyclerView = findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(getCursor(),this);
        recyclerView.setAdapter(adapter);
        sharedPref = new SharedPref(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.log_out:
                startActivity(new Intent(this, MainActivity.class));
                sharedPref.writeStatus(false);
                return true;
            case R.id.exit:


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    private Cursor getCursor() {
        return sdb.query(databaseHelper.TABLE_NAME,null,null,null,null,null,null);
    }



}

