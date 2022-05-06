package com.example.barmanagement.db;

import android.provider.BaseColumns;

public final class CategoryTable {

    public static class CategoryColumn implements BaseColumns{
        public static final String TABLE_CATEGORY = "Category";
        public static final String COLUMN_CATEGORY_ID = "Id";
        public static final String COLUMN_CATEGORY_TYPE = "Type";
        public static final String COLUMN_CATEGORY_NAME = "Name";
        public static final String COLUMN_CATEGORY_FAMILY = "Family";
        public static final String COLUMN_CATEGORY_PRICE = "Price";
        public static final String COLUMN_CATEGORY_QUANTITY = "Quantity";
        public static final String COLUMN_CATEGORY_IMGURL = "ImgUrl";
    }
}
