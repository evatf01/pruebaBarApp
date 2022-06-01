package com.example.barmanagement.controllers;

import static com.example.barmanagement.db.TableMesas.TablesColumns.*;
import static com.example.barmanagement.db.UserTable.UserColumn.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.barmanagement.db.DbHelper;


public class SqliteController {
    public void createUserSqlite(Context context, String name, String password, String dni, String email, String phone){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_DNI, dni);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        sqLiteDatabase.insert(TABLE_USERS,null, values);
    }

    public int comprobarUserSqlite(Context context,String nombre, String password) {
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = "+ nombre, null);
        Log.d("query", String.valueOf(cursor));
        if(cursor.moveToFirst()){
            String contraseña = cursor.getString(2);
            if(contraseña.equals(password)){
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    public void createTables(Context context){
        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        for (int i = 0; i<=5;i++){
            ContentValues values = new ContentValues();
            values.put(COLUMN_TABLES_NUM, i+1);
            values.put(COLUMN_TABLES_ZONE, "INTERIOR");
            values.put(COLUMN_TABLES_IMG, "https://cdn-icons-png.flaticon.com/512/47/47638.png");
            sqLiteDatabase.insert(TABLE_MESAS,null, values);
        }
    }
}
