package edu.example.stafflist.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StaffData {

    private SQLiteDatabase database;
    private StaffDBHelper dbHelper;
    private Cursor cursor;

    private List<Staff> defaultStaffData;
    private List<Staff> staffToDB;

    public StaffData(StaffDBHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.database =  dbHelper.getWritableDatabase();
    }



    public List<Staff> create() {

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
        defaultStaffData = new ArrayList<>();
        defaultStaffData.add(new Staff("John", "22", "95-45-63", "male"));
        defaultStaffData.add(new Staff("Mike", "33", "096-123-45-69",  "male"));
        defaultStaffData.add(new Staff("Ann", "75", "02", "male"));
        defaultStaffData.add(new Staff("Karl", "12", "95-89-79", "male"));
        defaultStaffData.add(new Staff("Dan", "43", "105",  "female"));
        defaultStaffData.add(new Staff("Helen", "29", "23-56-55",  "female"));
        defaultStaffData.add(new Staff("Mark", "43", "777 777 777",  "male"));
        defaultStaffData.add(new Staff("Dan", "43", "105",  "female"));
        defaultStaffData.add(new Staff("Dan", "41", "105", "female"));
        return defaultStaffData;
    }

    public void addToDB(Staff newStaff){
        AddNewStaff addNewStaff = new AddNewStaff();
        addNewStaff.execute(newStaff);

    }

    private class AddNewStaff extends AsyncTask<Staff, Void, Void> {


        @Override
        protected Void doInBackground(Staff... staff) {

            ContentValues cv = new ContentValues();
            cv.put(Constans.StaffConstants.COLUMN_NAME,staff[0].getName());
            cv.put(Constans.StaffConstants.COLUMN_AGE,staff[0].getAge());
            cv.put(Constans.StaffConstants.COLUMN_NUMBER,staff[0].getPhoneNumber());
            cv.put(Constans.StaffConstants.COLUMN_GENDER,staff[0].getGender());
            database.insert(Constans.StaffConstants.TABLE_NAME,null, cv);
            return null;
        }
    }




}
