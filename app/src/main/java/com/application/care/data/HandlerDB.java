package com.application.care.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.application.care.R;
import com.application.care.model.WorkTime;
import com.application.care.util.UtilDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HandlerDB extends SQLiteOpenHelper {

    private static final String TAG = "HandlerDB";
    private static HandlerDB instance;

    public HandlerDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static HandlerDB getInstance(Context context) {

        if (instance == null)
            instance = new HandlerDB(context, UtilDB.DATABASE_NAME, null, UtilDB.DATABASE_VERSION);
        return instance;
    }

    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        //SQL - Structured Query Language
        String CREATE_DATA_TIME_TABLE = "CREATE TABLE " + UtilDB.TABLE_DATA_TIME + "("
                + UtilDB.KEY_DATE + " INTEGER PRIMARY KEY,"
                + UtilDB.KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_DATA_TIME_TABLE); //creating our table

    }

    @Override
    public void onUpgrade(@NotNull SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.drop);
        db.execSQL(DROP_TABLE, new String[]{UtilDB.DATABASE_NAME});

        //Create a table again
        onCreate(db);
    }

    /*
       CRUD = Create, Read, Update, Delete

     */
    //Add WorkTime
    public void addWorkTime(@NotNull WorkTime workTime) {

//        if there is just a date, it will update this worktime
        if (thereIsADate(workTime.getDate()))
            updateWorkTime(workTime);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_DATE, workTime.getDate());
        values.put(UtilDB.KEY_TIME, workTime.getTime());

        //Insert to row
        db.insert(UtilDB.TABLE_DATA_TIME, null, values);

        Log.d(TAG, "addWorkTime: " + "item added");
        db.close(); //closing db connection!

    }

    //Get a workTime
    public WorkTime getWorkTime(String date) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(UtilDB.TABLE_DATA_TIME,
                new String[]{UtilDB.KEY_DATE, UtilDB.KEY_TIME},
                UtilDB.KEY_DATE + "=?", new String[]{date},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        else
            throw new SQLException("Date not present!");

        Log.d(TAG, "getWorkTime item get");
        return new WorkTime(cursor.getString(0), Float.parseFloat(cursor.getString(1)));
    }


    //    check if there is a worktime with a custom date
    public boolean thereIsADate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(UtilDB.TABLE_DATA_TIME,
                new String[]{UtilDB.KEY_DATE, UtilDB.KEY_TIME},
                UtilDB.KEY_DATE + "=?", new String[]{date},
                null, null, null);


        Log.d(TAG, "thereIsADate ok");
        return cursor != null;
    }

    //Get all WorkTimes
    public List<WorkTime> getAllWorkTimes() {
        List<WorkTime> workTimeList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all workTimes
        String selectAll = "SELECT * FROM " + UtilDB.TABLE_DATA_TIME;
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                WorkTime workTime = new WorkTime(cursor.getString(0), Integer.parseInt(cursor.getString(1)));

                //add workTime objects to our list
                workTimeList.add(workTime);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "getAllWorkTimes items get");
        return workTimeList;
    }

    //Update workTime
    public int updateWorkTime(@NotNull WorkTime workTime) {

//        if there isn't a worktime with a custom date, it will add this.
        if (!thereIsADate(workTime.getDate()))
            addWorkTime(workTime);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDB.KEY_TIME, workTime.getTime());

        //update the row

        Log.d(TAG, "updateWorkTime update ok");

        return db.update(UtilDB.TABLE_DATA_TIME, values, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(workTime.getDate())});
    }

    //Delete single workTime
    public void deleteWorkTime(@NotNull WorkTime workTime) {

        if (!thereIsADate(workTime.getDate()))
            return;

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(UtilDB.TABLE_DATA_TIME, UtilDB.KEY_DATE + "=?",
                new String[]{String.valueOf(workTime.getDate())});

        Log.d(TAG, "deleteWorkTime delete ok ");

        db.close();
    }

    //Get workTimes count
    public int getCount() {
        String countQuery = "SELECT * FROM " + UtilDB.TABLE_DATA_TIME;
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(countQuery, null);


        Log.d(TAG, "getCount get count ok! ");
        return cursor.getCount();

    }
}
