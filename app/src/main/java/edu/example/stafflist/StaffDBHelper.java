package edu.example.stafflist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.example.stafflist.model.Constans.*;


public class StaffDBHelper extends SQLiteOpenHelper {

    public StaffDBHelper(Context context) {
        super(context, StaffConstants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                StaffConstants.TABLE_NAME + " (" +
                StaffConstants._ID + " integer primary key autoincrement, " +
                StaffConstants.COLUMN_NAME + " text not null, " +
                StaffConstants.COLUMN_AGE + " text not null, " +
                StaffConstants.COLUMN_NUMBER + " text not null, " +
                StaffConstants.COLUMN_GENDER + " text not null" + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
