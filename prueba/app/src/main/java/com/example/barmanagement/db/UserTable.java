package com.example.barmanagement.db;

import android.provider.BaseColumns;

public final class UserTable {
    public static class  UserColumn implements BaseColumns{
        public static final String TABLE_USERS = "Usuraios";

        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_TYPE = "Type";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_PASSWORD = "Password";
        public static final String COLUMN_DNI = "DNI";
        public static final String COLUMN_EMAIL = "Email";
        public static final String COLUMN_PHONE = "Phone";
    }
}
