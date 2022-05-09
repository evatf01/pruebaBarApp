package com.example.barmanagement.db;

import static com.example.barmanagement.db.AdminTable.AdminColumns.*;
import static com.example.barmanagement.db.CategoryTable.CategoryColumn.*;
import static com.example.barmanagement.db.TableMesas.TablesColumns.*;
import static com.example.barmanagement.db.UserTable.UserColumn.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bar_management.db";




    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_USERS+" (" +
                " "+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+COLUMN_TYPE+ " TEXT NOT NUll," +
                " "+COLUMN_NAME+ " TEXT NOT NULL," +
                " "+COLUMN_PASSWORD+ " TEXT NOT NULL," +
                " "+COLUMN_DNI+ " TEXT NOT NULL," +
                " "+COLUMN_EMAIL+ " TEXT NOT NULL," +
                " "+COLUMN_PHONE+ " INTEGER NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_ADMIN+ "(" +
                " "+COLUMN_ID_ADMIN+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+COLUMN_IDUSER_ADMIN+ " INTEGER," +
                " "+COLUMN_CODE_ADMIN+ " INTEGER, " +
                "FOREIGN KEY (" +COLUMN_IDUSER_ADMIN+ ") REFERENCES "+ TABLE_USERS+ " ("+COLUMN_ID+"))");

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_MESAS+ " (" +
                " "+COLUMN_TABLES_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+COLUMN_TABLES_NUM+ "  TEXT," +
                " "+COLUMN_TABLES_ZONE+ " TEXT," +
                " "+COLUMN_TABLES_GHESTS+" INTEGER," +
                " "+COLUMN_TABLES_RESERVED+ "INTEGER )");


        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CATEGORY+ " (" +
                " "+COLUMN_CATEGORY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+COLUMN_CATEGORY_TYPE+ "  TEXT," +
                " "+COLUMN_CATEGORY_NAME+ " TEXT," +
                " "+COLUMN_CATEGORY_FAMILY+" TEXT," +
                " "+COLUMN_CATEGORY_PRICE+ "INTEGER," +
                " "+COLUMN_CATEGORY_QUANTITY+ " INTEGER," +
                " "+COLUMN_CATEGORY_IMGURL+ " TEXT )");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_USERS);
        onCreate(sqLiteDatabase);
    }
}
