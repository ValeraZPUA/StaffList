package edu.example.stafflist.model;

import android.provider.BaseColumns;

public class Constans  {

    private Constans() {

    }

    public static final class StaffConstants implements BaseColumns {
        public static final String DATABASE_NAME = "stafflist.db";
        public static final String TABLE_NAME = "staffList";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_GENDER = "gender";
    }
}
