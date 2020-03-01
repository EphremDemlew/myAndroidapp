package com.ephrem.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{
        SQLiteDatabase db;


        public static final String DATABASE_NAME = "USER.db";
        public static final String TABLE_NAME = "USER_TABLE";
        public static final String COLON_1 = "FULL_NAME";
        public static final String COLON_2 = "USER_NAME";
        public static final String COLON_3 = "E_MAIL";
        public static final String COLON_4 = "PASSWORD";
        public static final String COLON_5 = "PHONE_NUMBER";
        public static final String COLON_6 = "GENDER";

        public DatabaseHelper( Context context) {
            super(context, DATABASE_NAME, null, 1);
         db = getWritableDatabase();

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + " (FULL_NAME TEXT, USER_NAME TEXT NOT NULL,E_MAIL TEXT PRIMARY KEY NOT NULL,PASSWORD TEXT NOT NULL,PHONE_NUMBER TEXT , GENDER TEXT) " );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop Table if exists " + TABLE_NAME);
            onCreate(db);

        }

        public boolean insertData(String full_name, String user_name, String Email , String pass, String pno, String sex){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentvalue = new ContentValues();
            contentvalue.put(COLON_1,full_name);
            contentvalue.put(COLON_2,user_name);
            contentvalue.put(COLON_3,Email);
            contentvalue.put(COLON_4,pass);
            contentvalue.put(COLON_5,pno);
            contentvalue.put(COLON_6,sex);
            long result = db.insert(TABLE_NAME,null,contentvalue);
            if(result == -1)
                return false;
            else
                return true;

        }

        public Cursor getData(){

            return db.rawQuery("Select * from USER_TABLE",null);
        }


        public void delete(String  val){
        db.rawQuery("DELETE FROM USER_TABLE WHERE E_MAIL="+val ,null);
            // db.delete(TABLE_NAME,"where E_MAIL=?",new String[]{val});
        }




        public boolean authentication(String check_email,String check_password){
            SQLiteDatabase readableDatabasedb = this.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE", null);
            boolean istrue = false;
           while(cursor.moveToNext())  {
               String loginEmail = cursor.getString(2);
               String loginPassword = cursor.getString(3);

               if (check_email.equals(loginEmail) && check_password.equals(loginPassword)) {
                   istrue = true;
                   break;
               }  }
           return istrue;
        }

/* Cursor cursor = readableDatabasedb.rawQuery("SELECT * FROM USER_TABLE WHERE E_MAIL=='"
            + check_email + "'" +
            "AND PASSWORD=='"+check_password+"'", null);*/




}
