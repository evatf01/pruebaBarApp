package com.example.barmanagement.db;

import android.provider.BaseColumns;

public final class AdminTable {
    public static class AdminColumns implements BaseColumns{

        public static final String TABLE_ADMIN = "Admin";

        public static final String COLUMN_ID_ADMIN = "ID";
        public static final String COLUMN_IDUSER_ADMIN = "IdUser";
        public static final String COLUMN_CODE_ADMIN = "Code";

    }

}
