package com.example.barmanagement.controllers;

import static com.example.barmanagement.db.UserTable.UserColumn.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
