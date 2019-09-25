package edu.example.stafflist.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StaffData {

    private SQLiteDatabase database;
    private StaffDBHelper dbHelper;
    private Cursor cursor;

    private List<Staff> staff;
    private List<Staff> staffToDB;

    public StaffData(StaffDBHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.database =  dbHelper.getWritableDatabase();
    }

    public List<Staff> create() {
        ;
        int dbSize = (int) DatabaseUtils.queryNumEntries(database,Constans.StaffConstants.TABLE_NAME);
        if(dbSize==0) {
            staffToDB = addDefaultData();
            for(int i=0;i<staffToDB.size();i++){
                ContentValues cv = new ContentValues();
                cv.put(Constans.StaffConstants.COLUMN_NAME, staffToDB.get(i).getName());
                cv.put(Constans.StaffConstants.COLUMN_AGE, staffToDB.get(i).getAge());
                cv.put(Constans.StaffConstants.COLUMN_NUMBER, staffToDB.get(i).getPhoneNumber());
                cv.put(Constans.StaffConstants.COLUMN_GENDER, staffToDB.get(i).getGender());
                database.insert(Constans.StaffConstants.TABLE_NAME, null, cv);
            }
            Log.d("tag","Database has been created");
        } else {
            staffToDB = new ArrayList<>();
            cursor = database.query(Constans.StaffConstants.TABLE_NAME,null,null,null,null,null,null,null);
            while(cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(Constans.StaffConstants.COLUMN_NAME));
                String age = cursor.getString(cursor.getColumnIndex(Constans.StaffConstants.COLUMN_AGE));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(Constans.StaffConstants.COLUMN_NUMBER));
                String gender = cursor.getString(cursor.getColumnIndex(Constans.StaffConstants.COLUMN_GENDER));
                staffToDB.add(new Staff(name, age, phoneNumber, gender));
            }
        }

        return staffToDB;
    }

    private List<Staff> addDefaultData() {
        staff = new ArrayList<>();
        staff.add(new Staff("John", "22", "95-45-63", "male"));
        staff.add(new Staff("Mike", "33", "096-123-45-69",  "male"));
        staff.add(new Staff("Ann", "75", "02", "male"));
        staff.add(new Staff("Karl", "12", "95-89-79", "male"));
        staff.add(new Staff("Dan", "43", "105",  "female"));
        staff.add(new Staff("Helen", "29", "23-56-55",  "female"));
        staff.add(new Staff("Mark", "43", "777 777 777",  "male"));
        staff.add(new Staff("Dan", "43", "105",  "female"));
        staff.add(new Staff("Dan", "41", "105", "female"));
        return staff;
    }

}
