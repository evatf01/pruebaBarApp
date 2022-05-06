package com.example.barmanagement.db;

import android.provider.BaseColumns;

public final class TableMesas {
    public static class TablesColumns implements BaseColumns{
        public static final String TABLE_MESAS = "Tables";
        public static final String COLUMN_TABLES_ID = "Id";
        public static final String COLUMN_TABLES_NUM = "NunMesa";
        public static final String COLUMN_TABLES_ZONE = "Zone";
        public static final String COLUMN_TABLES_GHESTS = "Ghests";
        public static final String COLUMN_TABLES_RESERVED = "Reserved";
    }
}
