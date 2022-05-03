package com.example.barmanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bar_management.db";
    private static final String TABLE_USERS = "usuraios";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_USERS+" (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT NOT NUll," +
                ")");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
